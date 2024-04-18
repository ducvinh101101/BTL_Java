package view.ui;

import model.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static model.utilz.Constants.UI.PauseButton.SOUND_SIZE;
import static model.utilz.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static model.utilz.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

public class AudioOptions {
    private SoundButton musicButton, fsxButton;
    private VolumeButton volumeButton;
    private Game game;
    public AudioOptions(Game game){
        this.game = game;
        createSoundButton();
        createVolumeButton();

    }
    private void createSoundButton() {
        int soundX = (int)(450 * Game.SCALE);
        int musicY = (int)(140 * Game.SCALE);
        int fsxY = (int)(186 * Game.SCALE);
        musicButton = new SoundButton(soundX,musicY,SOUND_SIZE,SOUND_SIZE);
        fsxButton = new SoundButton(soundX,fsxY,SOUND_SIZE,SOUND_SIZE);
    }
    private void createVolumeButton() {
        int volumeX = (int)(309 * Game.SCALE);
        int volumeY = (int)(278 * Game.SCALE);
        volumeButton = new VolumeButton(volumeX,volumeY,SLIDER_WIDTH,VOLUME_HEIGHT);
    }
    public void update(){
        musicButton.update();
        fsxButton.update();
        volumeButton.update();
    }
    public void draw(Graphics g){
        musicButton.draw(g);
        fsxButton.draw(g);

        volumeButton.draw(g);

    }
    public void mouseDragged(MouseEvent e){
        if(volumeButton.isMousePressed()){
            float valueBefore = volumeButton.getFloatValue();
            volumeButton.changX(e.getX());
            float valueAfter = volumeButton.getFloatValue();
            if(valueBefore != valueAfter){
                game.getAudioPlayer().setVolume(valueAfter);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if(isIn(e,musicButton)){
            musicButton.setMousePressed(true);
        }
        else if (isIn(e,fsxButton)){
            fsxButton.setMousePressed(true);
        }
        else if (isIn(e,volumeButton)){
            volumeButton.setMousePressed(true);
        }

    }

    public void mouseReleased(MouseEvent e) {
        if(isIn(e,musicButton)){
            if(musicButton.isMousePressed()){
                musicButton.setMuted(!musicButton.isMuted());
                game.getAudioPlayer().toggleSongMute();
            }
        }
        else if (isIn(e,fsxButton)){
            if(fsxButton.isMousePressed()){
                fsxButton.setMuted(!fsxButton.isMuted());
                game.getAudioPlayer().toggleEffectMute();
            }
        }
        musicButton.resetBool();
        fsxButton.resetBool();
        volumeButton.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        fsxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);
        if(isIn(e,musicButton)){
            musicButton.setMouseOver(true);
        }
        else if (isIn(e,fsxButton)){
            fsxButton.setMouseOver(true);
        }

        else if (isIn(e,volumeButton)){
            volumeButton.setMouseOver(true);
        }
    }
    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(),e.getY());
    }
}
