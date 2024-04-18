package view.ui;

import model.gamestates.Gamestate;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.utilz.Constants.UI.Buttons.*;

public class MenuButton {
    private int posX, posY, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private boolean mouseOver, mousePressed;
    private Gamestate state;
    private Rectangle bounds;
    BufferedImage[] imgs;

    public MenuButton(int posX, int posY, int rowIndex, Gamestate state) {
        this.posX = posX;
        this.posY = posY;
        this.rowIndex = rowIndex;
        this.state = state;
        loadsImg();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(posX - xOffsetCenter, posY, B_WIDTH, B_HEIGHT);
    }

    private void loadsImg() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAlas(LoadSave.MENU_BUTTON);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], posX - xOffsetCenter, posY, B_WIDTH, B_HEIGHT, null);

    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void apllyGamestate(){
        Gamestate.state = state;
    }
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
    public Gamestate getState(){
        return  state;
    }
}
