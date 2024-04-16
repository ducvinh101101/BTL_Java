
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
    private BufferedImage frogImg;
    private BufferedImage[][] crabImg, crabImgLeft, crabImgRight, deathImg, deathImgRight, deathImgLeft;
    private BufferedImage[]  dummyImg;
    private ArrayList<Crab> crabs;
    private ArrayList<Dummy> dummies;
    private ArrayList<Reaper> reapers;
    private boolean spawnMonster = false;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        crabs = new ArrayList<>();
        dummies = new ArrayList<>();
        reapers = new ArrayList<>();


        loadCrabImgsLeft();
        loadCrabImgsRight();
        loadDummyImgs();
        loadDeathImgsRight();
        loadDeathImgsLeft();
//        addEnemy();
    }

    public void addEnemyMap1() {
//        Crab newCrab = new Crab(TILES_DEFAULT_SIZE* 12 + 200 , TILES_DEFAULT_SIZE * 12 - 100);
//        crabs.add(newCrab);
        dummies.add(new Dummy(TILES_DEFAULT_SIZE* 12 + 200 , TILES_DEFAULT_SIZE * 12 - 100));
    }
    public void addEnemyMap2() {
        crabs.add(new Crab(TILES_DEFAULT_SIZE* 15 + 200 , TILES_DEFAULT_SIZE * 12 - 100));
        reapers.add(new Reaper(TILES_DEFAULT_SIZE* 12 + 100 , TILES_DEFAULT_SIZE * 12 - 50));
    }
    public void update(int[][] lvData, Player player) {
//        ArrayList<Crab> tempCrabs = new ArrayList<>();
//        for (Crab c : crabs) {
//            if (c.isAlive()) c.update(lvData, player);
//            else {
//                tempCrabs.add(c);
//                spawnMonster = true;
//            }
//        }
//        crabs.removeAll(tempCrabs);
//        if (spawnMonster) {
//            addEnemy(12,12);
//            spawnMonster = false;
//        }
        for(Crab X : crabs){
            if(X.alive) X.update(lvData, player);
        }
        for(Dummy X : dummies){
            if(X.alive) X.update(lvData, player);
        }
        for (Reaper X : reapers)  if(X.alive) X.update(lvData, player);
    }

    public void draw(Graphics g, int xLvOffset, int yLevelOffset) {
        for (Crab X : crabs) {
            if (X.isAlive()) {
                crabImg = (X.getWalkDir() == RIGHT) ? crabImgRight : crabImgLeft;
                g.drawImage(crabImg[X.getEnemyState()][X.getAnimationIndex()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH, MONSTER_HEIGHT, null);
                X.drawHitBox(g, xLvOffset, yLevelOffset);
                X.drawAttackBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
        for (Dummy X : dummies){
            if(X.isAlive()){
                g.drawImage(dummyImg[X.getEnemyState()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH, MONSTER_HEIGHT, null);
                X.drawHitBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
        for (Reaper X : reapers) {
            if (X.isAlive()) {
                deathImg = (X.getWalkDir() == RIGHT) ? deathImgRight : deathImgLeft;
                g.drawImage(deathImg[X.getEnemyState()][X.getAnimationIndex()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH*2, MONSTER_HEIGHT*2, null);
                X.drawHitBox(g, xLvOffset, yLevelOffset);
                X.drawAttackBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
    }

    // player ATT monster
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Crab X : crabs) {
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(10);
                break;
            }
        }
        for (Dummy X : dummies){
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(10);
                break;
            }
        }
        for (Reaper X : reapers){
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(10);
                break;
            }
        }
    }
    public void loadCrabImgsRight() {
        crabImgRight = new BufferedImage[4][2];
        crabImgRight[0][0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0);
        crabImgRight[1][0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0);
        crabImgRight[1][1] = LoadSave.getSpriteAlas(LoadSave.CRAB_1);
        crabImgRight[2][0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0);
        crabImgRight[2][1] = LoadSave.getSpriteAlas(LoadSave.CRAB_3);
        crabImgRight[3][0] = LoadSave.getSpriteAlas(LoadSave.CRAB_2);
    }

    public void loadCrabImgsLeft() {
        crabImgLeft = new BufferedImage[4][2];
        crabImgLeft[0][0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0_LEFT);
        crabImgLeft[1][0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0_LEFT);
        crabImgLeft[1][1] = LoadSave.getSpriteAlas(LoadSave.CRAB_1_LEFT);
        crabImgLeft[2][0] = LoadSave.getSpriteAlas(LoadSave.CRAB_0_LEFT);
        crabImgLeft[2][1] = LoadSave.getSpriteAlas(LoadSave.CRAB_3_LEFT);
        crabImgLeft[3][0] = LoadSave.getSpriteAlas(LoadSave.CRAB_2_LEFT);
    }
    public void loadDummyImgs() {
        dummyImg = new BufferedImage[4];
        dummyImg[0] = LoadSave.getSpriteAlas(LoadSave.DUMMY_1);
        dummyImg[1] = LoadSave.getSpriteAlas(LoadSave.DUMMY_1);
        dummyImg[3] = LoadSave.getSpriteAlas(LoadSave.DUMMY_2);
    }
    public void loadDeathImgsRight(){
        deathImgRight = new BufferedImage[4][5];
        deathImgRight[0][0] = LoadSave.getSpriteAlas(LoadSave.REAPER_1);
        deathImgRight[0][1] = LoadSave.getSpriteAlas(LoadSave.REAPER_2);
        deathImgRight[0][2] = LoadSave.getSpriteAlas(LoadSave.REAPER_3);
        deathImgRight[1][0] = LoadSave.getSpriteAlas(LoadSave.REAPER_4);
        deathImgRight[1][1] = LoadSave.getSpriteAlas(LoadSave.REAPER_5);
        deathImgRight[2][0] = LoadSave.getSpriteAlas(LoadSave.REAPER_6);
        deathImgRight[2][1] = LoadSave.getSpriteAlas(LoadSave.REAPER_7);
        deathImgRight[3][0] = LoadSave.getSpriteAlas(LoadSave.REAPER_8);
    }
    public void loadDeathImgsLeft(){
        deathImgLeft = new BufferedImage[4][5];
        deathImgLeft[0][0] = LoadSave.getSpriteAlas(LoadSave.REAPER_1_LEFT);
        deathImgLeft[0][1] = LoadSave.getSpriteAlas(LoadSave.REAPER_2_LEFT);
        deathImgLeft[0][2] = LoadSave.getSpriteAlas(LoadSave.REAPER_3_LEFT);
        deathImgLeft[1][0] = LoadSave.getSpriteAlas(LoadSave.REAPER_4_LEFT);
        deathImgLeft[1][1] = LoadSave.getSpriteAlas(LoadSave.REAPER_5_LEFT);
        deathImgLeft[2][0] = LoadSave.getSpriteAlas(LoadSave.REAPER_6_LEFT);
        deathImgLeft[2][1] = LoadSave.getSpriteAlas(LoadSave.REAPER_7_LEFT);
        deathImgLeft[3][0] = LoadSave.getSpriteAlas(LoadSave.REAPER_8_LEFT);
    }
    public ArrayList<Crab> getCrabs() {
        return crabs;
    }
    public ArrayList<Dummy> getDummies() {
        return dummies;
    }
}