package org.example;

import org.example.dao.StudentDAO;
import org.example.model.Student;
import java.util.List;

/**
 * Question 5: Write a program to display records of all Students whose semester
 * is 7 and branch is EC.
 */
public class Question5 {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        System.out.println("--- Students with Semester 7 and Branch EC ---");
        List<Student> students = dao.findByBranchAndSemester("EC", 7);

        if (students.isEmpty()) {
            System.out.println("No students found with semester 7 and branch EC");
        } else {
            System.out.println("Found " + students.size() + " student(s):");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}

