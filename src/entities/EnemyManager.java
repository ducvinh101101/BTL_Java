
package entities;

import gamestates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Main.Game.TILES_DEFAULT_SIZE;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;


public class EnemyManager {
    private Playing playing;
    private BufferedImage dummyImg;
    private BufferedImage frogImg;
    private BufferedImage[] crabImg, crabImgLeft, crabImgRight;
    private ArrayList<Crab> crabs;
    private boolean spawnMonster = false;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        crabs = new ArrayList<>();
        loadEnemyImgsLeft();
        loadEnemyImgsRight();
        addEnemy();
    }

    public void addEnemy() {
        Crab newCrab = new Crab(TILES_DEFAULT_SIZE * 12, TILES_DEFAULT_SIZE * 12 - 10);
        crabs.add(newCrab);
    }

    public void update(int[][] lvData, Player player) {
        ArrayList<Crab> tempCrabs = new ArrayList<>();
        for (Crab c : crabs) {
            if (c.isAlive()) c.update(lvData, player);
            else {
                tempCrabs.add(c);
                spawnMonster = true;
            }
        }
        crabs.removeAll(tempCrabs);
        if (spawnMonster) {
            addEnemy();
            spawnMonster = false;
        }
    }

    public void draw(Graphics g, int xLvOffset) {
        for (Crab crab : crabs) {
            if (crab.isAlive()) {
                BufferedImage[] crabImg = (crab.getWalkDir() == RIGHT) ? crabImgRight : crabImgLeft;
                g.drawImage(crabImg[crab.getEnemyState()], (int) crab.getHitBox().x - xLvOffset, (int) crab.getHitBox().y, MONSTER_WIDTH, MONSTER_HEIGHT, null);
                crab.drawHitBox(g, xLvOffset);
                crab.drawAttackBox(g, xLvOffset);
            }
        }
    }

    // player ATT monster
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Crab crab : crabs) {
            if (crab.isAlive() && attackBox.intersects(crab.getHitBox())) {
                crab.hurt(1);
                break;
            }
        }
    }

    public void loadEnemyImgsRight() {
        crabImgRight = new BufferedImage[9];
        crabImgRight[0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0);
        crabImgRight[1] = LoadSave.getSpriteAlas(LoadSave.CRAB_1);
        crabImgRight[2] = LoadSave.getSpriteAlas(LoadSave.CRAB_3);
        crabImgRight[4] = LoadSave.getSpriteAlas(LoadSave.CRAB_2);
    }

    public void loadEnemyImgsLeft() {
        crabImgLeft = new BufferedImage[9];
        crabImgLeft[0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0_LEFT);
        crabImgLeft[1] = LoadSave.getSpriteAlas(LoadSave.CRAB_1_LEFT);
        crabImgLeft[2] = LoadSave.getSpriteAlas(LoadSave.CRAB_3_LEFT);
        crabImgLeft[4] = LoadSave.getSpriteAlas(LoadSave.CRAB_2_LEFT);
    }
}