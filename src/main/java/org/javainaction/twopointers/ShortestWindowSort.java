package org.javainaction.twopointers;

/**
 * Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
 *
 * Example 1:
 *
 * Input: [1, 2, 5, 3, 7, 10, 9, 12]
 * Output: 5
 * Explanation: We need to sort only the subarray [5, 3, 7, 10, 9] to make the whole array sorted
 * Example 2:
 *
 * Input: [1, 3, 2, 0, -1, 7, 10]
 * Output: 5
 * Explanation: We need to sort only the subarray [1, 3, 2, 0, -1] to make the whole array sorted
 * Example 3:
 *
 * Input: [1, 2, 3]
 * Output: 0
 * Explanation: The array is already sorted
 * Example 4:
 *
 * Input: [3, 2, 1]
 * Output: 3
 * Explanation: The whole array needs to be sorted.
 * @see SubArraySort
 */
public class ShortestWindowSort {

    public static int sort(int[] arr) {
        int minOutOfOrder = Integer.MAX_VALUE;
        int maxOutOfOrder = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (isOutOfOrder(i, num, arr)) {
                minOutOfOrder = Math.min(minOutOfOrder, arr[i]);
                maxOutOfOrder = Math.max(maxOutOfOrder, arr[i]);
            }
        }

        if (minOutOfOrder == Integer.MAX_VALUE) return 0;

        int leftUnsortedIdx = 0;

        while (arr[leftUnsortedIdx] < minOutOfOrder) {
            leftUnsortedIdx++;
        }

        int rightUnsortedIdx = arr.length - 1;
        while (arr[rightUnsortedIdx] > maxOutOfOrder) {
            rightUnsortedIdx--;
        }

        return rightUnsortedIdx - leftUnsortedIdx + 1;
    }

    public static boolean isOutOfOrder(int i, int num, int[] array) {
        if (i == 0) return array[i + 1] < num;

        if (i == array.length - 1) return num < array[i - 1];

        return array[i + 1] < num || num < array[i - 1];
    }

    public static void main(String[] args) {
        System.out.println(sort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(sort(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(sort(new int[] { 1, 2, 3 }));
        System.out.println(sort(new int[] { 3, 2, 1 }));
    }
}
