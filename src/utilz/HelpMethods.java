package utilz;

import Main.Game;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData){
        float left = x / Game.TILES_SIZE;
        float right = (x + width) / Game.TILES_SIZE;
        float top = y / Game.TILES_SIZE;
        float bottom = (y + height) / Game.TILES_SIZE;

        // Kiểm tra từng ô trong phạm vi di chuyển của nhân vật
        for (int i = (int) top; i <= (int) bottom; i++) {
            for (int j = (int) left; j <= (int) right; j++) {
                // Kiểm tra xem ô hiện tại có phải là vật cản hay không
                if (isSolid(j, i, lvlData)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSolid(float xIndex, float yIndex, int[][] lvlData){
        if (xIndex < 0 || xIndex >= lvlData[0].length || yIndex < 0 || yIndex >= lvlData.length) {
            return true;
        }
        int value = lvlData[(int)yIndex][(int)xIndex];
        return value !=80;
    }
}
