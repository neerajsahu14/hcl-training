package org.example.collections;

import java.util.Hashtable;
import java.util.Enumeration;

public class HashtableDemo {
    public static void run(){
        System.out.println("=== Hashtable Demo ===");
        // Constructors
        Hashtable<String,Integer> t1 = new Hashtable<>();
        Hashtable<String,Integer> t2 = new Hashtable<>(20);
        Hashtable<String,Integer> t3 = new Hashtable<>(t1);

        System.out.println("Constructors created t1, t2, t3");

        // Methods
        t1.put("x", 10);
        t1.put("y", 20);
        t1.put("z", 30);
        System.out.println("After puts: " + t1);

        System.out.println("get('y'): " + t1.get("y"));
        System.out.println("containsKey 'x': " + t1.containsKey("x"));
        System.out.println("contains '30' (via containsValue): " + t1.containsValue(30));

        System.out.println("size: " + t1.size());

        t1.remove("x");
        System.out.println("After remove x: " + t1);

        Enumeration<Integer> elements = t1.elements();
        System.out.print("elements: ");
        while(elements.hasMoreElements()) System.out.print(elements.nextElement()+" ");
        System.out.println();

        t1.clear();
        System.out.println("After clear, size: " + t1.size());

        System.out.println();
    }
}

