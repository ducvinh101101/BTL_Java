package Main;

import entities.Player;
import levels.Background;
import levels.Level;
import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable{
    private Thread gameThread;
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private final int FPS_SET = 60;
    private final int UPS_SET =100;
    private Player player;
    private LevelManager levelManager;

    private Background backGround1;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH = TILES_SIZE*TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_IN_HEIGHT*TILES_SIZE;

    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        backGround1 = new Background();
        levelManager = new LevelManager(this);
        player=new Player(32,32*3,30,42);
        player.loadlvlData(levelManager.getCurrenLevel().getlvlData());
    }

    private void startGameLoop(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    private void update() {
        levelManager.update();
        player.update();
    }

    public void render(Graphics g){
        backGround1.draw(g);
        levelManager.draw(g);
        player.render(g);
    }

    public void run(){
        double timePerFime = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU=0,deltaF=0;
        while(true){
            long currentTime = System.nanoTime();
            deltaU += (currentTime-previousTime)/timePerUpdate;
            deltaF += (currentTime-previousTime)/timePerFime;
            previousTime=currentTime;
            if(deltaU>=1){
                update();
                updates++;
                deltaU--;
            }
            if(deltaF>=1){
                gamePanel.repaint();
                deltaF--;
                frames++;
            }
            if(System.currentTimeMillis()-lastCheck>=1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: "+ frames+"| UPS: "+updates);
                frames = 0;
                updates=0;
            }
        }
    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

}
