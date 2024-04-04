package Main;
import javax.swing.*;
public class GameWindow extends JFrame{
    private JFrame jfame;
    public GameWindow(GamePanel gamePanel){
        jfame= new JFrame();
        jfame.setTitle("Ninja Kun");
        jfame.add(gamePanel);
        jfame.pack();
        jfame.setResizable(false);
        jfame.setLocationRelativeTo(null);
        jfame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfame.setVisible(true);
    }
}
