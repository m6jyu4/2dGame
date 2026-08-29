package main.object;

import main.GameSettings;
import main.Util;
import main.tile.Tile;

import javax.imageio.ImageIO;

public class Object_Key extends GameObject {

        public Object_Key() {

                Util util = new Util();

                try {
                        image = ImageIO.read(getClass().getResourceAsStream("/object/" + "key" + ".png"));
                        image = util.scaleImage(image, GameSettings.TILE_SIZE, GameSettings. TILE_SIZE);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                name = "Key";
        }

}
