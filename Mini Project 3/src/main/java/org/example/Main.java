package org.example;

import org.example.dao.LoginDAO;
import org.example.dao.StudentDAO;
import org.example.exception.StudentException;
import org.example.exception.ValidationException;
import org.example.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final StudentDAO studentDAO = new StudentDAO();
    private static final LoginDAO loginDAO = new LoginDAO();
    private static final Scanner scanner = new Scanner(System.in);
    private static String currentUser = null;

    public static void main(String[] args) {
        try {
            // Initialize database tables
            System.out.println("========================================");
            System.out.println("Initializing Database...");
            System.out.println("========================================");
            studentDAO.runSchemaSqlIfPresent();
            studentDAO.createStudentTableIfNotExists();
            loginDAO.createUsersTableIfNotExists();
            loginDAO.initializeDefaultUsers();

            System.out.println("\n========================================");
            System.out.println("Student Management System");
            System.out.println("========================================\n");

            // Login flow
            if (!login()) {
                System.out.println("✗ Login failed. Exiting application.");
                System.exit(0);
            }

            // Main menu
            mainMenu();

        } catch (StudentException e) {
            System.err.println("✗ Error: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Handles user login with 3 attempts
     */
    private static boolean login() {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            try {
                if (loginDAO.authenticate(username, password)) {
                    currentUser = username;
                    System.out.println("✓ Login successful! Welcome, " + currentUser + "\n");
                    return true;
                } else {
                    attempts--;
                    if (attempts > 0) {
                        System.out.println("✗ Invalid credentials. Attempts remaining: " + attempts + "\n");
                    }
                }
            } catch (StudentException e) {
                System.err.println("✗ Error: " + e.getMessage());
                attempts--;
                if (attempts > 0) {
                    System.out.println("Attempts remaining: " + attempts + "\n");
                }
            }
        }

        System.out.println("✗ Maximum login attempts exceeded.");
        return false;
    }

    /**
     * Displays and handles main menu
     */
    private static void mainMenu() {
        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Add Students");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Students by Eno");
            System.out.println("4. Update Students Branch");
            System.out.println("5. Delete Students by Eno");
            System.out.println("6. Display Sorted Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> addStudent();
                    case "2" -> displayAllStudents();
                    case "3" -> searchStudent();
                    case "4" -> updateStudentBranch();
                    case "5" -> deleteStudent();
                    case "6" -> displaySortedStudents();
                    case "7" -> {
                        System.out.println("✓ Thank you for using Student Management System. Goodbye!");
                        System.exit(0);
                    }
                    default -> System.out.println("✗ Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (Exception e) {
                System.err.println("✗ Error: " + e.getMessage());
            }
        }
    }

    /**
     * Option 1: Add a new student
     */
    private static void addStudent() {
        try {
            System.out.println("\n--- Add New Student ---");

            System.out.print("Enter Student Eno: ");
            String eno = scanner.nextLine().trim();

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Branch: ");
            String branch = scanner.nextLine().trim();

            System.out.print("Enter Semester (1-8): ");
            int semester;
            try {
                semester = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid semester. Must be a number.");
                return;
            }

            System.out.print("Enter Percentage: ");
            double percentage;
            try {
                percentage = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid percentage. Must be a decimal number.");
                return;
            }

            System.out.print("Enter Year of Passing: ");
            int year;
            try {
                year = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid year. Must be a number.");
                return;
            }

            Student student = new Student(eno, name, branch, semester, percentage, year);
            studentDAO.addStudent(student);

        } catch (ValidationException e) {
            System.out.println("✗ Validation Error: " + e.getMessage());
        } catch (StudentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Option 2: Display all students
     */
    private static void displayAllStudents() {
        try {
            System.out.println("\n--- All Students ---");
            List<Student> students = studentDAO.getAllStudents();

            if (students.isEmpty()) {
                System.out.println("No students found in the database.");
                return;
            }

            printStudentsTable(students);
        } catch (StudentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Option 3: Search student by enrollment number
     */
    private static void searchStudent() {
        try {
            System.out.println("\n--- Search Student by Eno ---");
            System.out.print("Enter Student Eno: ");
            String eno = scanner.nextLine().trim();

            Optional<Student> student = studentDAO.searchByEno(eno);

            if (student.isPresent()) {
                System.out.println("\n✓ Student Found:");
                printStudentsTable(List.of(student.get()));
            } else {
                System.out.println("✗ Student with Eno '" + eno + "' not found.");
            }
        } catch (StudentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Option 4: Update student branch
     */
    private static void updateStudentBranch() {
        try {
            System.out.println("\n--- Update Student Branch ---");
            System.out.print("Enter Student Eno: ");
            String eno = scanner.nextLine().trim();

            Optional<Student> student = studentDAO.searchByEno(eno);
            if (student.isEmpty()) {
                System.out.println("✗ Student with Eno '" + eno + "' not found.");
                return;
            }

            System.out.println("Current branch: " + student.get().getBranch());
            System.out.print("Enter new branch: ");
            String newBranch = scanner.nextLine().trim();

            studentDAO.updateBranch(eno, newBranch);

        } catch (ValidationException e) {
            System.out.println("✗ Validation Error: " + e.getMessage());
        } catch (StudentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Option 5: Delete student by enrollment number
     */
    private static void deleteStudent() {
        try {
            System.out.println("\n--- Delete Student ---");
            System.out.print("Enter Student Eno: ");
            String eno = scanner.nextLine().trim();

            Optional<Student> student = studentDAO.searchByEno(eno);
            if (student.isEmpty()) {
                System.out.println("✗ Student with Eno '" + eno + "' not found.");
                return;
            }

            System.out.println("Student to delete: " + student.get());
            System.out.print("Are you sure you want to delete this student? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes") || confirm.equals("y")) {
                studentDAO.deleteByEno(eno);
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (StudentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Option 6: Display sorted students
     */
    private static void displaySortedStudents() {
        try {
            System.out.println("\n--- Display Sorted Students ---");
            System.out.println("Sort by:");
            System.out.println("1. Enrollment Number (Eno)");
            System.out.println("2. Name");
            System.out.println("3. Percentage (highest first)");
            System.out.println("4. Semester");
            System.out.print("Choose sort option (1-4): ");

            String choice = scanner.nextLine().trim();
            String sortBy;

            switch (choice) {
                case "1":
                    sortBy = "eno";
                    break;
                case "2":
                    sortBy = "name";
                    break;
                case "3":
                    sortBy = "percentage";
                    break;
                case "4":
                    sortBy = "semester";
                    break;
                default:
                    System.out.println("✗ Invalid choice. Sorting by Eno.");
                    sortBy = "eno";
            }

            List<Student> students = studentDAO.getSortedStudents(sortBy);

            if (students.isEmpty()) {
                System.out.println("No students found in the database.");
                return;
            }

            System.out.println("\n--- Students sorted by " + sortBy + " ---");
            printStudentsTable(students);

        } catch (StudentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Prints students in a formatted table
     */
    private static void printStudentsTable(List<Student> students) {
        System.out.println("\n" + "=".repeat(120));
        System.out.printf("%-5s %-10s %-20s %-15s %-10s %-12s %-12s%n",
                "ID", "ENO", "NAME", "BRANCH", "SEMESTER", "PERCENTAGE", "YEAR");
        System.out.println("=".repeat(120));

        for (Student student : students) {
            System.out.printf("%-5d %-10s %-20s %-15s %-10d %-12.2f %-12d%n",
                    student.getId(),
                    student.getEno(),
                    student.getName(),
                    student.getBranch(),
                    student.getSemester(),
                    student.getPercentage(),
                    student.getYear_of_passing());
        }
        System.out.println("=".repeat(120));
        System.out.println("Total: " + students.size() + " student(s)\n");
    }
}

