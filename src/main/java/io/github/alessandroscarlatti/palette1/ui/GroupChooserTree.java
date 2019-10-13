package io.github.alessandroscarlatti.palette1.ui;

import io.github.alessandroscarlatti.palette1.domain.Palette;

import javax.swing.*;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class GroupChooserTree extends JTree {

    private Palette palette;

    public GroupChooserTree(Palette palette) {
        this.palette = palette;
    }
}
