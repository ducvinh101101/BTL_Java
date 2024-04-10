package levels;

import Main.Game;
import entities.Player;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Main.Game.*;

public class Background {
    public void draw(Graphics g){
        g.drawImage(LoadSave.getPlayerAlas(LoadSave.BACKGROUND_MAP_1),0,0, GAME_WIDTH, GAME_HEIGHT,null);
    }
}
