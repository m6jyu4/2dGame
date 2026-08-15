package main.tile;

import main.GamePanel;
import main.GameSettings;
import main.Util;

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
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        mapTileNum = new int[GameSettings.MAX_WORLD_COL][GameSettings.MAX_WORLD_ROW];

        registerTile();
        loadMap("/map/map02.txt");
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

                    mapTileNum[col][row] = num;
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

        getTileTexture(0, "grass");
        getTileTexture(1, "wall",  true);
        getTileTexture(2, "water", true);
        getTileTexture(3, "earth");
        getTileTexture(4, "tree",  true);
        getTileTexture(5, "sand");

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

            int tileNum = mapTileNum[world_col][world_row];

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
