package com.m6jyu4.game;

import com.m6jyu4.game.entity.OldMan;

public class AssetPlacer {

        GamePanel gamePanel;

        public AssetPlacer(GamePanel gamePanel) {
                this.gamePanel = gamePanel;

        }

        public void setObject() {

        }

        public void setNPC() {
                gamePanel.npc[0] = new OldMan(gamePanel);
                gamePanel.npc[0].world_X = GameSettings.TILE_SIZE * 21;
                gamePanel.npc[0].world_Y = GameSettings.TILE_SIZE * 21;
        }

}
