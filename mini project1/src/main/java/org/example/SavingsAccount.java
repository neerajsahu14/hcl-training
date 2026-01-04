package org.example;

import java.text.DecimalFormat;

public class SavingsAccount extends Account {
    private double interestRate; // example extra field

    public SavingsAccount(String id, String holderName) {
        super(id, holderName);
        this.interestRate = 0.03; // default
    }

    public SavingsAccount(String id, String holderName, double balance) {
        this(id, holderName);
        this.balance = balance;
    }

    @Override
    public void displayDetails() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("---- Savings Account ----");
        System.out.println("Account ID : " + id);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance     : " + df.format(balance));
        System.out.println("InterestRate: " + interestRate);
    }
}

