package org.javainaction.heap;

import java.util.*;

/**
 * Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.
 *
 * Example 1:
 *
 * Input: [1, 3, 5, 12, 11, 12, 11], K = 2
 * Output: [12, 11]
 * Explanation: Both '11' and '12' apeared twice.
 * Example 2:
 *
 * Input: [5, 12, 11, 3, 11], K = 2
 * Output: [11, 5] or [11, 12] or [11, 3]
 * Explanation: Only '11' appeared twice, all other numbers appeared once.
 */
public class TopKFrequentNumbers {
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for (int n : nums) {
            numFrequencyMap.put(n, numFrequencyMap.getOrDefault(0, n) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> (a.getValue() - b.getValue())
        );

        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) minHeap.poll();
        }

        List<Integer> topNumbers = new ArrayList<>(k);
        while (!minHeap.isEmpty()){
            topNumbers.add(minHeap.poll().getKey());
        }

        return topNumbers;
    }

    public static void main(String[] args) {
        List<Integer> result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);

        result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    }
}
