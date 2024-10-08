package view;

import model.utilz.LoadSave;

import java.awt.*;

import static model.Game.*;

public class Background {
    public void draw(Graphics g) {
        g.drawImage(LoadSave.getImage(LoadSave.BACKGROUND_MAP_1), 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    }
    public void drawBackgroundPauseGame(Graphics g){
        g.drawImage(LoadSave.getImage(LoadSave.BACKGROUND_PAUSE_GAME),0,0,GAME_WIDTH,GAME_HEIGHT,null);
    }
    public void drawBackgroundMap2(Graphics g){
        g.drawImage(LoadSave.getImage(LoadSave.BACKGROUND_MAP_2),0,0,GAME_WIDTH,GAME_HEIGHT,null);
    }
    public void drawBackgroundMap1(Graphics g){
        g.drawImage(LoadSave.getImage(LoadSave.BACKGROUND_MAP_11),0,0,GAME_WIDTH,GAME_HEIGHT,null);
    }
}
