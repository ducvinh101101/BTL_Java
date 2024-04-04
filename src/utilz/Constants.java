package utilz;

public class Constants {
    public static class PlayerConstants{
        public static final int RUNNING = 0;
        public static final int IDLE = 1;
        public static final int JUMPPING =2;
        public static final int FALLING = 3;
        public static final int GROUND =4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int getSpriteAmount(int player_action){
            switch (player_action){
                case RUNNING:
                    return 5;
                default:
                    return 1;
            }
        }
    }


}
