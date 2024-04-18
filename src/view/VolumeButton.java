package view;


import model.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.UI.VolumeButtons.*;


public class VolumeButton extends PauseButton{
    private BufferedImage[] imgs;
    private BufferedImage slide;
    private int index = 0;
    private boolean mouseOver, mousePressed;
    private int buttonX;
    private int minX,maxX;
    private float floatValue = 0f;

    public VolumeButton(int x, int y, int width, int height) {
        super(x+width/3, y, (int) (VOLUME_WIDTH), (int) (height/1.5));
        bounds.x -= VOLUME_WIDTH / 2;
        buttonX = x + (int) (width / 3);
        this.x = x;
        this.width = (int) (width/1.5);
        minX = x + (int)(VOLUME_WIDTH/2);
        maxX = x + (int) (width/1.5) - VOLUME_WIDTH / 4;
        loadVolumeButton();
    }


    private void loadVolumeButton() {
        BufferedImage img = LoadSave.getImage(LoadSave.VOLUME_BUTTON);
        imgs = new  BufferedImage[3];
        for(int i = 0; i < imgs.length; i++){
            imgs[i] = img.getSubimage(i * VOLUME_DEFAULT_WIDTH,0,VOLUME_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);
        }
        slide = img.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);
    }

    public void update(){
        index =0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 2;
        }

    }
    public void draw(Graphics g){
        g.drawImage(slide,x,y,width,height,null);
        g.drawImage(imgs[index],buttonX - VOLUME_WIDTH / 3,y,(int) (VOLUME_WIDTH/1.5),(int)(height),null);
    }
    public void changX(int x){
        if(x < minX){
            buttonX = minX;
        }
        else if(x > maxX){
            buttonX = maxX;
        }
        else buttonX = x;
        updateFloatValue();
        bounds.x = buttonX - VOLUME_WIDTH / 2;
    }

    private void updateFloatValue() {
        float range = maxX - minX;
        float value = buttonX - minX;
        floatValue = value / range;
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
    public float getFloatValue(){
        return floatValue;
    }
}
