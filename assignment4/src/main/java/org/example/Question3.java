package org.example;

import org.example.dao.StudentDAO;
import org.example.model.Student;

/**
 * Question 3: Write a program to increase 5% to percentage of all Students
 * whose branch is CSE.
 */
public class Question3 {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        System.out.println("--- Before Update ---");
        System.out.println("Students in CSE branch:");
        for (Student student : dao.listAll()) {
            if ("CSE".equalsIgnoreCase(student.getBranch())) {
                System.out.println(student);
            }
        }

        // Increase percentage by 5% for all CSE students
        System.out.println("\nUpdating CSE students with 5% increase...");
        dao.updatePercentageByBranch("CSE", 5.0);

        System.out.println("\n--- After Update ---");
        System.out.println("Students in CSE branch:");
        for (Student student : dao.listAll()) {
            if ("CSE".equalsIgnoreCase(student.getBranch())) {
                System.out.println(student);
            }
        }
    }
}

