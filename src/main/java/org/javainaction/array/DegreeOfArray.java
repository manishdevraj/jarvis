package org.javainaction.array;

import java.util.*;

/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency
 * of any one of its elements.
 *
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3,1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 *
 * Input: nums = [1,2,2,3,1,4,2]
 * Output: 6
 * Explanation:
 * The degree is 3 because the element 2 is repeated 3 times.
 * So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 *
 *
 * Constraints:
 *
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 */
public class DegreeOfArray {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
            //put left most window
            if (!leftMap.containsKey(val)) leftMap.put(val, i);
            //put at right at anytime this could be right window index
            //we put in right always even when it was at left because we could be having max degree of 1 so we need
            //left and right pointing to same index.
            rightMap.put(val, i);
        }

        if (freqMap.isEmpty()) return -1;

        int degree = Collections.max(freqMap.values());
        int minLength = nums.length;
        for (Map.Entry<Integer, Integer> frequencyEntry : freqMap.entrySet()) {
            //if we have element whose degree matches to max degree
            if (frequencyEntry.getValue() == degree) {
                minLength = Math.min(minLength,
                        rightMap.getOrDefault(frequencyEntry.getKey(), 0)
                                - leftMap.getOrDefault(frequencyEntry.getKey(), 0) + 1);
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        var obj = new DegreeOfArray();
        int[] input = new int[]{1,2,2,3,1};
        System.out.println(Arrays.toString(input) + " smallest length of the degree "  + obj.findShortestSubArray(input));
        input = new int[]{1, 2, 2, 3, 1, 4, 2};
        System.out.println(Arrays.toString(input) + " smallest length of the degree "  + obj.findShortestSubArray(input));
        input = new int[]{};
        System.out.println(Arrays.toString(input) + " smallest length of the degree "  + obj.findShortestSubArray(input));
    }
}
