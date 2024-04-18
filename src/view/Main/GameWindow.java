package view.Main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {
    private JFrame jfame;

    public GameWindow(GamePanel gamePanel) {
        jfame = new JFrame();
        jfame.setTitle("Ninja Kun");
        jfame.add(gamePanel);
        jfame.pack();
        jfame.setResizable(false);
        jfame.setLocationRelativeTo(null);
        jfame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfame.setVisible(true);
        jfame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowForcusLost();
            }
        });
    }
}
