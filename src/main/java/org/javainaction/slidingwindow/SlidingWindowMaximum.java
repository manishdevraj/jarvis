package org.javainaction.slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very
 * left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 * Example 4:
 *
 * Input: nums = [9,11], k = 2
 * Output: [11]
 * @see MaxSubArrayK
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] result = SlidingWindowMaximum.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println("{1,3,-1,-3,5,3,6,7} maxing within sliding window : " + Arrays.toString(result));
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //remove additional items
            while (!queue.isEmpty() && queue.peek() < i - k + 1) queue.poll();

            //remove smaller elements
            while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) queue.pollLast();
            //add index
            queue.offer(i);

            if (i - k + 1 >= 0 ) result[i - k + 1] = nums[queue.peek()];
        }
        return result;
    }
}
