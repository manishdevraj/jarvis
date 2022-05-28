package org.javainaction.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal
 * is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type
 * of fruit.
 *
 * You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each
 * tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
 *
 * Write a function to return the maximum number of fruits in both the baskets.
 *
 * Example 1:
 *
 * Input: Fruit=['A', 'B', 'C', 'A', 'C']
 * Output: 3
 * Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']
 *
 * Problem 1: Longest Substring with at most 2 distinct characters
 *
 * Given a string, find the length of the longest substring in it with at most two distinct characters.
 *
 * Solution: This problem is exactly similar to our parent problem.
 * @see LongestSubstringKDistinct
 */
public class MaxFruitCountOf2Types {
    public static int findLength(char[] arr) {
        int baskets = 2;
        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> fruitFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            fruitFrequencyMap.put(arr[windowEnd], fruitFrequencyMap.getOrDefault(arr[windowEnd], 0) + 1);
            // shrink the sliding window, until we are left with '2' fruits in the frequency map
            while (fruitFrequencyMap.size() > baskets) {
                fruitFrequencyMap.put(arr[windowStart], fruitFrequencyMap.get(arr[windowStart]) - 1);
                if (fruitFrequencyMap.get(arr[windowStart]) == 0) {
                    fruitFrequencyMap.remove(arr[windowStart]);
                }
                windowStart++; // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'A', 'B', 'C' }));
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { '1', '2', '3', '2', '2'}));

    }
}
