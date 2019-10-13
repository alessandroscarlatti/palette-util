package io.github.alessandroscarlatti;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class ComponentByExample extends JPanel {

    private JComponent example;
    private JComponent actual;

    public ComponentByExample(JComponent example, JComponent actual) {
        this.example = example;
        this.actual = actual;
    }

    @Override
    public Color getForeground() {
        return example.getForeground();
    }

    @Override
    public boolean isOpaque() {
        return example.isOpaque();
    }


}
