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
 * @see SlidingWindowMaximum
 */
public class MaxSubArrayK {

    public static void printMaximums(int[] a, int k) {
        int n = a.length;
        Deque<int[]> deck = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //if value we are adding is bigger than any elements in queue, remove all of them so we do not keep
            //track of them anymore
            while (!deck.isEmpty() && a[i] >= deck.peekLast()[0]) deck.pollLast();
            //add value with its index
            deck.offer(new int[] {a[i], i});
            //if index of first val in queue is beyond the range of K pop that out
            //removing last max elements that is going out of range
            while (!deck.isEmpty() && deck.peekFirst()[1] <= i - k) deck.pollFirst();
            //when we have hit the k window rage, get the first of the maximum values
            if (i >= k - 1) result.add(Objects.requireNonNull(deck.peekFirst())[0]);
        }
        System.out.println(result);
    }

    public static void main(String[] arg) {
        int[] array = new int[] {10, 5, 2 , 7, 8, 7};
        printMaximums(array, 3);
        printMaximums(array, 2);
    }
}
