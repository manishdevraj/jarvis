package org.javainaction.heap;

import java.util.Collections;
import java.util.PriorityQueue;

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
 */
public class SlidingWindowMedian {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (minHeap.isEmpty() || minHeap.peek() >= val) {
                minHeap.offer(val);
            } else maxHeap.offer(val);

            rebalanceHeaps();

            if (i - k + 1 >= 0) {
                result[i - k + 1] = getMedian();

                // remove the the element going out of the sliding window
                int elementToBeRemoved = nums[i - k + 1];
                if (elementToBeRemoved <= minHeap.peek()) {
                    minHeap.remove(elementToBeRemoved);
                } else {
                    maxHeap.remove(elementToBeRemoved);
                }
                rebalanceHeaps();
            }
        }
        return result;
    }

    public double getMedian(){
        if (minHeap.size() == maxHeap.size()) {
            return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }

    public void rebalanceHeaps() {
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        double[] result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 2);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
        System.out.println();

        slidingWindowMedian = new SlidingWindowMedian();
        result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 3);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
    }
}
