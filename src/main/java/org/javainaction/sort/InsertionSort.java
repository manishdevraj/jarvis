package org.javainaction.sort;

public class InsertionSort {
    //Best: O(n) time | O(1) space
    //Average: O(n^2) time | O(1) space;
    //Worse: O(n^2) time | O(1) space;
    public static int[] insertionSort(int[] array) {
        if (array.length == 0) return new int[] {};

        for(int i = 1; i < array.length; i++) {
            int j = i;
            while(j > 0 && array[j] < array[j - 1]) {
                swap(j, j - 1, array);
                j--;
            }
        }
        return array;
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /** Inversion
     *We can determine how "out of order" an array A is by counting the number of inversions it has. T
     * wo elements A[i] and A[j] form an inversion if A[i] > A[j] but i < j. That is, a smaller element
     * appears after a larger element.
     *
     * Given an array, count the number of inversions it has. Do this faster than O(N^2) time.
     *
     * You may assume each element in the array is distinct.
     *
     * For example, a sorted list has zero inversions. The array [2, 4, 1, 3, 5] has three inversions:
     * (2, 1), (4, 1), and (4, 3). The array [5, 4, 3, 2, 1] has ten inversions: every distinct pair forms an inversion.
     *
     *
     * for (int i = 1; i < n; i++) {
     *     int temp = A[i];
     *     int j;
     *     for (j = i - 1; j >= 0 && temp < A[j]; j--) {
     *         A[j + 1] = A[j];
     *         k++;  // Number of inversions
     *     }
     *     A[j + 1] = temp;
     * }
     */
}
