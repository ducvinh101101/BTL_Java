package Main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel{
    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private int frames=0;
    private long lastCheck=0;
    private int xDelta=0,yDelta = 0;
    private BufferedImage img;
    private BufferedImage[] idAniIm;
    private int aniTick, aniIndex, aniSpeed = 15;
    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        setPanelSize();
        importImg();
        loadAnimations();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        idAniIm = new BufferedImage[5];
        for(int i=0;i<idAniIm.length;i++){
            idAniIm[i]= img.getSubimage(i*50, 0, 50, 70);
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/pyrunleft.png");
        try {
            if (is != null) {
                img = ImageIO.read(is);

            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1000,500);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    public void chanexDelta(int value){
        this.xDelta+=value;
    }
    public void chaneyDelta(int value){
        this.yDelta+=value;
    }
    public void setRectPos(int x, int y){
        this.xDelta=x;
        this.yDelta=y;
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=idAniIm.length){
                aniIndex=0;
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateAnimationTick();
        g.drawImage(idAniIm[aniIndex],xDelta,yDelta,30,42,null);
        frames++;
    }


}
