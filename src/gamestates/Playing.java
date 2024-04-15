package gamestates;

import Main.Game;
import entities.EnemyManager;
import entities.Player;
import levels.Background;
import levels.LevelManager;
import objects.ObjectManager;
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
    private ObjectManager objectManager;
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
    private boolean lvlCompleted = false;
    private int checkNextMap = 0;

    private boolean paused = false;

    public Playing(Game game) {
        super(game);
        initClasses();
         loadStartLevel();
    }

    private void loadStartLevel() {
        objectManager.loadObject(levelManager.getCurrenLevel());
    }
//    public void loadNextMap(){
//        levelManager.nextMap(lvlCompleter);
//        lvlCompleter = false;
//    }

    private void initClasses() {
        background = new Background();
        levelManager = new LevelManager(game);
        objectManager = new ObjectManager(this);
        player = new Player(game.TILES_DEFAULT_SIZE, game.TILES_DEFAULT_SIZE * 12 - 1 - 40, 30, 42, this);
        player.loadlvlData(levelManager.getCurrenLevel().getlvlData());
        objectManager.loadObject(levelManager.getCurrenLevel());
        enemyManager = new EnemyManager(this);
        pauseOverplay = new PauseOverplay(this);

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
        if(paused){
            pauseOverplay.update();
        }

        else {
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrenLevel().getlvlData(), player);
            objectManager.update();
            checkCloseToBorder();
            checkOpenToBorder();
            if (HelpMethods.canNextMap((float)player.getHitBox().x, (float)player.getHitBox().y, (float)player.getHitBox().width, (float)player.getHitBox().height, levelManager.getCurrenLevel().getlvlData())) {
                lvlCompleted = true;
                levelManager.nextMap(lvlCompleted);
            }
        }
        if(levelManager.getInnext()==0 && checkNextMap ==0){ // create monter
            addCrab();
            checkNextMap =1;
        }
        else if(levelManager.getInnext()==1 && checkNextMap ==1){
            enemyManager.getCrabs().clear();
            addCrab();
            enemyManager.addEnemy(10,20);
            checkNextMap =2;
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
    public void resetAll(){
        //gameOver = false;
        paused = false;
        lvlCompleted = false;
        player.resetAll();
        objectManager.resetAllObjects();
    }


    @Override
    public void draw(Graphics g) {
        background.draw(g);
        levelManager.draw(g, xLvOffset, yLvOffset);
        player.render(g, xLvOffset, yLvOffset);
        enemyManager.draw(g, xLvOffset, yLvOffset);
        objectManager.draw(g,xLvOffset, yLvOffset);
        if (paused) {
            pauseOverplay.draw(g);
        }
        if(lvlCompleted){
            player.setHitBox(TILES_DEFAULT_SIZE*3,TILES_DEFAULT_SIZE*20+3);
            levelManager.importOutsideSprite();
            levelManager.draw(g,xLvOffset, yLvOffset);
            objectManager.draw(g,xLvOffset, yLvOffset);
            lvlCompleted = false;
        }

    }

    public void unPauseGame() {
        paused = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseOverplay.mousePresser(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseOverplay.mouseReleased(e);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverplay.mouseMoved(e);
        }

    }

    public void mouseDragged(MouseEvent e) {
        if (paused) {
            pauseOverplay.mouseDragger(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            case KeyEvent.VK_P:
                paused = !paused;
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

    public ObjectManager getObjectManager(){
        return objectManager;
    }
    public void checkObjectHit(Rectangle2D.Float attackBox){
        objectManager.checkObjectHit(attackBox);
    }
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
    }
    public void checkPotionTouch(Rectangle2D.Float hitBox){
        objectManager.checkObjectTouched(hitBox);
    }
    public LevelManager getLevelManager() {
        return levelManager;
    }

}
