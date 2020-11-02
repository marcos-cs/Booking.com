package main.java.com.booking;

import java.util.Arrays;
import java.util.Collections;

public class ZigzagFlashion {
    public static void main(String[] args) {
        int arr[] = new int[]{4, 3, 7, 8, 6, 2, 1, 5, 19};
        int expected[] = new int[]{3, 7, 4, 8, 2, 6, 1, 19, 5}; // a < b > c < d > e < f > 1 < 19 > 5.
        boolean less = true;
        for (int i=0; i<arr.length-1; i++) {

            if (less && arr[i]>arr[i+1]) {
                var aux = arr[i+1];
                arr[i+1] = arr[i];
                arr[i] = aux;

            } else if (!less && arr[i]<arr[i+1] ){
                var aux = arr[i+1];
                arr[i+1] = arr[i];
                arr[i] = aux;

            }
            less = !less;
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.equals(arr, expected));


    }
}
