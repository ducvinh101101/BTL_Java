package model.entities;

import model.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;
    protected int aniTick, aniIndex;
    protected int state;
    protected boolean inAir = false;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }


    protected void drawHitBox(Graphics g, int xlevelOffset, int yLevelOffset) {
        g.setColor(Color.BLACK);
        g.drawRect((int) hitBox.x - xlevelOffset, (int) hitBox.y - yLevelOffset, (int) hitBox.width, (int) hitBox.height);
    }

    protected void initHitBox( float width, float height) {
        hitBox = new Rectangle2D.Float(x, y,(int) (width * Game.SCALE),(int) (height * Game.SCALE));
    }


//    public void updateHitBox(){
//        hitBox.x=(int)x;
//        hitBox.y=(int)y;
//    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public void setHitBox(float x, float y) {
        this.hitBox.x = x;
        this.hitBox.y = y;
    }
}
