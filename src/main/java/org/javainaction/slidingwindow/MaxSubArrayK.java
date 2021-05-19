package org.javainaction.slidingwindow;

import java.util.*;

/**
 * Given an array of integers and a number k, where 1 <= k <= length of the array,
 * compute the maximum values of each subarray of length k.
 *
 * For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:
 *
 * 10 = max(10, 5, 2)
 * 7 = max(5, 2, 7)
 * 8 = max(2, 7, 8)
 * 8 = max(7, 8, 7)
 * Do this in O(n) time and O(k) space.
 */
public class MaxSubArrayK {

    public static void main(String[] arg) {
        int[] array = new int[] {10, 5, 2 , 7, 8, 7};
        printMaximums(array, 3);
        printMaximums(array, 2);
    }

    public static void printMaximums(int[] a, int k) {
        int n = a.length;
        Deque<int[]> deck = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (!deck.isEmpty() && a[i] >= deck.peekLast()[0]) deck.pollLast();
            deck.offer(new int[] {a[i], i});
            while (!deck.isEmpty() && deck.peekFirst()[1] <= i - k) deck.pollFirst();
            if (i >= k - 1) result.add(deck.peekFirst()[0]);
        }
        System.out.println(result);
    }

    public static int findMaxSumSubArray(int k, int[] arr) {
        if (arr == null || k > arr.length) return -1;
        int maxSum = -1, sum;
        for (int i = 0; i < arr.length - k; i++) {
            sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += arr[j];
            }
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
}
