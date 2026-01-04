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

        // Debugging: print resolved working dir and URL so we can see where H2 will create files
        try {
            String userDir = System.getProperty("user.dir");
            System.out.println("[DB DEBUG] user.dir = " + userDir);
            System.out.println("[DB DEBUG] jdbc.url = " + url);
            // If using a relative H2 file URL (jdbc:h2:./...), show the resolved file path
            if (url != null && url.startsWith("jdbc:h2:")) {
                String pathPart = url.substring("jdbc:h2:".length());
                // Remove any JDBC options after ';'
                String filePath = pathPart.split(";")[0];
                if (filePath.startsWith("./") || filePath.startsWith("/") || filePath.matches("^[A-Za-z]:.*")) {
                    java.nio.file.Path resolved = java.nio.file.Paths.get(userDir).resolve(filePath).normalize();
                    System.out.println("[DB DEBUG] resolved db file path = " + resolved.toAbsolutePath());
                }
            }
        } catch (Exception e) {
            // don't fail connection for logging
            System.err.println("[DB DEBUG] failed to compute resolved path: " + e.getMessage());
        }
        return DriverManager.getConnection(url, user, password);
    }
}
