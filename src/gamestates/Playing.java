package gamestates;

import Main.Game;
import entities.Player;
import levels.Background;
import levels.LevelManager;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static Main.Game.*;

public class Playing extends State implements Statemethod{
    private Player player;
    private LevelManager levelManager;
    private Background background;

    private int xLvOffset;
    private int leftBorder = (int) (0.2 * GAME_WIDTH);
    private int rightBorder = (int) (0.8 * GAME_WIDTH);
    private int lvTilesWide = LoadSave.getLevelData()[0].length;
    private int maxTilesOffset = lvTilesWide - TILES_IN_WIDTH;
    private int maxLvOffset = maxTilesOffset * TILES_SIZE;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        background = new Background();
        levelManager = new LevelManager(game);
        player=new Player(game.TILES_DEFAULT_SIZE,game.TILES_DEFAULT_SIZE*12-1-40,30,42);
        player.loadlvlData(levelManager.getCurrenLevel().getlvlData());
    }
    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
        checkCloseToBorder();
    }

    private void checkCloseToBorder() {
        int playerX = (int)player.getHitBox().x;
        int diff = playerX - xLvOffset;
        if (diff > rightBorder) xLvOffset += diff - rightBorder;
        else if (diff < leftBorder) xLvOffset += diff - leftBorder;

        if(xLvOffset > maxLvOffset) xLvOffset = maxLvOffset;
        else if (xLvOffset < 0) xLvOffset = 0;
    }

    @Override
    public void draw(Graphics g) {
        background.draw(g);
        levelManager.draw(g, xLvOffset);
        player.render(g, xLvOffset);

    }

    @Override
    public void mouseClicker(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePresser(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPresser(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_W:
                player.setJump(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setAttacking(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state=Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:

                break;
            case KeyEvent.VK_W:
                player.setJump(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;

        }
    }
}
