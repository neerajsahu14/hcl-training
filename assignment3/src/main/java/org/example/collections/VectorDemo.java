package org.example.collections;

import java.util.Arrays;
import java.util.Vector;

public class VectorDemo {
    public static void run(){
        System.out.println("=== Vector Demo ===");
        // Constructors
        Vector<Integer> v1 = new Vector<>();
        Vector<Integer> v2 = new Vector<>(20);
        Vector<Integer> v3 = new Vector<>(10, 5);

        System.out.println("Constructors created v1, v2, v3");

        System.out.println("v3 elements: "+v3.capacity() +" "+ v3.size());

        // Methods
        v1.add(1);
        v1.add(2);
        v1.add(0, 0);
        v1.addFirst(-1);
        v1.addAll(Arrays.asList(3,4,5));
        System.out.println("After adds: " + v1);

        System.out.println("elementAt(1): " + v1.elementAt(1));
        System.out.println("firstElement: " + v1.firstElement());
        System.out.println("lastElement: " + v1.lastElement());

        v1.insertElementAt(99, 2);
        System.out.println("After insertElementAt: " + v1);

        v1.removeElement(99);
        System.out.println("After removeElement(99): " + v1);

        System.out.println("contains 3: " + v1.contains(3));
        System.out.println("indexOf 3: " + v1.indexOf(3));

        v1.addElement(6);
        System.out.println("After addElement(6): " + v1);

        v1.removeAllElements();
        System.out.println("After removeAllElements, size: " + v1.size());

        v1.ensureCapacity(100);
        v1.trimToSize();

        System.out.println();
    }
}

