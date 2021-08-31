package org.javainaction.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an unsorted array of numbers, find Kth smallest number in it.
 *
 * Please note that it is the Kth smallest number in the sorted order, not the Kth distinct element.
 *
 * Note: For a detailed discussion about different approaches to solve this problem, take a look at Kth Smallest Number.
 *
 * Example 1:
 *
 * Input: [1, 5, 12, 2, 11, 5], K = 3
 * Output: 5
 * Explanation: The 3rd smallest number is '5', as the first two smaller numbers are [1, 2].
 * Example 2:
 *
 * Input: [1, 5, 12, 2, 11, 5], K = 4
 * Output: 5
 * Explanation: The 4th smallest number is '5', as the first three small numbers are [1, 2, 5].
 * Example 3:
 *
 * Input: [5, 12, 11, -1, 12], K = 3
 * Output: 11
 * Explanation: The 3rd smallest number is '11', as the first two small numbers are [5, -1].
 * @see KLargestNumbers
 */
public class KthSmallestNumber {
    public static int findKthSmallestNumber(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> n2 - n1);
        //add K elements
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }

        //try K... N - 1 elements and see if they are smaller that heap element
        //as it is max heap top will give maximum of 3 and we can safely remove that and add other
        for (int i = k; i < nums.length && !maxHeap.isEmpty(); i++) {
            if (nums[i] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(nums[i]);
            }
        }

        return maxHeap.isEmpty() ? -1 : maxHeap.peek();
    }

    public static int findKthSmallestNumberV2(int[] nums, int k) {
        var minHeap  = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k && !minHeap.isEmpty()) minHeap.poll();
        }
        return minHeap.isEmpty() ? -1 : minHeap.peek();
    }

    public static void main(String[] args) {
        int[] input = new int[] { 1, 5, 12, 2, 11, 5 };
        System.out.println(Arrays.toString(input) + " 3rd smallest number is: " + findKthSmallestNumber(input, 3));
        System.out.println(Arrays.toString(input) + " 3rd smallest number is: " + findKthSmallestNumberV2(input, 3));

        input = new int[] { 1, 5, 12, 2, 11, 5 };
        System.out.println(Arrays.toString(input) + " 4th smallest number is: " + findKthSmallestNumber(input, 4));
        System.out.println(Arrays.toString(input) + " 4th smallest number is: " + findKthSmallestNumberV2(input, 4));

        input = new int[] { 5, 12, 11, -1, 12 };
        System.out.println(Arrays.toString(input) + " 3rd smallest number is: " + findKthSmallestNumber(input, 3));
        System.out.println(Arrays.toString(input) + " 3rd smallest number is: " + findKthSmallestNumberV2(input, 3));
    }
}
