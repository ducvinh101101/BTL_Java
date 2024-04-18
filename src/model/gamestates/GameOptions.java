package model.gamestates;

import model.Game;
import view.AudioOptions;
import view.PauseButton;
import view.UrmButton;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static model.utilz.Constants.UI.URMButtons.*;

public class GameOptions extends State implements Statemethod{
    private AudioOptions audioOptions;
    private BufferedImage backgroundImgs, optionsBackgroundImg;
    private  int bgX, bgY, bgWidth, bgHeight;
    private UrmButton menuButton;
    public GameOptions(Game game) {
        super(game);
        loadImgs();
        loadButtons();
        audioOptions = game.getAudioOptions();
    }

    private void loadButtons() {
        int menuX = (int) (390 * Game.SCALE);
        int menuY = (int) (330  * Game.SCALE);
        menuButton = new UrmButton(menuX,menuY,URM_SIZE, URM_SIZE, 2);
    }

    private void loadImgs() {
        backgroundImgs = LoadSave.getImage(LoadSave.IN_BACKGROUND);
        optionsBackgroundImg = LoadSave.getImage(LoadSave.OPTIONS_MENU);

        bgWidth = (int) ((optionsBackgroundImg.getWidth() / 1.5) * Game.SCALE);
        bgHeight = (int) ((optionsBackgroundImg.getHeight() / 1.5)* Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgWidth / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    @Override
    public void update() {
        menuButton.update();
        audioOptions.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImgs,0,0,Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(optionsBackgroundImg, bgX, bgY, bgWidth,bgHeight,null);

        menuButton.draw(g);
        audioOptions.draw(g);
    }
    public void mouseDragged(MouseEvent e){
        audioOptions.mouseDragged(e);
    }



    @Override
    public void mousePressed(MouseEvent e) {
        if(isIn(e,menuButton)){
            menuButton.setMousePressed(true);
        }else{
            audioOptions.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isIn(e,menuButton)){
            if(menuButton.isMousePressed()){
                Gamestate.state = Gamestate.MENU;
            }

        }else{
            audioOptions.mouseReleased(e);
        }
        menuButton.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuButton.setMouseOver(false);
        if(isIn(e,menuButton)){
            menuButton.setMouseOver(true);

        }else{
            audioOptions.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            Gamestate.state = Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(),e.getY());
    }
}
