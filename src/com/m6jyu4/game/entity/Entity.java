package com.m6jyu4.game.entity;

import com.m6jyu4.game.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

        public float world_X, world_Y;
        public float speed;
        public float moveX, moveY;

        public BufferedImage up_idle1, up_idle2, down_idle1, down_idle2, left_idle1, left_idle2, right_idle1, right_idle2, up1, up2, down1, down2, left1, left2, right1, right2;
        public Direction direction;

        public int spriteCounter = 0;
        public int spriteNum = 1;

        public Rectangle AABB = new Rectangle(0,0,64,64);
        public int AABB_X;
        public int AABB_Y;

        public boolean collisionUp;
        public boolean collisionDown;
        public boolean collisionLeft;
        public boolean collisionRight;

        public int actionLockCounter = 0;

        int dialogueIndex = 0;


        public String dialogues[] = new String[20];

        public GamePanel gamePanel;

        public Entity(GamePanel gamePanel) {
                this.gamePanel = gamePanel;
        }

        protected BufferedImage loadTexture(String imageName) {
                Util util = new Util();
                BufferedImage image = null;
                try {
                        image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
                        image = util.scaleImage(image, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return image;
        }

        private void setSpriteCounter() {
                spriteCounter++;
                if (spriteCounter > 12) {
                        if(spriteNum == 1) spriteNum = 2;
                        else if(spriteNum == 2) spriteNum = 1;
                        spriteCounter= 0;
                }
        }

        private BufferedImage selectTexture() {

                return switch (direction) {
                        case UP    -> (spriteNum == 1) ? up1    : up2;
                        case DOWN  -> (spriteNum == 1) ? down1  : down2;
                        case LEFT  -> (spriteNum == 1) ? left1  : left2;
                        case RIGHT -> (spriteNum == 1) ? right1 : right2;
                };

        }

        public void setAction() {

        }

        private static final float DIAGONAL_MULTIPLIER = 0.70710678f;

        public void update() {
                setSpriteDirection();
                setSpriteCounter();
                setAction();

                collisionDown = false;
                collisionLeft = false;
                collisionUp = false;
                collisionRight = false;

                if (moveX == 0 && moveY == 0) return;


                gamePanel.collisionChecker.checkTileCollision(this, 0, moveY * speed);
                gamePanel.collisionChecker.checkNpcCollision(this, 0, moveY * speed);
                gamePanel.collisionChecker.checkPlayerCollision(this, 0, moveY * speed);
                if(collisionUp || collisionDown) moveY = 0;

                gamePanel.collisionChecker.checkTileCollision(this, moveX * speed, 0);
                gamePanel.collisionChecker.checkNpcCollision(this, moveX * speed, 0);
                gamePanel.collisionChecker.checkPlayerCollision(this, moveX * speed, 0);
                if(collisionLeft || collisionRight) moveX = 0;

                float tempX = (float) (moveX * speed );
                float tempY = (float) (moveY * speed );

                if (moveX != 0 && moveY != 0) {
                        tempX = moveX * speed * DIAGONAL_MULTIPLIER;
                        tempY = moveY * speed * DIAGONAL_MULTIPLIER;
                }
                if (!collisionUp && !collisionDown) world_Y += tempY;

                if (!collisionLeft && !collisionRight) world_X += tempX;

        }

        private void setSpriteDirection() {
                if (gamePanel.gameState == GameState.PLAY) {
                        if (moveY < 0) {
                                direction = Direction.UP;
                        }
                        if (moveY > 0) {
                                direction = Direction.DOWN;
                        }
                        if (moveX < 0) {
                                direction = Direction.LEFT;
                        }
                        if (moveX > 0) {
                                direction = Direction.RIGHT;
                        }
                        if (moveX == 0 && moveY == 0) {
                                direction = Direction.DOWN;
                        }
                }

        }

        public void draw(Graphics2D g2) {

                int screen_X = (int) (world_X - gamePanel.player.world_X + gamePanel.player.screen_X);
                int screen_Y = (int) (world_Y - gamePanel.player.world_Y + gamePanel.player.screen_Y);

                if(world_X + GameSettings.TILE_SIZE > gamePanel.player.world_X - gamePanel.player.screen_X &&
                        world_X - GameSettings.TILE_SIZE < gamePanel.player.world_X + gamePanel.player.screen_X &&
                        world_Y + GameSettings.TILE_SIZE > gamePanel.player.world_Y - gamePanel.player.screen_Y &&
                        world_Y - GameSettings.TILE_SIZE < gamePanel.player.world_Y + gamePanel.player.screen_Y){
                        g2.drawImage(selectTexture(), screen_X, screen_Y, null);

                        g2.setColor(Color.RED);
                        int aabbScreenX = screen_X + AABB.x;
                        int aabbScreenY = screen_Y + AABB.y;
                        g2.drawRect(aabbScreenX, aabbScreenY, AABB.width, AABB.height);
                }

        }

        public void speak() {
                if (dialogues[dialogueIndex] == null) dialogueIndex = 0;

                gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
                dialogueIndex++;

                switch(gamePanel.player.direction) {
                        case UP -> this.direction = Direction.DOWN;
                        case DOWN -> this.direction = Direction.UP;
                        case LEFT -> this.direction = Direction.RIGHT;
                        case RIGHT -> this.direction = Direction.LEFT;
                }
        }
}
