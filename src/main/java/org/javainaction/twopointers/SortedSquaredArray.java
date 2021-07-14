package org.javainaction.twopointers;

import java.util.Arrays;
/**
 * Given a sorted array, create a new array containing squares of all the number of the input array in the
 * sorted order.
 * Example 1:
 *
 * Input: [-2, -1, 0, 2, 3]
 * Output: [0, 1, 4, 4, 9]
 * Example 2:
 *
 * Input: [-3, -1, 0, 1, 2]
 * Output: [0 1 1 4 9]
 *
 * @see SortedArraySquares
 */
public class SortedSquaredArray {
    //O(n) time | O(n) space - where n is length of the input array
    public static int[] sortedSquaredArray(int[] array) {
        int[] result = new int[array.length];
        int left = 0;
        int right = array.length - 1;
        int index = array.length - 1;
        while (left <= right) {
            int leftSqr = array[left] * array[left];
            int rightSqr = array[right] * array[right];
            if (leftSqr > rightSqr) {
                result[index--] = leftSqr;
                left++;
            } else {
                result[index--] = rightSqr;
                right--;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = sortedSquaredArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("{1, 2, 3, 4, 5, 6} Sorted array is " + Arrays.toString(result));
        result = sortedSquaredArray(new int[]{-3, -2, -1, 2});
        System.out.println("{-3, -2, -1, -2} Sorted array is " + Arrays.toString(result));
        result = sortedSquaredArray(new int[]{0});
        System.out.println("{0} Sorted array is " + Arrays.toString(result));
    }
}
