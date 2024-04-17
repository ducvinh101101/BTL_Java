package levels;

import objects.Cannon;
import objects.GameContainer;
import objects.Potion;
import utilz.HelpMethods;

import java.util.ArrayList;

public class Level {
    private int[][] lvData;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Cannon> cannons;
    public Level(int[][] lvData){
        this.lvData=lvData;
        createPotions();
        createContainers();
        createCannons();
    }
    private void createCannons() {
        cannons = HelpMethods.getCannons(lvData);
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
    public ArrayList<Cannon> getCannons(){return cannons;}
}
