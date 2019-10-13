package io.github.alessandroscarlatti.palette1.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * @author Alessandro Scarlatti
 * @since Sunday, 10/13/2019
 */
public class Images {

    public static Image icon32() {
        return getImage("/icon32.png");
    }

    public static Image icon48() {
        return getImage("/icon48.png");
    }

    public static Image add16() {
        return getImage("/add16.png");
    }

    public static Image getImage(String path) {
        try {
            return ImageIO.read(Images.class.getResource(path));
        } catch (IOException e) {
            throw new RuntimeException("Error reading image at " + path, e);
        }
    }
}
