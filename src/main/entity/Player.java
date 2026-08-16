package main.entity;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screen_X, screen_Y;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel  = gamePanel;
        this.keyHandler = keyHandler;

        screen_X = GameSettings.SCREEN_WIDTH / 2 - GameSettings.TILE_SIZE / 2;
        screen_Y = GameSettings.SCREEN_HEIGHT / 2 - GameSettings.TILE_SIZE / 2;

        AABB = new Rectangle(8 * 2, 16 * 2, 32, 32);

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
        up_idle1    = loadTexture("up_idle1");
        up_idle2    = loadTexture("up_idle2");
        down_idle1  = loadTexture("down_idle1");
        down_idle2  = loadTexture("down_idle2");
        left_idle1  = loadTexture("left_idle1");
        left_idle2  = loadTexture("left_idle2");
        right_idle1 = loadTexture("right_idle1");
        right_idle2 = loadTexture("right_idle2");

        // Walking animations
        up1    = loadTexture("up1");
        up2    = loadTexture("up2");
        down1  = loadTexture("down1");
        down2  = loadTexture("down2");
        left1  = loadTexture("left1");
        left2  = loadTexture("left2");
        right1 = loadTexture("right1");
        right2 = loadTexture("right2");
    }
    
    private BufferedImage loadTexture(String imageName) {
        Util util = new Util();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = util.scaleImage(image, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private BufferedImage selectTexture() {

        setSpriteDirection();
        setSpriteCounter();

        if (keyHandler.upPressed)   return (spriteNum == 1) ? up1 : up2;
        if (keyHandler.downPressed) return (spriteNum == 1) ? down1 : down2;
        if (keyHandler.leftPressed)  return (spriteNum == 1) ? left1 : left2;
        if (keyHandler.rightPressed) return (spriteNum == 1) ? right1 : right2;

        return switch (direction) {
            case UP    -> (spriteNum == 1) ? up_idle1    : up_idle2;
            case DOWN  -> (spriteNum == 1) ? down_idle1  : down_idle2;
            case LEFT  -> (spriteNum == 1) ? left_idle1  : left_idle2;
            case RIGHT -> (spriteNum == 1) ? right_idle1 : right_idle2;
        };
    }

    private void setSpriteCounter() {
        spriteCounter++;
        if(spriteCounter > 12){
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

    private void handlePlayerMovement() {

        float moveX = 0;
        float moveY = 0;

        speed = 4;

        collisionUp = collisionDown = collisionLeft = collisionRight = false;

        if (keyHandler.runPressed) speed = 5;

        if (keyHandler.upPressed) moveY -= 1;
        if (keyHandler.downPressed) moveY += 1;
        if (keyHandler.leftPressed) moveX -= 1;
        if (keyHandler.rightPressed) moveX += 1;

        if (moveX == 0 && moveY == 0) return;

        float length = (float) Math.sqrt(moveX * moveX + moveY * moveY);
        moveX = moveX * speed / length;
        moveY = moveY * speed / length;

        gamePanel.collisionChecker.checkCollision(this, 0, moveY);
        if (!collisionUp && !collisionDown) world_Y += moveY;

        gamePanel.collisionChecker.checkCollision(this, moveX, 0);
        if (!collisionLeft && !collisionRight) world_X += moveX;

    }

    public void update() {
        handlePlayerMovement();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(selectTexture(), screen_X, screen_Y, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE, null);

        g2.setColor(Color.RED);
        int aabbScreenX = screen_X + AABB.x;
        int aabbScreenY = screen_Y + AABB.y;
        g2.drawRect(aabbScreenX, aabbScreenY, AABB.width, AABB.height);

    }
}
