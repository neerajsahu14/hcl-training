package org.example.model;

public class AccountRecord {
    private String id;
    private String type;
    private String holderName;
    private double balance;

    public AccountRecord() { }

    public AccountRecord(String id, String type, String holderName, double balance) {
        this.id = id;
        this.type = type;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return String.format("Account[id=%s,type=%s,holder=%s,balance=%.2f]", id, type, holderName, balance);
    }
}

