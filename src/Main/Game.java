package Main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable{
    private Thread gameThread;
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private final int FPS_SET = 120;
    private final int UPS_SET =200;
    private Player player;
    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        player=new Player(200,200);
    }

    private void startGameLoop(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    private void update() {
        player.update();
    }

    public void render(Graphics g){
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
