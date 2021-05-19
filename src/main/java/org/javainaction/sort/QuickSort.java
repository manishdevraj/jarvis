package org.javainaction.sort;

public class QuickSort {

    //Best O(nlog(n)) time | O(nlog(n)) space
    //Avg O(nlog(n)) time | O(nlog(n)) space
    //Worse O(n^2) time | O(nlog(n)) space
    public static int[] quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    public static void quickSort(int[] array, int startIdx, int endIdx) {
        if (startIdx >= endIdx) return;

        int pivotIdx = startIdx;
        int leftIdx = startIdx + 1;
        int rightIdx = endIdx;

        while (leftIdx <= rightIdx) {
            if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]){
                swap(leftIdx, rightIdx, array);
                leftIdx++;
                rightIdx--;
            }
            if (array[leftIdx] <= array[pivotIdx]) leftIdx++;
            if (array[rightIdx] >= array[pivotIdx]) rightIdx--;
        }
        swap(pivotIdx, rightIdx, array);
        boolean leftSubarraySmaller = rightIdx - 1 - startIdx <
                endIdx - (rightIdx + 1);
        if (leftSubarraySmaller) {
            quickSort(array, startIdx, rightIdx -1);
            quickSort(array, rightIdx + 1, endIdx);
        } else {
            quickSort(array, rightIdx + 1, endIdx);
            quickSort(array, startIdx, rightIdx - 1);
        }
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
