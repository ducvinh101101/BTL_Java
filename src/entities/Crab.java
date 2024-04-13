package entities;

import Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;

public class Crab extends Enemy {
    private Rectangle2D.Float attackBox;

    private int attackBoxOffsetX;

    public Crab(float x, float y) {
        super(x, y, MONSTER_WIDTH, MONSTER_HEIGHT, DUMMY);
        initHitBox(x, y, (int) (20 * Game.SCALE) - 10, (int) (42 * Game.SCALE) - 10);
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (100 * Game.SCALE) - 10, (int) (42 * Game.SCALE) - 10);
        attackBoxOffsetX = (int) (Game.SCALE * 30);

    }

    public void update(int[][] lvData, Player player) {
        updateBehavior(lvData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackBox.x = hitBox.x - attackBoxOffsetX;
        attackBox.y = hitBox.y;
    }

    public void drawAttackBox(Graphics g, int xlvOffset) {
        g.setColor(Color.red);
        g.drawRect((int) (attackBox.x - xlvOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.y);
    }

    private void updateBehavior(int[][] lvData, Player player) {
        if (firstUpdate) {
            firstUpdateCheck(lvData);
        }
        if (inAir) {
            updateInAir(lvData);
        } else {
            switch (enemyState) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvData, player)) turnTowardsPlayer(player);
                    if (isPlayerCloseForAttack(player)) newState(ATTACK);
                    move(lvData);
                    break;
                case ATTACK:
                    if (animationIndex == 0) attackChecked = false;
                    if (animationIndex == 3 && !attackChecked) checkPlayerHit(attackBox, player);
                    break;

            }
        }
    }


    public int flipX() {
        if (walkDir == RIGHT) return width;
        else return 0;
    }

    public int flipW() {
        if (walkDir == RIGHT) return 1;
        else return -1;
    }
}