package com.m6jyu4.game.object;

import com.m6jyu4.game.GamePanel;
import com.m6jyu4.game.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

        public BufferedImage image;
        public String name;
        public int worldX, worldY;
        public boolean collision = false;
        public Rectangle AABB = new Rectangle(0,0,16 * GameSettings.TEXTURE_SCALE,16 * GameSettings.TEXTURE_SCALE);
        public int AABB_X = 0;
        public int AABB_Y = 0;


        public void draw(Graphics2D g2, GamePanel gamePanel) {
                int screen_X = (int) (worldX - gamePanel.player.world_X + gamePanel.player.screen_X);
                int screen_Y = (int) (worldY - gamePanel.player.world_Y + gamePanel.player.screen_Y);

                if(worldX + GameSettings.TILE_SIZE > gamePanel.player.world_X - gamePanel.player.screen_X &&
                        worldX - GameSettings.TILE_SIZE < gamePanel.player.world_X + gamePanel.player.screen_X &&
                        worldY + GameSettings.TILE_SIZE > gamePanel.player.world_Y - gamePanel.player.screen_Y &&
                        worldY - GameSettings.TILE_SIZE < gamePanel.player.world_Y + gamePanel.player.screen_Y){
                        g2.drawImage(image, screen_X, screen_Y, null);

                }

                for (int i = 0; i < gamePanel.object.length; i++) {
                        if(gamePanel.object[i] == null) continue;
                        int aabbScreenX = screen_X + AABB.x;
                        int aabbScreenY = screen_Y + AABB.y;

                        g2.setColor(Color.red);
                        g2.drawRect(aabbScreenX, aabbScreenY, gamePanel.object[i].AABB.width, gamePanel.object[i].AABB.height);

                }


        }
}
