package entities;

import Main.Game;
import gamestates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utilz.HelpMethods.*;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.getEntityXPosNextToWall;

public class Player extends Entity {

    private BufferedImage[] idAniIm, idAniLeft, idAniRight, idAniH, idAniAt, idAniL, idAniAtL, idAniJumpL, idAniFallL, idAniJump, idAniFall, idAniInAir;
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = IDLE;
    //    private int playerDir = -1;
    private boolean left, right, jump, checkL, checkR;
    private boolean moving = false, attacking = false;
    private float playerSpeed = 1.5f;
    private int widthPy = 30, heightPy = 42;
    private float xDrawOffSet = 5f * Game.SCALE;
    private float yDrawOffSet = 9 * Game.SCALE;
    // nhảy trọng lực:
    private float airSpeed = 0f;
    private float gravity = 0.03f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    private int lvlData[][];
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (10 * Game.SCALE);
    private int statusBarY = (int) (10 * Game.SCALE);

    private int healthBarWidth = (int) (150 * Game.SCALE);
    private int healthBarHeight = (int) (4 * Game.SCALE);
    private int healthBarXStart = (int) (34 * Game.SCALE);
    private int healthBarYStart = (int) (14 * Game.SCALE);

    private int maxHealth = 100;
    private int currentHealth = maxHealth;

    private int healthWidth = healthBarWidth;

    private Rectangle2D.Float attackBox;
    private boolean attackChecked;
    private Playing playing;

    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
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
        initHitBox(x, y, widthPy * Game.SCALE - 10, heightPy * Game.SCALE - 10);
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
    }

    public void update() {
        lvlData = playing.getLevelManager().getCurrenLevel().getlvlData(); // bổ sung update map mỗi khi load lại map
        updateHealthBar();
        updateAttackBox();
        updatePos();
        if (attacking) checkAttack();
        updateAnimationTick();
        setAnimation();
    }

    private void checkAttack() {
        if (attackChecked || aniIndex != 1) return;
        attackChecked = true;
        playing.checkEnemyHit(attackBox);
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
        healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
    }

    public void render(Graphics g, int xLevelOffset , int yLevelOffset) {
        g.drawImage(idAniIm[aniIndex], (int) (hitBox.x - xDrawOffSet) - xLevelOffset, (int) (hitBox.y - yDrawOffSet) - yLevelOffset, widthPy, heightPy, null);
        drawHitBox(g, xLevelOffset, yLevelOffset);
        drawAttackBox(g, xLevelOffset, yLevelOffset);
        drawUI(g);
    }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.red);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
    }

    public void changeHealth(int value) {
        currentHealth += value;
        if (currentHealth <= 0) currentHealth = 0;
        else if (currentHealth >= maxHealth) currentHealth = maxHealth;
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAlas(LoadSave.PLAYER_ANI);
        idAniIm = new BufferedImage[playerAction];
        for (int i = 0; i < idAniIm.length; i++) {
            idAniIm[i] = img.getSubimage(i * 50, 0, 50, 70);
        }
        idAniH = new BufferedImage[5];
        idAniH = idAniIm;
        statusBarImg = LoadSave.getSpriteAlas(LoadSave.STATUS_BAR);
    }

    private void loadAnimationsImLeft() {
        BufferedImage imgLeft = LoadSave.getSpriteAlas(LoadSave.PLAYER_IML);
        idAniL = new BufferedImage[5];
        for (int i = 0; i < idAniL.length; i++) {
            idAniL[i] = imgLeft.getSubimage(i * 50, 0, 50, 70);
        }
    }

    private void loadAnimationsLeft() {
        BufferedImage imgLeft = LoadSave.getSpriteAlas(LoadSave.PLAYER_RUNL);
        idAniLeft = new BufferedImage[5];
        for (int i = 0; i < idAniLeft.length; i++) {
            idAniLeft[i] = imgLeft.getSubimage(i * 50, 0, 50, 70);
        }
    }

    private void loadAnimationsRight() {
        BufferedImage imgRight = LoadSave.getSpriteAlas(LoadSave.PLAYER_RUNR);
        idAniRight = new BufferedImage[5];
        for (int i = 0; i < idAniRight.length; i++) {
            idAniRight[i] = imgRight.getSubimage(i * 50, 0, 50, 70);
        }
    }

    private void loadAnimationsAttack() {

        BufferedImage imgAt = LoadSave.getSpriteAlas(LoadSave.PLAYER_AT);
        idAniAt = new BufferedImage[5];
        for (int i = 0; i < idAniAt.length; i++) {
            idAniAt[i] = imgAt.getSubimage(i * 100, 0, 100, 70);
        }
    }

    private void loadAnimationsAttackLeft() {
        BufferedImage imgAt = LoadSave.getSpriteAlas(LoadSave.PLAYER_AT_LEFT);
        idAniAtL = new BufferedImage[5];
        for (int i = 0; i < idAniAtL.length; i++) {
            idAniAtL[i] = imgAt.getSubimage(i * 100, 0, 100, 70);
        }
    }

    private void loadAnimationsJump() {
        BufferedImage imgJump = LoadSave.getSpriteAlas(LoadSave.PLAYER_JUMP);
        idAniJump = new BufferedImage[5];
        for (int i = 0; i < idAniJump.length; i++) {
            idAniJump[i] = imgJump;
        }
    }

    private void loadAnimationsFalling() {
        BufferedImage imgFall = LoadSave.getSpriteAlas(LoadSave.PLAYER_FALL);
        idAniFall = new BufferedImage[5];
        for (int i = 0; i < idAniFall.length; i++) {
            idAniFall[i] = imgFall;
        }
    }

    private void loadAnimationsJumpLeft() {
        BufferedImage imgJump = LoadSave.getSpriteAlas(LoadSave.PLAYER_JUMP_LEFT);
        idAniJumpL = new BufferedImage[5];
        for (int i = 0; i < idAniJumpL.length; i++) {
            idAniJumpL[i] = imgJump;
        }
    }

    private void loadAnimationsFallingLeft() {
        BufferedImage imgFall = LoadSave.getSpriteAlas(LoadSave.PLAYER_FALL_LEFT);
        idAniFallL = new BufferedImage[5];
        for (int i = 0; i < idAniFallL.length; i++) {
            idAniFallL[i] = imgFall;
        }
    }

    private void loadAnimationAir() {
        BufferedImage imgInAir = LoadSave.getSpriteAlas(LoadSave.PLAYER_IN_AIR);
        idAniInAir = new BufferedImage[4];
        for (int i = 0; i < idAniInAir.length - 1; i++) {
            idAniInAir[i] = imgInAir.getSubimage(i * 50, 0, 50, 70);
        }

    }

    public void loadlvlData(int lvlData[][]) {
        this.lvlData = lvlData;
        if (!isEntityOnFloor(hitBox, lvlData)) {
            inAir = true;
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
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
        int startAnimation = playerAction;
        if (moving) {
            if (left && !right) {
                checkL = true;
                checkR = false;
                playerAction = RUNNING;
                idAniIm = idAniLeft;
            } else if (!left && right) {
                checkR = true;
                checkL = false;
                playerAction = RUNNING;
                idAniIm = idAniRight;
            } else if (!left && !right) {
                playerAction = IDLE;
                if (checkL && !checkR) {
                    idAniIm = idAniL;
                } else {
                    idAniIm = idAniH;
                }
            }
        } else {
            playerAction = IDLE;
            if (checkL && !checkR) {
                idAniIm = idAniL;
            } else {
                idAniIm = idAniH;
            }
        }
        if (inAir) {
            if (airSpeed > 0) {
                playerAction = FALLING;
                if (checkL && !checkR) {
                    idAniIm = idAniFallL;
                } else {
                    idAniIm = idAniFall;
                }
            } else if (airSpeed > -1 && airSpeed <= 0) {
                playerAction = AIR;
                idAniIm = idAniInAir;
            } else {
                playerAction = JUMPPING;
                if (checkL && !checkR) {
                    idAniIm = idAniJumpL;
                } else {
                    idAniIm = idAniJump;
                }
            }

        }
        if (attacking) {
            widthPy = 60;
            playerAction = ATTACK_1;
            if (checkL && !checkR) {
                idAniIm = idAniAtL;
            } else {
                idAniIm = idAniAt;
            }
        } else {
            widthPy = 30;

        }
        if (startAnimation != playerAction) {
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
            if (!isEntityOnFloor(hitBox, lvlData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if(airSpeed>0){
                if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData) && canJumpTile(hitBox.x, hitBox.y+airSpeed, hitBox.width, hitBox.height, lvlData)) {
                    hitBox.y += airSpeed;
                    airSpeed += gravity;
                    updateXPos(xSpeed);
                } else {
                    if (airSpeed > 0) {
                        resetInAir();
                    } else {
                        airSpeed = fallSpeedAfterCollision;
                    }
                    updateXPos(xSpeed);
                }
            }
            else{
                if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData) ) {
                    hitBox.y += airSpeed;
                    airSpeed += gravity;
                    updateXPos(xSpeed);
                } else {

                    hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                    if (airSpeed > 0) {
                        resetInAir();
                    } else {
                        airSpeed = fallSpeedAfterCollision;
                    }
                    updateXPos(xSpeed);
                }
            }

        } else updateXPos(xSpeed);
        moving = true;
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
        jump = false;
    }


    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
            hitBox.x += xSpeed;
        } else {
            hitBox.x = getEntityXPosNextToWall(hitBox, xSpeed);
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
    }

    public int getWidthPy() {
        return widthPy;
    }

    public int getHeightPy() {
        return heightPy;
    }

    public void setAttacking(boolean attacking) {
        if (!this.attacking) {
            aniIndex = 0;
            aniTick = 0;
            this.attacking = attacking;
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
