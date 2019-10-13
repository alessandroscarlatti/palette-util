package io.github.alessandroscarlatti.palette1.ui;

import io.github.alessandroscarlatti.palette1.domain.Palette;
import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;

import javax.swing.*;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class PaletteFrame extends JFrame {

    public PaletteFrame(Palette palette) {
        PalettePanel palettePanel = new PalettePanel(palette);
        add(palettePanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void display() {
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());

            Palette palette = new Palette();

            SwingUtilities.invokeLater(() -> {
                PaletteFrame paletteFrame = new PaletteFrame(palette);
                paletteFrame.display();
            });
        } catch (Exception e) {
             throw new RuntimeException("Error running palette.", e);
        }
    }
}
