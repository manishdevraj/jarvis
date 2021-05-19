package org.javainaction.twopointers;

import java.util.Arrays;

/**
 * Write a function that takes an array anf returns two indices between which we need to sort an integer array
 * to make entire array sorted in ascending order.
 * If input array is already sorted return {-1, -1}
 *
 * For e.g. {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19} sorted needed at {3, 9}
 */
public class SubArraySort {
    public static void main(String[] args) {
        int[] expected = {3, 9};
        System.out.println("{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19} sub array sorting at : "
                + Arrays.toString(SubArraySort.subarraySort(new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19})));
    }

    public static int[] subarraySort(int[] array) {
        int leftOutOfOrder = Integer.MAX_VALUE;
        int rightOutOfOrder = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++) {
            int num = array[i];
            if(isOutOfOrder(i, num, array)) {
                leftOutOfOrder = Math.min(leftOutOfOrder, num);
                rightOutOfOrder = Math.max(rightOutOfOrder, num);
            }
        }

        if(leftOutOfOrder == Integer.MAX_VALUE) return new int[]{-1, -1};

        int leftIndex = 0;
        while (leftIndex < array.length
                && array[leftIndex] <= leftOutOfOrder ) {
            leftIndex++;
        }

        int rightIndex = array.length - 1;
        while (rightIndex >= 0
                && rightOutOfOrder <= array[rightIndex]) {
            rightIndex--;
        }

        return new int[]{leftIndex, rightIndex};
    }

    public static boolean isOutOfOrder(int i, int num, int[] array) {
        if (i == 0) {
            return array[i + 1] < num;
        }
        if (i == array.length - 1) {
            return num < array[i-1];
        }
        return array[i + 1] < num || num < array[i-1];
    }
}
