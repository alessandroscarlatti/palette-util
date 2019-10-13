package io.github.alessandroscarlatti.palette1.ui.frame;

import io.github.alessandroscarlatti.palette1.App;
import io.github.alessandroscarlatti.palette1.PropertyUtils;
import io.github.alessandroscarlatti.palette1.domain.Palette;
import io.github.alessandroscarlatti.palette1.ui.Images;
//import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static io.github.alessandroscarlatti.palette1.PropertyUtils.optProperty;
import static io.github.alessandroscarlatti.palette1.domain.Palette.loadProperties;
import static io.github.alessandroscarlatti.palette1.ui.Images.icon32;
import static io.github.alessandroscarlatti.palette1.ui.Images.icon48;
import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperties;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class PaletteFrame extends JFrame {

    public PaletteFrame(Palette palette) {
        try {
            PaletteTabbedPane paletteTabbedPane = new PaletteTabbedPane(palette);
            add(paletteTabbedPane);
            setTitle("Palette [" + palette.getDir() + "]");
            setAlwaysOnTop(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            List<Image> images = Arrays.asList(icon32(), icon48());

            setIconImages(images);
        } catch (Exception e) {
            throw new RuntimeException("Error creating palette frame.", e);
        }
    }

    private void display() {
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            loadProperties();

//            if (parseBoolean(optProperty(getProperties(), "palette.ui.laf.enabled", "false"))) {
//                UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
//            } else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            }

            Palette palette = Palette.parsePalette();

            App.getInstance().setPalette(palette);

            SwingUtilities.invokeLater(() -> {
                PaletteFrame paletteFrame = new PaletteFrame(palette);
                paletteFrame.display();
            });
        } catch (Exception e) {
            throw new RuntimeException("Error running palette.", e);
        }
    }
}
