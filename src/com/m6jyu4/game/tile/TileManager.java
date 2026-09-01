package com.m6jyu4.game.tile;

import com.m6jyu4.game.GamePanel;
import com.m6jyu4.game.GameSettings;
import com.m6jyu4.game.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

        GamePanel gamePanel;
        public Tile[] tile;
        public int mapTileType[][];

        public TileManager(GamePanel gamePanel) {
                this.gamePanel = gamePanel;

                tile = new Tile[50];
                mapTileType = new int[GameSettings.MAX_WORLD_COL][GameSettings.MAX_WORLD_ROW];

                registerTile();
                loadMap("/map/map03.txt");
        }

        private BufferedImage loadTexture(String path) {
                BufferedImage image = null;
                try {
                        image = ImageIO.read(getClass().getResourceAsStream(path));
                } catch (IOException e) {
                        System.err.println("Error loading image: " + path);
                        e.printStackTrace();
                }
                return image;
        }

        private void loadMap(String path) {
                try {
                        InputStream is = getClass().getResourceAsStream(path);
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));

                        int col = 0;
                        int row = 0;

                        while(col < GameSettings.MAX_WORLD_COL && row < GameSettings.MAX_WORLD_ROW) {
                                String line = br.readLine();
                                while(col < GameSettings.MAX_WORLD_COL) {
                                        String number[] = line.split(" ");

                                        int num = Integer.parseInt(number[col]);

                                        mapTileType[col][row] = num;
                                        col ++;
                                }
                                if (col == GameSettings.MAX_WORLD_COL) {
                                        col = 0;
                                        row ++;
                                }
                        }
                        br.close();
                } catch (Exception e) {

                }
        }

        private void registerTile() {

                getTileTexture(0, "grass00");
                getTileTexture(1, "grass00");
                getTileTexture(2, "grass00");
                getTileTexture(3, "grass00");
                getTileTexture(4, "grass00");
                getTileTexture(5, "grass00");
                getTileTexture(6, "grass00");
                getTileTexture(7, "grass00");
                getTileTexture(8, "grass00");
                getTileTexture(9, "grass00");
                getTileTexture(10, "grass00");
                getTileTexture(11, "grass01");
                getTileTexture(12, "water00", true);
                getTileTexture(13, "water01", true);
                getTileTexture(14, "water02", true);
                getTileTexture(15, "water03", true);
                getTileTexture(16, "water04", true);
                getTileTexture(17, "water05", true);
                getTileTexture(18, "water06", true);
                getTileTexture(19, "water07", true);
                getTileTexture(20, "water08", true);
                getTileTexture(21, "water09", true);
                getTileTexture(22, "water10", true);
                getTileTexture(23, "water11", true);
                getTileTexture(24, "water12", true);
                getTileTexture(25, "water13", true);
                getTileTexture(26, "road00");
                getTileTexture(27, "road01");
                getTileTexture(28, "road02");
                getTileTexture(29, "road03");
                getTileTexture(30, "road04");
                getTileTexture(31, "road05");
                getTileTexture(32, "road06");
                getTileTexture(33, "road07");
                getTileTexture(34, "road08");
                getTileTexture(35, "road09");
                getTileTexture(36, "road10");
                getTileTexture(37, "road11");
                getTileTexture(38, "road12");
                getTileTexture(39, "earth");
                getTileTexture(40, "wall", true);
                getTileTexture(41, "tree", true);

        }

        private void getTileTexture(int index, String tileName) {
                getTileTexture(index, tileName, false);
        }

        private void getTileTexture(int index, String tileName, boolean collision) {
                Util util = new Util();

                try {
                        tile[index] = new Tile();
                        tile[index].texture = ImageIO.read(getClass().getResourceAsStream("/tile/" + tileName + ".png"));
                        tile[index].texture = util.scaleImage(tile[index].texture, GameSettings.TILE_SIZE, GameSettings. TILE_SIZE);
                        tile[index].collision = collision;
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }

        public void draw(Graphics2D g2) {
                int world_col = 0;
                int world_row = 0;

                while(world_col < GameSettings.MAX_WORLD_COL && world_row < GameSettings.MAX_WORLD_ROW) {

                        int tileNum = mapTileType[world_col][world_row];

                        int world_X = world_col * GameSettings.TILE_SIZE;
                        int world_Y = world_row * GameSettings.TILE_SIZE;
                        int screen_X = (int) (world_X - gamePanel.player.world_X + gamePanel.player.screen_X);
                        int screen_Y = (int) (world_Y - gamePanel.player.world_Y + gamePanel.player.screen_Y);


                        if(world_X + GameSettings.TILE_SIZE > gamePanel.player.world_X - gamePanel.player.screen_X &&
                                world_X - GameSettings.TILE_SIZE < gamePanel.player.world_X + gamePanel.player.screen_X &&
                                world_Y + GameSettings.TILE_SIZE > gamePanel.player.world_Y - gamePanel.player.screen_Y &&
                                world_Y - GameSettings.TILE_SIZE < gamePanel.player.world_Y + gamePanel.player.screen_Y){
                                g2.drawImage(tile[tileNum].texture, screen_X, screen_Y, null);
                        }
                        world_col++;

                        if (world_col == GameSettings.MAX_WORLD_COL) {
                                world_col = 0;
                                world_row++;
                        }

                }
        }
}
