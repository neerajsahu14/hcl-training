import java.util.*;
import java.lang.*;

/* * Questio5 :
    Design a console-based Bank Management Program in Java that allows a user to create a
    bank account and perform basic banking operations such as deposit, withdrawal, balance
    enquiry, and account details display. The program should handle exceptions as required.
 */

class BankAccount{
    private String hodlerName;
    private String accountNumber;
    private double balance;
    public BankAccount(String holderName, String accountNumber){
        this.hodlerName = holderName;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }
    public void deposit(double amount){
        if(amount<=0){
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposited: " + amount);
    }
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }
    public double getBalance(){
        return balance;
    }
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + hodlerName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

public class Question5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        BankAccount account = new BankAccount(name, accNumber);
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Display Account Details");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.println("Current Balance: " + account.getBalance());
                        break;
                    case 4:
                        account.displayAccountDetails();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
