package utilz;

import Main.Game;

public class Constants {
    public static final float GRAVITY = 0.04f * Game.SCALE;
    public static final int ANI_SPEED = 10;
    public static class Projectiles{
        public static final int CANNON_BALL_DEFAULT_WIDTH = 15;
        public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;

        public static final int CANNON_BALL_WIDTH = (int) (Game.SCALE * CANNON_BALL_DEFAULT_WIDTH);
        public static final int CANNON_BALL_HEIGHT = (int) (Game.SCALE * CANNON_BALL_DEFAULT_HEIGHT);

        public static final float SPEED = 4f * Game.SCALE;
    }
    public static class EnemyConstants {
        public static final int DUMMY = 0;
        public static final int FROG = 1;
        public static final int CRAB = 2;
        public static final int REAPER = 3;
        public static final int SAMURAI = 4;
        public static final int TENGU = 5;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;
        public static final int MONSTER_WIDTH_DEFAULT = 30;
        public static final int MONSTER_HEIGHT_DEFAULT = 30;
        public static final int MONSTER_WIDTH = (int) (MONSTER_WIDTH_DEFAULT * Game.SCALE);
        public static final int MONSTER_HEIGHT = (int) (MONSTER_HEIGHT_DEFAULT * Game.SCALE);

        public static final int MONSTER_DRAWOFFSET_X = (int) (18 * Game.SCALE);
        public static final int MONSTER_DRAWOFFSET_Y = (int) (6 * Game.SCALE);

        public static int GetSpritesAmount(int enemy_type, int enemy_state) {
            switch (enemy_type) {
                case DUMMY:
                    switch (enemy_state) {
                        case IDLE, DEAD:
                            return 1;
                    }
                case CRAB:
                    switch (enemy_state) {
                        case IDLE, HIT, DEAD:
                            return 1;
                        case RUNNING, ATTACK:
                            return 2;
                    }
                case REAPER:
                    switch (enemy_state) {
                        case IDLE:
                            return 3;
                        case RUNNING, ATTACK: return 2;
                        case HIT,DEAD:
                            return 1;
                    }
                case SAMURAI:
                    switch (enemy_state) {
                        case IDLE:
                            return 1;
                        case RUNNING: return 2;
                        case ATTACK:
                            return 2;
                        case HIT,DEAD:
                            return 1;
                    }
                case TENGU:
                    switch (enemy_state) {
                        case IDLE:
                            return 6;
                        case RUNNING: return 8;
                        case ATTACK:
                            return 6;
                    }
            }
            return 0;
        }

        public static int getMaxHealth(int enemyType) {
            switch (enemyType) {
                case CRAB:
                    return 10;
                case REAPER: return 50;
                case SAMURAI: return 100;
                case TENGU: return 200;
                default:
                    return 1;

            }
        }

        public static int getEnemyDmg(int enemyType) {
            switch (enemyType) {
                case CRAB:
                    return 10;
                case REAPER: return 20;
                case SAMURAI: return 30;
                case TENGU: return 50;
                default:
                    return 1;
            }
        }
    }
    public static class ObjectConstants{
        public static final int RED_POTION = 21;
        public static final int BLUE_POTION = 22;
        public static final int BARREL = 60;
        public static final int BOX = 116;
        public static final int CANNON_LEFT = 100;
        public static final int CANNON_RIGHT = 101;

        public static final int RED_POTION_VALUE = 15;
        public static final int BLUE_POTION_VALUE = 10;

        public static final int CONTAINER_WIDTH_DEFAULT = 40;
        public static final int CONTAINER_HEIGHT_DEFAULT = 30;
        public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
        public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

        public static final int POTION_WIDTH_DEFAULT = 12;
        public static final int POTION_HEIGHT_DEFAULT = 16;
        public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
        public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);

        public static final int CANNON_WIDTH_DEFAULT = 40;
        public static final int CANNON_HEIGHT_DEFAULT = 26;
        public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
        public static final int CANNON_HEIHT  = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);
        public static int getSpriteAmount(int object_type){
            switch (object_type){
                case RED_POTION, BLUE_POTION:
                    return 7;
                case BARREL, BOX:
                    return 8;
                case CANNON_LEFT, CANNON_RIGHT:
                    return 7;
            }
            return 1;
        }
    }

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 210;
            public static final int B_HEIGHT_DEFAULT = 84;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT / 1.5 * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT / 1.5 * Game.SCALE);
        }

        public static class PauseButton {
            public static final int SOUND_SIZE_DEFAUL = (int)(42*1.5);
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAUL * Game.SCALE);
        }

        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = (int)(56*1.5);
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE* Game.SCALE);

        }

        public static class VolumeButtons {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT* Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }

    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int RUNNING = 5;
        public static final int IDLE = 5;
        public static final int JUMPPING = 1;
        public static final int FALLING = 1;
        public static final int JUMPPING_LEFT = 1;
        public static final int FALLING_LEFT = 1;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int DEAD = 6;
        public static final int ATTACK_1 = 5;
        public static final int AIR = 4;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;
        public static int getSpriteAmountPlayer(int player_action) {
            switch (player_action) {
                case DEAD:
                    return 1;

                default:
                    return 1;
            }
        }
    }
}
