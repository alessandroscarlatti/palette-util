package io.github.alessandroscarlatti.palette1.ui;

import io.github.alessandroscarlatti.palette1.domain.PaletteItem;

import javax.swing.*;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class PaletteItemPanel extends JPanel {

    private PaletteItem item;

    public PaletteItemPanel(PaletteItem item) {
        this.item = item;
    }
}
