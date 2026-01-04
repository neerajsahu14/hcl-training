package org.example;

import java.util.Scanner;

import org.example.question1.BankingApp;
import org.example.question2.RegexValidator;
import org.example.question3.StudentManager;
import org.example.question4.EmployeeManager;
import org.example.question5.UniversityManager;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--- Assignment Runner ---");
            System.out.println("1. Assignment 1 - BankAccount (interfaces & exceptions)");
            System.out.println("2. Assignment 2 - Regex Validator (mobile/email/username/password)");
            System.out.println("3. Assignment 3 - Student Manager (ArrayList/Vector/List)");
            System.out.println("4. Assignment 4 - Employee Manager (HashMap/Hashtable/TreeMap)");
            System.out.println("5. Assignment 5 - University Manager (collections)");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            String line = sc.nextLine().trim();
            switch (line) {
                case "1":
                    BankingApp.demo();
                    break;
                case "2":
                    RegexValidator.menu();
                    break;
                case "3":
                    StudentManager.menu();
                    break;
                case "4":
                    EmployeeManager.menu();
                    break;
                case "5":
                    UniversityManager.menu();
                    break;
                case "6":
                    System.out.println("Goodbye");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();
        }
    }
}

