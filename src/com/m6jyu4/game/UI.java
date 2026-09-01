package com.m6jyu4.game;

import com.m6jyu4.game.object.Object_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

        GamePanel gamePanel;
        Font arial_40, arial_80B;
        BufferedImage keyImage;
        Graphics2D g2;

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
                this.g2 = g2;

                g2.setFont(arial_40);
                g2.setColor(Color.WHITE);

                if(gamePanel.gameState == GamePanel.playState) {

                }
                if(gamePanel.gameState == GamePanel.pauseState) {
                        drawPauseScreen();
                }


        }

        public void drawPauseScreen() {

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
               String text = "GAME PAUSED";
               int x = centrePosX(text);
               int y = GameSettings.SCREEN_HEIGHT / 2;

               g2.drawString(text, x, y);
        }

        private int centrePosX(String text) {
                int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                return GameSettings.SCREEN_WIDTH / 2 - length / 2;
        }
}
