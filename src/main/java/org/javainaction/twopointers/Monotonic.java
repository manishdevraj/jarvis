package org.javainaction.twopointers;

import java.util.Arrays;

/**
 * If array is monotonic or not
 * Either increasing or decreasing
 * Example: {-1, -5, -10, -1100, -1100, -1101, -1102, -9001}
 * Output: true
 *
 * Example: {3, 3, 2, 2, 2, 1, 1, -1}
 * Output: true
 *
 * Example: {3, 3, 4, 5, 6, 7, 1, 1}
 * Output: false
 *
 */
public class Monotonic {

    // O(n) time | O(1) space
    public static boolean isMonotonic(int[] array) {
        for(int i = 0, j = i + 1, count = 0; i < array.length
                && j < array.length ; i++, j++) {
            int left = array[i];
            int right = array[j];
            //we got similar elements
            if (left == right) continue;
            //count acts way to know if we have increasing sequence or decreasing sequence
            else if (left <= right && count >= 0) {
                count++; //increasing
                continue;
            } else if(left >= right && count <= 0) {
                count--; //decreasing
                continue;
            }
            //it means we could not match it with previous case
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        var array = new int[] {-1, -5, -10, -1100, -1100, -1101, -1102, -9001};
        System.out.println("Is array monotonic " + Arrays.toString(array) + " " + isMonotonic(array));
        array = new int[] {3, 3, 2, 2, 2, 1, 1, -1};
        System.out.println("Is array monotonic " + Arrays.toString(array) + " " + isMonotonic(array));
        array = new int[] {3, 3, 4, 5, 6, 7, 1, 1};
        System.out.println("Is array monotonic " + Arrays.toString(array) + " " + isMonotonic(array));

    }
}
