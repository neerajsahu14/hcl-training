package org.example;

import java.text.DecimalFormat;

public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String id, String holderName) {
        super(id, holderName);
        this.overdraftLimit = 0.0; // for demo
    }

    public CurrentAccount(String id, String holderName, double balance) {
        this(id, holderName);
        this.balance = balance;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw amount must be positive");
        if (amount > balance + overdraftLimit) throw new InsufficientFundsException("Insufficient funds (including overdraft)");
        balance -= amount;
    }

    @Override
    public void displayDetails() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("---- Current Account ----");
        System.out.println("Account ID : " + id);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance     : " + df.format(balance));
        System.out.println("Overdraft   : " + overdraftLimit);
    }
}

