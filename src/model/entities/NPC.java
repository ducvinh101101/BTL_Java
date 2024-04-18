package model.entities;

import model.Game;

import static model.utilz.Constants.EnemyConstants.MONSTER_HEIGHT;
import static model.utilz.Constants.EnemyConstants.MONSTER_WIDTH;

public class NPC extends Entity{

    public NPC(float x, float y) {
        super(x, y,(int) (MONSTER_WIDTH * Game.SCALE), (int) (MONSTER_HEIGHT*2* Game.SCALE));
        initHitBox( (int) (30 * Game.SCALE), (int) (38 * Game.SCALE));
    }


}
