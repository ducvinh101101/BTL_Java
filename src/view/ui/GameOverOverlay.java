package view.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import model.gamestates.Gamestate;
import model.gamestates.Playing;
import view.Main.Game;
import model.utilz.LoadSave;

import static model.utilz.Constants.UI.URMButtons.URM_SIZE;

public class GameOverOverlay {

    private Playing playing;
    private BufferedImage img;
    private int imgX, imgY, imgW, imgH;
    private UrmButton menu, play;
    public GameOverOverlay(Playing playing) {
        this.playing = playing;
        creatImg();
        creatButtons();
    }

    private void creatButtons() {
        int menuX = (int) (350 * Game.SCALE);
        int playX = (int) (436 * Game.SCALE);
        int y = (int) (220 * Game.SCALE);
        play = new UrmButton(playX,y,URM_SIZE, URM_SIZE, 0);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE,2);

    }

    private void creatImg() {
        img = LoadSave.getSpriteAlas(LoadSave.GAMEOVER_BACKGROUND);
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        imgX = Game.GAME_WIDTH / 2 - imgW / 2;
        imgY = (int) (55 * Game.SCALE);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(img,imgX,imgY,imgW,imgH, null);
        menu.draw(g);
        play.draw(g);

    }
    private boolean isIn(PauseButton b,MouseEvent e){
        return b.getBounds().contains(e.getX(),e.getY());
    }

    public void keyPressed(KeyEvent e) {

    }
    public void update(){
        playing.setCheckNextMap(0);
        menu.update();
        play.update();

    }
    public void mouseMoved(MouseEvent e) {
        play.setMouseOver(false);
        menu.setMouseOver(false);

        if (isIn(menu, e))
            menu.setMouseOver(true);
        else if (isIn(play, e))
            play.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
                playing.resetAll();
                playing.setGamState(Gamestate.MENU);
            }
        } else if (isIn(play, e))
            if (play.isMousePressed()){
                playing.resetAll();
                playing.getGame().getAudioPlayer().setLevelSong(playing.getLevelManager().getInnext());
            }


        menu.resetBools();
        play.resetBools();
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e))
            menu.setMousePressed(true);
        else if (isIn(play, e))
            play.setMousePressed(true);
    }
}
