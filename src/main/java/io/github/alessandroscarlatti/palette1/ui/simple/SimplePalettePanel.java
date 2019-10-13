package io.github.alessandroscarlatti.palette1.ui.simple;

import io.github.alessandroscarlatti.palette1.App;
import io.github.alessandroscarlatti.palette1.domain.Palette;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alessandro Scarlatti
 * @since Sunday, 10/13/2019
 */
public class SimplePalettePanel extends JPanel {

    public SimplePalettePanel(Palette palette) {
        setLayout(new BorderLayout());

        JLabel description = new JLabel();
        description.setText("You can also drag and drop items to install.");

        SimplePaletteTree simplePaletteTree = new SimplePaletteTree(palette);
        App.getInstance().setFileSelector(simplePaletteTree);

        add(description, BorderLayout.PAGE_END);
        add(new JScrollPane(simplePaletteTree), BorderLayout.CENTER);
        add(new SimplePaletteToolbar(palette), BorderLayout.PAGE_START);
    }
}
