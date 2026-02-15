package com.booking;

import java.util.Arrays;

/**
 * Zigzag Fashion
 *
 * Rearranges an array into zigzag fashion: a < b > c < d > e < f ...
 * The algorithm swaps adjacent elements that violate the zigzag property.
 */
public class ZigzagFlashion {

    public static void main(String[] args) {
        int[] arr = {4, 3, 7, 8, 6, 2, 1, 5, 19};
        zigzag(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Rearranges the array in-place so that:
     * arr[0] < arr[1] > arr[2] < arr[3] > arr[4] ...
     *
     * @param arr the array to rearrange
     */
    public static void zigzag(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        boolean expectLess = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if ((expectLess && arr[i] > arr[i + 1]) || (!expectLess && arr[i] < arr[i + 1])) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
            expectLess = !expectLess;
        }
    }
}
