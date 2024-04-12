package levels;

import utilz.LoadSave;

import java.awt.*;

import static Main.Game.*;

public class Background {
    public void draw(Graphics g) {
        g.drawImage(LoadSave.getSpriteAlas(LoadSave.BACKGROUND_MAP_1), 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    }
}
