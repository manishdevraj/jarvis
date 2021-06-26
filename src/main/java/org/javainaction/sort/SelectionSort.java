package org.javainaction.sort;

import java.util.Arrays;

/**
 * Implement selection sort
 *
 * Idea is find the smallest number from left to right and swap with start index until start reaches the end.
 *
 * "We select number to swap"
 */
public class SelectionSort {
    public static int[] selectionSort(int[] array) {
        //Best: O(n^2) time | O(1) space
        //Average: O(n^2) time | O(1) space;
        //Worse: O(n^2) time | O(1) space;
        if (array.length == 0) return new int[] {};
        for(int i = 0; i < array.length - 1; i++) {
            int smallestIndex = i;
            for(int j = i + 1; j < array.length; j++) {
                if (array[j] < array[smallestIndex]) {
                    smallestIndex = j;
                }
            }
            swap(array, i, smallestIndex);
        }
        return array;
    }

    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] arg) {
        int[] input = {5, 2, 8, 5, 6, 3, 9};
        System.out.println(Arrays.toString(SelectionSort.selectionSort(input)));
    }
}
