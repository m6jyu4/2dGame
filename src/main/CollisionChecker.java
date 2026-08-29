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

        public void checkTileCollision(Entity entity, float moveX, float moveY) {

                calculateEntityBound(entity);

                if (moveY != 0) { // MOVEMENT ALONG AXIS: Y ( UP / DOWN )
                        if (moveY < 0) {
                                setUpCollision(entity, moveY);
                        }
                        if (moveY > 0) {
                                setDownCollision(entity, moveY);
                        }
                }
                if (moveX != 0) { // MOVEMENT ALONG AXIS: X ( LEFT / RIGHT )
                        if (moveX < 0) {
                                setLeftCollision(entity, moveX);
                        }
                        if (moveX > 0) {
                                setRightCollision(entity, moveX);
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

        private void setUpCollision(Entity entity, float move) {
                int tileMovingInto = (int) (entityTop + move) / GameSettings.TILE_SIZE;
                entity.collisionUp = isCollidingWithTileY(tileMovingInto);
        }

        private void setDownCollision(Entity entity, float move) {
                int tileMovingInto = (int) (entityBottom + move) / GameSettings.TILE_SIZE;
                entity.collisionDown = isCollidingWithTileY(tileMovingInto);
        }

        private void setLeftCollision(Entity entity, float move) {
                int tileMovingInto = (int) (entityLeft + move) / GameSettings.TILE_SIZE;
                entity.collisionLeft = isCollidingWithTileX(tileMovingInto);
        }

        private void setRightCollision(Entity entity, float move) {
                int tileMovingInto = (int) (entityRight + move) / GameSettings.TILE_SIZE;
                entity.collisionRight = isCollidingWithTileX(tileMovingInto);
        }

        private boolean isCollidingWithTileX(int tile) {
                int TopTileType    = gamePanel.tileManager.mapTileType[tile][entityTopTile];
                int BottomTileType = gamePanel.tileManager.mapTileType[tile][entityBottomTile];
                return (gamePanel.tileManager.tile[TopTileType].collision || gamePanel.tileManager.tile[BottomTileType].collision);
        }

        private boolean isCollidingWithTileY(int tile) {
                int LeftTileType  = gamePanel.tileManager.mapTileType[entityLeftTile][tile];
                int RightTileType = gamePanel.tileManager.mapTileType[entityRightTile][tile];
                return (gamePanel.tileManager.tile[LeftTileType].collision || gamePanel.tileManager.tile[RightTileType].collision);
        }

        public int checkObjectCollision(Entity entity, float moveX, float moveY) {
                float newX = entity.world_X + entity.AABB.x + moveX;
                float newY = entity.world_Y + entity.AABB.y + moveY;

                float activationRangeSq = 16384f; // (16 * GameSettings.TEXTURE_SCALE) SQUARED

                for (int i = 0; i < gamePanel.object.length; i++) {
                        if (gamePanel.object[i] == null) continue;

                        float dx = gamePanel.object[i].worldX - entity.world_X;
                        float dy = gamePanel.object[i].worldY - entity.world_Y;
                        float distanceSq = (dx * dx) + (dy * dy);

                        if (distanceSq > activationRangeSq) continue;

                        float objLeft   = gamePanel.object[i].worldX + gamePanel.object[i].AABB.x;
                        float objRight  = objLeft + gamePanel.object[i].AABB.width;
                        float objTop    = gamePanel.object[i].worldY + gamePanel.object[i].AABB.y;
                        float objBottom = objTop + gamePanel.object[i].AABB.height;

                        boolean moveIntoObjectX = newX < objRight && newX + entity.AABB.width > objLeft && newY < objBottom && newY + entity.AABB.height > objTop;
                        boolean moveIntoObjectY = newX < objRight && newX + entity.AABB.width > objLeft && newY < objBottom && newY + entity.AABB.height > objTop;

                        if (moveIntoObjectY && moveY != 0) { // MOVEMENT ALONG AXIS: Y ( UP / DOWN )
                                if (moveY < 0) {
                                        entity.collisionUp    = gamePanel.object[i].collision;
                                        System.out.println("UP");
                                }
                                if (moveY > 0) {
                                        entity.collisionDown  = gamePanel.object[i].collision;
                                        System.out.println("DOWN");
                                }
                                return i;
                        }
                        if (moveIntoObjectX && moveX != 0) { // MOVEMENT ALONG AXIS: X ( LEFT / RIGHT )
                                if (moveX < 0) {
                                        entity.collisionLeft  = gamePanel.object[i].collision;
                                        System.out.println("LEFT");
                                }
                                if (moveX > 0) {
                                        entity.collisionRight = gamePanel.object[i].collision;
                                        System.out.println("RIGHT");
                                }
                                return i;
                        }
                }
                return -1;
        }

}
