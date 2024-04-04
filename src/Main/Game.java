package Main;
public class Game implements Runnable{
    private Thread gameThread;
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private final int FPS_SET = 120;
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    public void run(){
        double timePerFime = 1000000000.0/FPS_SET;
        long now=System.nanoTime();
        long lastFrame = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while(true){
            now = System.nanoTime();
            if(now-lastFrame>=timePerFime){
                gamePanel.repaint();
                lastFrame=now;
                frames++;
            }
            if(System.currentTimeMillis()-lastCheck>=1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS"+ frames);
                frames = 0;
            }
        }
    }
}
