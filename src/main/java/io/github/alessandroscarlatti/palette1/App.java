package io.github.alessandroscarlatti.palette1;

import io.github.alessandroscarlatti.palette1.domain.Palette;
import io.github.alessandroscarlatti.palette1.ui.FileSelector;

import javax.swing.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alessandro Scarlatti
 * @since Sunday, 10/13/2019
 */
public class App {

    private static App instance = new App();

    private Palette palette;
    private FileSelector fileSelector;

    private App() {
    }

    public List<Path> getSelections() {
        if (fileSelector == null)
            return Collections.emptyList();
        else {
            return fileSelector.getSelections();
        }
    }

    public void executeWithSwingWorker(Runnable runnable) {
        new SimpleSwingWorker(runnable).execute();
    }

    public static App getInstance() {
        return instance;
    }

    public static class SimpleSwingWorker extends SwingWorker {

        private Runnable runnable;

        public SimpleSwingWorker(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        protected Object doInBackground() throws Exception {
            runnable.run();
            return null;
        }
    }


    public Palette getPalette() {
        return palette;
    }

    public void setPalette(Palette palette) {
        this.palette = palette;
    }

    public void setFileSelector(FileSelector fileSelector) {
        this.fileSelector = fileSelector;
    }
}
