package com.example.properties;

import java.io.InputStream;
import java.util.Properties;

public class DBProperties {
    public static final String url;
    public static final String username;
    public static final String password;
    public static final String driver;

    private static final String CONFIG_FILE = "/db.properties";

    static {
        Properties properties = new Properties();
        try {
            InputStream fileInputStream = DBProperties.class.getResourceAsStream(CONFIG_FILE);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        driver = properties.getProperty("driver");
    }
}

