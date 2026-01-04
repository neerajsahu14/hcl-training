package org.example.collections;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void run(){
        System.out.println("=== TreeMap Demo ===");
        // Constructors
        TreeMap<String,Integer> r1 = new TreeMap<>();
        TreeMap<String,Integer> r2 = new TreeMap<>(Map.of("b",2,"a",1));
        TreeMap<String,Integer> r3 = new TreeMap<>(r2);

        System.out.println("Constructors created r1, r2, r3");

        r1.put("c",3);
        r1.put("a",1);
        r1.put("b",2);
        System.out.println("After puts: " + r1);

        System.out.println("firstKey: " + r1.firstKey());
        System.out.println("lastKey: " + r1.lastKey());

        System.out.println("headMap("+"b"+"): " + r1.headMap("b"));
        System.out.println("tailMap("+"b"+"): " + r1.tailMap("b"));
        System.out.println("subMap(a,c): " + r1.subMap("a","c"));

        System.out.println("ceilingKey('b'): " + r1.ceilingKey("b"));
        System.out.println("floorKey('b'): " + r1.floorKey("b"));
        System.out.println("higherKey('a'): " + r1.higherKey("a"));
        System.out.println("lowerKey('a'): " + r1.lowerKey("a"));

        r1.clear();
        System.out.println("After clear, size: " + r1.size());

        System.out.println();
    }
}

