package gamestates;

import Main.Game;
import entities.EnemyManager;
import entities.Player;
import levels.Background;
import levels.LevelManager;
import ui.PauseOverplay;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import static Main.Game.*;

public class Playing extends State implements Statemethod {
    private Player player;
    private LevelManager levelManager;
    private Background background;
    private EnemyManager enemyManager;
    private int xLvOffset;
    private int leftBorder = (int) (0.4 * GAME_WIDTH);
    private int rightBorder = (int) (0.6 * GAME_WIDTH);
    private int lvTilesWide = LoadSave.getLevelData()[0].length;
    private int maxTilesOffset = lvTilesWide - TILES_IN_WIDTH;
    private int maxLvOffset = maxTilesOffset * TILES_SIZE;
    private PauseOverplay pauseOverplay;

    private boolean pause = false;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        background = new Background();
        levelManager = new LevelManager(game);
        player = new Player(game.TILES_DEFAULT_SIZE, game.TILES_DEFAULT_SIZE * 12 - 1 - 40, 30, 42, this);
        player.loadlvlData(levelManager.getCurrenLevel().getlvlData());
        enemyManager = new EnemyManager(this);
        pauseOverplay = new PauseOverplay(this);
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        if (!pause) {
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrenLevel().getlvlData(), player);
            checkCloseToBorder();
        } else {
            pauseOverplay.update();
        }

    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitBox().x;
        int diff = playerX - xLvOffset;
        if (diff > rightBorder) xLvOffset += diff - rightBorder;
        else if (diff < leftBorder) xLvOffset += diff - leftBorder;

        if (xLvOffset > maxLvOffset) xLvOffset = maxLvOffset;
        else if (xLvOffset < 0) xLvOffset = 0;
    }

    @Override
    public void draw(Graphics g) {
        background.draw(g);
        levelManager.draw(g, xLvOffset);
        player.render(g, xLvOffset);
        enemyManager.draw(g, xLvOffset);
        if (pause) {
            pauseOverplay.draw(g);
        }
    }

    public void unPauseGame() {
        pause = false;
    }

    @Override
    public void mouseClicker(MouseEvent e) {
    }

    @Override
    public void mousePresser(MouseEvent e) {
        if (pause) {
            pauseOverplay.mousePresser(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pause) {
            pauseOverplay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (pause) {
            pauseOverplay.mouseMoved(e);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (pause) {
            pauseOverplay.mouseDragger(e);
        }
    }

    @Override
    public void keyPresser(KeyEvent e) {
        switch (e.getKeyCode()) {
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
                Gamestate.state = Gamestate.MENU;
                break;
            case KeyEvent.VK_P:
                pause = !pause;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
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

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
    }
}
