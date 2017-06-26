package com.framework.template.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Noop on 20.06.2017.
 */
public class PropertyReader {

    private static final String PROP_FILE = "application.properties";

    public static String loadProperty(String property) throws FileNotFoundException {
        return getPropertyString(property, PROP_FILE);
    }

    private static String getPropertyString(String name, String filePath) throws FileNotFoundException {
        Properties property = new Properties();
        InputStream input = new FileInputStream(filePath);

        try {
            property.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = property.getProperty(name);
        }
        return value;
    }

}
