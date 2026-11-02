package com.m6jyu4.game.entity;

import com.m6jyu4.game.*;
import com.m6jyu4.game.GamePanel;
import com.m6jyu4.game.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

        KeyHandler keyHandler;

        private static final float DIAGONAL_MULTIPLIER = 0.70710678f;

        public final int screen_X, screen_Y;

        public Player(GamePanel gamePanel, KeyHandler keyHandler) {
                super(gamePanel);

                this.keyHandler = keyHandler;

                screen_X = GameSettings.SCREEN_WIDTH / 2 - GameSettings.TILE_SIZE / 2;
                screen_Y = GameSettings.SCREEN_HEIGHT / 2 - GameSettings.TILE_SIZE / 2;

                AABB = new Rectangle(8 * 2, 16 * 2, 32, 32);
                AABB_X = AABB.x;
                AABB_Y = AABB.y;


                setDefaultValue();
                registerPlayerTexture();
        }

        private void setDefaultValue() {
                world_X = GameSettings.TILE_SIZE * 23;
                world_Y = GameSettings.TILE_SIZE * 21;
                speed = 4;
                direction = Direction.DOWN;
        }

        private void registerPlayerTexture() {
                // Idle animations
                up_idle1    = loadTexture("/player/up_idle1");
                up_idle2    = loadTexture("/player/up_idle2");
                down_idle1  = loadTexture("/player/down_idle1");
                down_idle2  = loadTexture("/player/down_idle2");
                left_idle1  = loadTexture("/player/left_idle1");
                left_idle2  = loadTexture("/player/left_idle2");
                right_idle1 = loadTexture("/player/right_idle1");
                right_idle2 = loadTexture("/player/right_idle2");

                // Walking animations
                up1    = loadTexture("/player/up1");
                up2    = loadTexture("/player/up2");
                down1  = loadTexture("/player/down1");
                down2  = loadTexture("/player/down2");
                left1  = loadTexture("/player/left1");
                left2  = loadTexture("/player/left2");
                right1 = loadTexture("/player/right1");
                right2 = loadTexture("/player/right2");
        }

        private BufferedImage selectTexture() {

                if (gamePanel.gameState == GameState.PLAY) {
                        setSpriteDirection();
                        setSpriteCounter();

                        if (keyHandler.upPressed)    return (spriteNum == 1) ? up1 : up2;
                        if (keyHandler.downPressed)  return (spriteNum == 1) ? down1 : down2;
                        if (keyHandler.leftPressed)  return (spriteNum == 1) ? left1 : left2;
                        if (keyHandler.rightPressed) return (spriteNum == 1) ? right1 : right2;
                }

                return switch (direction) {
                        case UP    -> (spriteNum == 1) ? up_idle1    : up_idle2;
                        case DOWN  -> (spriteNum == 1) ? down_idle1  : down_idle2;
                        case LEFT  -> (spriteNum == 1) ? left_idle1  : left_idle2;
                        case RIGHT -> (spriteNum == 1) ? right_idle1 : right_idle2;
                };
        }

        private void setSpriteCounter() {
                spriteCounter++;
                if (spriteCounter > 12) {
                        if(spriteNum == 1) spriteNum = 2;
                        else if(spriteNum == 2) spriteNum = 1;
                        spriteCounter= 0;
                }
        }

        private void setSpriteDirection() {
                if (keyHandler.upPressed) {
                        direction = Direction.UP;
                }
                if (keyHandler.downPressed) {
                        direction = Direction.DOWN;
                }
                if (keyHandler.leftPressed) {
                        direction = Direction.LEFT;
                }
                if (keyHandler.rightPressed) {
                        direction = Direction.RIGHT;
                }
        }

        private void handlePlayerMovement(double delta) {

                float moveX = 0;
                float moveY = 0;

                collisionUp = collisionDown = collisionLeft = collisionRight = false;

                speed = 4;
                if (keyHandler.runPressed) speed = 5;

                if (keyHandler.upPressed) moveY -= 1;
                if (keyHandler.downPressed) moveY += 1;
                if (keyHandler.leftPressed) moveX -= 1;
                if (keyHandler.rightPressed) moveX += 1;

                if (moveX == 0 && moveY == 0) return;


                gamePanel.collisionChecker.checkTileCollision(this, 0, moveY * speed);
                int objectIndexY = gamePanel.collisionChecker.checkObjectCollision(this, 0, moveY * speed);
                int npcIndexY    = gamePanel.collisionChecker.checkNpcCollision(this, 0, moveY * speed);
                if(collisionUp || collisionDown) moveY = 0;

                gamePanel.collisionChecker.checkTileCollision(this, moveX * speed, 0);
                int objectIndexX = gamePanel.collisionChecker.checkObjectCollision(this, moveX * speed, 0);
                int npcIndexX    = gamePanel.collisionChecker.checkNpcCollision(this, moveX * speed, 0);
                if(collisionLeft || collisionRight) moveX = 0;

                int objectIndex = -1;
                if (objectIndexX != -1) objectIndex = objectIndexX;
                if (objectIndexY != -1) objectIndex = objectIndexY;
                pickUpObject(objectIndex);

                int npcIndex = -1;
                if (npcIndexX != -1) npcIndex = npcIndexX;
                if (npcIndexY != -1) npcIndex = npcIndexY;
                interactWithNpc(npcIndex);

                float tempX = (float) (moveX * speed );
                float tempY = (float) (moveY * speed );

                if (moveX != 0 && moveY != 0) {
                        tempX = moveX * speed * DIAGONAL_MULTIPLIER;
                        tempY = moveY * speed * DIAGONAL_MULTIPLIER;
                }
                if (!collisionUp && !collisionDown) world_Y += tempY;

                if (!collisionLeft && !collisionRight) world_X += tempX;

        }

        private void pickUpObject(int i) {
                if (i == -1) return;
        }

        private void interactWithNpc(int i) {
                if (i == -1) return;

                gamePanel.gameState = GameState.DIALOGUE;
                gamePanel.npc[i].speak();
        }

        public void update(double delta) {
                handlePlayerMovement(delta);
        }

        public void draw(Graphics2D g2) {

                g2.drawImage(selectTexture(), screen_X, screen_Y, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE, null);

                g2.setColor(Color.RED);
                int aabbScreenX = screen_X + AABB.x;
                int aabbScreenY = screen_Y + AABB.y;
                g2.drawRect(aabbScreenX, aabbScreenY, AABB.width, AABB.height);
        }
}
