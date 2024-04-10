package utilz;

import Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HelpMethods {
//    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData){

//
//        // Kiểm tra từng ô trong phạm vi di chuyển của nhân vật
//        for (int i = (int) top; i <= (int) bottom; i++) {
//            for (int j = (int) left; j <= (int) right; j++) {
//                // Kiểm tra xem ô hiện tại có phải là vật cản hay không
//                if (!isSolid(j, i, lvlData)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
    float left = x / Game.TILES_SIZE;
    float right = (x + width) / Game.TILES_SIZE;
    float top = y / Game.TILES_SIZE;
    float bottom = (y + height) / Game.TILES_SIZE;
    if (!isSolid(left, top, lvlData))
        if (!isSolid(right, bottom, lvlData))
            if (!isSolid(right, top, lvlData))
                if (!isSolid(left, bottom, lvlData))
                    return true;
    return false;
}

    private static boolean isSolid(float xIndex, float yIndex, int[][] lvlData){
        if (xIndex < 0 || xIndex >= lvlData[0].length || yIndex < 0 || yIndex >= lvlData.length) {
            return true;
        }
        int value = lvlData[(int)yIndex][(int)xIndex];
        return value !=80;
//        if (x < 0 || x >= lvlData[0].length)
//            return true;
//        if (y < 0 || y >= lvlData.length)
//            return true;
//        int value = lvlData[(int) y][(int) x];
//
//        if (value >= 81 || value < 0 || value != 80)
//            return true;
//        return false;
    }

    public static float getEntityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed){
        int currentTile = (int) (hitBox.x/Game.TILES_SIZE);
        if(xSpeed>0){
            int tileXPos =  currentTile*Game.TILES_SIZE;
            int xOffSet = (int) (Game.TILES_SIZE-hitBox.width);
            return tileXPos+xOffSet-1;
        }
        else {
            return currentTile*Game.TILES_SIZE;
        }
    }
    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed){
        int currentTile = (int) ((hitBox.y)/Game.TILES_SIZE);
        if(airSpeed > 0){
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffSet = (int) (Game.TILES_SIZE - hitBox.height);
            return tileYPos + yOffSet-1+Game.TILES_DEFAULT_SIZE;
        }
        else {
            return currentTile * Game.TILES_SIZE;
        }
    }
    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int [][] lvlData){
//        float leftTile = hitBox.x / Game.TILES_SIZE;
//        float rightTile = (hitBox.x + hitBox.width) / Game.TILES_SIZE;
//        int bottomTile = (int) ((hitBox.y + hitBox.height) / Game.TILES_SIZE);
//        for (int i = (int) leftTile; i <= rightTile; i++) {
//            if (isSolid(i, bottomTile, lvlData)) {
//                return false;
//            }
//        }
//        return true;
        float left = hitBox.x / Game.TILES_SIZE;
        float right = (hitBox.x + hitBox.width) / Game.TILES_SIZE;
        float top = hitBox.y / Game.TILES_SIZE;
        float bottom = (hitBox.y + hitBox.height+1) / Game.TILES_SIZE;
        if (!isSolid(left, bottom, lvlData))
            if (!isSolid(right, bottom, lvlData))
                if (!isSolid(right, top, lvlData))
                    if (!isSolid(left, top, lvlData))
                return false;
        return true;
    }
}
