package utilz;

import Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HelpMethods {

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
        return isTileSolid((int) xIndex, (int) yIndex, lvlData);
    }

    public static boolean isTileSolid(int xTile, int yTile, int[][] lvData) {
        int value = lvData[(int) yTile][(int) xTile];
        return value != 17 && value !=7; // thêm vị tr có thể đi  là 7
    }
    public static boolean canNextMap(float x, float y, float width, float height, int[][] lvlData) {
        float left = x / Game.TILES_SIZE;
        float right = (x + width) / Game.TILES_SIZE;
        float top = y / Game.TILES_SIZE;
        float bottom = (y + height) / Game.TILES_SIZE;
        if (!isNextMap(left, top, lvlData))
            if (!isNextMap(right, bottom, lvlData))
                if (!isNextMap(right, top, lvlData))
                    if (!isNextMap(left, bottom, lvlData))
                        return false;
        return true;
    }
    private static boolean isNextMap(float xIndex, float yIndex, int[][] lvlData){
        if (xIndex < 0 || xIndex >= lvlData[0].length || yIndex < 0 || yIndex >= lvlData.length) {
            return true;
        }
        return isTileNextMap((int) xIndex, (int) yIndex, lvlData);
    }
    public static boolean isTileNextMap(int xTile, int yTile, int[][] lvData) { // thêm kiểm tra cổng dịch chuyển
        int value = lvData[(int) yTile][(int) xTile];
        return value == 7;
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
    public static boolean isFloor(Rectangle2D.Float hitBox, float xSpeed, int[][] lvData) {
        return isSolid(hitBox.x + xSpeed, hitBox.y + hitBox.height + 1, lvData);
    }

    public static boolean isAllTileWalkable(int xStart, int xEnd, int y, int[][] lvData) {
        for (int i = 0; i < xEnd - xStart; i++) {
            if (isTileSolid(xStart + i, y, lvData)) return false;
        }
        return true;
    }
    public static boolean isSightClear(int[][] lvData, Rectangle2D.Float firstHitBox, Rectangle2D.Float secondHitBox, int yTile) {
        int firstXTile = (int) (firstHitBox.x / Game.TILES_SIZE);
        int secondXTile = (int) (secondHitBox.x / Game.TILES_SIZE);
        if (firstXTile > secondXTile) return isAllTileWalkable(secondXTile, firstXTile, yTile, lvData);
        else return isAllTileWalkable(firstXTile, secondXTile, yTile, lvData);
    }
}
