package utilz;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_AT = "playerAttackRight.png";
    public static final String PLAYER_ANI = "playerRight.png";
    public static final String PLAYER_RUNR = "pyrunright.png";
    public static final String PLAYER_RUNL = "pyrunleft.png";
    public static final String PLAYER_IML = "playerLeft.png";
    public static final String BACKGROUND_MAP_1 = "background.png";
    public static final String TILESET = "Tileset.png";
    public static final String LEVEL_ONE = "level_one_data.png";
    public static BufferedImage getPlayerAttack(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+PLAYER_AT);
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
        InputStream is = LoadSave.class.getResourceAsStream("/"+PLAYER_ANI);
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
    public static BufferedImage getPlayerAniLeft(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+PLAYER_IML);
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
        InputStream is = LoadSave.class.getResourceAsStream("/"+PLAYER_RUNR);
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
        InputStream is = LoadSave.class.getResourceAsStream("/"+PLAYER_RUNL);
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
    public static BufferedImage getAtlas(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+TILESET);
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
    public static BufferedImage getBackground(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+BACKGROUND_MAP_1);
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
    public static BufferedImage getLevelOne(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+LEVEL_ONE);
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
    public static int[][] getLevelData(){
        int[][] lvData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        int[][] sampleMap = {
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 2, 2, 2, 2, 80, 80, 80, 80, 56, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

        };

        // Copy dữ liệu từ mảng mẫu vào mảng thực tế của game
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            System.arraycopy(sampleMap[i], 0, lvData[i], 0, Game.TILES_IN_WIDTH);
        }
        return lvData;
    }
}

//                new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
//        BufferedImage img = getLevelOne();
//        for(int i=0;i< img.getWidth();i++){
//            for(int j=0;j<img.getHeight();j++){
//                Color color = new Color(img.getRGB(i,j));
//                int value = color.getRed();
//                if(value>48){
//                    value = 0;
//                }
//                lvData[j][i] = value;
//            }
//        }