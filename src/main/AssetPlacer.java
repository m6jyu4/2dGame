package main;

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
    }


}
