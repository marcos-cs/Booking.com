package com.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

class ZigzagFlashionTest {

    @Test
    @DisplayName("Should rearrange array in zigzag fashion: a < b > c < d > ...")
    void shouldRearrangeInZigzag() {
        int[] arr = {4, 3, 7, 8, 6, 2, 1, 5, 19};
        ZigzagFlashion.zigzag(arr);

        // Verify zigzag property: arr[0] < arr[1] > arr[2] < arr[3] > ...
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 == 0) {
                assertThat(arr[i]).isLessThan(arr[i + 1]);
            } else {
                assertThat(arr[i]).isGreaterThan(arr[i + 1]);
            }
        }
    }

    @Test
    @DisplayName("Should handle already zigzag array")
    void shouldHandleAlreadyZigzag() {
        int[] arr = {1, 3, 2, 4, 1};
        ZigzagFlashion.zigzag(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 == 0) {
                assertThat(arr[i]).isLessThan(arr[i + 1]);
            } else {
                assertThat(arr[i]).isGreaterThan(arr[i + 1]);
            }
        }
    }

    @Test
    @DisplayName("Should handle single element array")
    void shouldHandleSingleElement() {
        int[] arr = {42};
        ZigzagFlashion.zigzag(arr);
        assertThat(arr).containsExactly(42);
    }

    @Test
    @DisplayName("Should handle null array")
    void shouldHandleNull() {
        ZigzagFlashion.zigzag(null); // Should not throw
    }

    @Test
    @DisplayName("Should handle two element array")
    void shouldHandleTwoElements() {
        int[] arr = {5, 2};
        ZigzagFlashion.zigzag(arr);
        assertThat(arr[0]).isLessThan(arr[1]);
    }
}
