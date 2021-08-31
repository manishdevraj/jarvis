package org.javainaction.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
 *
 * Note: For a detailed discussion about different approaches to solve this problem, take a look at Kth Smallest Number.
 *
 * Example 1:
 *
 * Input: [3, 1, 5, 12, 2, 11], K = 3
 * Output: [5, 12, 11]
 * Example 2:
 *
 * Input: [5, 12, 11, -1, 12], K = 3
 * Output: [12, 11, 12]
 * @see KthSmallestNumber
 * @see KthLargestNumberInStream
 */
public class KLargestNumbers {
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < nums.length && !minHeap.isEmpty(); i++) {
            if (nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return new ArrayList<>(minHeap);
    }

    public static List<Integer> findKLargestNumbersV2(int[] nums, int k) {
        var minHeap  = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k && !minHeap.isEmpty()) minHeap.poll();
        }
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        var input = new int[] { 3, 1, 5, 12, 2, 11 };
        System.out.println(Arrays.toString(input) + " the top K = 3 numbers: " + findKLargestNumbers(input, 3));
        System.out.println(Arrays.toString(input) + " the top K = 3 numbers: " + findKLargestNumbersV2(input, 3));

        input = new int[] { 5, 12, 11, -1, 12 };
        System.out.println(Arrays.toString(input) + " the top K = 3 numbers: " + findKLargestNumbers(input, 3));
        System.out.println(Arrays.toString(input) + " the top K = 3 numbers: " + findKLargestNumbersV2(input, 3));
    }
}
