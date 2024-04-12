package levels;

import Main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Main.Game.*;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprite();
        levelOne = new Level(LoadSave.getLevelData());
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
            for (int j = 0; j < levelOne.getlvlData()[0].length; j++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * j - xLvOffset, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrenLevel() {
        return levelOne;
    }
}
