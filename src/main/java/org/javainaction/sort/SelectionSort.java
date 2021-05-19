package org.javainaction.sort;

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
}
