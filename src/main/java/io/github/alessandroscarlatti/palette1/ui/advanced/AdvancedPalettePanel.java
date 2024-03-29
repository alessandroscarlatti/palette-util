package io.github.alessandroscarlatti.palette1.ui.advanced;

import io.github.alessandroscarlatti.palette1.domain.Palette;
import io.github.alessandroscarlatti.palette1.domain.PaletteItemGroup;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class AdvancedPalettePanel extends JPanel {

    private GroupChooserTree groupChooserTree;
    private GroupViewerPanel groupViewerPanel;

    public AdvancedPalettePanel(Palette palette) {
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        groupChooserTree = new GroupChooserTree(palette);
        groupViewerPanel = new GroupViewerPanel(new PaletteItemGroup());

        add(groupChooserTree, BorderLayout.LINE_START);
        add(groupViewerPanel, BorderLayout.CENTER);
    }
}
