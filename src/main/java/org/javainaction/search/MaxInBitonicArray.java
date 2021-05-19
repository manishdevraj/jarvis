package org.javainaction.search;

/**
 * Find the maximum value in a given Bitonic array. An array is considered bitonic if it is monotonically increasing
 * and then monotonically decreasing. Monotonically increasing or decreasing means that for any index i in the array
 * arr[i] != arr[i+1].
 *
 * Example 1:
 *
 * Input: [1, 3, 8, 12, 4, 2]
 * Output: 12
 * Explanation: The maximum number in the input bitonic array is '12'.
 * Example 2:
 *
 * Input: [3, 8, 3, 1]
 * Output: 8
 * Example 3:
 *
 * Input: [1, 3, 8, 12]
 * Output: 12
 * Example 4:
 *
 * Input: [10, 9, 8]
 * Output: 10
 */
public class MaxInBitonicArray {
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int n = arr.length;
        int left = 0; int right = n - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] > arr[middle + 1]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return arr[left];
    }

    public static void main(String[] args) {
        System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12, 4, 2 }));
        System.out.println(MaxInBitonicArray.findMax(new int[] { 3, 8, 3, 1 }));
        System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12 }));
        System.out.println(MaxInBitonicArray.findMax(new int[] { 10, 9, 8 }));
        System.out.println(MaxInBitonicArray.findMax(new int[] { 10, 15, 1, 3, 8 }));
    }
}
