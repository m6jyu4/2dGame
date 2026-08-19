package main;

import main.entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    private float entityLeft;
    private float entityRight;
    private float entityTop;
    private float entityBottom;

    private int entityLeftTile;
    private int entityRightTile;
    private int entityTopTile;
    private int entityBottomTile;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkCollision(Entity entity, float moveX, float moveY) {

        calculateEntityBound(entity);

        if (moveY != 0) { // MOVEMENT ALONG AXIS: Y ( UP / DOWN )
            if (moveY < 0) {
                checkUpTileCollision(entity, moveY);
            }
            if (moveY > 0) {
                checkDownTileCollision(entity, moveY);
            }
        }
        if (moveX != 0) { // MOVEMENT ALONG AXIS: X ( LEFT / RIGHT )
            if (moveX < 0) {
                checkLeftTileCollision(entity, moveX);
            }
            if (moveX > 0) {
                checkRightTileCollision(entity, moveX);
            }
        }
    }

    private void calculateEntityBound(Entity entity) {
        entityLeft   = (entity.world_X + entity.AABB.x);
        entityRight  = (entity.world_X + entity.AABB.x + entity.AABB.width);
        entityTop    = (entity.world_Y + entity.AABB.y);
        entityBottom = (entity.world_Y + entity.AABB.y + entity.AABB.height);

        entityLeftTile   = (int) entityLeft   / GameSettings.TILE_SIZE;
        entityRightTile  = (int) entityRight  / GameSettings.TILE_SIZE;
        entityTopTile    = (int) entityTop    / GameSettings.TILE_SIZE;
        entityBottomTile = (int) entityBottom / GameSettings.TILE_SIZE;
    }

    private void checkUpTileCollision(Entity entity, float move) {
        int tileMovingInto = (int) (entityTop + move) / GameSettings.TILE_SIZE;
        entity.collisionUp = setCollisionTileY(tileMovingInto);
    }

    private void checkDownTileCollision(Entity entity, float move) {
        int tileMovingInto = (int) (entityBottom + move) / GameSettings.TILE_SIZE;
        entity.collisionDown = setCollisionTileY(tileMovingInto);
    }

    private void checkLeftTileCollision(Entity entity, float move) {
        int tileMovingInto = (int) (entityLeft + move) / GameSettings.TILE_SIZE;
        entity.collisionLeft = setCollisionTileX(tileMovingInto);
    }

    private void checkRightTileCollision(Entity entity, float move) {
        int tileMovingInto = (int) (entityRight + move) / GameSettings.TILE_SIZE;
        entity.collisionRight = setCollisionTileX(tileMovingInto);
    }

    private boolean setCollisionTileX(int tile) {
        int TopTileType    = gamePanel.tileManager.mapTileType[tile][entityTopTile];
        int BottomTileType = gamePanel.tileManager.mapTileType[tile][entityBottomTile];
        return (gamePanel.tileManager.tile[TopTileType].collision || gamePanel.tileManager.tile[BottomTileType].collision);
    }

    private boolean setCollisionTileY(int tile) {
        int LeftTileType  = gamePanel.tileManager.mapTileType[entityLeftTile][tile];
        int RightTileType = gamePanel.tileManager.mapTileType[entityRightTile][tile];
        return (gamePanel.tileManager.tile[LeftTileType].collision || gamePanel.tileManager.tile[RightTileType].collision);
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gamePanel.object.length; i++) {
            if(gamePanel.object[i] != null) {
                entity.AABB.x = (int) (entity.world_X + entity.AABB.x);
                entity.AABB.y = (int) (entity.world_Y + entity.AABB.y);

                gamePanel.object[i].AABB.x = gamePanel.object[i].worldX + gamePanel.object[i].AABB.x;
                gamePanel.object[i].AABB.y = gamePanel.object[i].worldY + gamePanel.object[i].AABB.y;

                switch (entity.direction) {
                    case UP:
                        entity.AABB.y -= entity.speed;
                        if(entity.AABB.intersects(gamePanel.object[i].AABB)){
                            if(gamePanel.object[i].collision){
                                entity.collisionUp = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case DOWN:
                        entity.AABB.y += entity.speed;
                        if(entity.AABB.intersects(gamePanel.object[i].AABB)){
                            if(gamePanel.object[i].collision){
                                entity.collisionDown = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case LEFT:
                        entity.AABB.x -= entity.speed;
                        if(entity.AABB.intersects(gamePanel.object[i].AABB)){
                            if(gamePanel.object[i].collision){
                                entity.collisionLeft = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case RIGHT:
                        entity.AABB.x += entity.speed;
                        if(entity.AABB.intersects(gamePanel.object[i].AABB)){
                            if(gamePanel.object[i].collision){
                                entity.collisionRight = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.AABB.x = entity.AABB_X;
                entity.AABB.y = entity.AABB_Y;
                gamePanel.object[i].AABB.x = gamePanel.object[i].AABB_X;
                gamePanel.object[i].AABB.y = gamePanel.object[i].AABB_Y;
            }

        }

        return index;
    }

}
