package entities;

import Main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.*;

public class Enemy extends Entity {

    protected int animationIndex, enemyState , enemyType;
    protected int animationTick, animationSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean inAir;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = Game.SCALE;
    protected int walkDir = LEFT;

    protected int tileY;

    protected float attackDistance = Game.TILES_SIZE;
    protected int maxHealth;

    protected int currentHealth = maxHealth;
    protected boolean alive = true;
    private int stepsCount;
    protected boolean attackChecked;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitBox( width, height);
        maxHealth = getMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    public int getEnemyState() {
        return enemyState;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    protected void firstUpdateCheck(int[][] lvData) {
        if (!isEntityOnFloor(hitBox, lvData)) {
            inAir = true;
        }
        firstUpdate = false;
    }

    protected void updateInAir(int[][] lvData) {
        if (canMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, lvData)) {
            hitBox.y += fallSpeed;
            fallSpeed += gravity;
        } else {
            inAir = false;
            hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
            tileY = (int) (hitBox.y / Game.TILES_SIZE);
        }
    }

    protected void  move(int[][] lvData) {
        float xSpeed = 0;
        if (walkDir == LEFT) xSpeed -= walkSpeed;
        else xSpeed += walkSpeed;
        if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvData)) {
            if (isFloor(hitBox, xSpeed,  lvData)) {
                hitBox.x += xSpeed;
                return;
            }
        }
        changeWalkDir();
    }
    public void move3(){
        if (walkDir == RIGHT){
            hitBox.x += walkSpeed;
            stepsCount++;
            if (stepsCount >= 60 * 3) changeWalkDir();
        }
        else {
            hitBox.x -= walkSpeed;
            stepsCount++;
            if (stepsCount >= 60 * 3) changeWalkDir();
        }

    }
    protected void turnTowardsPlayer(Player player) {
        if (player.hitBox.x > hitBox.x) walkDir = RIGHT;
        else walkDir = LEFT;
    }

    protected boolean canSeePlayer(int[][] lvData, Player player) {
//        int playerTileY = (int) (player.getHitBox().y / Game.TILES_SIZE);
//        if (playerTileY == tileY) {
//            if (isPlayerInRange(player)) {
//                if (isSightClear(lvData, hitBox, player.hitBox, tileY)) return true;
//            }
//        }
//        return false;
        if (isPlayerInRange(player)) {
            if (isSightClear(lvData, hitBox, player.hitBox)) return true;
        }
        return false;
    }

    private boolean isPlayerInRange(Player player) {
        float distanceX = Math.abs((int) (player.getHitBox().getCenterX() - this.getHitBox().getCenterX())-10);
        float distanceY = Math.abs((int)(player.getHitBox().getCenterY() - this.getHitBox().getCenterY())-10);
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distance <= 2*attackDistance;
    }

    protected boolean isPlayerCloseForAttack(Player player) {
        int absValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return absValue <= attackDistance;
    }

    protected void newState(int enemyState) {
        this.enemyState = enemyState;
        animationTick = 0;
        animationIndex = 0;
    }

    protected void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpritesAmount(enemyType, enemyState)) {
                animationIndex = 0;
                if (enemyState == ATTACK) enemyState = IDLE;
                else if (enemyState == HIT) enemyState = IDLE;
                else if (enemyState == DEAD) alive = false;
            }
        }
    }


    protected void changeWalkDir() {
        if (walkDir == LEFT) walkDir = RIGHT;
        else walkDir = LEFT;
        stepsCount = 0;
    }

    // PLAYER ATT MON
    public void hurt(int i, Player player) {
        currentHealth -= i;
        if (currentHealth <= 0) {
            newState(DEAD);
            player.setCurrentExp(player.getCurrentExp() + 60);
        }
        else newState(HIT);
    }

    // MON ATT PLAYER
    protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.hitBox)) {
            player.changeHealth(-getEnemyDmg(enemyType));
            attackChecked = true;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public int getWalkDir() {
        return walkDir;
    }

}
