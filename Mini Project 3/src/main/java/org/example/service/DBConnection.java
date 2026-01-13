package org.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in != null) {
                props.load(in);
                String driver = props.getProperty("jdbc.driver");
                if (driver != null && !driver.isEmpty()) {
                    try {
                        Class.forName(driver);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("JDBC Driver class not found: " + driver, e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load db.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.user");
        String password = props.getProperty("jdbc.password");

        if (url == null || url.isEmpty()) {
            throw new SQLException("jdbc.url property not found in db.properties");
        }
        if (user == null || user.isEmpty()) {
            throw new SQLException("jdbc.user property not found in db.properties");
        }

        return DriverManager.getConnection(url, user, password);
    }
}

