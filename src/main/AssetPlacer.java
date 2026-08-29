package main;

import main.object.Object_Boots;
import main.object.Object_Chest;
import main.object.Object_Door;
import main.object.Object_Key;

public class AssetPlacer {

        GamePanel gamePanel;

        public AssetPlacer(GamePanel gamePanel) {
                this.gamePanel = gamePanel;

        }

        public void setObject() {
                gamePanel.object[0] = new Object_Key();
                gamePanel.object[0].worldX = 23 * GameSettings.TILE_SIZE;
                gamePanel.object[0].worldY = 7  * GameSettings.TILE_SIZE;

                gamePanel.object[1] = new Object_Key();
                gamePanel.object[1].worldX = 23 * GameSettings.TILE_SIZE;
                gamePanel.object[1].worldY = 40  * GameSettings.TILE_SIZE;

                gamePanel.object[2] = new Object_Key();
                gamePanel.object[2].worldX = 38 * GameSettings.TILE_SIZE;
                gamePanel.object[2].worldY = 8  * GameSettings.TILE_SIZE;

                gamePanel.object[3] = new Object_Door();
                gamePanel.object[3].worldX = 10 * GameSettings.TILE_SIZE;
                gamePanel.object[3].worldY = 11  * GameSettings.TILE_SIZE;

                gamePanel.object[4] = new Object_Door();
                gamePanel.object[4].worldX = 8 * GameSettings.TILE_SIZE;
                gamePanel.object[4].worldY = 28  * GameSettings.TILE_SIZE;

                gamePanel.object[5] = new Object_Door();
                gamePanel.object[5].worldX = 12 * GameSettings.TILE_SIZE;
                gamePanel.object[5].worldY = 22  * GameSettings.TILE_SIZE;

                gamePanel.object[6] = new Object_Chest();
                gamePanel.object[6].worldX = 10 * GameSettings.TILE_SIZE;
                gamePanel.object[6].worldY = 7  * GameSettings.TILE_SIZE;

                gamePanel.object[7] = new Object_Boots();
                gamePanel.object[7].worldX = 37 * GameSettings.TILE_SIZE;
                gamePanel.object[7].worldY = 42  * GameSettings.TILE_SIZE;
        }


}
