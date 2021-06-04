package org.javainaction.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Supplier;

/**
 * Given an array of numbers and a number ‘k’, find the median of all the ‘k’ sized sub-arrays (or windows) of the array.
 *
 * Example 1:
 *
 * Input: nums=[1, 2, -1, 3, 5], k = 2
 * Output: [1.5, 0.5, 1.0, 4.0]
 * Explanation: Lets consider all windows of size ‘2’:
 *
 * [1, 2, -1, 3, 5] -> median is 1.5
 * [1, 2, -1, 3, 5] -> median is 0.5
 * [1, 2, -1, 3, 5] -> median is 1.0
 * [1, 2, -1, 3, 5] -> median is 4.0
 * Example 2:
 *
 * Input: nums=[1, 2, -1, 3, 5], k = 3
 * Output: [1.0, 2.0, 3.0]
 * Explanation: Lets consider all windows of size ‘3’:
 *
 * [1, 2, -1, 3, 5] -> median is 1.0
 * [1, 2, -1, 3, 5] -> median is 2.0
 * [1, 2, -1, 3, 5] -> median is 3.0
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 * Example 2:
 *
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 */
public class SlidingWindowMedian {


    public static double[] findSlidingWindowMedian(int[] nums, int k) {
        PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> greater = new PriorityQueue<>();

        Runnable balance = () -> {
            if (lower.size() > greater.size() + 1) {
                greater.offer(lower.poll());
            } else if (greater.size() > lower.size()) {
                lower.offer(greater.poll());
            }
        };

        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (lower.isEmpty() || val <= lower.peek()) {
                lower.offer(val);
            } else {
                greater.offer(val);
            }

            balance.run();

            if (i - k + 1 >= 0) {
                result[i - k + 1] = getMedian(lower, greater);

                // remove the the element going out of the sliding window
                int elementToBeRemoved = nums[i - k + 1];
                if (elementToBeRemoved <= lower.peek()) {
                    lower.remove(elementToBeRemoved);
                } else {
                    greater.remove(elementToBeRemoved);
                }
                balance.run();
            }
        }
        return result;
    }

    public static double getMedian(PriorityQueue<Integer> lower, PriorityQueue<Integer> greater) {
        return lower.size() == greater.size()
                ? ((double) lower.peek() + (double) greater.peek()) / 2
                : greater.size() > lower.size()
                ? (double) greater.peek() : (double) lower.peek();
    }

    public static void main(String[] args) {
        System.out.println("{ 1, 2, -1, 3, 5 } sliding window K=2 medians are: " +
                Arrays.toString(findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 2)));

        System.out.println("{ 1, 2, -1, 3, 5 } sliding window K=5 medians are: " +
                Arrays.toString(findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 5)));

        System.out.println("{ 1,3,-1,-3,5,3,6,7 } sliding window K=3 medians are: " +
                Arrays.toString(findSlidingWindowMedian(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));

        System.out.println("{1, 2, 3, 4, 2, 3, 1, 4, 2} sliding window K=3 medians are: " +
                Arrays.toString(findSlidingWindowMedian(new int[] {1, 2, 3, 4, 2, 3, 1, 4, 2 }, 3)));
        //[2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
    }
}
