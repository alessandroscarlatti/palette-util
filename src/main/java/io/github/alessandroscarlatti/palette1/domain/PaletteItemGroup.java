package io.github.alessandroscarlatti.palette1.domain;

import java.nio.file.Path;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class PaletteItemGroup {

    private String name;
    private Path dir;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getDir() {
        return dir;
    }

    public void setDir(Path dir) {
        this.dir = dir;
    }
}
