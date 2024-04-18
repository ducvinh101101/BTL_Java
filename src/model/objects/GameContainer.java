package model.objects;

import view.Main.Game;

import static model.utilz.Constants.ObjectConstants.*;
public class GameContainer extends GameObject{
    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    private void createHitbox() {
        if(objType == BOX){
            initHitBox(25,18);
            xDrawOffset = (int)(7 * Game.SCALE);
            yDrawOffset = (int)(12 * Game.SCALE);
        }else {
            initHitBox(23,25);
            xDrawOffset = (int) (8 * Game.SCALE);
            yDrawOffset = (int) (5 * Game.SCALE);
        }
        hitBox.y +=  yDrawOffset + (int)(Game.SCALE * 2);
        hitBox.x += xDrawOffset ;
    }
    public void update(){
        if(doAnimation){
            updateAnimationTick();
        }
    }
}
