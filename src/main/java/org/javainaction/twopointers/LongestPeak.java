package org.javainaction.twopointers;

/**
 * Write a function that takes an integer array and finds the longest peak in that array.
 * It needs minimum three integers to form a peak.
 * For e.g. {1, 4, 10, 2} form a peak at 10 with length of 4.
 * But all these aren't peak values {4, 0, 1}, {1, 2, 2, 0} or {1, 2, 3}
 * Return 0 if there is no peak found
 */
public class LongestPeak {

    public static void main(String[] args) {
        System.out.println(longestPeak(new int[]{1, 4, 10, 2}));
        System.out.println(longestPeak(new int[]{1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3}));
        System.out.println(longestPeak(new int[]{5, 4, 3, 2, 1, 2, 1}));
        System.out.println(longestPeak(new int[]{5, 4, 3, 2, 1, 2, 10, 12, -3, 5, 6, 7, 10}));
        System.out.println(longestPeak(new int[]{1, 2, 3, 4, 5, 6, 10, 100, 1000}));
    }

    // O(n) time | O(1) space
    private static int longestPeak(int[] array) {
        if (array == null || array.length == 0) return 0;
        int longestPeak = 0;
        int i = 1;

        while (i < array.length - 1) {
            boolean isPeak = array[i - 1] < array[i] && array[i] > array[i + 1];
            if (!isPeak) {
                i++;
                continue;
            }

            int leftIndex = i;
            while (leftIndex > 0 && array[leftIndex - 1] < array[leftIndex])
                leftIndex--;

            int rightIndex = i;
            while (rightIndex < array.length - 1 && array[rightIndex] > array[rightIndex + 1])
                rightIndex++;

            int currentLength = rightIndex - leftIndex + 1;
            longestPeak = Math.max(currentLength, longestPeak);

            //remember we already traversed until right pointer
            i = rightIndex;
        }

        return longestPeak;
    }
}
