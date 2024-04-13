package ui;


import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.VolumeButtons.*;


public class VolumeButton extends PauseButton{
    private BufferedImage[] imgs;
    private BufferedImage slide;
    private int index = 0;
    private boolean mouseOver, mousePressed;
    private int buttonX;

    public VolumeButton(int x, int y, int width, int height) {
        super(x+width/2, y, VOLUME_WIDTH, height);
        buttonX = x+width/2;
        loadVolumeButton();
    }


    private void loadVolumeButton() {
        BufferedImage img = LoadSave.getSpriteAlas(LoadSave.VOLUME_BUTTON);
        imgs = new  BufferedImage[3];
        for(int i=0; i<imgs.length;i++){
            imgs[i] = img.getSubimage(i*VOLUME_DEFAULT_WIDTH,0,VOLUME_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);
        }
        slide = img.getSubimage(3*VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);
    }

    public void update(){
        index =0;
        if(mouseOver){
            index =1;
        }
        if(mousePressed){
            index =2;
        }

    }
    public void draw(Graphics g){
        g.drawImage(slide,x,y,width,height,null);
        g.drawImage(imgs[index],buttonX,y,VOLUME_WIDTH,height,null);
    }
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
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
}
