package model.objects;

import view.Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static model.utilz.Constants.ANI_SPEED;
import static model.utilz.Constants.ObjectConstants.*;

public class GameObject {
    protected int x, y, objType;
    protected Rectangle2D.Float hitBox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;
    public GameObject(int x, int y, int objType){
        this.x = x;
        this.y = y;
        this.objType = objType;
    }
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(objType)) {
                aniIndex = 0;
                if(objType == BARREL || objType == BOX){
                    doAnimation = false;
                    active = false;
                }else if(objType == CANNON_LEFT || objType == CANNON_RIGHT){
                    doAnimation = false;
                }
            }
        }
    }

    public void reset(){
        aniIndex = 0;
        aniTick = 0;
        active = true;

        if(objType == BARREL || objType == BOX || objType == CANNON_LEFT || objType == CANNON_RIGHT){
            doAnimation = false;
        }else {
            doAnimation = true;
        }
        doAnimation = true;
    }
    protected void initHitBox( float width, float height) {
        hitBox = new Rectangle2D.Float(x, y,(int) (width * Game.SCALE),(int) (height * Game.SCALE));
    }
    public void drawHitBox(Graphics g, int levelOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitBox.x - levelOffset, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    public int getObjType() {
        return objType;
    }
    public Rectangle2D.Float getHitbox() {
        return hitBox;
    }
    public boolean isActive() {
        return active;
    }
    public void setAnimation(boolean doAnimation){
        this.doAnimation = doAnimation;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public int getxDrawOffset() {
        return xDrawOffset;
    }
    public int getyDrawOffset() {
        return yDrawOffset;
    }
    public int getAniIndex(){
        return aniIndex;
    }
    public int getAniTick(){
        return aniTick;
    }

}
