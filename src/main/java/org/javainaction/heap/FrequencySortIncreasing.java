package org.javainaction.heap;

import java.util.*;

/**
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
 *
 * Return the sorted array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,2,2,3]
 * Output: [3,1,1,2,2,2]
 * Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
 * Example 2:
 *
 * Input: nums = [2,3,1,3,2]
 * Output: [1,3,3,2,2]
 * Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
 * Example 3:
 *
 * Input: nums = [-1,1,-6,4,5,-6,1,4,1]
 * Output: [5,-1,4,4,-6,-6,1,1,1]
 */
public class FrequencySortIncreasing {
    public static int[] frequencySort(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        Arrays.stream(nums).forEach(i -> frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1));


        PriorityQueue<Map.Entry<Integer, Integer>> minHeap
                = new PriorityQueue<>(
                        (a, b) -> a.getValue().equals(b.getValue()) ?
                                b.getKey().compareTo(a.getKey()) :
                                a.getValue() - b.getValue()
        );

        //add all from frequency map
        minHeap.addAll(frequencyMap.entrySet());

        int[] result = new int[nums.length];

        for(int i = 0; i < result.length && !minHeap.isEmpty();) {
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            int e = entry.getKey();
            int count = entry.getValue();
            while (count != 0 && i < result.length) {
                result[i] = e;
                count--;
                i++;
            }
        }
        return result;
    }

    public static int[] frequencySortWithoutHeap(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        Arrays.stream(nums).forEach(i -> frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1));

        return Arrays.stream(nums).boxed()
                //if frequency of two numbers are not the same, sort by ascending frequency.
                // If frequencies are the same, sort by descending numeric value
                .sorted((a, b) -> !frequencyMap.get(a).equals(frequencyMap.get(b))
                        ? frequencyMap.get(a) - frequencyMap.get(b) :
                        b - a)
                .mapToInt(n -> n)
                .toArray();
    }

    public static void main(String[] args) {
        System.out.println("{ 1,1,2,2,2,3 } frequency sort increasing : "
                + Arrays.toString(frequencySort(new int[]{1, 1, 2, 2, 2, 3})));
        System.out.println("{ -1,1,-6,4,5,-6,1,4,1 } frequency sort increasing : "
                + Arrays.toString(frequencySort(new int[]{-1,1,-6,4,5,-6,1,4,1})));
    }
}
