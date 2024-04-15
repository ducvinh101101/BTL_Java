package ui;

import Main.Game;
import gamestates.Gamestate;
import gamestates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButton.*;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;

public class PauseOverplay {
    private BufferedImage pauseBackground;
    private int bgX,bgY,bgWidth,bgHeight;
    private SoundButton musicButton, fsxButton;
    private UrmButton menuButton, replayButton, unPauseButton;
    private VolumeButton volumeButton;
    private Playing playing;



    public PauseOverplay(Playing playing){
        this.playing = playing;
        loadBackground();
        createSoundButton();
        createUrmButton();
        createVolumeButton();
    }

    private void createVolumeButton() {
        int volumeX = (int)(309 * Game.SCALE);
        int volumeY = (int)(278 * Game.SCALE);
        volumeButton = new VolumeButton(volumeX,volumeY,SLIDER_WIDTH,VOLUME_HEIGHT);
    }

    private void createSoundButton() {
        int soundX = (int)(450 * Game.SCALE);
        int musicY = (int)(140 * Game.SCALE);
        int fsxY = (int)(186 * Game.SCALE);
        musicButton = new SoundButton(soundX,musicY,SOUND_SIZE,SOUND_SIZE);
        fsxButton = new SoundButton(soundX,fsxY,SOUND_SIZE,SOUND_SIZE);
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
        pauseBackground = LoadSave.getSpriteAlas(LoadSave.PAUSE_BACKGROUND);
        bgWidth =(int) (pauseBackground.getWidth() / 1.5 * Game.SCALE);
        bgHeight = (int)(pauseBackground.getHeight() / 1.5 * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgWidth / 2;
        bgY = (int)(25 * Game.SCALE);
    }

    public void update(){
        musicButton.update();
        fsxButton.update();
        menuButton.update();
        replayButton.update();
        unPauseButton.update();
        volumeButton.update();
    }
    public void draw(Graphics g){
        g.drawImage(pauseBackground,bgX,bgY,bgWidth,bgHeight,null);
        musicButton.draw(g);
        fsxButton.draw(g);
        menuButton.draw(g);
        replayButton.draw(g);
        unPauseButton.draw(g);
        volumeButton.draw(g);
    }

    public void mouseDragger(MouseEvent e){
        if(volumeButton.isMousePressed()){
            volumeButton.changX(e.getX());
        }
    }

    public void mouseClicker(MouseEvent e) {
    }

    public void mousePresser(MouseEvent e) {
        if(isIn(e,musicButton)){
            musicButton.setMousePressed(true);
        }
        else if (isIn(e,fsxButton)){
            fsxButton.setMousePressed(true);
        }
        else if (isIn(e,menuButton)) {
            menuButton.setMousePressed(true);
        }
        else if (isIn(e,replayButton)) {
            replayButton.setMousePressed(true);
        }
        else if (isIn(e,unPauseButton)){
            unPauseButton.setMousePressed(true);
        }
        else if (isIn(e,volumeButton)){
            volumeButton.setMousePressed(true);
        }

    }

    public void mouseReleased(MouseEvent e) {
        if(isIn(e,musicButton)){
            if(musicButton.isMousePressed()){
                musicButton.setMuted(!musicButton.isMuted());
            }
        }
        else if (isIn(e,fsxButton)){
            if(fsxButton.isMousePressed()){
                fsxButton.setMuted(!fsxButton.isMuted());
            }
        }
        else if (isIn(e,menuButton)){
            if(menuButton.isMousePressed()){
                Gamestate.state = Gamestate.MENU;
                playing.unPauseGame();
            }
        }
        else if (isIn(e,replayButton)){
            if(replayButton.isMousePressed()){
                System.out.println("replay lvl");
            }
        }
        else if (isIn(e,unPauseButton)){
            if(unPauseButton.isMousePressed()){
                playing.unPauseGame();
            }
        }

        musicButton.resetBool();
        fsxButton.resetBool();
        menuButton.resetBools();
        replayButton.resetBools();
        unPauseButton.resetBools();
        volumeButton.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        fsxButton.setMouseOver(false);
        menuButton.setMouseOver(false);
        replayButton.setMouseOver(false);
        unPauseButton.setMouseOver(false);
        volumeButton.setMouseOver(false);
        if(isIn(e,musicButton)){
            musicButton.setMouseOver(true);
        }
        else if (isIn(e,fsxButton)){
            fsxButton.setMouseOver(true);
        }
        else if (isIn(e,menuButton)){
            menuButton.setMouseOver(true);
        }
        else if (isIn(e,replayButton)){
            replayButton.setMouseOver(true);
        }
        else if (isIn(e,unPauseButton)){
            unPauseButton.setMouseOver(true);
        }
        else if (isIn(e,volumeButton)){
            volumeButton.setMouseOver(true);
        }
    }
    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(),e.getY());
    }
}
