package org.example.question2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexValidator {
    private static final Pattern MOBILE = Pattern.compile("^(?:\\+91|0)?[6-9][0-9]{9}$");
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern USERNAME = Pattern.compile("^[A-Za-z0-9_]{3,20}$");
    private static final Pattern PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--- Regex Validator ---");
            System.out.println("1. Validate Mobile Number");
            System.out.println("2. Validate Email ID");
            System.out.println("3. Validate Username");
            System.out.println("4. Validate Password");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            try {
                switch (c) {
                    case "1":
                        System.out.print("Enter mobile: ");
                        String m = sc.nextLine().trim();
                        validate(MOBILE, m, "Mobile");
                        break;
                    case "2":
                        System.out.print("Enter email: ");
                        String e = sc.nextLine().trim();
                        validate(EMAIL, e, "Email");
                        break;
                    case "3":
                        System.out.print("Enter username: ");
                        String u = sc.nextLine().trim();
                        validate(USERNAME, u, "Username");
                        break;
                    case "4":
                        System.out.print("Enter password: ");
                        String p = sc.nextLine();
                        validate(PASSWORD, p, "Password");
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }
            System.out.println();
        }
    }

    private static void validate(Pattern pat, String input, String kind) {
        if (input == null) throw new IllegalArgumentException("null input");
        boolean ok = pat.matcher(input).matches();
        if (ok) System.out.println("Welcome, " + kind + " validated");
        else System.out.println("Invalid " + kind);
    }
}

