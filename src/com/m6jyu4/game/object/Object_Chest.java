package com.m6jyu4.game.object;

import com.m6jyu4.game.GameSettings;
import com.m6jyu4.game.Util;

import javax.imageio.ImageIO;

public class Object_Chest extends GameObject {

        public Object_Chest() {

                Util util = new Util();

                try {
                        image = ImageIO.read(getClass().getResourceAsStream("/object/" + "chest" + ".png"));
                        image = util.scaleImage(image, GameSettings.TILE_SIZE, GameSettings. TILE_SIZE);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                name = "Chest";
        }

}
