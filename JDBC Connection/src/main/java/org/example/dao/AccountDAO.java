package org.example.dao;

import org.example.model.AccountRecord;
import org.example.service.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO {
    private static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS accounts (id VARCHAR(50) PRIMARY KEY, type VARCHAR(50), holder_name VARCHAR(100), balance DOUBLE)";

    public AccountDAO() {
    }

    public void createTableIfNotExists() {
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create accounts table", e);
        }
    }

    public void insert(AccountRecord a) {
        String sql = "INSERT INTO accounts(id, type, holder_name, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getId());
            ps.setString(2, a.getType());
            ps.setString(3, a.getHolderName());
            ps.setDouble(4, a.getBalance());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert account", e);
        }
    }

    public Optional<AccountRecord> findById(String id) {
        String sql = "SELECT id, type, holder_name, balance FROM accounts WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AccountRecord a = new AccountRecord(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                    return Optional.of(a);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to query account", e);
        }
        return Optional.empty();
    }

    public List<AccountRecord> listAll() {
        String sql = "SELECT id, type, holder_name, balance FROM accounts";
        List<AccountRecord> out = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new AccountRecord(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list accounts", e);
        }
        return out;
    }
    public List<AccountRecord> allSingleTypeOfAccounts(String type){
    	String sql = "SELECT id, type, holder, balance FROM accounts WHERE type = ?";
    	List<AccountRecord> out = new ArrayList<>();
    	try(Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
    		ps.setString(1,type);
    		try (ResultSet rs = ps.executeQuery()) {
	    		while (rs.next()) {
	                out.add(new AccountRecord(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
	            }
    		}
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list accounts", e);
        }
        return out;
    }

    public boolean update(AccountRecord a) {
        String sql = "UPDATE accounts SET type = ?, holder_name = ?, balance = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getType());
            ps.setString(2, a.getHolderName());
            ps.setDouble(3, a.getBalance());
            ps.setString(4, a.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update account", e);
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete account", e);
        }
    }

    public void runSchemaSqlIfPresent() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("schema.sql")) {
            if (in == null) return;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                String[] parts = sb.toString().split(";\n");
                try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement()) {
                    for (String p : parts) {
                        String t = p.trim();
                        if (!t.isEmpty()) st.execute(t);
                    }
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Failed to run schema.sql", e);
        }
    }
}

