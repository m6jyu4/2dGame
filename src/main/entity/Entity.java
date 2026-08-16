package main.entity;

import main.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public float world_X, world_Y;
    public float speed;

    public BufferedImage up_idle1, up_idle2, down_idle1, down_idle2, left_idle1, left_idle2, right_idle1, right_idle2, up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle AABB;

    public boolean collisionUp;
    public boolean collisionDown;
    public boolean collisionLeft;
    public boolean collisionRight;
}
