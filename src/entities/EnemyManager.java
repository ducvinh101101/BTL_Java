package entities;

import Main.Game;
import gamestates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static Main.Game.TILES_DEFAULT_SIZE;
import static utilz.Constants.EnemyConstants.*;


public class EnemyManager {
    private Playing playing;
    private BufferedImage dummyImg;
    private BufferedImage frogImg;
    private BufferedImage[] crabImg;

    private Crab crabs;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
        addEnemy();
    }

    public void addEnemy() {
        crabs = new Crab(TILES_DEFAULT_SIZE + 50, TILES_DEFAULT_SIZE * 12 - 10);
    }

    public void update(int[][] lvData, Player player) {
        if (crabs.isActive()) crabs.update(lvData, player);
    }

    public void draw(Graphics g, int xLvOffset) {
        if (crabs.isActive()) {
            g.drawImage(crabImg[crabs.getEnemyState()], (int) crabs.getHitBox().x - xLvOffset - crabs.flipX(), (int) crabs.getHitBox().y, MONSTER_WIDTH * crabs.flipW(), MONSTER_HEIGHT, null);
            crabs.drawAttackBox(g, xLvOffset);
        }

    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        if (attackBox.intersects(crabs.getHitBox())) {
            crabs.hurt(10);
            return;
        }
    }

    public void loadEnemyImgs() {
        crabImg = new BufferedImage[9];
        crabImg[0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0);
        crabImg[1] = LoadSave.getSpriteAlas(LoadSave.CRAB_1);
        crabImg[2] = LoadSave.getSpriteAlas(LoadSave.CRAB_3);
        crabImg[4] = LoadSave.getSpriteAlas(LoadSave.CRAB_2);
    }
}
