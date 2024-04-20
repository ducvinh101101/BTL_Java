package model.objects;

import model.Game;
import model.entities.Player;
import model.gamestates.Playing;
import model.maps.Map;
import model.utilz.LoadSave;
import static model.utilz.Constants.ObjectConstants.*;
import static model.utilz.Constants.Projectiles.CANNON_BALL_HEIGHT;
import static model.utilz.Constants.Projectiles.CANNON_BALL_WIDTH;
import static model.utilz.HelpMethods.canCannonSeePlayer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {
    private Playing playing;
    private BufferedImage[][] potionImgs, containerImgs;
    private BufferedImage[] cannonImgs;
    private BufferedImage cannonBallImgs, spikeImg;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Cannon> cannons;
    private ArrayList<Spike> spikes;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private Map currentMap;

    public ObjectManager(Playing playing){
        this.playing = playing;
        this.currentMap = playing.getLevelManager().getCurrenLevel();
        loadImgs();
        //loadObject(currentLevel);

    }
    public void loadObject(Map newMap) {
        this.currentMap = newMap;
        potions = new ArrayList<>(newMap.getPotions());
        containers = new ArrayList<>(newMap.getContainers());
        cannons = new ArrayList<>(newMap.getCannons());
        spikes = newMap.getSpikes();
        projectiles.clear();
    }
    public void checkSpikesTouched(Player p){
        for(Spike s : spikes){
            if(s.getHitbox().intersects(p.getHitBox())){
                p.kill();
            }
        }
    }

    public void checkObjectTouched(Rectangle2D.Float hitBox){
        for(Potion p : potions){
            if(p.isActive()){
                if(hitBox.intersects(p.getHitbox())){
                    p.setActive(false);
                    applyEffectToPlayer(p);
                }
            }
        }
    }

    public void applyEffectToPlayer(Potion p){
        if(p.getObjType() == RED_POTION){
            playing.getPlayer().changeHealth(RED_POTION_VALUE);
        }
        else{
            playing.getPlayer().changePower(BLUE_POTION_VALUE);
        }
    }
    public void checkObjectHit(Rectangle2D.Float attackBox){
        for(GameContainer gc : containers){
            if(gc.isActive()){
                if(gc.getHitbox().intersects(attackBox)){
                    gc.setAnimation(true);
                    int type = 21;
                    if(gc.getObjType() == BARREL){
                        type = 22;
                    }
                    potions.add(new Potion((int)(gc.getHitbox().x + gc.getHitbox().width / 2),  (int)(gc.getHitbox().y - gc.getHitbox().height / 2), type));
                    return;
                }
            }
        }
    }

    private void loadImgs() {
        BufferedImage potionSprite = LoadSave.getImage(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];
        for(int j = 0; j < potionImgs.length; j++){
            for(int i = 0; i < potionImgs[j].length; i++){
                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);
            }
        }
        BufferedImage containerSprite = LoadSave.getImage(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][8];
        for(int j = 0; j < containerImgs.length; j++){
            for(int i = 0; i < containerImgs[j].length; i++){
                containerImgs[j][i] = containerSprite.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }
        cannonImgs = new BufferedImage[7];
        BufferedImage temp = LoadSave.getImage(LoadSave.CANNON_ATLAS);
        for(int i = 0; i < cannonImgs.length; i++){
            cannonImgs[i] = temp.getSubimage(i * 40, 0, 40, 26);
        }
        cannonBallImgs = LoadSave.getImage(LoadSave.CANNON_BALL);
        spikeImg = LoadSave.getImage(LoadSave.TRAP_ATLAS);
    }

    public void update(int[][] lvlData, Player player){
        for(Potion p : potions){
            if(p.isActive()){
                p.update();
            }
        }
        for(GameContainer gc : containers){
            if(gc.isActive()){
                gc.update();
            }
        }
        updateCannons(lvlData,player);
        updateProjectiles(lvlData,player);
    }
    private void updateProjectiles(int[][] lvlData, Player player) {
        for(Projectile p : projectiles){
            if(p.isActive()){
                p.updatePos();
                if (p.getHitBox().intersects(player.getHitBox())) {
                    player.changeHealth(-25);
                    p.setActive(false);
                }
//                else if (isProjectileHittingLevel(p, lvlData))
//                    p.setActive(false);
            }
        }
    }
    private boolean isPlayerInRange(Cannon c, Player player) {
        int absValue = (int) Math.abs(player.getHitBox().x - c.getHitbox().x);
        return absValue <= Game.TILES_SIZE * 5;

    }
    private boolean isPlayerInFrontOfCannon(Cannon c, Player player) {
        if(c.getObjType() == CANNON_LEFT){
            if(c.getHitbox().x > player.getHitBox().x){
                return true;
            }
        }else if(c.getHitbox().x < player.getHitBox().x){
            return true;
        }
        return false;
    }
    private void updateCannons(int[][] lvlData, Player player) {
        for(Cannon c : cannons){
            if(!c.doAnimation){
                if(c.getTileY() == player.getTileY()){
                    if(isPlayerInRange(c,player)){
                        if(isPlayerInFrontOfCannon(c,player)){
                            if(canCannonSeePlayer(lvlData, player.getHitBox(), c.getHitbox(),c.getTileY())){
                                c.setAnimation(true);
                            }
                        }
                    }
                }
            }
            c.update();
            if(c.getAniIndex() == 4 && c.getAniTick() == 0){
                shootCannon(c);
            }
        }
    }
    private void shootCannon(Cannon c) {
        int dir = 1;
        if(c.getObjType() == CANNON_LEFT){
            dir = -1;
        }
        projectiles.add(new Projectile((int)c.getHitbox().x, (int)c.getHitbox().y,dir));
    }
    public void draw(Graphics g, int xLevelOffset, int yLevelOffset){
        drawPotions(g, xLevelOffset, yLevelOffset);
        drawContainer(g, xLevelOffset, yLevelOffset);
        drawCannons(g,xLevelOffset,yLevelOffset);
        drawProjectiles(g,xLevelOffset,yLevelOffset);
        drawTrap(g,xLevelOffset,yLevelOffset);
    }
    public void setCurrentLevel(Map newMap){
        this.currentMap = newMap;
        loadObject(currentMap);
    }

    public void resetAllObjects(){
        loadObject(currentMap);
        for(Potion p : potions){
            p.reset();
        }
        for(GameContainer gc : containers){
            gc.reset();
        }
        for(Cannon c : cannons){
            c.reset();
        }
    }
    private void drawTrap(Graphics g, int xLevelOffset, int yLevelOffset) {
        for(Spike s : spikes){
            g.drawImage(spikeImg,(int) (s.getHitbox().x - xLevelOffset),(int) (s.getHitbox().y - yLevelOffset), SPIKE_WIDTH,SPIKE_HEIGHT, null );
        }
    }
    private void drawProjectiles(Graphics g, int xLevelOffset, int yLevelOffset) {
        for(Projectile p : projectiles){
            if(p.isActive()){
                g.drawImage(cannonBallImgs, (int)p.getHitBox().x - xLevelOffset,(int)(p.getHitBox().y) - yLevelOffset, CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT, null);
            }
        }
    }
    private void drawCannons(Graphics g, int xLevelOffset, int yLevelOffset) {
        for(Cannon c : cannons){
            int x = (int)(c.getHitbox().x - xLevelOffset);
            int y = (int)(c.getHitbox().y - yLevelOffset);
            int width = CANNON_WIDTH;
            if(c.getObjType() == CANNON_RIGHT){
                x += width;
                width *= -1;
            }
            g.drawImage(cannonImgs[c.getAniIndex()],x,y,width,CANNON_HEIHT,null);
        }
    }
    private void drawPotions(Graphics g, int xLevelOffset, int yLevelOffset){
        for(Potion p : potions){
            if(p.isActive()){
                int type = 0;
                if(p.getObjType() == RED_POTION){
                    type = 1;
                }
                g.drawImage(potionImgs[type][p.getAniIndex()],
                        (int)(p.getHitbox().x - p.getxDrawOffset() - xLevelOffset),
                        (int)(p.getHitbox().y - p.getyDrawOffset()) - yLevelOffset,
                        POTION_WIDTH,
                        POTION_HEIGHT,
                        null);

            }
        }
    }
    private void drawContainer(Graphics g, int xLevelOffset, int yLevelOffset){
        for(GameContainer gc : containers){
            if(gc.isActive()){
                int type = 0;
                if(gc.getObjType() == BARREL){
                    type = 1;
                }
                g.drawImage(containerImgs[type][gc.getAniIndex()],
                        (int)(gc.getHitbox().x - gc.getxDrawOffset() - xLevelOffset),
                        (int)(gc.getHitbox().y - gc.getyDrawOffset()) - yLevelOffset,
                        CONTAINER_WIDTH,
                        CONTAINER_HEIGHT,
                        null);

            }
        }
    }


}
