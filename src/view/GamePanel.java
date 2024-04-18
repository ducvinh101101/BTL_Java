package view;

import controller.KeyboardInputs;
import controller.MouseInputs;
import model.Game;

import javax.swing.*;
import java.awt.*;

import static model.Game.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private int frames = 0;
    private long lastCheck = 0;
    private Game game;

    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        this.game = game;
        setFocusable(true);
        requestFocusInWindow();
        setPanelSize();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }


    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        //System.out.println(GAME_WIDTH + " " + GAME_HEIGHT);
    }

    public void updateGame() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
        frames++;
    }

    public Game getGame() {
        return game;
    }
}
