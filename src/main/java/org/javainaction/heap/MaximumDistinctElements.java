package org.javainaction.heap;

import java.util.*;

/**
 * Given an array of numbers and a number ‘K’, we need to remove ‘K’ numbers from the array such that we are left with
 * maximum distinct numbers.
 *
 * Example 1:
 *
 * Input: [7, 3, 5, 8, 5, 3, 3], and K=2
 * Output: 3
 * Explanation: We can remove two occurrences of 3 to be left with 3 distinct numbers [7, 3, 8], we have
 * to skip 5 because it is not distinct and occurred twice.
 * Another solution could be to remove one instance of '5' and '3' each to be left with three
 * distinct numbers [7, 5, 8], in this case, we have to skip 3 because it occurred twice.
 * Example 2:
 *
 * Input: [3, 5, 12, 11, 12], and K=3
 * Output: 2
 * Explanation: We can remove one occurrence of 12, after which all numbers will become distinct. Then
 * we can delete any two numbers which will leave us 2 distinct numbers in the result.
 * Example 3:
 *
 * Input: [1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5], and K=2
 * Output: 3
 * Explanation: We can remove one occurrence of '4' to get three distinct numbers.
 * @see MinimumDistinctElements
 */
public class MaximumDistinctElements {
    public static int findMaximumDistinctElements(int[] nums, int k) {
        int distinctElementsCount = 0;
        if (nums.length <= k)
            return distinctElementsCount;

        // find the frequency of each number
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for (int i : nums)
            numFrequencyMap.put(i, numFrequencyMap.getOrDefault(i, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (e1, e2) -> e1.getValue() - e2.getValue());

        // insert all numbers with frequency greater than '1' into the min-heap
        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            if (entry.getValue() == 1)
                distinctElementsCount++;
            else
                minHeap.add(entry);
        }

        // following a greedy approach, try removing the least frequent numbers first from the min-heap
        while (k > 0 && !minHeap.isEmpty()) {
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            // to make an element distinct, we need to remove all of its occurrences except one
            // if we remove move then K would be negative indicating we could not make number distinct in that iteration
            // - 1 below indicates apply k - 1 deletion to number in an attempt to try and make it unique

            // [7, 3, 5, 8, 5, 3, 3], and K=2
            // [5: 2] will make K = 2 - (2 - 1) = 1, so we count that as distinct because we could keep K above 0
            // [3 : 3] will make K = 1 - (3 - 1) = -1, we cannot count this distinct because
            // we cannot replace 3 freq in this attempt to
            k -= entry.getValue() - 1;
            if (k >= 0)
                distinctElementsCount++;
        }

        // if k > 0, this means we have to remove some distinct numbers
        if (k > 0) distinctElementsCount -= k;

        return distinctElementsCount;
    }

    public static void main(String[] args) {
        int result = findMaximumDistinctElements(new int[] { 7, 3, 5, 8, 5, 3, 3 }, 2);
        System.out.println("{ 7, 3, 5, 8, 5, 3, 3 } maximum distinct numbers after removing K=2 numbers: "
                + result);

        result = findMaximumDistinctElements(new int[] { 3, 5, 12, 11, 12 }, 3);
        System.out.println("{ 3, 5, 12, 11, 12 } maximum distinct numbers after removing K=3 numbers: "
                + result);

        result = findMaximumDistinctElements(new int[] { 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5 }, 2);
        System.out.println("{ 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5 } maximum distinct numbers after removing K=2 numbers: "
                + result);
    }
}
