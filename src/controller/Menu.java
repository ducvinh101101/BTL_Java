package controller;

import model.gamestates.Gamestate;
import model.gamestates.State;
import model.gamestates.Statemethod;
import model.Game;
import view.ui.MenuButton;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethod {
    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg,backInGround;
    private int menuX, menuY, menuWidth, menuHeight;
    public Menu(Game game) {
        super(game);
        loadButton();
        loadBackground();
        loadInBackground();
    }

    private void loadBackground() {
        backgroundImg = LoadSave.getImage(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth()/1.5 * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight()/1.5 * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);
    }
    private void loadInBackground(){
        backInGround = LoadSave.getImage(LoadSave.IN_BACKGROUND);
    }

    private void loadButton() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backInGround,0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT,null);
        g.drawImage(backgroundImg,menuX,menuY,menuWidth,menuHeight,null);

        for (MenuButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed())
                    mb.apllyGamestate();
                if(mb.getState() == Gamestate.PLAYING){
                    game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getInnext());
                }
                break;
            }
        }

        resetButton();
    }

    private void resetButton() {
        for (MenuButton mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);

        for (MenuButton mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
