package io.github.alessandroscarlatti.palette1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

/**
 * @author Alessandro Scarlatti
 * @since Sunday, 10/13/2019
 */
public class PropertyUtils {

    public static String reqProperty(Properties props, String name) {
        String value = props.getProperty(name);
        System.out.println("Read req property " + name + "=" + value);
        if (value == null)
            throw new IllegalStateException("Missing required property " + name);
        else
            return value;
    }

    public static String optProperty(Properties props, String name, String defaultValue) {
        String value = props.getProperty(name);
        System.out.println("Read opt property " + name + "=" + value + " (default=" + defaultValue + ")");
        if (value == null)
            return defaultValue;
        else
            return value;
    }

    public static Properties readPropertiesFile(Path file) {
        try (InputStream is = new FileInputStream(file.toFile())) {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (Exception e) {
            throw new RuntimeException("Error reading properties file " + file, e);
        }
    }

    public static Properties overlayProperties(Properties[] arrProps) {
        // overlay each prop in the list on top of the previous
        Properties targetProps = new Properties();

        if (arrProps == null)
            return targetProps;

        for (Properties props : arrProps) {
            // ignore null properties objects
            if (props == null)
                continue;
            for (Object key : props.keySet()) {
                targetProps.put(key, props.get(key));
            }
        }

        return targetProps;
    }
}
