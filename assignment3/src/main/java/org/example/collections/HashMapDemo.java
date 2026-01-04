package org.example.collections;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void run(){
        System.out.println("=== HashMap Demo ===");
        // Constructors
        HashMap<String,Integer> m1 = new HashMap<>();
        HashMap<String,Integer> m2 = new HashMap<>(20);
        HashMap<String,Integer> m3 = new HashMap<>(m1);

        System.out.println("Constructors created m1, m2, m3");

        // Methods
        m1.put("a",1);
        m1.put("b",2);
        m1.putIfAbsent("b", 20);
        m1.putAll(Map.of("c",3, "d",4));
        System.out.println("After puts: " + m1);

        System.out.println("get('c'): " + m1.get("c"));
        System.out.println("containsKey 'a': " + m1.containsKey("a"));
        System.out.println("containsValue 4: " + m1.containsValue(4));

        System.out.println("size: " + m1.size() + ", isEmpty: " + m1.isEmpty());

        m1.remove("a");
        System.out.println("After remove a: " + m1);

        m1.replace("b", 22);
        System.out.println("After replace b: " + m1);

        m1.computeIfAbsent("z", k->100);
        System.out.println("After computeIfAbsent z: " + m1);

        m1.merge("c", 5, Integer::sum);
        System.out.println("After merge c: " + m1);

        m1.clear();
        System.out.println("After clear, size: " + m1.size());

        System.out.println();
    }
}

