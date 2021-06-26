package org.javainaction.sort;

/**
 * Implement quick sort
 *
 * Idea is to start with some kind of pivot : which can be random or start at 0
 * Algorithm :
 * pivot
 * left = start + 1
 * right = end - 1
 *
 * if both left is smaller than pivot and right is greater than pivot then swap both left and right moving them inwards
 * if left is smaller than or equal to pivot move left pointer
 * if right is greater than or equal to pivot move right pointer
 * At the end of first iteration swap pivot and right
 *
 * Now pivot is at correct position:
 * To improve efficiency which sub array from pivot is smaller and start with it recursively
 *
 *
 */
public class QuickSort {

    //Best O(nlog(n)) time | O(nlog(n)) space
    //Avg O(nlog(n)) time | O(nlog(n)) space
    //Worse O(n^2) time | O(nlog(n)) space
    public static int[] quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) return;

        int pivot = start;
        int left = start + 1;
        int right = end;

        while (left <= right) {
            if (array[left] > array[pivot] && array[right] < array[pivot]){
                swap(left, right, array);
                left++;
                right--;
            }
            if (array[left] <= array[pivot]) left++;
            if (array[right] >= array[pivot]) right--;
        }
        swap(pivot, right, array);

        boolean leftSubarraySmaller = right - 1 - start <
                end - (right + 1);
        //To improve efficiency which sub array from pivot is smaller and start with it recursively
        if (leftSubarraySmaller) {
            quickSort(array, start, right -1);
            quickSort(array, right + 1, end);
        } else {
            quickSort(array, right + 1, end);
            quickSort(array, start, right - 1);
        }
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] arg) {
        int[] expected = {2, 3, 5, 5, 6, 8, 9};
        int[] input = {8, 5, 2, 9, 5, 6, 3};
        System.out.println(compare(QuickSort.quickSort(input), expected));
    }

    public static boolean compare(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
