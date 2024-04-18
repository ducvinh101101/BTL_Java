package view.Main;

import model.audio.AudioPlayer;
import model.gamestates.GameOptions;
import model.gamestates.Gamestate;
import controller.Menu;
import model.gamestates.Playing;
import view.ui.AudioOptions;

import java.awt.*;

public class Game implements Runnable {
    private Thread gameThread;
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private final int FPS_SET = 60;
    private final int UPS_SET = 100;
    private Playing playing;
    private Menu menu;
    private GameOptions gameOptions;
    private AudioOptions audioOptions;
    private AudioPlayer audioPlayer;
    public static final int ROW = 30;
    public static final int COL = 70;
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        audioOptions = new AudioOptions(this);
        audioPlayer = new AudioPlayer();
        menu = new Menu(this);
        playing = new Playing(this);
        gameOptions = new GameOptions(this);
    }


    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {

        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
                gameOptions.update();
                break;
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case OPTIONS:
                gameOptions.draw(g);
                break;
            case QUIT:
            default:
                break;
        }
    }

    public void run() {
        double timePerFime = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0, deltaF = 0;
        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFime;
            previousTime = currentTime;
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //    System.out.println("FPS: "+ frames+"| UPS: "+updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowForcusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
    public AudioOptions getAudioOptions(){
        return audioOptions;
    }
    public GameOptions getGameOptions(){
        return gameOptions;
    }
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
