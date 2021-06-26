package org.javainaction.sort;

import java.util.Arrays;

/**
 * Implement bubble sort
 *
 * Idea is to use to move from left to right and use counter to start from 0
 * swap elements until next element is greater than previous element until counter reached at the end
 *
 * "We bubble until end and keep on swapping numbers that are unsorted"
 *
 */
public class BubbleSort {
    //Best: O(n) time | O(1) space
    //Average: O(n^2) time | O(1) space;
    //Worse: O(n^2) time | O(1) space;
    public static int[] bubbleSort(int[] array) {
        if(array.length == 0) return new int[] {};

        boolean isSorted = false;
        int counter = 0;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1 - counter; i++) {
                if (array[i] > array[i+1]) {
                    swap(i, i+1, array);
                    isSorted = false;
                }
            }
            counter++;
        }
        return array;
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] arg) {
        int[] input = {5, 2, 8, 5, 6, 3, 9};
        System.out.println(Arrays.toString(BubbleSort.bubbleSort(input)));
    }
}
