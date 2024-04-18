package model.entities;

import view.Main.Game;

import java.awt.*;

import static model.utilz.Constants.EnemyConstants.*;

public class Dummy extends Enemy {
    public Dummy(float x, float y) {
        super(x, y, (int) (MONSTER_WIDTH * Game.SCALE), (int) (MONSTER_HEIGHT*2 * Game.SCALE), DUMMY);
        initHitBox( (int) (30 * Game.SCALE), (int) (38 * Game.SCALE));
    }

    public void update(int[][] lvData, Player player) {
        updateBehavior(lvData, player);
        updateAnimationTick();
    }


    private void updateBehavior(int[][] lvData, Player player) {
        //if (firstUpdate) firstUpdateCheck(lvData);
        if (inAir) updateInAir(lvData);
        else {
            switch (enemyState) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case DEAD:
                    alive = false;
                    break;
            }
        }
    }
    public void drawHP(Graphics g,  int xLvOffset, int yLevelOffset){
        double oneScale =  (MONSTER_WIDTH* Game.SCALE)/maxHealth;
        double hpBarValue = oneScale * currentHealth;

        // thanh máu nền
        g.setColor(new Color(35, 35, 35));
        g.fillRect((int) hitBox.x - xLvOffset, (int) hitBox.y - yLevelOffset - 14, (int) (MONSTER_WIDTH* Game.SCALE), 6);

        // thanh máu hiện tại
        g.setColor(new Color(255, 0, 30));
        g.fillRect((int) hitBox.x - xLvOffset, (int) hitBox.y - yLevelOffset - 14, (int)hpBarValue, 6);
    }

}