package io.github.alessandroscarlatti.palette1.ui.simple;

import io.github.alessandroscarlatti.palette1.App;
import io.github.alessandroscarlatti.palette1.domain.Palette;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static io.github.alessandroscarlatti.palette1.ui.Images.add16;

/**
 * @author Alessandro Scarlatti
 * @since Sunday, 10/13/2019
 */
public class SimplePaletteToolbar extends JToolBar {

    public SimplePaletteToolbar(Palette palette) {
        add(new JButton(new InstallAction()));
        setAlignmentY(-1f);
    }

    public static class InstallAction extends AbstractAction {

        private Palette palette;

        public InstallAction(Palette palette) {
            this.palette = palette;
        }

        public InstallAction() {
            super("Add Selected Item(s)", new ImageIcon(add16()));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Path> files = App.getInstance().getSelections();
            System.out.println("Adding files: " + files);

            App.getInstance().executeWithSwingWorker(() -> {
                try {
                    for (Path file : files) {
                        Files.copy(file, App.getInstance().getPalette().getTargetDir().resolve(file.getFileName()));
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }
    }
}
