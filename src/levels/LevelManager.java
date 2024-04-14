package levels;

import Main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Main.Game.*;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level[] level = new Level[3];
    private int innext = 0;


    public LevelManager(Game game) {
        this.game = game;
        level[0] = new Level(LoadSave.getLevelData());
        level[1] = new Level(LoadSave.getLevelData2());
        level[2] = new Level(LoadSave.getLevelData3());
        importOutsideSprite();
    }
    public void nextMap(boolean kt){
        if(kt == true){
            innext +=1;
        }
        if(innext>=level.length){
            innext = 0;
        }
    }
    public void importOutsideSprite() {
        if(innext==0){
            BufferedImage img = LoadSave.getSpriteAlas(LoadSave.TILESET);
            levelSprite = new BufferedImage[81];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int index = i * 9 + j;
                    levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);
                }
            }
        }
        else if (innext == 1) {
            BufferedImage img = LoadSave.getSpriteAlas(LoadSave.TILESET_2);
            levelSprite = new BufferedImage[143];
            for (int i = 0; i < 143; i++) {
                int index2 = i;
                levelSprite[index2] = img.getSubimage(0, i*72, 72, 72);
            }
        }

        else  {
            BufferedImage img = LoadSave.getSpriteAlas(LoadSave.TILESET_3);
            levelSprite = new BufferedImage[120];
            for (int i = 0; i < 120; i++) {
                int index3 = i;
                levelSprite[index3] = img.getSubimage( 0, i*72, 72, 72);
            }
        }
    }

    public void draw(Graphics g, int xLvOffset) {
        if(innext==0){
            int tileSize = Game.TILES_SIZE;
            for (int i = 0; i < TILES_IN_HEIGHT; i++) {
                for (int j = 0; j < level[innext].getlvlData()[0].length; j++) {
                    int index = level[innext].getSpriteIndex(i, j);
                    g.drawImage(levelSprite[index], Game.TILES_SIZE * j - xLvOffset, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        }
        else if (innext ==1){
            int tileSize = Game.TILES_SIZE;
            for (int i = 0; i < TILES_IN_HEIGHT; i++) {
                for (int j = 0; j < level[innext].getlvlData()[0].length; j++) {
                    int index = level[innext].getSpriteIndex(i, j);
                    g.drawImage(levelSprite[index], Game.TILES_SIZE * j - xLvOffset, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        }
        else {
            int tileSize = Game.TILES_SIZE;
            for (int i = 0; i < TILES_IN_HEIGHT; i++) {
                for (int j = 0; j < level[innext].getlvlData()[0].length; j++) {
                    int index = level[innext].getSpriteIndex(i, j);
                    g.drawImage(levelSprite[index], Game.TILES_SIZE * j - xLvOffset, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        }

    }

    public void update() {

    }

    public Level getCurrenLevel() {
        return level[innext];
    }

    public Level[] getLevel() {
        return level;
    }

    public void setLevel(Level[] level) {
        this.level = level;
    }

    public int getInnext() {
        return innext;
    }

    public void setInnext(int innext) {
        this.innext = innext;
    }
}
