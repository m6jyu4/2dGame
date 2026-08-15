package main;

import main.entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity, float moveX, float moveY) {
        float entityMinWorldX = (entity.world_X + entity.AABB.x);
        float entityMaxWorldX = (entity.world_X + entity.AABB.x + entity.AABB.width);
        float entityMinWorldY = (entity.world_Y + entity.AABB.y);
        float entityMaxWorldY = (entity.world_Y + entity.AABB.y + entity.AABB.height);

        float entityMinCol  = entityMinWorldX / GameSettings.TILE_SIZE;
        float entityMaxCol  = entityMaxWorldX / GameSettings.TILE_SIZE;
        float entityMinRow  = entityMinWorldY / GameSettings.TILE_SIZE;
        float entityMaxRow  = entityMaxWorldY / GameSettings.TILE_SIZE;

        int tileNum1, tileNum2;

        if(moveY != 0) {
            if (moveY < 0) {
                int tempMinRow = (int) ((entityMinWorldY + moveY) / GameSettings.TILE_SIZE);
                tileNum1 = gamePanel.tileManager.mapTileNum[(int)entityMinCol][(int)tempMinRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[(int)entityMaxCol][(int)tempMinRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionUp = true;
                }
            }
            if (moveY > 0) {
                float tempMaxRow = ((entityMaxWorldY + moveY) / GameSettings.TILE_SIZE);
                tileNum1 = gamePanel.tileManager.mapTileNum[(int)entityMinCol][(int)tempMaxRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[(int)entityMaxCol][(int)tempMaxRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionDown = true;
                }
            }
        }
        if(moveX != 0) {
            if (moveX < 0) {
                float tempMinCol = ((entityMinWorldX + moveX) / GameSettings.TILE_SIZE);
                tileNum1 = gamePanel.tileManager.mapTileNum[(int)tempMinCol][(int)entityMinRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[(int)tempMinCol][(int)entityMaxRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionLeft = true;
                }
            }
            if (moveX > 0) {
                float tempMaxCol =((entityMaxWorldX + moveX) / GameSettings.TILE_SIZE);
                tileNum1 = gamePanel.tileManager.mapTileNum[(int)tempMaxCol][(int)entityMinRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[(int)tempMaxCol][(int)entityMaxRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionRight = true;
                }
            }
        }
    }
}
