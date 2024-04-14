package ui;

import Main.Game;
import gamestates.Gamestate;
import gamestates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

public class LevelCompletedOverlay {
    // thêm lớp để thông báo chuyển map
    private Playing playing;
    private UrmButton menu, next;
    private BufferedImage img;
    private int bgX,bgY,bgWidth,bgHeight;
    private boolean map = false;
    public LevelCompletedOverlay(Playing playing){
        this.playing = playing;
        initImg();
        initButton();
    }

    private void initButton() {
        int menuX = (int) (330 * Game.SCALE);
        int nextX = (int) (445 * Game.SCALE);
        int y = (int) (195 * Game.SCALE);
        next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    private void initImg() {
        img = LoadSave.getSpriteAlas(LoadSave.COMPLETED_IMG);
        bgWidth = (int)(img.getWidth()* Game.SCALE);
        bgHeight = (int) (img.getHeight()*Game.SCALE);
        bgX = Game.GAME_WIDTH/2 - bgWidth/2;
        bgY = (int) (75*Game.SCALE);
    }
    public void draw(Graphics g){
        g.drawImage(img,bgX,bgY,bgWidth,bgHeight,null);
        next.draw(g);
        menu.draw(g);
    }
    public void update(){
        next.update();
        menu.update();
    }
    private boolean isIn(UrmButton b, MouseEvent e){
        return b.getBounds().contains(e.getX(),e.getY());
    }
    public void mouseMoved(MouseEvent e) {
        next.setMouseOver(false);
        menu.setMouseOver(false);
        if(isIn(menu,e)){
            menu.setMouseOver(true);
        }
        else if(isIn(next,e)){
            next.setMouseOver(true);
        }

    }

    public void mouseReleased(MouseEvent e) {
        if(isIn(menu,e)){
            if(menu.isMousePressed()){
                System.out.println("Menu");
            }
        }
        else if(isIn(next,e)){
            if(next.isMousePressed()){
                playing.loadNextMap();
                map = true;
            }
        }
        menu.resetBools();
        next.resetBools();
    }

    public void mousePressed(MouseEvent e) {
        if(isIn(menu,e)){
            menu.setMousePressed(true);
        }
        else if(isIn(next,e)){
            next.setMousePressed(true);
        }
    }

    public boolean isMap() {
        return map;
    }

    public void setMap(boolean map) {
        this.map = map;
    }
}
