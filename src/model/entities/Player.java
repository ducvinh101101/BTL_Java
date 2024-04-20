package model.entities;

import model.Game;
import model.AudioPlayer;
import model.gamestates.Playing;
import model.objects.Projectile;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static model.utilz.Constants.ANI_SPEED;
import static model.utilz.Constants.GRAVITY;
import static model.utilz.Constants.PlayerConstants.DEAD;
import static model.utilz.Constants.Projectiles.CANNON_BALL_HEIGHT;
import static model.utilz.Constants.Projectiles.CANNON_BALL_WIDTH;
import static model.utilz.HelpMethods.*;
import static model.utilz.Constants.PlayerConstants.*;
import static model.utilz.HelpMethods.getEntityXPosNextToWall;

public class Player extends Entity {
    private boolean levelUp;
    private int frameCount;
    private static final int DISPLAY_FRAMES = 180;
    private BufferedImage[] idAniIm, idAniLeft, idAniRight, idAniH, idAniAt, idAniL, idAniAtL, idAniJumpL, idAniFallL, idAniJump, idAniFall, idAniInAir;
    private BufferedImage idAniHit;
    //    private int playerAction = IDLE;
    //    private int playerDir = -1;
    private boolean left, right, jump, checkL, checkR;
    private boolean moving = false, attacking = false, canDoubleJump = false, skill = false;
    private float playerSpeed = 2f;
    private int widthPlayer = 30, heightPlayer = 42;
    private float cameraX = 5f * Game.SCALE;
    private float cameraY = 9 * Game.SCALE;
    // nhảy trọng lực:
    private float airSpeed = 0f;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;

    private int mapData[][];
    private BufferedImage statusBarImg;
    private int levelPlayer = 1;
    private int statusBarWidth = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (1 * Game.SCALE);
    private int statusBarY = (int) (3 * Game.SCALE);

    private int barWidth = (int) (152 * Game.SCALE);
    private int barHeight = (int) (6 * Game.SCALE);
    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int maxMana = 100;
    private int currentMana = maxMana;
    private int maxExp = 100;
    private int currentExp = 0;
    private int damageSword = 500, damageShuriken = 2;

    private int healthWidth = barWidth;
    private int expWidth = barWidth;
    private int manaWidth = barWidth;
    private int tileY = 0;

    private Rectangle2D.Float attackBox;

    private boolean attackChecked;
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage shurikenImg, levelUpImg;
    private Rectangle2D.Float skillBox;

    public void setSkillBox(Rectangle2D.Float skillBox) {
        this.skillBox = skillBox;
    }

    private void activeSkill(){
    //    if(currentMana <= 0) skill = false;
        if(skill) {
            currentMana-=5;
            int dir = 0;
            if(checkR) dir = 1;
            else if(checkL) dir = -1;
            projectiles.add(new Projectile((int) getHitBox().x, (int) (getHitBox().y), dir));
            skill = false;
        }
    }
    private void drawProjectiles(Graphics g, int xLevelOffset, int yLevelOffset) {
        activeSkill();
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                g.drawImage(
                        shurikenImg,
                        (int) p.getHitBox().x - xLevelOffset,
                        (int) p.getHitBox().y - yLevelOffset,
                        CANNON_BALL_WIDTH,
                        CANNON_BALL_HEIGHT,
                        null
                );
            }
        }
    }

    private void updateProjectiles() {
        for(Projectile p : projectiles){
            if(p.isActive()) {
                p.updatePos();
                setSkillBox(p.getHitBox());
                playing.checkEnemyHit(skillBox);
                projectiles.getLast().setMaxDistance(300);
            }
        }
    }
    public int getCurrentExp() {return currentExp;}

    public void setCurrentExp(int currentExp) {this.currentExp = currentExp;}

    public int getDamage() {
        if(attackChecked) return damageSword;
        else return damageShuriken;
    }

    @Override
    public void setState(int state) {
        super.setState(state);
        aniTick = 0;
        aniIndex = 0;
    }

    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        this.state = IDLE;
        projectiles = new ArrayList<>();
        loadImg();
        initHitBox(widthPlayer * Game.SCALE - 10, heightPlayer * Game.SCALE - 10);
        initAttackBox();
    }
    public void loadImg(){
        loadAnimations();
        loadAnimationsImLeft();
        loadAnimationsLeft();
        loadAnimationsRight();
        loadAnimationsAttack();
        loadAnimationsAttackLeft();
        loadAnimationsJump();
        loadAnimationsFalling();
        loadAnimationsJumpLeft();
        loadAnimationsFallingLeft();
        loadAnimationAir();
        idAniHit = LoadSave.getImage(LoadSave.PLAYER_HIT);
        shurikenImg = LoadSave.getImage(LoadSave.SHURIKEN);
        levelUpImg = LoadSave.getImage(LoadSave.LEVELUP);
    }
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
    }

    public void update() {
        activeSkill();
        mapData = playing.getLevelManager().getCurrenLevel().getlvlData(); // bổ sung update map mỗi khi load lại map
        checkLevelUp();
        updateBar();
        if(currentHealth <= 0){
            if(state != DEAD){
                state = DEAD;
                aniTick = 0;
                aniIndex = 0;
                playing.setPlayerDying(true);
                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.DIE);
            }else if(aniIndex == getSpriteAmountPlayer(DEAD) && aniTick >= ANI_SPEED - 1){
                playing.setGameOver(true);
                playing.getGame().getAudioPlayer().stopSong();
                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.GAMEOVER);
            }else {
                updateAnimationTick();
            }
            return;
        }
        updateAttackBox();
        updateProjectiles();
        updatePos();
        if(moving){
            checkPotionTouched();
            checkSpikesTouched();
            tileY = (int) hitBox.y / Game.TILES_SIZE;
        }
        if (attacking) checkAttack();
        updateAnimationTick();
        setAnimation();
    }
    public void updateBar(){
        updateHealthBar();
        updateManaBar();
        updateExpBar();
    }
    private void checkSpikesTouched() {
        playing.checkSpikesTouched(this);
    }
    private void checkPotionTouched() {
        playing.checkPotionTouch(hitBox);
    }

    private void checkAttack() {
        if (attackChecked || aniIndex != 1) return;
        attackChecked = true;
        playing.checkEnemyHit(attackBox);
        playing.checkObjectHit(attackBox);
        playing.getGame().getAudioPlayer().playAttackSound();
    }

    private void drawAttackBox(Graphics g, int xLevelOffset, int yLevelOffset) {
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x - xLevelOffset, (int) attackBox.y - yLevelOffset, (int) attackBox.width, (int) attackBox.height);
    }

    private void updateAttackBox() {
        if (right) {
            attackBox.x = hitBox.x + hitBox.width + (int) (Game.SCALE * 10);

        } else if (left) {
            attackBox.x = hitBox.x - hitBox.width + (int) (Game.SCALE * 10);
        }
        attackBox.y = hitBox.y + (int) (Game.SCALE);
    }

    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float) maxHealth) * barWidth);
    }
    private void updateManaBar(){
        manaWidth = (int) ((currentMana / (float) maxMana) * barWidth);
    }
    private void updateExpBar(){
        expWidth = (int) ((currentExp / (float) maxExp) * barWidth);
    }
    private void checkLevelUp(){
        if(currentExp >= maxExp){
            maxHealth += 100;currentHealth = maxHealth;

            maxMana += 100; currentMana = maxMana;
            currentExp -= maxExp; maxExp += 100;
            levelPlayer++;
            levelUp = true;
            frameCount = 0;
        }
    }
    public void render(Graphics g, int xLevelOffset , int yLevelOffset) {
        g.drawImage(idAniIm[aniIndex], (int) (hitBox.x - cameraX) - xLevelOffset, (int) (hitBox.y - cameraY) - yLevelOffset, widthPlayer, heightPlayer, null);
        //drawHitBox(g, xLevelOffset, yLevelOffset);
        //drawAttackBox(g, xLevelOffset, yLevelOffset);
        drawProjectiles(g, xLevelOffset, yLevelOffset);
        drawUI(g);
        if (levelUp && frameCount < DISPLAY_FRAMES) {
            g.drawImage(levelUpImg, (int) (hitBox.x - cameraX - 20) - xLevelOffset,
                    (int) (hitBox.y - cameraY -30) - yLevelOffset,
                    80, 30, null);
            frameCount++;
        } else {
            levelUp = false;
            frameCount = 0;
        }
    }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.red);
        g.fillRect(3, 6, healthWidth, barHeight);
        g.setColor(Color.blue);
        g.fillRect(3 , 21, manaWidth, barHeight);
        g.setColor(Color.yellow);
        g.fillRect(3 , 37, expWidth, barHeight);
    }

    public void changeHealth(int value) {
        currentHealth += value;
        if (currentHealth <= 0) {
            state = HIT;
            currentHealth = 0;
        }
        else if (currentHealth >= maxHealth) currentHealth = maxHealth;
    }
    public void changePower(int value){
        currentMana += value;
        if (currentMana <= 0) {
            currentMana = 0;
        }
        else if (currentMana >= maxMana) currentMana = maxMana;
    }
    public void kill(){
        currentHealth = 0;
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getImage(LoadSave.PLAYER_ANI);
        idAniIm = new BufferedImage[state];
        for (int i = 0; i < idAniIm.length; i++) {
            idAniIm[i] = img.getSubimage(i * 50, 0, 50, 70);
        }
        idAniH = new BufferedImage[5];
        idAniH = idAniIm;
        statusBarImg = LoadSave.getImage(LoadSave.STATUS_BAR);
    }

    private void loadAnimationsImLeft() {
        BufferedImage imgLeft = LoadSave.getImage(LoadSave.PLAYER_IML);
        idAniL = new BufferedImage[5];
        for (int i = 0; i < idAniL.length; i++) {
            idAniL[i] = imgLeft.getSubimage(i * 50, 0, 50, 70);
        }
    }

    private void loadAnimationsLeft() {
        BufferedImage imgLeft = LoadSave.getImage(LoadSave.PLAYER_RUNL);
        idAniLeft = new BufferedImage[5];
        for (int i = 0; i < idAniLeft.length; i++) {
            idAniLeft[i] = imgLeft.getSubimage(i * 50, 0, 50, 70);
        }
    }

    private void loadAnimationsRight() {
        BufferedImage imgRight = LoadSave.getImage(LoadSave.PLAYER_RUNR);
        idAniRight = new BufferedImage[5];
        for (int i = 0; i < idAniRight.length; i++) {
            idAniRight[i] = imgRight.getSubimage(i * 50, 0, 50, 70);
        }
    }
    private void loadAnimationsAttack() {
        BufferedImage imgAt = LoadSave.getImage(LoadSave.PLAYER_AT);
        idAniAt = new BufferedImage[5];
        for (int i = 0; i < idAniAt.length; i++) {
            idAniAt[i] = imgAt.getSubimage(i * 100, 0, 100, 70);
        }
    }
    private void loadAnimationsAttackLeft() {
        BufferedImage imgAt = LoadSave.getImage(LoadSave.PLAYER_AT_LEFT);
        idAniAtL = new BufferedImage[5];
        for (int i = 0; i < idAniAtL.length; i++) {
            idAniAtL[i] = imgAt.getSubimage(i * 100, 0, 100, 70);
        }
    }
    private void loadAnimationsJump() {
        BufferedImage imgJump = LoadSave.getImage(LoadSave.PLAYER_JUMP);
        idAniJump = new BufferedImage[5];
        for (int i = 0; i < idAniJump.length; i++) {
            idAniJump[i] = imgJump;
        }
    }
    private void loadAnimationsFalling() {
        BufferedImage imgFall = LoadSave.getImage(LoadSave.PLAYER_FALL);
        idAniFall = new BufferedImage[5];
        for (int i = 0; i < idAniFall.length; i++) {
            idAniFall[i] = imgFall;
        }
    }
    private void loadAnimationsJumpLeft() {
        BufferedImage imgJump = LoadSave.getImage(LoadSave.PLAYER_JUMP_LEFT);
        idAniJumpL = new BufferedImage[5];
        for (int i = 0; i < idAniJumpL.length; i++) {
            idAniJumpL[i] = imgJump;
        }
    }
    private void loadAnimationsFallingLeft() {
        BufferedImage imgFall = LoadSave.getImage(LoadSave.PLAYER_FALL_LEFT);
        idAniFallL = new BufferedImage[5];
        for (int i = 0; i < idAniFallL.length; i++) {
            idAniFallL[i] = imgFall;
        }
    }

    private void loadAnimationAir() {
        BufferedImage imgInAir = LoadSave.getImage(LoadSave.PLAYER_IN_AIR);
        idAniInAir = new BufferedImage[4];
        for (int i = 0; i < idAniInAir.length - 1; i++) {
            idAniInAir[i] = imgInAir.getSubimage(i * 50, 0, 50, 70);
        }

    }
    public void loadlvlData(int lvlData[][]) {
        this.mapData = lvlData;
        if (!isEntityOnFloor(hitBox, lvlData)) {
            inAir = true;
        }
    }
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= idAniIm.length) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }
        }
    }

    private void setAnimation() {
        int startAnimation = state;
        if (moving) {
            if (left && !right) {
                checkL = true;
                checkR = false;
                state = RUNNING;
                idAniIm = idAniLeft;
            } else if (!left && right) {
                checkR = true;
                checkL = false;
                state = RUNNING;
                idAniIm = idAniRight;
            } else if (!left && !right) {
                state = IDLE;
                if (checkL && !checkR) {
                    idAniIm = idAniL;
                } else {
                    idAniIm = idAniH;
                }
            }
        } else {
            state = IDLE;
            if (checkL && !checkR) {
                idAniIm = idAniL;
            } else {
                idAniIm = idAniH;
            }
        }
        if (inAir) {
            if (airSpeed > 0) {
                state = FALLING;
                if (checkL && !checkR) {
                    idAniIm = idAniFallL;
                } else {
                    idAniIm = idAniFall;
                }
            } else if (airSpeed > -1 && airSpeed <= 0) {
                state = AIR;
                idAniIm = idAniInAir;
            } else {
                state = JUMPPING;
                if (checkL && !checkR) {
                    idAniIm = idAniJumpL;
                } else {
                    idAniIm = idAniJump;
                }
            }

        }
        if (attacking) {
            widthPlayer = 60;
            state = ATTACK_1;
            if (checkL && !checkR) {
                idAniIm = idAniAtL;
            } else {
                idAniIm = idAniAt;
            }
        } else {
            widthPlayer = 30;

        }
        if (startAnimation != state) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniIndex = 0;
        aniTick = 0;
    }

//    public void resetAll()

    private void updatePos() {
        moving = false;
        if (jump) {
            jump();
        }
        if (!left && !right && !inAir) {
            return;
        }
        float xSpeed = 0;
        if (left) {
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }
        if (!inAir) {
            if (!isEntityOnFloor(hitBox, mapData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if(airSpeed>0){
                //playing.getGame().getAudioPlayer().playEffect(AudioPlayer.JUMP);
                if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, mapData) && canJumpTile(hitBox.x, hitBox.y+airSpeed, hitBox.width, hitBox.height, mapData)) {
                    hitBox.y += airSpeed;
                    airSpeed += GRAVITY;
                    updateXPos(xSpeed);
                }
                else {
//                    hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                    resetInAir();
                    airSpeed = fallSpeedAfterCollision;
                    updateXPos(xSpeed);
                }
            }
            else{
                if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, mapData) ) {
                    hitBox.y += airSpeed;
                    airSpeed += GRAVITY;
                    updateXPos(xSpeed);
                }
                else {

                    hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                    if (airSpeed > 0) {
                        resetInAir();
                    } else {
                        airSpeed = fallSpeedAfterCollision;
                    }
                    updateXPos(xSpeed);
                }
                //playing.getGame().getAudioPlayer().playEffect(AudioPlayer.FALL);
            }

        } else updateXPos(xSpeed);
        moving = true;
    }

    private void jump() {
        if (inAir) {
            if(jump && !canDoubleJump){
                canDoubleJump = true;
                inAir = true;
                airSpeed = jumpSpeed/10*8;
                return;
            }
            canDoubleJump = true;
        }
        else {
            canDoubleJump = false;
            inAir = true;
            airSpeed = jumpSpeed;
            jump = false;
        }
        playing.getGame().getAudioPlayer().playEffect(AudioPlayer.JUMP);

    }


    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, mapData)) {
            hitBox.x += xSpeed;
        } else {
            hitBox.x = getEntityXPosNextToWall(hitBox, xSpeed);
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
    }
    public void resetAll(){
        resetDirBooleans();
        inAir = false;
        attacking = false;
        moving = false;
        state = IDLE;
        currentHealth = maxHealth;
        hitBox.x = x;
        hitBox.y = y;
        if (!isEntityOnFloor(hitBox, mapData))
            inAir = true;
    }


    public void setAttacking(boolean attacking) {
        if (!this.attacking) {
            aniIndex = 0;
            aniTick = 0;
            this.attacking = attacking;
        }
    }

    public void setSkill(boolean skill) {
        this.skill = skill;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
    public int getTileY(){
        return tileY;
    }


}
