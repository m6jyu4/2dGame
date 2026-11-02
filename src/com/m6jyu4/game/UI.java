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
        public String currentDialogue;

        public int commandNum = 0;

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

                if(gamePanel.gameState == GameState.TITLE) {
                        drawTitleScreen();
                }
                if(gamePanel.gameState == GameState.PLAY) {

                }
                if(gamePanel.gameState == GameState.PAUSE) {
                        drawPauseScreen();
                }
                if(gamePanel.gameState == GameState.DIALOGUE) {
                        drawDialogueScreen();
                }


        }

        private void drawTitleScreen() {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
                String text = "2D Game";
                int x = centrePosX(text);
                int y = GameSettings.TILE_SIZE * 3;

                g2.setColor(Color.GRAY);
                g2.drawString(text, x + 5, y + 5);

                g2.setColor(Color.WHITE);
                g2.drawString(text, x, y);

                x = GameSettings.SCREEN_WIDTH / 2 - (GameSettings.TILE_SIZE * 2) / 2;
                y += GameSettings.TILE_SIZE * 2;

                g2.drawImage(gamePanel.player.down_idle1, x, y, GameSettings.TILE_SIZE * 2, GameSettings.TILE_SIZE * 2, null);

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
                text = "NEW GAME";
                x = centrePosX(text);
                y += GameSettings.TILE_SIZE * 4;
                g2.drawString(text, x, y);
                if (commandNum == 0) {
                        g2.drawString(">", x - GameSettings.TILE_SIZE, y);
                }

                text = "CONTINUE";
                x = centrePosX(text);
                y += GameSettings.TILE_SIZE;
                g2.drawString(text, x, y);
                if (commandNum == 1) {
                        g2.drawString(">", x - GameSettings.TILE_SIZE, y);
                }

                text = "EXIT";
                x = centrePosX(text);
                y += GameSettings.TILE_SIZE;
                g2.drawString(text, x, y);
                if (commandNum == 2) {
                        g2.drawString(">", x - GameSettings.TILE_SIZE, y);
                }
        }

        private void drawDialogueScreen() {
                int x = GameSettings.TILE_SIZE * 2;
                int y = GameSettings.TILE_SIZE / 2;
                int width = GameSettings.SCREEN_WIDTH - (GameSettings.TILE_SIZE * 4);
                int height = GameSettings.TILE_SIZE * 4;

                drawSubWindow(x, y, width, height);

                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
                x += GameSettings.TILE_SIZE;
                y += GameSettings.TILE_SIZE;
                for (String line : currentDialogue.split("\n")) {
                        g2.drawString(line, x, y);
                        y += 40;
                }
        }

        public void drawSubWindow(int x, int y, int width, int height) {
                var c = new Color(0, 0, 0, 210);
                g2.setColor(c);
                g2.fillRoundRect(x, y, width, height,35, 35);

                c = new Color(255, 255, 255);
                g2.setColor(c);
                g2.setStroke(new BasicStroke(5));
                g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        }

        private void drawPauseScreen() {

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
