package utilz;

import Main.Game;

public class Constants {
    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }
        public static class PauseButton{
            public static final int SOUND_SIZE_DEFAUL = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAUL*Game.SCALE);
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
    public static class Directions{
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int RUNNING = 5;
        public static final int IDLE = 5;
        public static final int JUMPPING =1;
        public static final int FALLING = 1;
        public static final int JUMPPING_LEFT =1;
        public static final int FALLING_LEFT = 1;
        public static final int GROUND =4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 5;
        public static final int AIR = 4;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;
    }
}
