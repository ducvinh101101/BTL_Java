package entities;

import Main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.*;

public class Enemy extends Entity {
    protected int animationIndex, enemyState, enemyType;
    protected int animationTick, animationSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean inAir;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 1.0f * Game.SCALE;
    protected float walkDir = RIGHT;

    protected int tileY;

    protected float attackDistance = Game.TILES_SIZE;
    protected int maxHealth;

    protected int currentHealth = maxHealth;
    protected boolean active = true;

    protected boolean attackChecked;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitBox(x, y, width, height);
        maxHealth = getMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public int getEnemyState() {
        return enemyState;
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

    protected void move(int[][] lvData) {
        float xSpeed = 0;
        if (walkDir == LEFT) xSpeed -= walkSpeed;
        else xSpeed = walkSpeed;
        if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvData)) {
            if (isFloor(hitBox, xSpeed, lvData)) {
                hitBox.x += xSpeed;
                return;
            }

        }
        changeWalkDir();
    }

    protected void turnTowardsPlayer(Player player) {
        if (player.hitBox.x > hitBox.x) walkDir = RIGHT;
        else walkDir = LEFT;
    }

    protected boolean canSeePlayer(int[][] lvData, Player player) {
        int playerTileY = (int) (player.getHitBox().y / Game.TILES_SIZE);
        if (playerTileY == tileY) {
            if (isPlayerInRange(player)) {
                if (isSightClear(lvData, hitBox, player.hitBox, tileY)) return true;
            }
        }
        return false;
    }

    private boolean isPlayerInRange(Player player) {
        int absValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return absValue <= attackDistance * 5;
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
                else if (enemyState == DEAD) active = false;
            }
        }
    }


    protected void changeWalkDir() {
        if (walkDir == LEFT) walkDir = RIGHT;
        else walkDir = LEFT;
    }

    // PLAYER ATT MON
    public void hurt(int i) {
        currentHealth -= i;
        if (currentHealth <= 0) newState(DEAD);
        else newState(HIT);
    }

    // MON ATT PLAYER
    protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.hitBox))
            player.changeHealth(-getEnemyDmg(enemyType));
        attackChecked = true;
    }

    public boolean isActive() {
        return active;
    }
}
