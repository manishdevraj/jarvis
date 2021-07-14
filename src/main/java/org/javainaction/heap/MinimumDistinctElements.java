package org.javainaction.heap;

import java.util.*;

/**
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly
 * k elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 * @see MaximumDistinctElements
 */
public class MinimumDistinctElements {
    public static int findMinimumDistinctElements(int[] nums, int k) {
        // find the frequency of each number
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        Arrays.stream(nums).forEach(n -> numFrequencyMap.put(n, numFrequencyMap.getOrDefault(n, 0) + 1));

        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(numFrequencyMap::get));

        // insert all numbers with frequency sorted by their frequencies
        minHeap.addAll(numFrequencyMap.keySet());

        // following a greedy approach, try removing the least frequent numbers first from the min-heap
        while (k > 0 && !minHeap.isEmpty()) {
            //try deducting the frequency from least occurring to try and make least distinct elements
            k -= numFrequencyMap.get(minHeap.poll());
        }

        return k < 0 ? minHeap.size() + 1 : minHeap.size();
    }

    public static void main(String[] args) {
        int result = findMinimumDistinctElements(new int[] { 5, 5, 4 }, 1);
        System.out.println("{ 5, 5, 4 } Least Number of Unique Integers after K=1 Removals: "
                + result);

        result = findMinimumDistinctElements(new int[] { 4,3,1,1,3,3,2 }, 3);
        System.out.println("{4,3,1,1,3,3,2 }  Least Number of Unique Integers after K=3 Removals: "
                + result);

        result = findMinimumDistinctElements(new int[] { 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5 }, 2);
        System.out.println("{ 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5 }  Least Number of Unique Integers after K=2 Removals: "
                + result);
    }
}
