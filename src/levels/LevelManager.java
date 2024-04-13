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
        importOutsideSprite();
        level[0] = new Level(LoadSave.getLevelData());
        level[1] = new Level(LoadSave.getLevelData2());
        level[2] = new Level(LoadSave.getLevelData3());
    }
    public void nextMap(boolean kt){
        if(kt == true){
            innext +=1;
        }
        if(innext>level.length){
            innext = 0;
        }
    }
    private void importOutsideSprite() {
        BufferedImage img = LoadSave.getSpriteAlas(LoadSave.TILESET);
        levelSprite = new BufferedImage[81];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int index = i * 9 + j;
                levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g, int xLvOffset) {
        int tileSize = Game.TILES_SIZE;
        for (int i = 0; i < TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < level[innext].getlvlData()[0].length; j++) {
                int index = level[innext].getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * j - xLvOffset, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE, null);
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
