package main;

public class GameSettings {

        public static final int TILE_TEXTURE_SIZE = 16;
        public static final int TEXTURE_SCALE     = 4;
        public static final int TILE_SIZE = TILE_TEXTURE_SIZE * TEXTURE_SCALE;

        public static final int MAX_SCREEN_COL = 16;
        public static final int MAX_SCREEN_ROW = 12;

        public static final int SCREEN_WIDTH  = TILE_SIZE * MAX_SCREEN_COL;
        public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

        public static final int FPS = 60;

        // WORLD SETTINGS
        public static final int MAX_WORLD_COL = 50;
        public static final int MAX_WORLD_ROW = 50;

        private GameSettings() {}
}
