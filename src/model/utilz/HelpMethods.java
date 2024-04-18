package model.utilz;

import model.Game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import model.objects.*;

import static model.utilz.Constants.ObjectConstants.*;
import static model.utilz.Constants.ObjectConstants.BOX;

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
    public static boolean isProjectileHittingLevel(Projectile p, int[][] lvlData){
        return isSolid(p.getHitBox().x + p.getHitBox().width / 2, p.getHitBox().y + p.getHitBox().height / 2,lvlData);
    }

    public static boolean isTileSolid(int xTile, int yTile, int[][] lvData) {
        int value = lvData[(int) yTile][(int) xTile];
        return value != 17 && value !=16 && value!=52 && value != 91 && value != 6 && value != 92 && value !=93 && value != 94 && value !=95 && value != 21 && value != 22 && value != 60 && value != 116 && value !=54 && value !=61 && value !=66 && value !=65 && value !=57 && value !=67 && value !=78 && value !=77 && value !=55&& value !=7&& value !=8&& value !=9&& value !=40&& value !=41&& value !=56&& value !=58&& value !=64&& value !=67&& value !=68&& value !=69&& value !=59&& value !=60&& value !=110&& value !=111&& value !=109&& value !=90&& value !=43&& value !=35&& value !=36&& value !=39&& value !=40&& value !=18&& value !=19 && value != 80 && value !=102&& value !=25&& value !=11&& value !=8&& value !=9 && value !=12d;
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

    public static boolean isFloor(Rectangle2D.Float hitBox, float xSpeed, int[][] lvData) {
        if(xSpeed > 0) return isSolid(hitBox.x + hitBox.width + xSpeed, hitBox.y + hitBox.height + 1, lvData);
        else return isSolid(hitBox.x + xSpeed, hitBox.y + hitBox.height + 1, lvData);
    }
    public static boolean canCannonSeePlayer(int[][] lvData, Rectangle2D.Float firstHitBox, Rectangle2D.Float secondHitBox, int yTile){
        int firstXTile = (int) (firstHitBox.x / Game.TILES_SIZE);
        int secondXTile = (int) (secondHitBox.x / Game.TILES_SIZE);
        if (firstXTile > secondXTile) return isAllTilesClear(secondXTile, firstXTile, yTile, lvData);
        else return isAllTilesClear(firstXTile, secondXTile, yTile, lvData);
    }
    public static boolean isAllTilesClear(int xStart, int xEnd, int y, int[][] lvData){
        for (int i = 0; i < xEnd - xStart; i++) {
            if (isTileSolid(xStart + i, y, lvData)) return false;
        }
        return true;
    }
    public static boolean isAllTileWalkable(int xStart, int xEnd, int y, int[][] lvData) {
        for (int i = 0; i < xEnd - xStart; i++) {
            if (isTileSolid(xStart + i, y, lvData)) return false;
        }
        return true;
    }
//    public static boolean isSightClear(int[][] lvData, Rectangle2D.Float firstHitBox, Rectangle2D.Float secondHitBox, int yTile) {
//        int firstXTile = (int) (firstHitBox.x / Game.TILES_SIZE);
//        int secondXTile = (int) (secondHitBox.x / Game.TILES_SIZE);
//        if (firstXTile > secondXTile) return isAllTileWalkable(secondXTile, firstXTile, yTile, lvData);
//        else return isAllTileWalkable(firstXTile, secondXTile, yTile, lvData);
//    }
public static boolean isSightClear(int[][] lvData, Rectangle2D.Float firstHitBox, Rectangle2D.Float secondHitBox) {
    int firstXTile = (int) (firstHitBox.getCenterX() / Game.TILES_SIZE);
    int secondXTile = (int) (secondHitBox.getCenterX() / Game.TILES_SIZE);
    int startYTile = (int) (firstHitBox.y / Game.TILES_SIZE);
    int endYTile = (int) (secondHitBox.y / Game.TILES_SIZE);
    int startY = Math.min(startYTile, endYTile);
    int endY = Math.max(startYTile, endYTile);

    if (firstXTile > secondXTile) {
        for (int x = secondXTile; x <= firstXTile; x++) {
            for (int y = startY; y <= endY; y++) {
                if (isTileSolid(x, y, lvData)) {
                    return false;
                }
            }
        }
    } else {
        for (int x = firstXTile; x <= secondXTile; x++) {
            for (int y = startY; y <= endY; y++) {
                if (isTileSolid(x, y, lvData)) {
                    return false;
                }
            }
        }
    }
    return true;
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
    public static ArrayList<Cannon> getCannons(int[][] lvlData){
        ArrayList<Cannon> list = new ArrayList<>();
        for(int j = 0; j < lvlData.length; j++){
            for(int i = 0; i < lvlData[j].length; i++){
                int value = lvlData[j][i];
                if(value == CANNON_LEFT || value == CANNON_RIGHT){
                    list.add(new Cannon(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }
            }
        }
        return list;
    }
    public static ArrayList<Spike> getSpikes(int[][] lvlData){
        ArrayList<Spike> list = new ArrayList<>();
        for(int j = 0; j < lvlData.length; j++){
            for(int i = 0; i < lvlData[j].length; i++){
                int value = lvlData[j][i];
                if(value == SPIKE){
                    list.add(new Spike(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                }
            }
        }
        return list;
    }
}
