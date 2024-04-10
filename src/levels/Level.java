package levels;

public class Level {
    private int[][] lvData;
    public Level(int[][] lvData){
        this.lvData=lvData;
    }
    public int getSpriteIndex(int x, int y){
        return lvData[x][y];
    }
    public  int[][] getlvlData(){
        return lvData;
    }
}
