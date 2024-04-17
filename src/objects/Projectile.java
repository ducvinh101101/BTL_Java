package objects;

import Main.Game;

import java.awt.geom.Rectangle2D;
import static utilz.Constants.Projectiles.*;

public class Projectile {
    private Rectangle2D.Float hitBox;
    private int dir;
    private boolean active = true;

    public Projectile(int x, int y, int dir){
        int xOffset = (int) (-3 * Game.SCALE);
        int yOffset = (int)(5 * Game.SCALE);
        if(dir == 1){
            xOffset = (int) (29 * Game.SCALE);
        }
        hitBox = new Rectangle2D.Float(x + xOffset,y + yOffset,CANNON_BALL_WIDTH,CANNON_BALL_HEIGHT);
        this.dir = dir;
    }

    public void updatePos(){
        hitBox.x += dir * SPEED;
    }

    public void setPos(int x, int y){
        hitBox.x = x;
        hitBox.y = y;
    }

    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public boolean isActive(){
        return active;
    }
}
