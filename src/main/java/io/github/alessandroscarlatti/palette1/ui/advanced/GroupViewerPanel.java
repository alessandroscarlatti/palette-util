package io.github.alessandroscarlatti.palette1.ui.advanced;

import io.github.alessandroscarlatti.palette1.domain.PaletteItemGroup;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class GroupViewerPanel extends JPanel {

    private CardLayout cardLayout;
    private PaletteItemGroup group;

    public GroupViewerPanel(PaletteItemGroup group) {
        this.group = group;
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        JPanel groupPanel = new JPanel();
        groupPanel.add(new JLabel("Stuff and things."));
        groupPanel.add(new JLabel("Stuff and things."));

        add(groupPanel, "group1");

        cardLayout.show(this, "group1");
    }
}
