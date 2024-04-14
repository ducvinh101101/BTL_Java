package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Statemethod {
    public void update();
    public void draw(Graphics g);
    public void mouseClicker(MouseEvent e);
    public void mousePresser(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void keyPresser(KeyEvent e);
    public void keyReleased(KeyEvent e);

}
