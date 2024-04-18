package model.maps;

import view.Main.Game;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static view.Main.Game.*;

public class MapManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Map[] map = new Map[3];
    private int innext = 0;


    public MapManager(Game game) {
        this.game = game;
        map[0] = new Map(LoadSave.getLevelData());
        map[1] = new Map(LoadSave.getLevelData2());
        map[2] = new Map(LoadSave.getLevelData3());
        importOutsideSprite();
    }

    public void nextMap(boolean kt) {
        if (kt) {
            innext += 1;
            kt = false;
        }
        if(innext >= map.length){
            innext = 0;
        }

    }

    public void importOutsideSprite() {
        if (innext == 0) {
            BufferedImage img = LoadSave.getImage(LoadSave.TILESET_1);
            levelSprite = new BufferedImage[141];
            for (int i = 0; i < 141; i++) {
                int index1 = i;
                levelSprite[index1] = img.getSubimage(0, i * 72, 72, 72);
            }
        } else if (innext == 1) {
            BufferedImage img = LoadSave.getImage(LoadSave.TILESET_2);
            levelSprite = new BufferedImage[120];
            for (int i = 0; i < 120; i++) {
                int index2 = i;
                levelSprite[index2] = img.getSubimage(0, i * 72, 72, 72);
            }
        } else {
            BufferedImage img = LoadSave.getImage(LoadSave.TILESET_3);
            levelSprite = new BufferedImage[143];
            for (int i = 0; i < 143; i++) {
                int index3 = i;
                levelSprite[index3] = img.getSubimage(0, i * 72, 72, 72);
            }
        }
    }

    public void draw(Graphics g, int xLvOffset, int yLvOffset) {
        if (innext == 0) {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    int index = map[innext].getSpriteIndex(i, j);
                    g.drawImage(levelSprite[index], Game.TILES_SIZE * j - xLvOffset, Game.TILES_SIZE * i - yLvOffset, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }

        } else if (innext == 1) {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    int index = map[innext].getSpriteIndex(i, j);
                    g.drawImage(levelSprite[index], Game.TILES_SIZE * j - xLvOffset , Game.TILES_SIZE * i- yLvOffset, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        } else {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    int index = map[innext].getSpriteIndex(i, j);
                    g.drawImage(levelSprite[index], Game.TILES_SIZE * j - xLvOffset, Game.TILES_SIZE * i- yLvOffset, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        }

    }

    public void update() {

    }

    public Map getCurrenLevel() {
        return map[innext];
    }

    public Map[] getLevel() {
        return map;
    }

    public void setLevel(Map[] map) {
        this.map = map;
    }

    public int getInnext() {
        return innext;
    }

    public void setInnext(int innext) {
        this.innext = innext;
    }
}
