package model.entities;

import model.gamestates.Playing;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.Game.TILES_DEFAULT_SIZE;
import static model.utilz.Constants.EnemyConstants.MONSTER_HEIGHT;
import static model.utilz.Constants.EnemyConstants.MONSTER_WIDTH;

public class NpcManager {
    Playing playing;
    private NPC npc1, npc2;
    private BufferedImage NpcImg, NpcImg2;
    public NpcManager(Playing playing){
        this.playing = playing;
        loadImg();
    }
    private int checkMap = 0;
    public void npcMap1(){
        checkMap = 1;
        npc1 = new NPC(TILES_DEFAULT_SIZE* 7  , TILES_DEFAULT_SIZE * 16 + 20);
    }
    public void npcMap2(){
        checkMap = 2;
        //npc2 = new NPC(TILES_DEFAULT_SIZE* 3  , TILES_DEFAULT_SIZE * 18);
    }
    public void npcMap3(){
        checkMap = 3;
        npc1 = new NPC(TILES_DEFAULT_SIZE* 10  , TILES_DEFAULT_SIZE * 20 - 16);
    }
    public void update(){

    }
    public void draw(Graphics g, int xLvOffset, int yLevelOffset){
        if(checkMap == 1 || checkMap == 3) g.drawImage(NpcImg, (int) (npc1.getHitBox().x - xLvOffset), (int) (npc1.getHitBox().y - yLevelOffset), MONSTER_WIDTH+2, MONSTER_HEIGHT+18 , null);
        else g.drawImage(NpcImg2,  (720 - xLvOffset), (710 - yLevelOffset), MONSTER_WIDTH*2+20, MONSTER_HEIGHT*2 , null);
    }
    public void loadImg(){
        NpcImg = LoadSave.getImage(LoadSave.NPC1);
        NpcImg2 = LoadSave.getImage(LoadSave.NPC2);
    }
}
