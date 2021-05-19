package org.javainaction.twopointers;

import java.util.Arrays;

/**
 * If array is monotonic or not
 * Either increasing or decreasing
 *
 */
public class Monotonic {
    public static void main(String[] args) {
        var array = new int[] {-1, -5, -10, -1100, -1100, -1101, -1102, -9001};
        System.out.println("Is array monotonic " + Arrays.toString(array) + " " + isMonotonic(array));
        array = new int[] {3, 3, 2, 2, 2, 1, 1, -1};
        System.out.println("Is array monotonic " + Arrays.toString(array) + " " + isMonotonic(array));
        array = new int[] {3, 3, 4, 5, 6, 7, 1, 1};
        System.out.println("Is array monotonic " + Arrays.toString(array) + " " + isMonotonic(array));

    }
    public static boolean isMonotonic(int[] array) {
        // O(n) time | O(1) space
        for(int i = 0, j = i + 1, count = 0; i < array.length
                && j < array.length ; i++, j++) {
            int left = array[i];
            int right = array[j];
            if (left == right) continue;
            else if (left <= right && count >= 0) {
                count++;
                continue;
            } else if(left >= right && count <= 0) {
                count--;
                continue;
            }
            return false;
        }
        return true;
    }
}
