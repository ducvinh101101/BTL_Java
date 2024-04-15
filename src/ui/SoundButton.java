package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.PauseButton.*;

public class SoundButton extends PauseButton{
    private BufferedImage [][] soundImgs;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex,colIndex;
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadSoundImgs();
    }
    private void loadSoundImgs(){
        BufferedImage img = LoadSave.getSpriteAlas(LoadSave.SOUND_BUTTON);
        soundImgs = new BufferedImage[2][3];
        for(int i =0; i < soundImgs.length; i++){
            for(int j = 0;j < soundImgs[i].length; j++){
                soundImgs[i][j] = img.getSubimage(j * SOUND_SIZE_DEFAUL,i * SOUND_SIZE_DEFAUL,SOUND_SIZE_DEFAUL,SOUND_SIZE_DEFAUL);
            }
        }
    }
    public void update(){
        if(muted){
            rowIndex = 1;
        }
        else {
            rowIndex = 0;
        }
        colIndex =0;
        if(mouseOver){
            colIndex = 1;
        }
        if(mousePressed){
            colIndex = 2;
        }
    }
    public void resetBool(){
        mousePressed=false;
        mouseOver = false;
    }
    public void draw(Graphics g){
        g.drawImage(soundImgs[rowIndex][colIndex],x,y,width,height,null);
    }

    public BufferedImage[][] getSoundImgs() {
        return soundImgs;
    }

    public void setSoundImgs(BufferedImage[][] soundImgs) {
        this.soundImgs = soundImgs;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
