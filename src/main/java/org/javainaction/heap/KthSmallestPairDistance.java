package org.javainaction.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The distance of a pair of integers a and b is defined as the absolute difference between a and b.
 *
 * Given an integer array nums and an integer k, return the kth smallest distance among all
 * the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
 *
 * Example 1:
 *
 * Input: nums = [1,3,1], k = 1
 * Output: 0
 * Explanation: Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Example 2:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1,6,1], k = 3
 * Output: 5
 * @see KthSmallestInMSortedArrays in this case we created element with array and element index
 * @see KthSmallestInSortedMatrix in this case we create element with row and col value
 * Here we create Tuple (i, j) such that i < j < arr.length
 * @see KPairswithSmallestSums Where we take sum between two array elements vs single array elements
 */
public class KthSmallestPairDistance {
    /**
     * This approach uses heap where we keep each i and i + 1 indices such that they are lowest possible abs difference
     * For a fairly large array with 6 figures k value this will take time
     *
     */
    public static int smallestDistancePairHeap(int[] nums, int k) {
        Arrays.sort(nums);

        PriorityQueue<Tuple> maxHeap =
                new PriorityQueue<>(nums.length, Comparator.comparingInt(tuple -> nums[tuple.j] - nums[tuple.i]));

        //store a all initial combinations of (i, j) such that i < j < arr.length
        for (int i = 1; i <= nums.length - 1; i++) {
            maxHeap.offer(new Tuple(i - 1, i));
        }

        Tuple result = null;
        for (; k > 0; --k) {
            Tuple current = maxHeap.poll();
            result = current;

            if (current.j  == nums.length - 1) continue;

            maxHeap.offer(new Tuple(current.i, current.j + 1));

        }

        if (result != null) return nums[result.j] - nums[result.i];

        return -1;
    }

    static class Tuple {
        int i;
        int j;
        Tuple(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    /**
     * Binary search using sliding window approach
     * We will use a sliding window approach to count the number of pairs with distance <= guess.
     *
     * For every possible right, we maintain the loop invariant: left is the smallest value such that
     * nums[right] - nums[left] <= guess. Then, the number of pairs with right as it's right-most endpoint
     * is right - left, and we add all of these up.
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0, left = 0;
            for (int right = 0; right < nums.length; ++right) {
                while (nums[right] - nums[left] > mi) left++;
                count += right - left;
            }
            //count = number of pairs with distance <= mi
            if (count >= k) hi = mi;
            else lo = mi + 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        System.out.println("{1, 3, 1 } k smallest pair k = 1 " + smallestDistancePair(new int[]{1, 3, 1}, 1));
        System.out.println("{9,10,7,10,6,1,5,4,9,8} k smallest pair k = 18 "
                + smallestDistancePair(new int[]{9,10,7,10,6,1,5,4,9,8}, 18));

        System.out.println("{1, 3, 1 } k smallest pair k = 1 " + smallestDistancePairHeap(new int[]{1, 3, 1}, 1));
        System.out.println("{9,10,7,10,6,1,5,4,9,8} k smallest pair k = 18 "
                + smallestDistancePairHeap(new int[]{9,10,7,10,6,1,5,4,9,8}, 18));
    }
}
