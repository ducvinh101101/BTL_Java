package entities;

import Main.Game;
import utilz.LoadSave;

import java.awt.*;
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
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    private int lvlData[][];

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
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
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g, int xLevelOffset) {
        g.drawImage(idAniIm[aniIndex], (int) (hitBox.x - xDrawOffSet) - xLevelOffset, (int) (hitBox.y - yDrawOffSet), widthPy, heightPy, null);
        drawHitBox(g, xLevelOffset);
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAlas(LoadSave.PLAYER_ANI);
        idAniIm = new BufferedImage[playerAction];
        for (int i = 0; i < idAniIm.length; i++) {
            idAniIm[i] = img.getSubimage(i * 50, 0, 50, 70);
        }
        idAniH = new BufferedImage[5];
        idAniH = idAniIm;
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
        for (int i = 0; i < idAniInAir.length-1; i++) {
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
            if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
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
            aniIndex=0;
            aniTick =0;
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
