package org.example;

import org.example.dao.AccountDAO;
import org.example.model.AccountRecord;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        // Try to run bundled schema.sql if present, otherwise create table
        dao.runSchemaSqlIfPresent();
        dao.createTableIfNotExists();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance enquiry");
            System.out.println("5. Display account details");
            System.out.println("6. List all accounts");
            System.out.println("7. List all SAVING/CURRENT accounts");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter account id (leave empty to auto-generate): ");
                        String id = sc.nextLine().trim();
                        if (id.isEmpty()) {
                            id = UUID.randomUUID().toString().substring(0, 8);
                            System.out.println("Generated id: " + id);
                        }
                        System.out.print("Holder name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Type (SAVINGS/CURRENT): ");
                        String type = sc.nextLine().trim().toUpperCase();
                        if (!type.equals("SAVINGS") && !type.equals("CURRENT")) {
                            System.out.println("Invalid type. Use SAVINGS or CURRENT.");
                            break;
                        }
                        AccountRecord acc = new AccountRecord(id, type, name, 0.0);
                        dao.insert(acc);
                        System.out.println("Account created: " + id);
                    }
                    case "2" -> {
                        System.out.print("Account id: ");
                        String id = sc.nextLine().trim();
                        System.out.print("Amount to deposit: ");
                        double amt;
                        try {
                            amt = Double.parseDouble(sc.nextLine().trim());
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid amount.");
                            break;
                        }
                        if (amt <= 0) {
                            System.out.println("Amount must be positive.");
                            break;
                        }
                        Optional<AccountRecord> found = dao.findById(id);
                        if (found.isEmpty()) {
                            System.out.println("Account not found.");
                            break;
                        }
                        AccountRecord a = found.get();
                        a.setBalance(a.getBalance() + amt);
                        dao.update(a);
                        System.out.println("Deposited. New balance: " + a.getBalance());
                    }
                    case "3" -> {
                        System.out.print("Account id: ");
                        String id = sc.nextLine().trim();
                        System.out.print("Amount to withdraw: ");
                        double amt;
                        try {
                            amt = Double.parseDouble(sc.nextLine().trim());
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid amount.");
                            break;
                        }
                        if (amt <= 0) {
                            System.out.println("Amount must be positive.");
                            break;
                        }
                        Optional<AccountRecord> found = dao.findById(id);
                        if (found.isEmpty()) {
                            System.out.println("Account not found.");
                            break;
                        }
                        AccountRecord a = found.get();
                        if (a.getBalance() < amt) {
                            System.out.println("Insufficient funds.");
                            break;
                        }
                        a.setBalance(a.getBalance() - amt);
                        dao.update(a);
                        System.out.println("Withdrawn. New balance: " + a.getBalance());
                    }
                    case "4" -> {
                        System.out.print("Account id: ");
                        String id = sc.nextLine().trim();
                        Optional<AccountRecord> found = dao.findById(id);
                        if (found.isEmpty()) {
                            System.out.println("Account not found.");
                        } else {
                            System.out.println("Balance: " + found.get().getBalance());
                        }
                    }
                    case "5" -> {
                        System.out.print("Account id: ");
                        String id = sc.nextLine().trim();
                        Optional<AccountRecord> found = dao.findById(id);
                        if (found.isEmpty()) {
                            System.out.println("Account not found.");
                        } else {
                            System.out.println(found.get());
                        }
                    }
                    case "6" -> {
                        List<AccountRecord> all = dao.listAll();
                        if (all.isEmpty()) {
                            System.out.println("No accounts found.");
                        } else {
                            all.forEach(System.out::println);
                        }
                    }
                    case "7" -> {
                    	System.out.println("type 1 for SAVING and 2 for CURRENT");
                    	String i = sc.nextLine();
                    	List<AccountRecord> all = null;
                    	if(i=="1") {
                    		all = dao.allSingleTypeOfAccounts("SAVING");
                    	}
                    	else if(i=="2") all = dao.allSingleTypeOfAccounts("CURRENT");
                    	else {
                    		System.out.println("Wrong Input Try Again");
                    	}
                    	if(!all.isEmpty()) {
                    		all.forEach(System.out::println);
                    	}
                    	
                    }
                    case "8" -> {
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}