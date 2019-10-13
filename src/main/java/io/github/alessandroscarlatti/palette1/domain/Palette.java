package io.github.alessandroscarlatti.palette1.domain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static io.github.alessandroscarlatti.palette1.PropertyUtils.*;
import static java.lang.System.getProperties;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 10/12/2019
 */
public class Palette {

    private Path dir;
    private Path targetDir;
    private String name;
    private List<PaletteItemGroup> groups = new ArrayList<>();
    private String excludeRegex;

    public static Palette parsePalette() {
        Palette palette = new Palette();
        palette.setDir(Paths.get(optProperty(getProperties(), "palette.dir", "")).toAbsolutePath().normalize());
        palette.setTargetDir(Paths.get(optProperty(getProperties(), "palette.target.dir", palette.getDir().getParent().toString())));
        palette.setExcludeRegex(optProperty(getProperties(), "palette.exclude.regex", "(?s).+\\.jar$"));
        return palette;
    }

    public static void loadProperties() {
        String strPalettePropsFile = optProperty(getProperties(), "palette.props.file", null);
        if (strPalettePropsFile == null) {
            return;
        } else {
            // load the props file properties into the system properties
            Path propsFile = Paths.get(strPalettePropsFile).toAbsolutePath().normalize();
            System.out.println("Loading props from " + propsFile);
            Properties props = readPropertiesFile(propsFile);
            getProperties().putAll(props);
        }
    }

    public List<PaletteItemGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PaletteItemGroup> groups) {
        this.groups = groups;
    }

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

    public Path getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(Path targetDir) {
        this.targetDir = targetDir;
    }

    public String getExcludeRegex() {
        return excludeRegex;
    }

    public void setExcludeRegex(String excludeRegex) {
        this.excludeRegex = excludeRegex;
    }
}
