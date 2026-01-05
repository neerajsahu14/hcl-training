package org.example;

import org.example.dao.StudentDAO;
import org.example.model.Student;

/**
 * Question 2: Write a program to create a table called Students using JDBC
 * and insert records into it.
 */
public class Question2 {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Create table if not exists
        dao.createTableIfNotExists();

        // Insert sample student records
        dao.insert(new Student("Raj Kumar", "CSE", 6, 78.5, 2024));
        dao.insert(new Student("Priya Singh", "CSE", 7, 82.3, 2024));
        dao.insert(new Student("Amit Patel", "EC", 7, 75.8, 2024));
        dao.insert(new Student("Sneha Sharma", "Civil", 6, 80.2, 2024));
        dao.insert(new Student("Vikram Reddy", "Civil", 7, 72.5, 2024));
        dao.insert(new Student("Neha Gupta", "CSE", 5, 85.1, 2025));
        dao.insert(new Student("Rohan Verma", "EC", 7, 79.4, 2024));
        dao.insert(new Student("Anjali Tiwari", "Mechanical", 6, 76.6, 2025));

        System.out.println("\nAll students have been inserted into the database.");
        System.out.println("\nStudent Records:");
        for (Student student : dao.listAll()) {
            System.out.println(student);
        }
    }
}

