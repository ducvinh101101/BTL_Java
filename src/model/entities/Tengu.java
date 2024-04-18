package model.entities;

import model.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static model.utilz.Constants.EnemyConstants.*;

public class Tengu extends Enemy {
    private Rectangle2D.Float attackBox;

    private int attackBoxOffsetX;
    private int attackBoxOffsetY;

    public Tengu(float x, float y) {
        super(x, y, (int) (MONSTER_WIDTH*5 * Game.SCALE), (int) (MONSTER_HEIGHT*5* Game.SCALE), TENGU);
        initHitBox( (int) (100 * Game.SCALE), (int) (100 * Game.SCALE) - 14);
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (150 * Game.SCALE) , (int) (80 * Game.SCALE) );
        attackBoxOffsetX = (int) (Game.SCALE * 30);
        attackBoxOffsetY = (int) (Game.SCALE * 0);
    }

    public void update(int[][] lvData, Player player) {
        updateBehavior(lvData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackBox.x = hitBox.x - attackBoxOffsetX;
        attackBox.y = hitBox.y - attackBoxOffsetY;
    }

    public void drawAttackBox(Graphics g, int xlvOffset, int yLevelOffset) {
        g.setColor(Color.red);
        g.drawRect((int) (attackBox.x - xlvOffset), (int) (attackBox.y-yLevelOffset), (int) attackBox.width, (int) attackBox.height);
    }

    private void updateBehavior(int[][] lvData, Player player) {
        if (firstUpdate) firstUpdateCheck(lvData);
        if (inAir) updateInAir(lvData);
        else {
            switch (enemyState) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvData, player)) {
                        turnTowardsPlayer(player);
                        if (isPlayerCloseForAttack(player)) newState(ATTACK);
                    }
                    //move3();
                    move(lvData);
                    break;
                case ATTACK:
                    if (animationIndex == 0) attackChecked = false;
                    if (animationIndex == 5 && !attackChecked) checkPlayerHit(attackBox, player);
                    break;
                case DEAD:
                    alive = false;
                    break;
            }
        }
    }
    public void drawHP(Graphics g,  int xLvOffset, int yLevelOffset){
        double oneScale =  (MONSTER_HEIGHT*2* Game.SCALE)/maxHealth;
        double hpBarValue = oneScale * currentHealth;

        g.setColor(new Color(35, 35, 35));
        g.fillRect((int) hitBox.x - xLvOffset, (int) hitBox.y - yLevelOffset - 14, (int) (MONSTER_HEIGHT*2* Game.SCALE) , 6);
        g.setColor(new Color(255, 0, 30));
        g.fillRect((int) hitBox.x - xLvOffset, (int) hitBox.y - yLevelOffset - 14, (int)hpBarValue, 6);
    }

}