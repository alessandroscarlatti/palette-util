package io.github.alessandroscarlatti.palette1.ui.simple;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static io.github.alessandroscarlatti.palette1.ui.Images.add16;

/**
 * @author Alessandro Scarlatti
 * @since Sunday, 10/13/2019
 */
public class SimpleContextMenu extends JPopupMenu {

    public SimpleContextMenu() {
        JMenuItem menuItemInstall = new JMenuItem(new InstallAction());
        add(menuItemInstall);
    }

    public static class InstallAction extends AbstractAction {

        public InstallAction() {
            super("Install", new ImageIcon(add16()));
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
