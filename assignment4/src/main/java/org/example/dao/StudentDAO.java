package org.example.dao;

import org.example.model.Student;
import org.example.service.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO {
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS students (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(100) NOT NULL, " +
            "branch VARCHAR(50) NOT NULL, " +
            "semester INT NOT NULL, " +
            "percentage DOUBLE NOT NULL, " +
            "year_of_passing INT NOT NULL)";

    public StudentDAO() {
    }

    public void createTableIfNotExists() {
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_SQL);
            System.out.println("Students table created or already exists.");
        } catch (SQLException e) {
            System.err.println("Failed to create students table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insert(Student student) {
        String sql = "INSERT INTO students (name, branch, semester, percentage, year_of_passing) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getBranch());
            ps.setInt(3, student.getSemester());
            ps.setDouble(4, student.getPercentage());
            ps.setInt(5, student.getYearOfPassing());
            ps.executeUpdate();
            System.out.println("Student inserted successfully: " + student.getName());
        } catch (SQLException e) {
            System.err.println("Failed to insert student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Optional<Student> findById(int id) {
        String sql = "SELECT id, name, branch, semester, percentage, year_of_passing FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getInt(4), rs.getDouble(5), rs.getInt(6));
                    return Optional.of(student);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to query student: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Student> listAll() {
        String sql = "SELECT id, name, branch, semester, percentage, year_of_passing FROM students";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getDouble(5), rs.getInt(6));
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Failed to list students: " + e.getMessage());
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> findByBranchAndSemester(String branch, int semester) {
        String sql = "SELECT id, name, branch, semester, percentage, year_of_passing FROM students WHERE branch = ? AND semester = ?";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, branch);
            ps.setInt(2, semester);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getInt(4), rs.getDouble(5), rs.getInt(6));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to find students by branch and semester: " + e.getMessage());
            e.printStackTrace();
        }
        return students;
    }

    public boolean updatePercentageByBranch(String branch, double percentageIncrease) {
        String sql = "UPDATE students SET percentage = percentage + ? WHERE branch = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, percentageIncrease);
            ps.setString(2, branch);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Updated " + rowsAffected + " student(s) in branch " + branch);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Failed to update percentage: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteByYearAndBranch(int year, String branch) {
        String sql = "DELETE FROM students WHERE year_of_passing = ? AND branch = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setString(2, branch);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Deleted " + rowsAffected + " student(s) with year " + year + " and branch " + branch);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Failed to delete students: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Student student) {
        String sql = "UPDATE students SET name = ?, branch = ?, semester = ?, percentage = ?, year_of_passing = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getBranch());
            ps.setInt(3, student.getSemester());
            ps.setDouble(4, student.getPercentage());
            ps.setInt(5, student.getYearOfPassing());
            ps.setInt(6, student.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Failed to update student: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Failed to delete student: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}

