package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[] idAniIm, idAniLeft, idAniRight, idAniH,idAniAt;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
//    private int playerDir = -1;
    private boolean left, right, up, down;
    private boolean moving = false,attacking = false;
    private float playerSpeed = 0.5f;
    private int widthPy = 30;
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
        loadAnimationsLeft();
        loadAnimationsRight();
        loadAnimationsAttack();
    }
    public void update(){
        updatePos();
        setAnimation();
        updateAnimationTick();
    }
    public void render(Graphics g){
        g.drawImage(idAniIm[aniIndex],(int)x,(int)y,widthPy,42,null);
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

    private void loadAnimationsAttack() {
        InputStream is = getClass().getResourceAsStream("/playerAttack.png");
        try(is) {
            if (is != null) {
                BufferedImage imgAt = ImageIO.read(is);
                idAniAt = new BufferedImage[5];
                for(int i=0;i<idAniAt.length;i++){
                    idAniAt[i]= imgAt.getSubimage(i*100, 0, 100, 70);
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
    }
    public void resetDirBooleans(){
        left=false;
        right=false;
        up=false;
        down=false;
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
