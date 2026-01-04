package org.example.question1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface CustomerOperations {
    void viewDetails();
}

interface BankingOperations {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientFundsException;
    double getBalance();
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) { super(msg); }
}

class BankAccount implements CustomerOperations, BankingOperations {
    private String name;
    private String accId;
    private double balance;

    public BankAccount(String accId, String name) {
        this.accId = accId;
        this.name = name;
        this.balance = 0.0;
    }

    @Override
    public void viewDetails() {
        System.out.printf("Account: %s, Name: %s, Balance: %.2f\n", accId, name, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw must be positive");
        if (amount > balance) throw new InsufficientFundsException("Not enough balance");
        balance -= amount;
    }

    @Override
    public double getBalance() { return balance; }
}

public class BankingApp {
    public static void demo() {
        Scanner sc = new Scanner(System.in);
        Map<String,BankAccount> db = new HashMap<>();
        System.out.println("--- Banking App Demo ---");
        System.out.print("Create account id: ");
        String id = sc.nextLine().trim();
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        BankAccount acc = new BankAccount(id, name);
        db.put(id, acc);
        try {
            acc.deposit(1000);
            acc.withdraw(200);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        acc.viewDetails();
        System.out.println("Demo finished. (exceptions handled)");
    }
}

