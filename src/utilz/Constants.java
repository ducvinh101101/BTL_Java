package utilz;

import Main.Game;

public class Constants {
    public static final float GRAVITY = 0.01f * Game.SCALE;
    public static final int ANI_SPEED = 10;
    public static class EnemyConstants {
        public static final int DUMMY = 0;
        public static final int FROG = 1;
        public static final int CRAB = 2;

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
                        case IDLE:
                            return 9;
                        case DEAD:
                            return 5;
                    }
                case CRAB:
                    switch (enemy_state) {
                        case IDLE:
                            return 1;
                        case RUNNING:
                            return 1;
                        case ATTACK:
                            return 1;
                        case HIT:
                            return 1;
                        case DEAD:
                            return 1;
                    }
            }
            return 0;
        }

        public static int getMaxHealth(int enemyType) {
            switch (enemyType) {
                case CRAB:
                    return 10;
                default:
                    return 1;
            }
        }

        public static int getEnemyDmg(int enemyType) {
            switch (enemyType) {
                case CRAB:
                    return 10;
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
        public static int getSpriteAmount(int object_type){
            switch (object_type){
                case RED_POTION, BLUE_POTION:
                    return 7;
                case BARREL, BOX:
                    return 8;
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
            public static final int SOUND_SIZE_DEFAUL = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAUL * Game.SCALE);
        }

        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

        }

        public static class VolumeButtons {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
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
        public static final int ATTACK_1 = 5;
        public static final int AIR = 4;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;
    }
}
