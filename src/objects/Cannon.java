package objects;

import Main.Game;

public class Cannon extends GameObject {
    private int tileY;

    public Cannon(int x, int y, int objType) {
        super(x, y, objType);
        tileY = y / Game.TILES_SIZE;
        initHitBox(40,26);
        hitBox.x -= (int)(4 * Game.SCALE);
        hitBox.y += (int)(6 * Game.SCALE);
    }

    public void update(){
        if(doAnimation){
            updateAnimationTick();
        }
    }

    public int getTileY(){
        return tileY;
    }
}
