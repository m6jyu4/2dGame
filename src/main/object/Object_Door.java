package main.object;

import main.GameSettings;
import main.Util;

import javax.imageio.ImageIO;

public class Object_Door extends GameObject {

    public Object_Door() {

        Util util = new Util();

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/" + "door" + ".png"));
            image = util.scaleImage(image, GameSettings.TILE_SIZE, GameSettings. TILE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        name = "Door";
        collision = true;

    }

}
