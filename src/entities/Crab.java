package entities;

import Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;

public class Crab extends Enemy {
    private Rectangle2D.Float attackBox;

    private int attackBoxOffsetX;
    private int attackBoxOffsetY;

    public Crab(float x, float y) {
        super(x, y, (int) (MONSTER_WIDTH * Game.SCALE), (int) (MONSTER_HEIGHT * Game.SCALE), CRAB);
        initHitBox(x, y, (int) (40 * Game.SCALE), (int) (42 * Game.SCALE) - 10);
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (100 * Game.SCALE) - 10, (int) (40 * Game.SCALE) - 10);
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
                    move(lvData);
                    break;
                case ATTACK:
                    if (animationIndex == 0) attackChecked = false;
                    if (animationIndex == 3 && !attackChecked) checkPlayerHit(attackBox, player);
                    break;
                case DEAD:
                    break;
            }
        }
    }

}