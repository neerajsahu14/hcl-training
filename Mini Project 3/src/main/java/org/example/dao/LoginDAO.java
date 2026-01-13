package org.example.dao;

import org.example.exception.StudentException;
import org.example.service.DBConnection;

import java.sql.*;

public class LoginDAO {

    /**
     * Creates users table if it doesn't exist
     */
    public void createUsersTableIfNotExists() throws StudentException {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    username VARCHAR(50) PRIMARY KEY,
                    password VARCHAR(100) NOT NULL
                )
                """;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✓ Users table initialized successfully");
        } catch (SQLException e) {
            throw new StudentException("Error creating users table: " + e.getMessage(), e);
        }
    }

    /**
     * Authenticates a user with username and password
     */
    public boolean authenticate(String username, String password) throws StudentException {
        if (username == null || username.trim().isEmpty()) {
            throw new StudentException("Username cannot be empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new StudentException("Password cannot be empty");
        }

        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username.trim());
            pstmt.setString(2, password.trim());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new StudentException("Error during authentication: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Initializes default test users
     */
    public void initializeDefaultUsers() {
        try {
            // Check if users table exists and has data
            String checkSql = "SELECT COUNT(*) FROM users";
            try (Connection conn = DBConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(checkSql)) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return; // Users already exist
                }
            }

            // Insert default users
            String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, "admin");
                pstmt.setString(2, "admin123");
                pstmt.executeUpdate();

                pstmt.setString(1, "user");
                pstmt.setString(2, "user123");
                pstmt.executeUpdate();

                System.out.println("✓ Default test users initialized (admin/admin123, user/user123)");
            }
        } catch (SQLException e) {
            System.err.println("Warning: Could not initialize default users: " + e.getMessage());
        }
    }
}

