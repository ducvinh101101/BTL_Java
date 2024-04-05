package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.PlayerConstants.IDLE;
import static utilz.Constants.PlayerConstants.RUNNING;

public class Player extends Entity{
    private BufferedImage[] idAniIm, idAniLeft, idAniRight, idAniH;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
//    private int playerDir = -1;
    private boolean left, right, up, down;
    private boolean moving = false;
    private float playerSpeed = 0.5f;
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
        loadAnimationsLeft();
        loadAnimationsRight();
    }
    public void update(){
        updatePos();
        setAnimation();
        updateAnimationTick();
    }
    public void render(Graphics g){
        g.drawImage(idAniIm[aniIndex],(int)x,(int)y,30,42,null);
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/player.png");
        try(is) {
            if (is != null) {
                BufferedImage img = ImageIO.read(is);
                idAniIm = new BufferedImage[5];
                for(int i=0;i<idAniIm.length;i++){
                    idAniIm[i]= img.getSubimage(i*50, 0, 50, 70);
                }
                idAniH= new BufferedImage[5];
                idAniH = idAniIm;

            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadAnimationsLeft() {
        InputStream is = getClass().getResourceAsStream("/pyrunleft.png");
        try(is) {
            if (is != null) {
                BufferedImage imgLeft = ImageIO.read(is);
                idAniLeft = new BufferedImage[5];
                for(int i=0;i<idAniLeft.length;i++){
                    idAniLeft[i]= imgLeft.getSubimage(i*50, 0, 50, 70);
                }

            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadAnimationsRight() {
        InputStream is = getClass().getResourceAsStream("/pyrunright.png");
        try(is) {
            if (is != null) {
                BufferedImage imgRight = ImageIO.read(is);
                idAniRight = new BufferedImage[5];
                for(int i=0;i<idAniRight.length;i++){
                    idAniRight[i]= imgRight.getSubimage(i*50, 0, 50, 70);
                }

            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public void setDerection(int derection){
//        this.playerDir = derection;
//        moving = true;
//    }
//    public void setMoving(boolean moving){
//        this.moving=moving;
//    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=idAniIm.length){
                aniIndex=0;
            }
        }
    }
    private void setAnimation() {
        if (moving) {
            if (left && !right) {
                idAniIm = idAniLeft;
                playerAction = RUNNING;
            } else if (!left && right) {
                idAniIm = idAniRight;
                playerAction = RUNNING;
            } else if(!left && !right) {
                playerAction = IDLE;
                idAniIm = idAniH;
            }
        }
        else {
            playerAction = IDLE;
            idAniIm = idAniH;
        }
    }
    private void updatePos() {
        moving = false;
        if(left &&!right){
            x-=playerSpeed;
            moving = true;
        }
        else if(!left && right){
            x+=playerSpeed;
            moving = true;
        }

        if(up &&!down){
            y-=playerSpeed;
            moving = true;
        }
        else if(!up && down){
            y+=playerSpeed;
            moving = true;
        }
//        if(moving){
//            switch (playerDir){
//                case LEFT:
//                    x -=1;
//                    break;
//                case RIGHT:
//                    x +=1;
//                    break;
//                case UP:
//                    y -=1;
//                    break;
//                case DOWN:
//                    y +=1;
//                    break;
//            }
//        }

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
