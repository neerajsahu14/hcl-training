package org.example;

import java.util.Scanner;

/**
 * Assignment 4 - JDBC Student Management System
 * Menu-driven program to execute all 5 questions
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n============================================");
            System.out.println("   JDBC Student Management System");
            System.out.println("============================================");
            System.out.println("1. Question 1 - Test JDBC Connection");
            System.out.println("2. Question 2 - Create Table and Insert Records");
            System.out.println("3. Question 3 - Increase 5% Percentage for CSE Branch");
            System.out.println("4. Question 4 - Delete Civil Branch Students (Year 2024)");
            System.out.println("5. Question 5 - Display EC Branch Semester 7 Students");
            System.out.println("6. Exit");
            System.out.println("============================================");
            System.out.print("Enter your choice (1-6): ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.println("\n--- Question 1: Testing JDBC Connection ---");
                        Question1.main(new String[]{});
                    }
                    case "2" -> {
                        System.out.println("\n--- Question 2: Creating Table and Inserting Records ---");
                        Question2.main(new String[]{});
                    }
                    case "3" -> {
                        System.out.println("\n--- Question 3: Updating CSE Branch Percentages ---");
                        Question3.main(new String[]{});
                    }
                    case "4" -> {
                        System.out.println("\n--- Question 4: Deleting Civil Branch Students (2024) ---");
                        Question4.main(new String[]{});
                    }
                    case "5" -> {
                        System.out.println("\n--- Question 5: Displaying EC Branch Semester 7 Students ---");
                        Question5.main(new String[]{});
                    }
                    case "6" -> {
                        System.out.println("\nExiting the application. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                }
            } catch (Exception e) {
                System.err.println("Error executing question: " + e.getMessage());
                e.printStackTrace();
            }
        }

        sc.close();
    }
}