package main.object;

import main.GamePanel;
import main.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        int screen_X = (int) (worldX - gamePanel.player.world_X + gamePanel.player.screen_X);
        int screen_Y = (int) (worldY - gamePanel.player.world_Y + gamePanel.player.screen_Y);

        if(worldX + GameSettings.TILE_SIZE > gamePanel.player.world_X - gamePanel.player.screen_X &&
                worldX - GameSettings.TILE_SIZE < gamePanel.player.world_X + gamePanel.player.screen_X &&
                worldY + GameSettings.TILE_SIZE > gamePanel.player.world_Y - gamePanel.player.screen_Y &&
                worldY - GameSettings.TILE_SIZE < gamePanel.player.world_Y + gamePanel.player.screen_Y){
            g2.drawImage(image, screen_X, screen_Y, null);
        }

    }
}
