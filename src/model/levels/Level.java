package model.levels;

import model.objects.Cannon;
import model.objects.GameContainer;
import model.objects.Potion;
import model.objects.Spike;
import model.utilz.HelpMethods;

import java.util.ArrayList;

public class Level {
    private int[][] lvData;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Cannon> cannons;
    private ArrayList<Spike> spikes;
    public Level(int[][] lvData){
        this.lvData=lvData;
        createPotions();
        createContainers();
        createCannons();
        createSpike();
    }
    private void createSpike() {
        spikes = HelpMethods.getSpikes(lvData);
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
    public ArrayList<Spike> getSpikes(){return spikes;}
}
