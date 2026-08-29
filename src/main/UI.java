package main;

import main.object.Object_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

        GamePanel gamePanel;
        Font arial_40, arial_80B;
        BufferedImage keyImage;

        public boolean messageOn = false;
        public String message = "";
        private int messageCounter = 0;

        public boolean gameCompleted = false;

        public UI(GamePanel gamePanel) {
                this.gamePanel = gamePanel;

                arial_40  = (new Font("Arial", Font.PLAIN, 40));
                arial_80B = (new Font("Arial", Font.BOLD, 80));
                Object_Key key = new Object_Key();
                keyImage = key.image;
        }

        public void showMessage(String text) {
                message = text;
                messageOn = true;
        }

        public void draw(Graphics2D g2) {

                if(gameCompleted) {

                        g2.setFont(arial_40);
                        g2.setColor(Color.WHITE);

                        String text;
                        int textLength;
                        int x;
                        int y;

                        text = "You've found the treasure!";
                        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();


                        x = GameSettings.SCREEN_WIDTH / 2 - textLength / 2;
                        y = GameSettings.SCREEN_HEIGHT / 2 - GameSettings.TILE_SIZE * 3;

                        g2.drawString(text, x, y);


                        g2.setFont(arial_80B);
                        g2.setColor(Color.YELLOW);

                        text = "Congratulations!";
                        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();


                        x = GameSettings.SCREEN_WIDTH / 2 - textLength / 2;
                        y = GameSettings.SCREEN_HEIGHT / 2 + GameSettings.TILE_SIZE * 2;

                        g2.drawString(text, x, y);

                        gamePanel.stopGame();

                        return;
                }


                g2.setFont(arial_40);
                g2.setColor(Color.WHITE);
                g2.drawImage(keyImage, GameSettings.TILE_SIZE / 2,GameSettings.TILE_SIZE / 2, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE, null);
                g2.drawString("x " + gamePanel.player.ownedKey, 95, 80);

                if(messageOn) {
                        g2.setFont(g2.getFont().deriveFont(30f));
                        g2.drawString(message, GameSettings.TILE_SIZE / 2,GameSettings.TILE_SIZE / 2);
                        messageCounter++;

                        if(messageCounter > 120) {
                                messageCounter = 0;
                                messageOn = false;
                        }
                }



        }
}
