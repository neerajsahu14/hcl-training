package org.example;

import org.example.dao.StudentDAO;
import org.example.model.Student;

/**
 * Question 4: Write a program to delete of all Students whose records whose
 * year of passing is 2024 and branch is Civil.
 */
public class Question4 {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        System.out.println("--- Before Deletion ---");
        System.out.println("All student records:");
        for (Student student : dao.listAll()) {
            System.out.println(student);
        }

        // Delete students where year_of_passing = 2024 and branch = Civil
        System.out.println("\nDeleting students with year 2024 and branch Civil...");
        dao.deleteByYearAndBranch(2024, "Civil");

        System.out.println("\n--- After Deletion ---");
        System.out.println("All remaining student records:");
        for (Student student : dao.listAll()) {
            System.out.println(student);
        }
    }
}

