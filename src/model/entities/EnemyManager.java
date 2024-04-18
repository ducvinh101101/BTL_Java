
package model.entities;

import model.gamestates.Playing;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static model.Game.TILES_DEFAULT_SIZE;
import static model.utilz.Constants.Directions.RIGHT;
import static model.utilz.Constants.EnemyConstants.*;


public class EnemyManager {
    private Playing playing;
    private BufferedImage[]  dummyImg;
    private BufferedImage[][] crabImg, crabImgLeft, crabImgRight;
    private BufferedImage[][] wandererImg, wandererImgLeft, wandererImgRight;
    private BufferedImage[][] deathImg, deathImgRight, deathImgLeft;
    private BufferedImage[][] samuraiImg, samuraiImgLeft, samuraiImgRight;
    private BufferedImage[][] tenguImg, tenguImgLeft, tenguImgRight;


    private ArrayList<Crab> crabs;
    private ArrayList<Dummy> dummies;
    private ArrayList<Reaper> reapers;
    private ArrayList<Samurai> samurais;
    private ArrayList<Tengu> tengus;
    private ArrayList<Wanderer> wanderers;

    private boolean spawnMonster = false;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        crabs = new ArrayList<>();
        dummies = new ArrayList<>();
        reapers = new ArrayList<>();
        samurais = new ArrayList<>();
        tengus = new ArrayList<>();
        wanderers = new ArrayList<>();
        loadImgs();

//        addEnemy();
    }
    public void loadImgs(){
        loadCrabImgsLeft();
        loadCrabImgsRight();
        loadDummyImgs();
        loadDeathImgsRight();
        loadDeathImgsLeft();
        loadSamuraiImgsRight();
        loadSamuraiImgsLeft();
        loadTenguImgsRight();
        loadTenguImgsLeft();
        loadWandererImgsRight();
        loadWandererImgsLeft();
    }
    public void addEnemyMap1() {
//        Crab newCrab = new Crab(TILES_DEFAULT_SIZE* 12 + 200 , TILES_DEFAULT_SIZE * 12 - 100);
//        crabs.add(newCrab);
        dummies.add(new Dummy(TILES_DEFAULT_SIZE* 12 + 200 , TILES_DEFAULT_SIZE * 22 - 100)); // 22 la o duoi roi, 12 cx dep
        dummies.add(new Dummy(TILES_DEFAULT_SIZE* 12 + 380 , TILES_DEFAULT_SIZE * 18 - 100)); // 22 la o duoi roi
        wanderers.add(new Wanderer(TILES_DEFAULT_SIZE * 60, TILES_DEFAULT_SIZE * 21 - 100));
    }
    public void addEnemyMap2() {
        crabs.add(new Crab(TILES_DEFAULT_SIZE* 5 + 100 , TILES_DEFAULT_SIZE * 12 + 100));
        reapers.add(new Reaper(TILES_DEFAULT_SIZE * 22  , TILES_DEFAULT_SIZE * 14));
        reapers.add(new Reaper(TILES_DEFAULT_SIZE * 25  , TILES_DEFAULT_SIZE * 3+100));
        // samurais.add(new Samurai(TILES_DEFAULT_SIZE* 30 + 100 , TILES_DEFAULT_SIZE * 12 + 200));
        tengus.add(new Tengu(TILES_DEFAULT_SIZE* 32 + 100 , TILES_DEFAULT_SIZE * 21 + 9 ));
    }
    public void addEnemyMap3(){

    }
    public void clearAll(){
        dummies.clear();
        wanderers.clear();
        crabs.clear();
        reapers.clear();
        samurais.clear();
        tengus.clear();
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
        for(Crab X : crabs) if(X.alive) X.update(lvData, player);
        for(Dummy X : dummies) if(X.alive) X.update(lvData, player);
        for (Reaper X : reapers)  if(X.alive) X.update(lvData, player);
        for (Samurai X : samurais) if(X.alive) X.update(lvData, player);
        for (Tengu X : tengus) if(X.alive) X.update(lvData, player);
        for (Wanderer X : wanderers)  if(X.alive) X.update(lvData, player);
    }

    public void draw(Graphics g, int xLvOffset, int yLevelOffset) {
        for (Crab X : crabs) {
            if (X.isAlive()) {
                crabImg = (X.getWalkDir() == RIGHT) ? crabImgRight : crabImgLeft;
                g.drawImage(crabImg[X.getEnemyState()][X.getAnimationIndex()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH, MONSTER_HEIGHT, null);
//                X.drawHitBox(g, xLvOffset, yLevelOffset);
//                X.drawAttackBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
        for (Wanderer X : wanderers){
            if (X.isAlive()) {
                wandererImg = (X.getWalkDir() == RIGHT) ? wandererImgRight : wandererImgLeft;
                g.drawImage(wandererImg[X.getEnemyState()][X.getAnimationIndex()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH, MONSTER_HEIGHT, null);
//                X.drawHitBox(g, xLvOffset, yLevelOffset);
//                X.drawAttackBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
        for (Dummy X : dummies){
            if(X.isAlive()){
                g.drawImage(dummyImg[X.getEnemyState()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH, MONSTER_HEIGHT + 8, null);
//                X.drawHitBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
        for (Reaper X : reapers) {
            if (X.isAlive()) {
                deathImg = (X.getWalkDir() == RIGHT) ? deathImgRight : deathImgLeft;
                g.drawImage(deathImg[X.getEnemyState()][X.getAnimationIndex()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH*2, MONSTER_HEIGHT*2, null);
//                X.drawHitBox(g, xLvOffset, yLevelOffset);
//                X.drawAttackBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
        for(Samurai X : samurais){
            if (X.isAlive()) {
                samuraiImg = (X.getWalkDir() == RIGHT) ? samuraiImgRight : samuraiImgLeft;
                g.drawImage(samuraiImg[X.getEnemyState()][X.getAnimationIndex()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH*2, MONSTER_HEIGHT*2, null);
//                X.drawHitBox(g, xLvOffset, yLevelOffset);
//                X.drawAttackBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
        for(Tengu X : tengus){
            if (X.isAlive()) {
                tenguImg = (X.getWalkDir() == RIGHT) ? tenguImgRight : tenguImgLeft;
                g.drawImage(tenguImg[X.getEnemyState()][X.getAnimationIndex()], (int) X.getHitBox().x - xLvOffset, (int) X.getHitBox().y - yLevelOffset, MONSTER_WIDTH*3, MONSTER_HEIGHT*3, null);
//                X.drawHitBox(g, xLvOffset, yLevelOffset);
//                X.drawAttackBox(g, xLvOffset, yLevelOffset);
                X.drawHP(g, xLvOffset, yLevelOffset);
            }
        }
    }

    // player ATT monster
    public void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        for (Crab X : crabs) {
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(player.getDamage(), player);
                break;
            }
        }
        for (Dummy X : dummies){
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(player.getDamage(), player);
                break;
            }
        }
        for (Reaper X : reapers){
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(player.getDamage(), player);
                break;
            }
        }
        for (Samurai X : samurais){
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(player.getDamage(), player);
                break;
            }
        }
        for (Tengu X : tengus){
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(player.getDamage(), player);
                break;
            }
        }
        for (Wanderer X : wanderers){
            if (X.isAlive() && attackBox.intersects(X.getHitBox())) {
                X.hurt(player.getDamage(), player);
                break;
            }
        }
    }
    public void loadCrabImgsRight() {
        crabImgRight = new BufferedImage[4][2];
        crabImgRight[0][0] = LoadSave.getImage(LoadSave.CRAB_0);
        crabImgRight[1][0] = LoadSave.getImage(LoadSave.CRAB_0);
        crabImgRight[1][1] = LoadSave.getImage(LoadSave.CRAB_1);
        crabImgRight[2][0] = LoadSave.getImage(LoadSave.CRAB_0);
        crabImgRight[2][1] = LoadSave.getImage(LoadSave.CRAB_3);
        crabImgRight[3][0] = LoadSave.getImage(LoadSave.CRAB_2);
    }

    public void loadCrabImgsLeft() {
        crabImgLeft = new BufferedImage[4][2];
        crabImgLeft[0][0] = LoadSave.getImage(LoadSave.CRAB_0_LEFT);
        crabImgLeft[1][0] = LoadSave.getImage(LoadSave.CRAB_0_LEFT);
        crabImgLeft[1][1] = LoadSave.getImage(LoadSave.CRAB_1_LEFT);
        crabImgLeft[2][0] = LoadSave.getImage(LoadSave.CRAB_0_LEFT);
        crabImgLeft[2][1] = LoadSave.getImage(LoadSave.CRAB_3_LEFT);
        crabImgLeft[3][0] = LoadSave.getImage(LoadSave.CRAB_2_LEFT);
    }
    public void loadDummyImgs() {
        dummyImg = new BufferedImage[4];
        dummyImg[0] = LoadSave.getImage(LoadSave.DUMMY_1);
        dummyImg[1] = LoadSave.getImage(LoadSave.DUMMY_1);
        dummyImg[3] = LoadSave.getImage(LoadSave.DUMMY_2);
    }
    public void loadDeathImgsRight(){
        deathImgRight = new BufferedImage[4][5];
        deathImgRight[0][0] = LoadSave.getImage(LoadSave.REAPER_1);
        deathImgRight[0][1] = LoadSave.getImage(LoadSave.REAPER_2);
        deathImgRight[0][2] = LoadSave.getImage(LoadSave.REAPER_3);
        deathImgRight[1][0] = LoadSave.getImage(LoadSave.REAPER_4);
        deathImgRight[1][1] = LoadSave.getImage(LoadSave.REAPER_5);
        deathImgRight[2][0] = LoadSave.getImage(LoadSave.REAPER_6);
        deathImgRight[2][1] = LoadSave.getImage(LoadSave.REAPER_7);
        deathImgRight[3][0] = LoadSave.getImage(LoadSave.REAPER_8);
    }
    public void loadDeathImgsLeft(){
        deathImgLeft = new BufferedImage[4][5];
        deathImgLeft[0][0] = LoadSave.getImage(LoadSave.REAPER_1_LEFT);
        deathImgLeft[0][1] = LoadSave.getImage(LoadSave.REAPER_2_LEFT);
        deathImgLeft[0][2] = LoadSave.getImage(LoadSave.REAPER_3_LEFT);
        deathImgLeft[1][0] = LoadSave.getImage(LoadSave.REAPER_4_LEFT);
        deathImgLeft[1][1] = LoadSave.getImage(LoadSave.REAPER_5_LEFT);
        deathImgLeft[2][0] = LoadSave.getImage(LoadSave.REAPER_6_LEFT);
        deathImgLeft[2][1] = LoadSave.getImage(LoadSave.REAPER_7_LEFT);
        deathImgLeft[3][0] = LoadSave.getImage(LoadSave.REAPER_8_LEFT);
    }
    public void loadSamuraiImgsRight(){
        samuraiImgRight = new BufferedImage[4][2];
        samuraiImgRight[0][0] = LoadSave.getImage(LoadSave.SAMURAI_1);
        samuraiImgRight[1][0] = LoadSave.getImage(LoadSave.SAMURAI_1);
        samuraiImgRight[1][1] = LoadSave.getImage(LoadSave.SAMURAI_2);
        samuraiImgRight[2][0] = LoadSave.getImage(LoadSave.SAMURAI_2);
        samuraiImgRight[2][1] = LoadSave.getImage(LoadSave.SAMURAI_4);
        samuraiImgRight[3][0] = LoadSave.getImage(LoadSave.SAMURAI_3);

    }
    public void loadSamuraiImgsLeft(){
        samuraiImgLeft = new BufferedImage[4][2];
        samuraiImgLeft[0][0] = LoadSave.getImage(LoadSave.SAMURAI_1_LEFT);
        samuraiImgLeft[1][0] = LoadSave.getImage(LoadSave.SAMURAI_1_LEFT);
        samuraiImgLeft[1][1] = LoadSave.getImage(LoadSave.SAMURAI_2_LEFT);
        samuraiImgLeft[2][0] = LoadSave.getImage(LoadSave.SAMURAI_2_LEFT);
        samuraiImgLeft[2][1] = LoadSave.getImage(LoadSave.SAMURAI_4_LEFT);
        samuraiImgLeft[3][0] = LoadSave.getImage(LoadSave.SAMURAI_3_LEFT);

    }
    public void loadTenguImgsRight(){
        tenguImgRight = new BufferedImage[5][8];
        BufferedImage tmp = LoadSave.getImage(LoadSave.TENGU_1);
        for(int i=0; i<6; i++){
            tenguImgRight[0][i] = tmp.getSubimage(i * 128, 0, 128, 128);
        }
        BufferedImage tmp1 = LoadSave.getImage(LoadSave.TENGU_2);
        for(int i=0; i<8; i++){
            tenguImgRight[1][i] = tmp1.getSubimage(i * 128, 0, 128, 128);
        }
        BufferedImage tmp2 = LoadSave.getImage(LoadSave.TENGU_3);
        for(int i=0; i<6; i++){
            tenguImgRight[2][i] = tmp2.getSubimage(i * 128, 0, 128, 128);
        }
    }
    public void loadTenguImgsLeft(){
        tenguImgLeft = new BufferedImage[5][8];
        BufferedImage tmp = LoadSave.getImage(LoadSave.TENGU_1_LEFT);
        for(int i=0; i<6; i++){
            tenguImgLeft[0][i] = tmp.getSubimage(i * 128, 0, 128, 128);
        }
        BufferedImage tmp1 = LoadSave.getImage(LoadSave.TENGU_2_LEFT);
        for(int i=0; i<8; i++){
            tenguImgLeft[1][i] = tmp1.getSubimage(i * 128, 0, 128, 128);
        }
        BufferedImage tmp2 = LoadSave.getImage(LoadSave.TENGU_3_LEFT);
        for(int i=0; i<6; i++){
            tenguImgLeft[2][i] = tmp2.getSubimage(i * 128, 0, 128, 128);
        }
    }

    public void loadWandererImgsRight(){
        wandererImgRight = new BufferedImage[5][3];
        wandererImgRight[0][0] = LoadSave.getImage(LoadSave.WANDERER_1);
        wandererImgRight[1][0] = LoadSave.getImage(LoadSave.WANDERER_1);
        wandererImgRight[1][1] = LoadSave.getImage(LoadSave.WANDERER_2);
        wandererImgRight[2][0] = LoadSave.getImage(LoadSave.WANDERER_2);
        wandererImgRight[2][1] = LoadSave.getImage(LoadSave.WANDERER_4);
        wandererImgRight[3][0] = LoadSave.getImage(LoadSave.WANDERER_3);
    }
    public void loadWandererImgsLeft(){
        wandererImgLeft = new BufferedImage[5][3];
        wandererImgLeft[0][0] = LoadSave.getImage(LoadSave.WANDERER_1_LEFT);
        wandererImgLeft[1][0] = LoadSave.getImage(LoadSave.WANDERER_1_LEFT);
        wandererImgLeft[1][1] = LoadSave.getImage(LoadSave.WANDERER_2_LEFT);
        wandererImgLeft[2][0] = LoadSave.getImage(LoadSave.WANDERER_2_LEFT);
        wandererImgLeft[2][1] = LoadSave.getImage(LoadSave.WANDERER_4_LEFT);
        wandererImgLeft[3][0] = LoadSave.getImage(LoadSave.WANDERER_3_LEFT);
    }
}