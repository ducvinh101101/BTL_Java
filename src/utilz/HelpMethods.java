package utilz;

import Main.Game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import objects.Potion;
import objects.GameContainer;

import static utilz.Constants.ObjectConstants.*;
import static utilz.Constants.ObjectConstants.BOX;

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
        return value != 17 && value !=16 && value!=52 && value != 91 && value != 6 && value != 92 && value !=93 && value != 94 && value !=95 && value != 21 && value != 22 && value != 60 && value != 116; // thêm vị tr có thể đi  là 7
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
        return value == 16;
    }

    public static boolean canJumpTile(float x, float y, float width, float height, int[][] lvlData) {
        float left = x / Game.TILES_SIZE;
        float right = (x + width) / Game.TILES_SIZE;
        float top = y / Game.TILES_SIZE;
        float bottom = (y + height) / Game.TILES_SIZE;
            if (!isJumptile(right, bottom, lvlData))
                    if (!isJumptile(left, bottom, lvlData))
                        if (!isNextMap(right, top, lvlData))
                            if (!isNextMap(left, top, lvlData))
                        return true;
        return false;
    }
    private static boolean isJumptile(float xIndex, float yIndex, int[][] lvlData){
        if (xIndex < 0 || xIndex >= lvlData[0].length || yIndex < 0 || yIndex >= lvlData.length) {
            return true;
        }
        return isTileJumpTile((int) xIndex, (int) yIndex, lvlData);
    }
    public static boolean isTileJumpTile(int xTile, int yTile, int[][] lvData) { // thêm kiểm tra block jump
        int value = lvData[(int) yTile][(int) xTile];
        return value == 91 || value == 92 || value ==93 || value == 94 || value ==95 || value ==6;
    }


    public static float getEntityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed){
        int currentTile = (int) (hitBox.x/Game.TILES_SIZE);
        if(xSpeed > 0){
            int tileXPos =  currentTile*Game.TILES_SIZE;
            int xOffSet = (int) (Game.TILES_SIZE-hitBox.width);
            return tileXPos + xOffSet - 1;
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
            return tileYPos + yOffSet - 1 + Game.TILES_DEFAULT_SIZE;
        }
        else {
            return currentTile * Game.TILES_SIZE;
        }
    }
    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int [][] lvlData){
        float left = hitBox.x / Game.TILES_SIZE;
        float right = (hitBox.x + hitBox.width) / Game.TILES_SIZE;
        float top = hitBox.y / Game.TILES_SIZE;
        float bottom = (hitBox.y + hitBox.height + 1) / Game.TILES_SIZE;
        if (!isSolid(left, bottom, lvlData))
            if (!isSolid(right, bottom, lvlData))
                if (!isSolid(right, top, lvlData))
                    if (!isSolid(left, top, lvlData))
                        return false;
        return true;
    }
    public static boolean isEntityOnFloor1(Rectangle2D.Float hitBox, int [][] lvlData){
        float left = hitBox.x / Game.TILES_SIZE;
        float right = (hitBox.x + hitBox.width) / Game.TILES_SIZE;
        float top = hitBox.y / Game.TILES_SIZE;
        float bottom = (hitBox.y + hitBox.height+1) / Game.TILES_SIZE;
        if (!isJumptile(left, bottom, lvlData))
            if (!isJumptile(right, bottom, lvlData))
                if (!isJumptile(right, top, lvlData))
                    if (!isJumptile(left, top, lvlData))
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
    public static ArrayList<Potion> getPotions(int[][] lvlData){
        ArrayList<Potion> list = new ArrayList<>();
        for(int j = 0; j < lvlData.length; j++){
            for(int i = 0; i < lvlData[j].length; i++){
                int value = lvlData[j][i];
                if(value == RED_POTION || value == BLUE_POTION){ // || value == 82
                    list.add(new Potion(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }
            }
        }
        return list;
    }
    public static ArrayList<GameContainer> getContainers(int[][] lvlData){
        ArrayList<GameContainer> list = new ArrayList<>();
        for(int j = 0; j < lvlData.length; j++){
            for(int i = 0; i < lvlData[j].length; i++){
                int value = lvlData[j][i];
                if(value == BARREL || value == BOX){ // || value == 84
                    list.add(new GameContainer(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }
            }
        }
        return list;
    }
}
