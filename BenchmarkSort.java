import java.util.*;

public class BenchmarkSort {
    public static void main(String[] args) {
        final int itemNumber = 100000;
        int[] intArr1 = new int[itemNumber];
        int[] intArr2 = new int[itemNumber];
        for (int i = 0; i < itemNumber; i++) {
            int ranNum = generateRandom();
            // Create 2 arrays
            intArr1[i] = ranNum;
            intArr2[i] = ranNum;
        }
        // Start selection sort in millisecond
        System.out.println("The array size is " + itemNumber);
        checkArrayEqual(intArr1, intArr2);
        long selectSortStart = System.currentTimeMillis();
        selectionSort(intArr1);
        // Stop selection sort in millisecond
        System.out.print("Selection sort Array 1 used ");
        System.out.print(System.currentTimeMillis() - selectSortStart);
        System.out.println(" milliseconds.");
        // Start array sort in millisecond
        long arraySortStart = System.currentTimeMillis();
        Arrays.sort(intArr2);
        // Stop array sort in millisecond
        System.out.print("Java array sort Array 2 used ");
        System.out.print(System.currentTimeMillis() - arraySortStart);
        System.out.println(" milliseconds.");
        checkArrayEqual(intArr1, intArr2);
    }
    
    public static int generateRandom() {
        return (int)(Integer.MAX_VALUE * Math.random());
    }
    
    static void selectionSort(int[] A) {
        for (int lastPlace = A.length-1; lastPlace > 0; lastPlace--) {
            int maxLoc = 0;
            for (int j = 1; j <= lastPlace; j++) {
                if (A[j] > A[maxLoc]) {
                    maxLoc = j;
                }
            }
            int temp = A[maxLoc];
            A[maxLoc] = A[lastPlace];
            A[lastPlace] = temp;
        }
    }
    
    static void checkArrayEqual(int[] arr1, int[] arr2) {
        if (Arrays.equals(arr1, arr2))
            System.out.println("Array 1 is the same as Array 2.");
        else
            System.out.println("Not same");
    }
       
}