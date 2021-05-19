package org.javainaction.twopointers;

import java.util.Arrays;

public class SortedSquaredArray {

    public static void main(String[] args) {
        int[] result = SortedSquaredArray.sortedSquaredArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("{1, 2, 3, 4, 5, 6} Sorted array is " + Arrays.toString(result));
        result = SortedSquaredArray.sortedSquaredArray(new int[]{-3, -2, -1, 2});
        System.out.println("{-3, -2, -1, 4} Sorted array is " + Arrays.toString(result));
        result = SortedSquaredArray.sortedSquaredArray(new int[]{0});
        System.out.println("{0} Sorted array is " + Arrays.toString(result));
    }

    //O(n) time | O(n) space - where n is length of the input array
    public static int[] sortedSquaredArray(int[] array) {
        int[] result = new int[array.length];
        int left = 0;
        int right = array.length - 1;
        int index = array.length - 1;
        while (left <= right) {
            int leftSqr = Math.abs(array[left] * array[left]);
            int rightSqr = Math.abs(array[right] * array[right]);
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
}
