package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class BankManager {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Path csvPath;

    public BankManager(Path csvPath) {
        this.csvPath = csvPath;
        try {
            loadFromCsv();
        } catch (IOException e) {
            System.out.println("Warning: could not load accounts: " + e.getMessage());
        }
    }

    public Account findAccount(String id) {
        return accounts.get(id);
    }

    public Account createAccount(String id, String type, String holderName) throws IllegalArgumentException {
        if (accounts.containsKey(id)) throw new IllegalArgumentException("Account id already exists");
        Account acc;
        if ("SAVINGS".equalsIgnoreCase(type)) {
            acc = new SavingsAccount(id, holderName);
        } else if ("CURRENT".equalsIgnoreCase(type)) {
            acc = new CurrentAccount(id, holderName);
        } else {
            throw new IllegalArgumentException("Unknown account type: " + type);
        }
        accounts.put(id, acc);
        try { saveAllToCsv(); } catch (IOException e) { System.out.println("Warning: failed to save: " + e.getMessage()); }
        return acc;
    }

    public void deposit(String id, double amount) {
        Account a = accounts.get(id);
        if (a == null) throw new IllegalArgumentException("Account not found");
        a.deposit(amount);
        try { saveAllToCsv(); } catch (IOException e) { System.out.println("Warning: failed to save: " + e.getMessage()); }
    }

    public void withdraw(String id, double amount) throws InsufficientFundsException {
        Account a = accounts.get(id);
        if (a == null) throw new IllegalArgumentException("Account not found");
        a.withdraw(amount);
        try { saveAllToCsv(); } catch (IOException e) { System.out.println("Warning: failed to save: " + e.getMessage()); }
    }

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        accounts.values().forEach(System.out::println);
    }

    private void ensureCsvParentExists() throws IOException {
        Path parent = csvPath.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
        if (!Files.exists(csvPath)) {
            Files.createFile(csvPath);
            // write header
            try (BufferedWriter w = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8)) {
                w.write("id,type,holderName,balance\n");
            }
        }
    }

    public void loadFromCsv() throws IOException {
        ensureCsvParentExists();
        try (BufferedReader r = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
            String line;
            boolean first = true;
            while ((line = r.readLine()) != null) {
                if (first) { first = false; if (line.trim().startsWith("id,")) continue; }
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",", -1);
                if (parts.length < 4) continue;
                String id = parts[0].trim();
                String type = parts[1].trim();
                String holder = parts[2].trim();
                double bal = 0.0;
                try { bal = Double.parseDouble(parts[3].trim()); } catch (NumberFormatException ignored) {}
                Account a;
                if ("SAVINGS".equalsIgnoreCase(type)) {
                    a = new SavingsAccount(id, holder, bal);
                } else {
                    a = new CurrentAccount(id, holder, bal);
                }
                accounts.put(id, a);
            }
        }
    }

    public void saveAllToCsv() throws IOException {
        ensureCsvParentExists();
        try (BufferedWriter w = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8)) {
            w.write("id,type,holderName,balance\n");
            for (Account a : accounts.values()) {
                String type = a instanceof SavingsAccount ? "SAVINGS" : "CURRENT";
                // escape commas in holder name by replacing with space (simple)
                String safeName = a.getHolderName().replace(',', ' ');
                w.write(String.format("%s,%s,%s,%.2f\n", a.getId(), type, safeName, a.getBalance()));
            }
        }
    }
}

