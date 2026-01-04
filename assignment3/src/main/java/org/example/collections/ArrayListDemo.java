package org.example.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListDemo {
    public static void run(){
        System.out.println("=== ArrayList Demo ===");
        // Constructors
        ArrayList<String> a1 = new ArrayList<>();
        ArrayList<String> a2 = new ArrayList<>(10);
        List<String> seed = Arrays.asList("one","two","three");
        ArrayList<String> a3 = new ArrayList<>(seed);

        System.out.println("Constructors created a1, a2, a3");

        // Methods demonstration
        a1.add("alpha"); // add(E)
        a1.add("beta");
        a1.add(1, "inserted"); // add(index, E)
        a1.addAll(a3); // addAll(Collection)
        System.out.println("After adds: " + a1);

        System.out.println("get(2): " + a1.get(2)); // get
        a1.set(2, "replaced"); // set
        System.out.println("After set: " + a1);

        a1.remove(0); // remove by index
        a1.remove("three"); // remove(Object)
        System.out.println("After removes: " + a1);

        System.out.println("contains 'beta': " + a1.contains("beta"));
        System.out.println("indexOf 'beta': " + a1.indexOf("beta"));
        a1.add("beta");
        System.out.println("lastIndexOf 'beta': " + a1.lastIndexOf("beta"));

        System.out.println("size: " + a1.size() + ", isEmpty: " + a1.isEmpty());

        Object[] arr = a1.toArray();
        System.out.println("toArray: " + Arrays.toString(arr));

        List<String> sub = a1.subList(0, Math.min(2, a1.size()));
        System.out.println("subList(0,2): " + sub);

        // capacity related (ensureCapacity and trimToSize)
        a1.ensureCapacity(50);
        a1.trimToSize();

        // clear
        a1.clear();
        System.out.println("After clear, size: " + a1.size());

        System.out.println();
    }
}

