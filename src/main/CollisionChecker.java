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
            if (moveY < 0) setUpCollision(entity, moveY);
            if (moveY > 0) setDownCollision(entity, moveY);
        }
        if (moveX != 0) { // MOVEMENT ALONG AXIS: X ( LEFT / RIGHT )
            if (moveX < 0) setLeftCollision(entity, moveX);
            if (moveX > 0) setRightCollision(entity, moveX);
        }
    }

    private void calculateEntityBound(Entity entity) {
        entityLeft   = (entity.world_X + entity.AABB.x);
        entityRight  = (entity.world_X + entity.AABB.x + entity.AABB.width);
        entityTop    = (entity.world_Y + entity.AABB.y);
        entityBottom = (entity.world_Y + entity.AABB.y + entity.AABB.height);

        entityLeftTile   = (int) entityLeft / GameSettings.TILE_SIZE;
        entityRightTile  = (int) entityRight / GameSettings.TILE_SIZE;
        entityTopTile    = (int) entityTop / GameSettings.TILE_SIZE;
        entityBottomTile = (int) entityBottom / GameSettings.TILE_SIZE;
    }

    private void setUpCollision(Entity entity, float move) {
        int tileMovingInto = (int) (entityTop + move) / GameSettings.TILE_SIZE;
        entity.collisionUp = getTileAxisY(tileMovingInto);
    }

    private void setDownCollision(Entity entity, float move) {
        int tileMovingInto = (int) (entityBottom + move) / GameSettings.TILE_SIZE;
        entity.collisionDown = getTileAxisY(tileMovingInto);
    }

    private void setLeftCollision(Entity entity, float move) {
        int tileMovingInto = (int) (entityLeft + move) / GameSettings.TILE_SIZE;
        entity.collisionLeft = getTileAxisX(tileMovingInto);
    }

    private void setRightCollision(Entity entity, float move) {
        int tileMovingInto = (int) (entityRight + move) / GameSettings.TILE_SIZE;
        entity.collisionRight = getTileAxisX(tileMovingInto);
    }

    private boolean getTileAxisX(int tile) {
        int Tile1Type = gamePanel.tileManager.mapTileType[tile][entityTopTile];
        int Tile2Type = gamePanel.tileManager.mapTileType[tile][entityBottomTile];
        return (gamePanel.tileManager.tile[Tile1Type].collision || gamePanel.tileManager.tile[Tile2Type].collision);
    }

    private boolean getTileAxisY(int tile) {
        int Tile1Type = gamePanel.tileManager.mapTileType[entityLeftTile][tile];
        int Tile2Type = gamePanel.tileManager.mapTileType[entityRightTile][tile];
        return (gamePanel.tileManager.tile[Tile1Type].collision || gamePanel.tileManager.tile[Tile2Type].collision);
    }

//    public void checkTile(Entity entity, float moveX, float moveY) {
//        int entityMinWorldX = (int)(entity.world_X + entity.AABB.x);
//        int entityMaxWorldX = (int)(entity.world_X + entity.AABB.x + entity.AABB.width);
//        int entityMinWorldY = (int)(entity.world_Y + entity.AABB.y);
//        int entityMaxWorldY = (int)(entity.world_Y + entity.AABB.y + entity.AABB.height);
//
//        int entityMinCol  = entityMinWorldX / GameSettings.TILE_SIZE;
//        int entityMaxCol  = entityMaxWorldX / GameSettings.TILE_SIZE;
//        int entityMinRow  = entityMinWorldY / GameSettings.TILE_SIZE;
//        int entityMaxRow  = entityMaxWorldY / GameSettings.TILE_SIZE;
//
//        int tileNum1, tileNum2;
//
//        if(moveY != 0) {
//            if (moveY < 0) {
//                int tempMinRow = (int) ((entityMinWorldY + moveY) / GameSettings.TILE_SIZE);
//                tileNum1 = gamePanel.tileManager.mapTileType[(int)entityMinCol][(int)tempMinRow];
//                tileNum2 = gamePanel.tileManager.mapTileType[(int)entityMaxCol][(int)tempMinRow];
//                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
//                    entity.collisionUp = true;
//                }
//            }
//            if (moveY > 0) {
//                float tempMaxRow = ((entityMaxWorldY + moveY) / GameSettings.TILE_SIZE);
//                tileNum1 = gamePanel.tileManager.mapTileType[(int)entityMinCol][(int)tempMaxRow];
//                tileNum2 = gamePanel.tileManager.mapTileType[(int)entityMaxCol][(int)tempMaxRow];
//                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
//                    entity.collisionDown = true;
//                }
//            }
//        }
//        if(moveX != 0) {
//            if (moveX < 0) {
//                float tempMinCol = ((entityMinWorldX + moveX) / GameSettings.TILE_SIZE);
//                tileNum1 = gamePanel.tileManager.mapTileType[(int)tempMinCol][(int)entityMinRow];
//                tileNum2 = gamePanel.tileManager.mapTileType[(int)tempMinCol][(int)entityMaxRow];
//                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
//                    entity.collisionLeft = true;
//                }
//            }
//            if (moveX > 0) {
//                float tempMaxCol =((entityMaxWorldX + moveX) / GameSettings.TILE_SIZE);
//                tileNum1 = gamePanel.tileManager.mapTileType[(int)tempMaxCol][(int)entityMinRow];
//                tileNum2 = gamePanel.tileManager.mapTileType[(int)tempMaxCol][(int)entityMaxRow];
//                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
//                    entity.collisionRight = true;
//                }
//            }
//        }
//    }
}
