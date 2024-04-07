package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static BufferedImage getPlayerAttack(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/playerAttack.png");
        try(is) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    public static BufferedImage getPlayerAni(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/player.png");
        try(is) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    public static BufferedImage getPlayerRunRight(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/pyrunright.png");
        try(is) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    public static BufferedImage getPlayerRunLeft(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/pyrunleft.png");
        try(is) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    
}
