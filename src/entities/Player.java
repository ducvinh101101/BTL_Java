package entities;

import Main.Game;
import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.DOWN;
import static utilz.HelpMethods.*;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[] idAniIm, idAniLeft, idAniRight, idAniH,idAniAt;
    private int aniTick, aniIndex, aniSpeed = 10;
    private int playerAction = IDLE;
//    private int playerDir = -1;
    private boolean left, right, up, down,jump;
    private boolean moving = false,attacking = false;
    private float playerSpeed = 1.5f;
    private int widthPy = 30,heightPy = 42;
    private float xDrawOffSet = 0* Game.SCALE;
    private float yDrawOffSet = 0* Game.SCALE;
    // nhảy trọng lực:
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f*Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f*Game.SCALE;
    private boolean inAir = false;




    private int lvlData[][];
    public Player(float x, float y,int widthPy,int heightPy) {
        super(x, y,widthPy,heightPy);
        loadAnimations();
        loadAnimationsLeft();
        loadAnimationsRight();
        loadAnimationsAttack();
        initHitBox(x,y,widthPy*Game.SCALE,heightPy*Game.SCALE);
    }
    public void update(){
        updatePos();
        setAnimation();
        updateAnimationTick();
    }
    public void render(Graphics g){
        g.drawImage(idAniIm[aniIndex],(int)(hitBox.x-xDrawOffSet),(int) (hitBox.y-yDrawOffSet),widthPy,heightPy,null);
        drawHitBox(g);
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getPlayerAni();
        idAniIm = new BufferedImage[5];
        for(int i=0;i<idAniIm.length;i++){
            idAniIm[i]= img.getSubimage(i*50, 0, 50, 70);
        }
        idAniH= new BufferedImage[5];
        idAniH = idAniIm;
    }

    private void loadAnimationsLeft() {
        BufferedImage imgLeft = LoadSave.getPlayerRunLeft();
        idAniLeft = new BufferedImage[5];
        for(int i=0;i<idAniLeft.length;i++){
            idAniLeft[i]= imgLeft.getSubimage(i*50, 0, 50, 70);
        }
    }

    private void loadAnimationsRight() {
        BufferedImage imgRight = LoadSave.getPlayerRunRight();
        idAniRight = new BufferedImage[5];
        for(int i=0;i<idAniRight.length;i++) {
            idAniRight[i] = imgRight.getSubimage(i * 50, 0, 50, 70);
        }
    }

    private void loadAnimationsAttack() {

        BufferedImage imgAt = LoadSave.getPlayerAttack();
        idAniAt = new BufferedImage[5];
        for(int i=0;i<idAniAt.length;i++){
            idAniAt[i]= imgAt.getSubimage(i*100, 0, 100, 70);
        }


    }

    public void loadlvlData(int lvlData[][]){
        this.lvlData=lvlData;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=idAniIm.length){
                aniIndex=0;
                attacking=false;
            }
        }
    }
    private void setAnimation() {
        int startAnimation = playerAction;
        if (moving) {
            if (left && !right) {
                playerAction = RUNNING;
                idAniIm = idAniLeft;
            } else if (!left && right) {
                playerAction = RUNNING;
                idAniIm = idAniRight;
            } else if(!left && !right) {
                playerAction = IDLE;
                idAniIm = idAniH;
            }
        }
        else {
            playerAction = IDLE;
            idAniIm = idAniH;
        }
        if(attacking){
            widthPy=60;
            playerAction=ATTACK_1;
            idAniIm=idAniAt;
        }
        else {
            widthPy = 30;
        }
        if(startAnimation!=playerAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniIndex = 0;
        aniTick = 0;
    }

    private void updatePos() {
        moving = false;
        if(!left&&!right&&!inAir){
            return;
        }
        float xSpeed = 0, ySpeed = 0;
        if(left &&!right){
            xSpeed=-playerSpeed;
        }
        else if(!left && right){
            xSpeed=playerSpeed;
        }

        if(canMoveHere(hitBox.x+xSpeed,hitBox.y,hitBox.width,hitBox.height,lvlData)){
            hitBox.x += xSpeed;
            moving = true;
        }
        if(inAir){

        }
        else{
            updateXPos(xSpeed);
        }


    }

    private void updateXPos(float xSpeed) {
        if(canMoveHere(hitBox.x+xSpeed,hitBox.y,hitBox.width,hitBox.height,lvlData)){
            this.x += xSpeed;
        }
        else {
            //this.x = getEntityXPosNextToWall()
        }
    }

    public void resetDirBooleans(){
        left=false;
        right=false;
        up=false;
        down=false;
    }

    public int getWidthPy() {
        return widthPy;
    }
    public int getHeightPy(){
        return heightPy;
    }

    public void setAttacking(boolean attacking){
        this.attacking=attacking;
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
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
}
