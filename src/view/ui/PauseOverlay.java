package view.ui;

import view.Main.Game;
import model.gamestates.Gamestate;
import model.gamestates.Playing;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static model.utilz.Constants.UI.URMButtons.*;

public class PauseOverlay {
    private BufferedImage pauseBackground;
    private int bgX,bgY,bgWidth,bgHeight;
    private AudioOptions audioOptions;
    private UrmButton menuButton, replayButton, unPauseButton;

    private Playing playing;



    public PauseOverlay(Playing playing){
        this.playing = playing;
        loadBackground();
        audioOptions = playing.getGame().getAudioOptions();
        createUrmButton();
    }




    private void createUrmButton(){
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (320 * Game.SCALE);
        menuButton = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayButton = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unPauseButton = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    private void loadBackground() {
        pauseBackground = LoadSave.getImage(LoadSave.PAUSE_BACKGROUND);
        bgWidth =(int) (pauseBackground.getWidth() / 1.5 * Game.SCALE);
        bgHeight = (int)(pauseBackground.getHeight() / 1.5 * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgWidth / 2;
        bgY = (int)(25 * Game.SCALE);
    }

    public void update(){

        menuButton.update();
        replayButton.update();
        unPauseButton.update();
        audioOptions.update();

    }
    public void draw(Graphics g){
        g.drawImage(pauseBackground,bgX,bgY,bgWidth,bgHeight,null);

        menuButton.draw(g);
        replayButton.draw(g);
        unPauseButton.draw(g);

        audioOptions.draw(g);
    }

    public void mouseDragged(MouseEvent e){
        audioOptions.mouseDragged(e);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

        if (isIn(e,menuButton)) {
            menuButton.setMousePressed(true);
        }
        else if (isIn(e,replayButton)) {
            replayButton.setMousePressed(true);
        }
        else if (isIn(e,unPauseButton)){
            unPauseButton.setMousePressed(true);
        }
        else {
            audioOptions.mousePressed(e);
        }

    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e,menuButton)){
            if(menuButton.isMousePressed()){
                playing.setGamState(Gamestate.MENU);
                playing.unPauseGame();
            }
        }
        else if (isIn(e,replayButton)){
            if(replayButton.isMousePressed()){
                playing.setCheckNextMap(0);
                playing.resetAll();
            }
        }
        else if (isIn(e,unPauseButton)){
            if(unPauseButton.isMousePressed()){
                playing.unPauseGame();
            }
        }
        else {
            audioOptions.mouseReleased(e);
        }

        menuButton.resetBools();
        replayButton.resetBools();
        unPauseButton.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        menuButton.setMouseOver(false);
        replayButton.setMouseOver(false);
        unPauseButton.setMouseOver(false);
        if (isIn(e,menuButton)){
            menuButton.setMouseOver(true);
        }
        else if (isIn(e,replayButton)){
            replayButton.setMouseOver(true);
        }
        else if (isIn(e,unPauseButton)){
            unPauseButton.setMouseOver(true);
        }
        else {
            audioOptions.mouseMoved(e);
        }
    }
    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(),e.getY());
    }
}
