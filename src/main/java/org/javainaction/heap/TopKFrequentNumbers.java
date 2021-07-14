package org.javainaction.heap;

import java.util.*;

/**
 * Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.
 *
 * Example 1:
 *
 * Input: [1, 3, 5, 12, 11, 12, 11], K = 2
 * Output: [12, 11]
 * Explanation: Both '11' and '12' appeared twice.
 * Example 2:
 *
 * Input: [5, 12, 11, 3, 11], K = 2
 * Output: [11, 5] or [11, 12] or [11, 3]
 * Explanation: Only '11' appeared twice, all other numbers appeared once.
 */
public class TopKFrequentNumbers {
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();

        Arrays.stream(nums)
                .forEach(n -> numFrequencyMap.put(n, numFrequencyMap.getOrDefault(n, 0) + 1));

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> (a.getValue() - b.getValue()));

        //min heap because we need to sort by least frequent and we remove them when threshold beyond k
        //min heap poll will remove least significant item (less frequent in this case)
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
        System.out.println("{ 1, 3, 5, 12, 11, 12, 11 } are the K=2 frequent numbers: " +
                findTopKFrequentNumbers(new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2));

        System.out.println("{ 1, 1, 1, 2, 2, 3 } are the K=2 frequent numbers: " +
                findTopKFrequentNumbers(new int[] { 1, 1, 1, 2, 2, 3 }, 2));

        System.out.println("{ 5, 12, 11, 3, 11 } are the K=2 frequent numbers: "
                + findTopKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2));

        System.out.println("{ 3, 0, 1, 0 } are the K=1 frequent numbers: "
                + findTopKFrequentNumbers(new int[] { 3, 0, 1, 0 }, 1));
    }
}
