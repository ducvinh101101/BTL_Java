package model.gamestates;

import model.Game;
import model.entities.EnemyManager;
import model.entities.NpcManager;
import model.entities.Player;
import view.Background;
import model.maps.MapManager;
import model.objects.ObjectManager;
import view.GameOverOverlay;
import view.PauseOverlay;
import model.utilz.HelpMethods;
import model.utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static model.Game.*;

public class Playing extends State implements Statemethod {
    private Player player;
    private MapManager mapManager;
    private ObjectManager objectManager;
    private Background background;
    private EnemyManager enemyManager;
    private NpcManager npcManager;
    private GameOverOverlay gameOverOverlay;
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
    private PauseOverlay pauseOverlay;
    private boolean nextMapChecked = false;
    private boolean gameOver;
    private boolean playerDying;
    private int checkNextMap = 0;
    private boolean paused = false;
    private BufferedImage NpcImg, NpcImg2;
    public Playing(Game game) {
        super(game);
        initClasses();
        loadStartLevel();
    }

    private void loadStartLevel() {
        objectManager.setCurrentLevel(mapManager.getCurrenLevel());
    }


    private void initClasses() {
        background = new Background();
        mapManager = new MapManager(game);
        npcManager = new NpcManager(this);
        objectManager = new ObjectManager(this);
        player = new Player(TILES_DEFAULT_SIZE, TILES_DEFAULT_SIZE * 12 - 1 - 40, 30, 42, this);
        player.loadlvlData(mapManager.getCurrenLevel().getlvlData());
        objectManager.setCurrentLevel(mapManager.getCurrenLevel());
        enemyManager = new EnemyManager(this);
        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
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
            pauseOverlay.update();
        }else if(gameOver){
           // mapManager.setInnext(0);
            gameOverOverlay.update();
        }else if(playerDying){
            player.update();
        }
        else {
            mapManager.update();
            player.update();

            enemyManager.update(mapManager.getCurrenLevel().getlvlData(), player);
            objectManager.update(mapManager.getCurrenLevel().getlvlData(),player);
            checkCameraX();
            checkCameraY();
            if (HelpMethods.canNextMap((float)player.getHitBox().x, (float)player.getHitBox().y, (float)player.getHitBox().width, (float)player.getHitBox().height, mapManager.getCurrenLevel().getlvlData())) {
                nextMapChecked = true;
                mapManager.nextMap(nextMapChecked);
                getGame().getAudioPlayer().setLevelSong(mapManager.getInnext());
            }
        }
        if(mapManager.getInnext()==0 && checkNextMap ==0){ // create monter
            player.setHitBox(100,100);
            enemyManager.clearAll();
            checkNextMap = 1;
            enemyManager.addEnemyMap1();
            npcManager.npcMap1();
            objectManager.setCurrentLevel(this.getLevelManager().getCurrenLevel());
        }
        else if(mapManager.getInnext()==1 && checkNextMap ==1){
            player.setHitBox(100,300);
            enemyManager.clearAll();
            checkNextMap = 2;
            enemyManager.addEnemyMap2();
            npcManager.npcMap2();
            objectManager.setCurrentLevel(this.getLevelManager().getCurrenLevel());
        }
        else if (mapManager.getInnext()==2&& checkNextMap==2) {
            player.setHitBox(200,500);
            enemyManager.clearAll();
            checkNextMap = 0;
            enemyManager.addEnemyMap3();
            npcManager.npcMap3();
            objectManager.setCurrentLevel(this.getLevelManager().getCurrenLevel());
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

    private void checkCameraX() {
        int playerX = (int) player.getHitBox().x;
        int diff = playerX - xLvOffset;
        if (diff > rightBorder) xLvOffset += diff - rightBorder;
        else if (diff < leftBorder) xLvOffset += diff - leftBorder;
        if (xLvOffset > maxLvOffsetWidth) xLvOffset = maxLvOffsetWidth;
        else if (xLvOffset < 0) xLvOffset = 0;
    }
    private void checkCameraY() {
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
    public void checkSpikesTouched(Player player){
        objectManager.checkSpikesTouched(player);
    }
    public void resetAll(){
        mapManager.setInnext(0);
        mapManager.importOutsideSprite();
        gameOver = false;
        paused = false;
        nextMapChecked = false;
        playerDying = false;
        player.resetAll();
        objectManager.resetAllObjects();
        this.update();
    }


    @Override
    public void draw(Graphics g) {
        if(mapManager.getInnext()==1){
            background.drawBackgroundMap2(g);
        }
        else if(mapManager.getInnext()==2){
            background.draw(g);
        }
        else {
            background.drawBackgroundMap1(g);
        }
        mapManager.draw(g, xLvOffset, yLvOffset);npcManager.draw(g, xLvOffset, yLvOffset);
        enemyManager.draw(g, xLvOffset, yLvOffset);
        objectManager.draw(g,xLvOffset, yLvOffset);
        player.render(g, xLvOffset, yLvOffset);
        if (paused) {
            background.drawBackgroundPauseGame(g);
            pauseOverlay.draw(g);
        }
        if(nextMapChecked){
            mapManager.importOutsideSprite();
            mapManager.draw(g,xLvOffset, yLvOffset);
            objectManager.draw(g,xLvOffset, yLvOffset);
            nextMapChecked = false;
        } else if (gameOver) {
            gameOverOverlay.draw(g);
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
        if(!gameOver){
            if(paused){
                pauseOverlay.mousePressed(e);
            }else if(nextMapChecked){

            }
        }else {
            gameOverOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!gameOver){
            if(paused){
                pauseOverlay.mouseReleased(e);
            }
        }else {
            gameOverOverlay.mouseReleased(e);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!gameOver){
            if(paused){
                pauseOverlay.mouseMoved(e);
            }
        }else {
            gameOverOverlay.mouseMoved(e);
        }

    }

    public void mouseDragged(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameOver){
            gameOverOverlay.keyPressed(e);
        }else {
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
                case KeyEvent.VK_H:
                    player.setSkill(true);
                    break;
                case KeyEvent.VK_P:
                    paused = !paused;
            }
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
            case KeyEvent.VK_H:
                player.setSkill(false);
                break;
        }
    }
    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
    public  void setPlayerDying(boolean playerDying){
        this.playerDying = playerDying;
    }
    public void setCheckNextMap(int checkNextMap) {
        this.checkNextMap = checkNextMap;
    }

    public void checkObjectHit(Rectangle2D.Float attackBox){
        objectManager.checkObjectHit(attackBox);
    }
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox, player);
    }
    public void checkPotionTouch(Rectangle2D.Float hitBox){
        objectManager.checkObjectTouched(hitBox);
    }
    public MapManager getLevelManager() {
        return mapManager;
    }

}
