package model.utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static model.Game.COL;
import static model.Game.ROW;

public class LoadSave {
    public static final String PLAYER_AT = "player/playerAttackRight.png";
    public static final String PLAYER_AT_LEFT = "player/playerAttackLeft.png";
    public static final String PLAYER_ANI = "player/playerRight.png";
    public static final String PLAYER_RUNR = "player/pyrunright.png";
    public static final String PLAYER_RUNL = "player/pyrunleft.png";
    public static final String PLAYER_IML = "player/playerLeft.png";
    public static final String BACKGROUND_MAP_1 = "map/background.png";
    public static final String BACKGROUND_PAUSE_GAME = "map/8.jpg";
    public static final String TILESET_1 = "map/map1.png";

    public static final String PLAYER_IN_AIR = "player/inAir.png";

    public static final String PLAYER_JUMP = "player/jump.png";

    public static final String PLAYER_FALL = "player/falling.png";
    public static final String PLAYER_JUMP_LEFT = "player/jumpLeft.png";

    public static final String PLAYER_FALL_LEFT = "player/fallingLeft.png";
    public static final String MENU_BUTTON = "button/button_atlas.png";
    public static final String MENU_BACKGROUND = "button/menu_background.png";
    public static final String IN_BACKGROUND = "map/Ninja.png";
    public static final String PAUSE_BACKGROUND = "button/pause_menu.png";
    public static final String GAMEOVER_BACKGROUND = "button/gameover.png";
    public static final String OPTIONS_MENU = "button/options.png";
    public static final String SOUND_BUTTON = "button/sound_button.png";
    public static final String URM_BUTTON = "button/urm_buttons.png";
    public static final String VOLUME_BUTTON = "button/volume_buttons.png";

    public static final String CRAB_0 = "monster/15_0.png";
    public static final String CRAB_1 = "monster/15_1.png";
    public static final String CRAB_2 = "monster/15_2.png";
    public static final String CRAB_3 = "monster/15_3.png";
    public static final String CRAB_0_LEFT = "monster/15_0_L.png";
    public static final String CRAB_1_LEFT = "monster/15_1_L.png";
    public static final String CRAB_2_LEFT = "monster/15_2_L.png";
    public static final String CRAB_3_LEFT = "monster/15_3_L.png";
    public static final String DUMMY_1 = "monster/0_1.png";
    public static final String DUMMY_2 = "monster/0_2.png";
    public static final String REAPER_1 = "monster/179_0.png";
    public static final String REAPER_2 = "monster/179_1.png";
    public static final String REAPER_3 = "monster/179_2.png";
    public static final String REAPER_4 = "monster/179_3.png";
    public static final String REAPER_5 = "monster/179_4.png";
    public static final String REAPER_6 = "monster/179_5.png";
    public static final String REAPER_7 = "monster/179_6.png";
    public static final String REAPER_8 = "monster/179_7.png";
    public static final String SAMURAI_1 = "monster/82_0.png";
    public static final String SAMURAI_2 = "monster/82_1.png";
    public static final String SAMURAI_3 = "monster/82_2.png";
    public static final String SAMURAI_4 = "monster/82_3.png";
    public static final String SAMURAI_1_LEFT = "monster/82_0_L.png";
    public static final String SAMURAI_2_LEFT = "monster/82_1_L.png";
    public static final String SAMURAI_3_LEFT = "monster/82_2_L.png";
    public static final String SAMURAI_4_LEFT = "monster/82_3_L.png";
    public static final String REAPER_1_LEFT = "monster/179_0_L.png";
    public static final String REAPER_2_LEFT = "monster/179_1_L.png";
    public static final String REAPER_3_LEFT = "monster/179_2_L.png";
    public static final String REAPER_4_LEFT = "monster/179_3_L.png";
    public static final String REAPER_5_LEFT = "monster/179_4_L.png";
    public static final String REAPER_6_LEFT = "monster/179_5_L.png";
    public static final String REAPER_7_LEFT = "monster/179_6_L.png";
    public static final String REAPER_8_LEFT = "monster/179_7_L.png";
    public static final String TENGU_1 = "monster/Tengu1.png";
    public static final String TENGU_2 = "monster/Tengu2.png";
    public static final String TENGU_3 = "monster/Tengu3.png";
    public static final String TENGU_1_LEFT = "monster/Tengu1_L.png";
    public static final String TENGU_2_LEFT = "monster/Tengu2_L.png";
    public static final String TENGU_3_LEFT = "monster/Tengu3_L.png";
    public static final String WANDERER_1 = "monster/17_0.png";
    public static final String WANDERER_2 = "monster/17_1.png";
    public static final String WANDERER_3 = "monster/17_2.png";
    public static final String WANDERER_4 = "monster/17_3.png";
    public static final String WANDERER_1_LEFT = "monster/17_0_L.png";
    public static final String WANDERER_2_LEFT = "monster/17_1_L.png";
    public static final String WANDERER_3_LEFT = "monster/17_2_L.png";
    public static final String WANDERER_4_LEFT = "monster/17_3_L.png";

    public static final String STATUS_BAR = "health_power_bar.png";
    public static final String COMPLETED_IMG = "button/completed_sprite.png";
    public static final String POTION_ATLAS = "object/potions_sprites.png";
    public static final String CONTAINER_ATLAS = "object/objects_sprites.png";
    public static final String TILESET_2 = "map/map2.png";
    public static final String TILESET_3 = "map/map3.png";
    public static final String CANNON_ATLAS = "object/cannon_atlas.png";
    public static final String TRAP_ATLAS = "object/trap_atlas.png";

    public static final String CANNON_BALL = "object/ball.png";
    public static final String SHURIKEN = "object/shuriken.png";

    public static BufferedImage getImage(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try (is) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                throw new IOException("Image file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static int[][] getLevelData() {
        int[][] lvData = new int[ROW][COL];
        int[][] sampleMap = {
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	17,	52,	17, 17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	17,	52,	17, 17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	17,	52,	17, 17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	17,	52,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	17,	52,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	17,	52,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	17,	52,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	17,	52,	17, 17,	17, 17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	6,	6,	6,	6,	6,	6,	6,	6, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	94,	91,	91,	91, 91,	91,	91,	91,	91, 91,	91,	91,	91,	91,	95,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	47,	39, 42,	49,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	9,	17,	17,	9,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	96,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	97,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	8,	17,	17,	8,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	77,	54,	64,	64,	64,	64,	64,	54,	64,	64,	64,	64,	64,	54,	77,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	94,	91,	91,	91,	91,	91,	91,	95,	17,	8,	17,	17,	8,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	54,	58,	65,	65,	65,	58,	54,	58,	65,	65,	65,	58,	54,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	96,	90,	90,	90,	90,	90,	90,	97,	17,	7,	17,	17,	7,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	54,	57,	57,	57,	57,	57,	54,	57,	57,	57,	57,	57,	54,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	77,	17,	54,	17,	17,	54,	17,	77,	17,	7,	17,	17,	7,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	94,	91,	91,	91,	91,	91,	91, 91,	91,	91,	91,	91, 91,	91,	91,	91,	91, 91,	91, 91,	95,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	47,	39,	42,	51,	17,	17,	17},
                {17,17,	17,	17,	17,	17, 54,	17,	17,	54,	17,	17,	17,	7,	17,	17,	7,	17,	17,	17,	17,	17,	17,	17,	21,	17,	17,	17,	17,	17,	17,	17,	96,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	90,	97,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17, 54,	17,	17,	54,	22,	17,	17,	7,	17,	17,	7,	17,	17,	17,	17,	17,	6,	6,	6,	6,	6,	17,	17,	17,	17,	17,	77,	54,	64,	64,	64,	64,	64,	54,	64,	64,	64,	64,	64,	54,	64,	64,	64,	64,	64,	54,	77,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	17},
                {17,17,	17,	17,	17,	17, 54,	17,	17,	54,	28,	17,	17,	7,	17,	17,	7,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	54,	58,	65,	65,	65,	58,	54,	58,	65,	65,	65,	58,	54,	58,	65,	65,	65,	58,	54,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	16},
                {17,17,	17,	17,	53, 53,	53,	53,	53,	53,	53,	53,	17,	7,	17,	17,	7,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	54,	57,	57,	57,	57,	57,	54,	57,	57,	57,	57,	57,	54,	57,	57,	57,	57,	57,	54,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17,	17,	40,	41,	17,	17,	17,	16},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	17,	17, 17,	17,	17,	17,	17,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	17,	17, 17,	17,	17,	17,	17,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	17,	17, 17,	17,	17,	17,	17,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	17,	17, 17,	17,	17,	17,	17,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	17,	17, 17,	17,	17,	17,	17,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	17,	17, 17,	17,	17,	17,	17,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	17,	17, 17,	17,	17,	17,	17,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	17,	17, 17,	17,	17,	17,	6,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14, 17,	17, 17,	17,	17,	17,	17,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14},
                {14,14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	14,	14, 14,	14,	14,	14,	18,	18, 18,	18,	18,	18,	18,	14, 14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14,	14}
        };

        // Copy dữ liệu từ mảng mẫu vào mảng thực tế của game
        for (int i = 0; i < ROW; i++) {
            System.arraycopy(sampleMap[i], 0, lvData[i], 0, COL);
        }
        return lvData;
    }

    public static int[][] getLevelData2() { // thêm mảng map 2
        int[][] lvData = new int[ROW][COL];
        int[][] sampleMap = {
                {17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	52,	17,	116,	17,	52,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	52,	25,	25,	17,	52,	17,	17,	17,	17,	17,	17,	17,	25,	25,	25,	25,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	53},
                {17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	52,	17,	25,	25,	25,	25,	25,	17,	52,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	53,	53,	53,	53,	53,	17,	17,	17,	17,	17,	17,	17,	17,	53,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	53,	53,	17},
                {17,	52,	17,	17,	17,	52,	25,	25,	25,	25,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	53,	53,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	53,	53,	53,	17,	17,	17,	17,	17},
                {17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	25,	25,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	17,	17,	17,	17,	17,	17,	53,	17,	17,	17,	17,	17,	17,	17,	17,	17,	53,	53,	53,	53,	53,	17,	17,	17,	17,	17,	17,	17,	53,	53,	17,	17,	17,	17,	17,	53,	53,	17,	17,	17,	17},
                {17,	52,	17,	25,	17,	52,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	85,	17,	17,	17,	17,	53,	53,	53,	53,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	53,	53,	53,	53,	53,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	52,	17,	25,	25,	52,	17,	17,	17,	17,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	17,	17,	52,	17,	37,	1,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {6,	6,	6,	6,	6,	6,	6,	17,	17,	17,	17,	17,	6,	6,	6,	6,	6,	6,	6,	6,	6,	6,	6,	6,	6,	6,	6,	37,	70,	1,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	37,	70,	70,	1,	1,	84,	17,	17,	17,	17,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	119,	17,	17,	17,	17,	0,	1,	1,	1,	1,	1,	1,	1,	1,	1,	119,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	53,	53,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	37,	70,	70,	70,	70,	38,	17,	17,	17,	17,	17,	37,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	38,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	53,	53,	53,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	37,	70,	70,	70,	70,	38,	17,	17,	17,	17,	17,	17,	37,	70,	70,	70,	70,	70,	70,	70,	70,	70,	1,	1,	1,	119,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	94,	92,	92,	92,	95,	17,	17,	17,	17,	17,	17,	17,	37,	70,	70,	70,	38,	17,	17,	17,	17,	17,	17,	17,	17,	37,	70,	70,	70,	70,	70,	70,	38,	17,	17,	17,	17,	17,	17,	17,	17,	17,	0,	1,	1,	1,	119,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	94,	91,	91,	91,	91,	91,	95,	17,	17,	17,	17,	17,	17,	37,	70,	70,	38,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	37,	70,	70,	70,	38,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	0,	1,	1,	1,	119,	17},
                {17,	17,	17,	17,	17,	17,	94,	92,	92,	95,	17,	17,	17,	17,	17,	54,	55,	66,	55,	54,	17,	17,	17,	17,	17,	17,	17,	37,	70,	38,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	37,	70,	42,	17,	17,	17,	17,	98,	17,	50,	48,	51,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	94,	91,	91,	91,	91,	95,	17,	17,	17,	17,	54,	57,	67,	57,	54,	17,	17,	17,	17,	17,	17,	17,	37,	38,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	83,	17,	17,	17,	98,	98,	17,	48,	48,	48,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	54,	66,	66,	54,	17,	17,	17,	17,	94,	93,	93,	93,	93,	93,	95,	17,	17,	17,	17,	17,	17,	37,	17,	17,	17,	17,	0,	1,	1,	1,	1,	119,	17,	17,	17,	17,	17,	17,	17,	83,	98,	17,	17,	17,	17,	17,	47,	43,	49,	17,	17,	17,	1,	1,	1,	1,	119,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	54,	65,	65,	54,	17,	17,	17,	94,	91,	91,	91,	91,	91,	91,	91,	95,	17,	17,	17,	17,	17,	37,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	83,	98,	98,	17,	17,	17,	17,	17,	82,	17,	17,	0,	1,	70,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	94,	93,	93,	93,	93,	95,	17,	17,	17,	54,	55,	55,	66,	55,	55,	54,	17,	17,	17,	17,	17,	17,	37,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	50,	46,	51,	17,	17,	17,	17,	17,	17,	82,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	94,	91,	91,	91,	91,	91,	91,	95,	17,	17,	54,	55,	55,	66,	55,	55,	54,	17,	17,	17,	17,	17,	17,	37,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	48,	48,	48,	17,	17,	98,	17,	17,	17,	82,	98,	17,	17,	17,	17,	17,	17,	17,	17,	17,	0,	1,	1,	1,	119,	17},
                {17,	17,	17,	17,	17,	54,	65,	66,	66,	65,	54,	17,	17,	17,	54,	57,	57,	67,	57,	57,	54,	17,	94,	92,	92,	92,	95,	37,	1,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	47,	48,	49,	17,	17,	17,	17,	17,	98,	82,	17,	0,	1,	1,	119,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	54,	65,	66,	66,	65,	54,	17,	17,	4,	1,	1,	1,	1,	1,	1,	1,	17,	17,	78,	16,	78,	17,	37,	70,	1,	1,	119,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	82,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	54,	67,	61,	61,	57,	54,	4,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	17,	77,	16,	77,	17,	37,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	98,	17,	39,	82,	42,	17,	17,	17,	17,	17,	0,	1,	119,	17,	17,	17,	17,	17,	17,	17},
                {4,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	70,	1,	1,	1,	1,	1,	1,	1,	3},
                {19,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	20},
                {19,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	20},
                {19,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	20},
                {19,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	70,	20},
                {19,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	71,	20}


        };

        // Copy dữ liệu từ mảng mẫu vào mảng thực tế của game
        for (int i = 0; i < ROW; i++) {
            System.arraycopy(sampleMap[i], 0, lvData[i], 0, COL);
        }
        return lvData;
    }

    public static int[][] getLevelData3() { //thêm mảng map 3
        int[][] lvData = new int[ROW][COL];
        int[][] sampleMap = {
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	116,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	111,	17,	17,	17,	17,	17,	17,	6,	6,	6,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	116,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	6,	6,	6,	6,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	87,	87,	87,	87,	87,	87,	87,	87,	87,	87,	87,	87,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	21,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	110,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	60,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	35,	36,	17,	41,	17,	17,	41,	17,	35,	36,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	87,	87,	87,	87,	17,	17,	17,	17,	17,	17,	17,	17,	17,	6,	6,	6,	6,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17, 98,	92,	92,	92,	92,	92,	92,	99,	17,	17,	17,	17,	17,	17,	17,	17,	17,	39,	35,	36,	40,	41,	17,	17,	41,	39,	35,	36,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	98,	93,	93,	93,	93,	93,	93,	93,	93,	99,	17,	17,	17,	17,	17,	17,	17,	17,	17,	35,	36,	17,	41,	17,	17,	41,	17,	35,	36,	17,	17,	17,	116,	17,	17,	17,	17,	17},
                {87,	87,	87,	87,	87,	87,	87,	87,	87,	87,	87,	17,	17,	17,	17,	17,	87,	87,	87,	87,	87,	87,	87,87,87,87,	17,17, 	17,	17,	17,	17,	100,	94,	94,	94,	94,	94,	94,	94,	94,	101,17,	17, 17,	17,	17,	17,	17,	17,	17,	35,	36,	17,	41,	17,	17,	41,	17,	35,	36,	40,	6,	6,	6,17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	41,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	41,	17,	17,	41,	17,	17,	35,	36,	17,	17,	17,	17,	17,	17,	80,	43,	55,	55,	64,	64,	55,	55,	43,	80,	17,	17,	17,	17,	17,	17,	17,	17,	39,	35,	36,	17,	41,	17,	17,	41,	17,	35,	36,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	41,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	41,	109,	17,	41,	17,	17,	35,	36,	40,	17,	17,	17,	17,	17,	17,	43,	56,	56,	66,	66,	56,	56,	43,	17,	17,	17, 17,	17,	17,	17,	17,	17,	17,	35,	36,	17,	41,	17,	102,	41,	17,	35,	36,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	41,	111, 17,	17,	17,	17,	17,	6,	6,	6,	6,	6,	6,	17,	41,	17,	17,	35,	36,	17,	17,	17,17,	17,	98,	93,	93,	93,	93,	93,	93,	93,	93,	93,	93,	99,	17,	17,	17,	17,	17,	17,	17,	17,	35,	36,	17,	102,	102,	102,	41,	17,	35,	36,	17,	17,	17,	17,	17,	17,	17,	6,	6},
                {17,	17,	17,	17,	17,	17,	6,	6,	6,	6,	6,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	41,	17,	39,	35,	36,	17,	17,	17, 17,	17,	100,	94,	94,	94,	94,	94,	94,	94,	94,	94,	94,	101,	17,	17,	17,	17,	17,	17,	17,	17,	35,	36,	30,	6,	6,	6,	30,	17,	35,	36,	17,	17,	17,	17,	17,	17,	17,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	109,	17,	17,	17,	17,	17,	17,	17,	17,	41,	17,	17,	35,	36,	17,	17,	17,	17, 17, 	80,	43,	55,	55,	57,	64,	64,	57,	55,	55,	43,	80,	17,	17,	17,	17,	17,	17,	17,	102,	35,	36,	102,	17,	17,	30,	41,	17,	35,	36,	17,	17,	17,	17,	17,	17,	17,	22,	17},
                {17,	17,	17,	17,	17,	17,	17,	98, 93,	93,	93,	93,	93,	99,	17,	17,	30,	17,	17,	17,	17,	41,	17,	17,	35,	36,	6,	6,	17,	17, 17,	17,	43,	56,	56,	56,	66,	66,	56,	56,	56,	43,	17,	17,	17,	17,	17, 17,	17,	102,	102,	35,	36,	102,	25, 17,	6,	6,	39,	35,	36,	40,	17,	17,	17,	17,	17,	6,	6,	6},
                {17,	17,	17,	17,	17,	17,	17,	100, 94, 94, 94, 94, 94, 101, 17,	17,	18,	17,	17,	17,	17,	41,	17,	39,	35,	36,	17,	17,	17,	17, 17,	42,	42,	42,	42, 42,	42,	42,	42,	42,	42,	42,	42,	17,	17,	17, 17,	17,	17,	0,	10,	10,	10,	10,	3,	17,	17,	17,	17,	35,	36,	17,	17,	17,	17,	17,	17,	16,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	80,	43,	17,	17,	17,	43,	80,	17,	30,	19,	30,	17,	17,	17,	41,	17,	17,	35,	36,	17,	17,	17,	17,	17,	17,	43,	17,	17,	43,	17,	17,	43,	17,	17,	43,	17,	17,	17,	17,	17,	17,	17,	17,	8,	11,	11,	12,	9,	 25,	25,	25,	17,	35,	36,	17,	17,	17,	17,	16,	102,	16,	17,	17},
                {17,	17,	17,	17,	17,	17,	17,	17,	43,	17,	17,	17,	43,	17,	17,	30,	18,	30,	17,	17,	17,	41,	17,	39,	35,	36,	6,	6,	6,	17,	17,	17,	43,	17,	17,	43,	17,	17,	43,	17,	17,	43,	17,	17,	17,	17,	17,	17,	17,	2,	10,	11, 12,	11,	 2,	10,	10,	10,	 1,	35,	36,	17,	17,	98,	93,	93,	93,	93,	93,	93},
                {17,	17,	17,	17,	17,	17,	17,	17,	43,	17,	17,	17,	43,	17,	17,	17,	19,	17,	17,	17,	17,	41,	17,	17,	35,	36,	40,	17,	17,	17,	98,	92,	92,	92,	92,	92,	92,	92,	92,	92,	92,	92,	92,	99,	17,	17,	17,	17,	 0,	10,	10,	 3,	11,	 11,	8,	11,	 12,	11,	 102,	35,	 36,	17,	98,	93,	93,	93,	93,	93,	93,	93},
                {17,	17,	17,	17,	17,	17,	17,	17,	43,	17,	17,	29,	43,	17,	17,	17,	18,	17,	17,	17,	6,	6,	6,	6,	35,	36,	17,	17,	17,	98,	93,	93,	93,	93,	93,	93,	93,	93,	93,	93,	93,	93,	93,	93,	99,	17,	17,	17,	17,	 8,	11,	 9,	11,	2,	10,	3,	11,	9,	102,	102,	36,	17,	93,	93,	93,	93,	93,	93,	93,	93},
                {17,	17,	17,	17,	17,	17,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	18,	17,	17,	17,	17,	17,	17,	17,	35,	36,	6,	6,	17,	100,	94,	94,	94,	94,	94,	94,	94,	94,	94,	94,	94,	94,	94,	94,	101,	17,	17,	 0,	10,	10,	 3,	 9,	11,	 8,	11,	9,	11,	 2,	10,	10,	 3,	17,	17,	43,	56,	56,	66,	66,	56,	56},
                {17,	17,	17,	17,	17,	17,	11,	11,	11,	11,	11,	11,	11,	11,	10,	10,	10,	10,	1,	17,	17,	17,	17,	17,	35,	36,	17,	17,	17,	80,	43,	57,	65,	65,	57,	43,	55,	55,	43,	57,	65,	65,	57,	43,	80,	17,	17,	17,	 8, 11,  2,	10,	10,	 3,	11,	 2,	10,	 3, 11,	12, 11,	9,	102,	43,	56,	56,	66,	66,	56,	56},
                {17,	17,	17,	17,	5,	10,	10, 10,	10,	10,	10,	10,	11,	11,	11,	11,	11,	11,	17,	17,	17,	17,	17,	17,	35,	36,	17,	17,	17,	17,	43,	66,	66,	66,	66,	43,	55,	55,	43,	66,	66,	66,	66,	43,	17,	17,	2,	 10,	10,	10,	3,	11,	12,	 9,	11,	 8,	11,	 9,	11,	11,	 5,	10,	10,	10,	10,	10,	10,	10,	10,	3},
                {10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10,	10},
                {14,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11},
                {14,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	12,	11,	11,	11,	11, 11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11},
                {14,	11,	11,	11,	11,	11,	11,	11,	11,12,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	12,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11},
                {14,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	12,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11},
                {14,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11,	11}
        };

        // Copy dữ liệu từ mảng mẫu vào mảng thực tế của game
        for (int i = 0; i < ROW; i++) {
            System.arraycopy(sampleMap[i], 0, lvData[i], 0, COL);
        }
        return lvData;
    }

}
