package org.example;

// Abstract base account demonstrating abstraction and encapsulation
public abstract class Account {
    protected String id;
    protected String holderName;
    protected double balance;

    public Account(String id, String holderName) {
        this.id = id;
        this.holderName = holderName;
        this.balance = 0.0;
    }

    public String getId() { return id; }
    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public double getBalance() { return balance; }

    // Method overloading: deposit with double and int
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        balance += amount;
    }

    public void deposit(int amount) {
        deposit((double) amount);
    }

    // Withdraw
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw amount must be positive");
        if (amount > balance) throw new InsufficientFundsException("Insufficient funds");
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s, holder=%s, balance=%.2f]", getClass().getSimpleName(), id, holderName, balance);
    }

    public abstract void displayDetails();
}

