import java.util.*;

/* * Question 2 :
    Write a menu driven program in Java to perform insertion, deletion, linear search, binary
    search, to find maximum value, to count even/ odd and to perform insertion sort operation in
    one dimensional array.
* */

class Question2{
    // insertion
    public int[] insert(int[] arr, int element, int pos) {
        int[] newArr = new int[arr.length + 1];
        for (int i = 0, j = 0; i < newArr.length; i++)
            if (i == pos)
                newArr[i] = element;
            else
                newArr[i] = arr[j++];
        return newArr;
    }

    // deletion
    public int[] delete(int[] arr, int pos) {
        int[] newArr = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++)
            if (i != pos)
                newArr[j++] = arr[i];
        return newArr;
    }

    // linear search
    public int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == key)
                return i;
        return -1;
    }

    // binary search
    public int binarySearch(int[] arr, int key) {
        int l=0, r = arr.length-1;
        while(l<=r){
            int mid = l+ (r-l)/2;
            if(arr[mid]==key) return mid;
            if(arr[mid]<key) l= mid+1;
            else r= mid-1;
        }
        return -1;
    }

    // find maximum value arr must be non empty
    public int findMax(int[] arr) {
        int max = arr[0];
        for(int i:arr)
            max = Math.max(max, i);
        return max;
    }

    // count even
    public int countEven(int[] arr) {
        int count = 0;
        for(int i:arr)
            if(i%2==0) count++;
        return count;
    }

    // count odd
    public int countOdd(int[] arr) {
        int count = 0;
        for(int i:arr)
            if(i%2!=0) count++;
        return count;
    }

    // insertion sort in place
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // main method
    public static void main(String[] args) {
        Question2 arrayOps = new Question2();
        int[] arr = {5, 2, 9, 1, 5, 6};

        // Insertion
        arr = arrayOps.insert(arr, 3, 2);
        System.out.println("After Insertion: " + Arrays.toString(arr));

        // Deletion
        arr = arrayOps.delete(arr, 4);
        System.out.println("After Deletion: " + Arrays.toString(arr));

        // Linear Search
        int index = arrayOps.linearSearch(arr, 9);
        System.out.println("Linear Search for 9: " + index);

        // Binary Search
        arrayOps.insertionSort(arr);
        index = arrayOps.binarySearch(arr, 5);
        System.out.println("Binary Search for 5: " + index);

        // Find Maximum
        int max = arrayOps.findMax(arr);
        System.out.println("Maximum Value: " + max);

        // Count Even and Odd
        int evenCount = arrayOps.countEven(arr);
        int oddCount = arrayOps.countOdd(arr);
        System.out.println("Even Count: " + evenCount);
        System.out.println("Odd Count: " + oddCount);

        // Insertion Sort
        int[] unsortedArr = {12, 11, 13, 5, 6};
        arrayOps.insertionSort(unsortedArr);
        System.out.println("Sorted Array: " + Arrays.toString(unsortedArr));
    }
}