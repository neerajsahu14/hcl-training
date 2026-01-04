package org.oop.exceptions;

public class TestExceptions {
    public static void main(String[] args) {

        try {
            System.out.println(10 / 0);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        // array index out of bound exception
        int[] arr = {1, 2, 3};
        int[] arr2 = {1,2,3,4,5};
        for(int i=0;i<arr2.length;i++) {
            try {
                System.out.println(arr2[i]/arr[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Array Index Out of Bounds Exception: " + e.getMessage());
            }
        }
    }
}
