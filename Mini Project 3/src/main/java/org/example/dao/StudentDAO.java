package org.example.dao;

import org.example.exception.StudentException;
import org.example.exception.ValidationException;
import org.example.model.Student;
import org.example.service.DBConnection;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO {

    /**
     * Creates students table if it doesn't exist
     */
    public void createStudentTableIfNotExists() throws StudentException {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    eno VARCHAR(20) UNIQUE NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    branch VARCHAR(50) NOT NULL,
                    semester INT NOT NULL,
                    percentage DOUBLE NOT NULL CHECK (percentage > 0),
                    year_of_passing INT NOT NULL
                )
                """;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✓ Students table initialized successfully");
        } catch (SQLException e) {
            throw new StudentException("Error creating students table: " + e.getMessage(), e);
        }
    }

    /**
     * Runs schema.sql if present in resources
     */
    public void runSchemaSqlIfPresent() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("schema.sql")) {
            if (in == null) {
                return;
            }

            StringBuilder sql = new StringBuilder();
            java.nio.file.Files.lines(java.nio.file.Paths.get(
                    getClass().getClassLoader().getResource("schema.sql").toURI()))
                    .forEach(line -> {
                        if (!line.trim().startsWith("--") && !line.trim().isEmpty()) {
                            sql.append(line).append("\n");
                        }
                    });

            String[] statements = sql.toString().split(";");
            try (Connection conn = DBConnection.getConnection();
                 Statement stmt = conn.createStatement()) {
                for (String statement : statements) {
                    if (!statement.trim().isEmpty()) {
                        try {
                            stmt.execute(statement.trim());
                        } catch (SQLException e) {
                            // Ignore duplicate key errors as they're expected
                            if (!e.getMessage().contains("Duplicate")) {
                                System.err.println("Warning: " + e.getMessage());
                            }
                        }
                    }
                }
                System.out.println("✓ Schema initialized from schema.sql");
            }
        } catch (Exception e) {
            System.err.println("Note: Could not run schema.sql: " + e.getMessage());
        }
    }

    /**
     * Validates student data before insertion/update
     */
    private void validateStudent(Student student) throws ValidationException {
        if (student.getEno() == null || student.getEno().trim().isEmpty()) {
            throw new ValidationException("Student Enrollment Number (Eno) cannot be empty");
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new ValidationException("Student Name cannot be empty");
        }
        if (student.getBranch() == null || student.getBranch().trim().isEmpty()) {
            throw new ValidationException("Branch cannot be empty");
        }
        if (student.getSemester() <= 0 || student.getSemester() > 8) {
            throw new ValidationException("Semester must be between 1 and 8");
        }
        if (student.getPercentage() <= 0) {
            throw new ValidationException("Percentage must be positive (> 0)");
        }
        if (student.getPercentage() > 100) {
            throw new ValidationException("Percentage cannot exceed 100");
        }
        if (student.getYear_of_passing() < 2000 || student.getYear_of_passing() > 2100) {
            throw new ValidationException("Year of passing must be reasonable (2000-2100)");
        }
    }

    /**
     * Checks if enrollment number already exists
     */
    public boolean isEnoExists(String eno) throws StudentException {
        String sql = "SELECT COUNT(*) FROM students WHERE eno = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, eno.trim());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new StudentException("Error checking enrollment number: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Adds a new student record
     */
    public void addStudent(Student student) throws ValidationException, StudentException {
        validateStudent(student);

        if (isEnoExists(student.getEno())) {
            throw new ValidationException("Student with enrollment number '" + student.getEno() + "' already exists");
        }

        String sql = "INSERT INTO students (eno, name, branch, semester, percentage, year_of_passing) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getEno().trim());
            pstmt.setString(2, student.getName().trim());
            pstmt.setString(3, student.getBranch().trim());
            pstmt.setInt(4, student.getSemester());
            pstmt.setDouble(5, student.getPercentage());
            pstmt.setInt(6, student.getYear_of_passing());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✓ Student added successfully with Eno: " + student.getEno());
            }
        } catch (SQLException e) {
            throw new StudentException("Error adding student: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all students
     */
    public List<Student> getAllStudents() throws StudentException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, eno, name, branch, semester, percentage, year_of_passing FROM students ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("eno"),
                        rs.getString("name"),
                        rs.getString("branch"),
                        rs.getInt("semester"),
                        rs.getDouble("percentage"),
                        rs.getInt("year_of_passing")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            throw new StudentException("Error retrieving students: " + e.getMessage(), e);
        }
        return students;
    }

    /**
     * Searches for a student by enrollment number
     */
    public Optional<Student> searchByEno(String eno) throws StudentException {
        String sql = "SELECT id, eno, name, branch, semester, percentage, year_of_passing FROM students WHERE eno = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, eno.trim());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("eno"),
                        rs.getString("name"),
                        rs.getString("branch"),
                        rs.getInt("semester"),
                        rs.getDouble("percentage"),
                        rs.getInt("year_of_passing")
                );
                return Optional.of(student);
            }
        } catch (SQLException e) {
            throw new StudentException("Error searching student: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    /**
     * Updates the branch of a student
     */
    public void updateBranch(String eno, String newBranch) throws ValidationException, StudentException {
        if (newBranch == null || newBranch.trim().isEmpty()) {
            throw new ValidationException("Branch cannot be empty");
        }

        Optional<Student> student = searchByEno(eno);
        if (student.isEmpty()) {
            throw new StudentException("Student with enrollment number '" + eno + "' not found");
        }

        String sql = "UPDATE students SET branch = ? WHERE eno = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newBranch.trim());
            pstmt.setString(2, eno.trim());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✓ Student branch updated successfully to: " + newBranch);
            }
        } catch (SQLException e) {
            throw new StudentException("Error updating student branch: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a student by enrollment number
     */
    public void deleteByEno(String eno) throws StudentException {
        Optional<Student> student = searchByEno(eno);
        if (student.isEmpty()) {
            throw new StudentException("Student with enrollment number '" + eno + "' not found");
        }

        String sql = "DELETE FROM students WHERE eno = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, eno.trim());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✓ Student deleted successfully with Eno: " + eno);
            }
        } catch (SQLException e) {
            throw new StudentException("Error deleting student: " + e.getMessage(), e);
        }
    }

    /**
     * Returns sorted list of students
     */
    public List<Student> getSortedStudents(String sortBy) throws StudentException {
        String orderBy;
        switch (sortBy.toLowerCase()) {
            case "name":
                orderBy = "name";
                break;
            case "percentage":
                orderBy = "percentage DESC";
                break;
            case "semester":
                orderBy = "semester";
                break;
            case "eno":
            default:
                orderBy = "eno";
                break;
        }

        String sql = "SELECT id, eno, name, branch, semester, percentage, year_of_passing FROM students ORDER BY " + orderBy;
        List<Student> students = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("eno"),
                        rs.getString("name"),
                        rs.getString("branch"),
                        rs.getInt("semester"),
                        rs.getDouble("percentage"),
                        rs.getInt("year_of_passing")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            throw new StudentException("Error retrieving sorted students: " + e.getMessage(), e);
        }
        return students;
    }
}

