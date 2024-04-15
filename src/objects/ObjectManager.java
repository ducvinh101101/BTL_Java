package objects;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.ObjectConstants.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {
    private Playing playing;
    private BufferedImage[][] potionImgs, containerImgs;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private Level currentLevel;

    public ObjectManager(Playing playing){
        this.playing = playing;
        this.currentLevel = playing.getLevelManager().getCurrenLevel();
        loadImgs();
        //loadObject(currentLevel);

    }
    public void loadObject(Level newLevel) {
        this.currentLevel = newLevel;
        potions = new ArrayList<>(newLevel.getPotions());
        containers = new ArrayList<>(newLevel.getContainers());
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
//        else{
//            playing.getPlayer().changePower(BLUE_POTION_VALUE);
//        }
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
        BufferedImage potionSprite = LoadSave.getSpriteAlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];
        for(int j = 0; j < potionImgs.length; j++){
            for(int i = 0; i < potionImgs[j].length; i++){
                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);
            }
        }
        BufferedImage containerSprite = LoadSave.getSpriteAlas(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][8];
        for(int j = 0; j < containerImgs.length; j++){
            for(int i = 0; i < containerImgs[j].length; i++){
                containerImgs[j][i] = containerSprite.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }
    }

    public void update(){
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
    }
    public void draw(Graphics g, int xLevelOffset, int yLevelOffset){
        drawPotions(g, xLevelOffset, yLevelOffset);
        drawContainer(g, xLevelOffset, yLevelOffset);
    }
    public void setCurrentLevel(Level newLevel){
        this.currentLevel = newLevel;
        loadObject(currentLevel);
    }

    public void resetAllObjects(){
        for(Potion p : potions){
            p.reset();
        }
        for(GameContainer gc : containers){
            gc.reset();
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
//                if (type >= 0 && type < containerImgs.length) {
//                    int aniIndex = gc.getAniIndex();
//
//                    // Đảm bảo aniIndex nằm trong khoảng hợp lệ của mảng containerImgs[type]
//                    if (aniIndex >= 0 && aniIndex < containerImgs[type].length) {
//                        g.drawImage(containerImgs[type][aniIndex],
//                                (int) (gc.getHitbox().x - gc.getxDrawOffset() - xLevelOffset),
//                                (int) (gc.getHitbox().y - gc.getyDrawOffset()),
//                                CONTAINER_WIDTH,
//                                CONTAINER_HEIGHT,
//                                null);
//                    } else {
//                        // Xử lý khi aniIndex vượt quá giới hạn của mảng containerImgs[type]
//                        System.out.println("aniIndex out of bounds for containerImgs[type]");
//                    }
//                }
            }
        }
    }


}
