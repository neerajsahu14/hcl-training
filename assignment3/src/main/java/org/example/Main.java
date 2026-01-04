package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.example.collections.ArrayListDemo;
import org.example.collections.HashMapDemo;
import org.example.collections.HashtableDemo;
import org.example.collections.TreeMapDemo;
import org.example.collections.VectorDemo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== Collections Demo Menu ===");
            System.out.println("1. ArrayList Demo");
            System.out.println("2. Vector Demo");
            System.out.println("3. HashMap Demo");
            System.out.println("4. Hashtable Demo");
            System.out.println("5. TreeMap Demo");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    ArrayListDemo.run();
                    break;
                case "2":
                    VectorDemo.run();
                    break;
                case "3":
                    HashMapDemo.run();
                    break;
                case "4":
                    HashtableDemo.run();
                    break;
                case "5":
                    TreeMapDemo.run();
                    break;
                case "6":
                    System.out.println("Bye");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();
        }
    }
}