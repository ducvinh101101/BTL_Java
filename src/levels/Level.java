package levels;

import objects.GameContainer;
import objects.Potion;
import utilz.HelpMethods;

import java.util.ArrayList;

public class Level {
    private int[][] lvData;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    public Level(int[][] lvData){
        this.lvData=lvData;
        createPotions();
        createContainers();
    }
    private void createContainers() {
        containers = HelpMethods.getContainers(lvData);
    }

    private void createPotions() {
        potions = HelpMethods.getPotions(lvData);
    }
    public int getSpriteIndex(int x, int y){
        return lvData[x][y];
    }
    public  int[][] getlvlData(){
        return lvData;
    }
    public ArrayList<Potion> getPotions() {
        return potions;
    }
    public ArrayList<GameContainer> getContainers() {
        return containers;
    }
}
