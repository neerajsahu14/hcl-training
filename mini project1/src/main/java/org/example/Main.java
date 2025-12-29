package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Path csv = Paths.get(System.getProperty("user.dir"), "mini project1", "src", "main", "resources", "data", "accounts.csv");
        BankManager manager = new BankManager(csv);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance enquiry");
            System.out.println("5. Display account details");
            System.out.println("6. List all accounts");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter account id: ");
                        String id = sc.nextLine().trim();
                        System.out.print("Holder name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Type (SAVINGS/CURRENT): ");
                        String type = sc.nextLine().trim();
                        manager.createAccount(id, type, name);
                        System.out.println("Account created.");
                    }
                    case "2" -> {
                        System.out.print("Account id: ");
                        String id = sc.nextLine().trim();
                        System.out.print("Amount to deposit: ");
                        double amt = Double.parseDouble(sc.nextLine().trim());
                        manager.deposit(id, amt);
                        System.out.println("Deposited.");
                    }
                    case "3" -> {
                        System.out.print("Account id: ");
                        String id = sc.nextLine().trim();
                        System.out.print("Amount to withdraw: ");
                        double amt = Double.parseDouble(sc.nextLine().trim());
                        manager.withdraw(id, amt);
                        System.out.println("Withdrawn.");
                    }
                    case "4" -> {
                        System.out.print("Account id: ");
                        String id = sc.nextLine().trim();
                        Account a = manager.findAccount(id);
                        if (a == null) System.out.println("Account not found."); else System.out.println("Balance: " + a.getBalance());
                    }
                    case "5" -> {
                        System.out.print("Account id: ");
                        String id = sc.nextLine().trim();
                        Account a = manager.findAccount(id);
                        if (a == null) System.out.println("Account not found."); else a.displayDetails();
                    }
                    case "6" -> manager.listAccounts();
                    case "7" -> {
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}