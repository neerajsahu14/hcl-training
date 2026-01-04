package org.oop.lamdaexpression.functioninterfaces;


import java.util.concurrent.atomic.AtomicReference;

public class Test {
    public static void main(String[] args) {
        A a = (s)-> {
            System.out.println("Hi "+ s);
        };
        a.show("Welcome");

        B b = ()-> 5;
        System.out.println(b.getValue());

        AtomicReference<Factorial> fRef = new AtomicReference<>();
        fRef.set(n->{
            if(n<=1) return 1;
            return n* fRef.get().compute(n-1);
        });

    }


}
