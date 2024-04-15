package gamestates;

import Main.Game;
import entities.EnemyManager;
import entities.Player;
import levels.Background;
import levels.LevelManager;
import ui.LevelCompletedOverlay;
import ui.PauseOverplay;
import utilz.HelpMethods;
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
    private int yLvOffset;
    private int leftBorder = (int) (0.4 * GAME_WIDTH);
    private int rightBorder = (int) (0.6 * GAME_WIDTH);
    private int upBorder = (int) (0.4 * GAME_HEIGHT);
    private int downBorder = (int) (0.6 * GAME_HEIGHT);
    private int lvTilesWide = LoadSave.getLevelData()[0].length;
    private int maxTilesOffset = lvTilesWide - TILES_IN_WIDTH;
    private int maxLvOffsetWidth = maxTilesOffset * TILES_SIZE;
    private int maxLvOffsetHeight = (30 - TILES_IN_HEIGHT) * TILES_SIZE;
    private PauseOverplay pauseOverplay;
    private LevelCompletedOverlay levelCompletedOverlay;
    private boolean lvlCompleter = false;
    private int kt=0;

    private boolean pause = false;

    public Playing(Game game) {
        super(game);
        initClasses();
    }
    public void loadNextMap(){
        levelManager.nextMap(lvlCompleter);
        lvlCompleter = false;
    }

    private void initClasses() {
        background = new Background();
        levelManager = new LevelManager(game);
        player = new Player(game.TILES_DEFAULT_SIZE, game.TILES_DEFAULT_SIZE * 12 - 1 - 40, 30, 42, this);
        player.loadlvlData(levelManager.getCurrenLevel().getlvlData());
        enemyManager = new EnemyManager(this);
        pauseOverplay = new PauseOverplay(this);
        levelCompletedOverlay = new LevelCompletedOverlay(this);

    }
    public void addCrab(){
        enemyManager.addEnemy(12,12);
    }


    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() { // đã đổi hàm update
        if(pause){
            pauseOverplay.update();
        }
        else if (lvlCompleter){
            levelCompletedOverlay.update();
        }
        else {
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrenLevel().getlvlData(), player);
            checkCloseToBorder();
            checkOpenToBorder();
            if (HelpMethods.canNextMap((float)player.getHitBox().x, (float)player.getHitBox().y, (float)player.getHitBox().width, (float)player.getHitBox().height, levelManager.getCurrenLevel().getlvlData())) {
                lvlCompleter = true;
            }
        }
        if(levelManager.getInnext()==0 && kt ==0){ // create monter
            addCrab();
            kt =1;
        }
        else if(levelManager.getInnext()==1 && kt ==1){
            enemyManager.getCrabs().clear();
            addCrab();
            enemyManager.addEnemy(15,12);
            kt =2;
        }

//        if (!pause && !lvlCompleter) { // thêm !lvlCompleter code cũ
//            levelManager.update();
//            player.update();
//            enemyManager.update(levelManager.getCurrenLevel().getlvlData(), player);
//            checkCloseToBorder();
//        } else {
//            pauseOverplay.update();
//        }

    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitBox().x;
        int diff = playerX - xLvOffset;
        if (diff > rightBorder) xLvOffset += diff - rightBorder;
        else if (diff < leftBorder) xLvOffset += diff - leftBorder;

        if (xLvOffset > maxLvOffsetWidth) xLvOffset = maxLvOffsetWidth;
        else if (xLvOffset < 0) xLvOffset = 0;
    }
    private void checkOpenToBorder() {
        int playerY = (int) player.getHitBox().y;
        int screenHeight = maxLvOffsetHeight;
        int centerY = yLvOffset + (screenHeight / 2);
        int diff = playerY - centerY;
        yLvOffset += diff;
        if (yLvOffset > maxLvOffsetHeight) {
            yLvOffset = maxLvOffsetHeight;
        } else if (yLvOffset < 0) {
            yLvOffset = 0;
        }
    }


    @Override
    public void draw(Graphics g) {
        background.draw(g);
        levelManager.draw(g, xLvOffset, yLvOffset);
        player.render(g, xLvOffset, yLvOffset);
        enemyManager.draw(g, xLvOffset, yLvOffset);
        if (pause) {
            pauseOverplay.draw(g);
        }
        else if (lvlCompleter){// thêm vẽ hộp thoại chuyển map
            levelCompletedOverlay.draw(g);
        }
        if(levelCompletedOverlay.isMap()){
            levelManager.importOutsideSprite();
            levelManager.draw(g,xLvOffset, yLvOffset);
            levelCompletedOverlay.setMap(false);
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
        } else if (lvlCompleter) {
            levelCompletedOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pause) {
            pauseOverplay.mouseReleased(e);
        }
        else if (lvlCompleter) {
            levelCompletedOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (pause) {
            pauseOverplay.mouseMoved(e);
        }
        else if (lvlCompleter) {
            levelCompletedOverlay.mouseMoved(e);
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

    public LevelManager getLevelManager() {
        return levelManager;
    }
}
