package io.github.alessandroscarlatti.palette1.ui.frame;

import io.github.alessandroscarlatti.palette1.domain.Palette;
import io.github.alessandroscarlatti.palette1.ui.advanced.AdvancedPalettePanel;
import io.github.alessandroscarlatti.palette1.ui.simple.SimplePalettePanel;
import io.github.alessandroscarlatti.palette1.ui.simple.SimplePaletteTree;

import javax.swing.*;

/**
 * @author Alessandro Scarlatti
 * @since Sunday, 10/13/2019
 */
public class PaletteTabbedPane extends JTabbedPane {

    private SimplePalettePanel simplePalettePanel;
    private AdvancedPalettePanel advancedPalettePanel;

    public PaletteTabbedPane(Palette palette) {
        simplePalettePanel = new SimplePalettePanel(palette);
        advancedPalettePanel = new AdvancedPalettePanel(palette);
        add("Palette", simplePalettePanel);
//        add("Advanced", advancedPalettePanel);
    }
}
