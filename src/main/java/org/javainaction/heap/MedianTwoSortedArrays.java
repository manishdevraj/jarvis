package org.javainaction.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * @see org.javainaction.binarysearch.MedianTwoSortedArrays
 */
public class MedianTwoSortedArrays {
    private final PriorityQueue<Double> low = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Double> high = new PriorityQueue<>();
    private double median = 0.0;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        clear();
        for (int i = 0, j = 0; i < nums1.length || j < nums2.length;) {
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    offer(nums1[i++]);
                } else {
                    offer(nums2[j++]);
                }
            } else {
                if (i < nums1.length) offer(nums1[i++]);
                if (j < nums2.length) offer(nums2[j++]);
            }
        }
        return median;
    }

    private void rebalance() {
        if (low.size() > high.size() + 1) high.offer(low.poll());
        else if (high.size() > low.size()) low.offer(high.poll());
    }

    private void offer(int num) {
        if (low.isEmpty() || num <= low.peek()) low.offer((double) num);
        else high.offer((double) num);
        rebalance();
        updateMedian();
    }

    private void updateMedian() {
        if (low.size() == high.size()) median = (low.peek() + high.peek()) / 2;
        else if(low.size() > high.size()) median = low.peek();
        else median = high.peek();
    }

    private void clear(){
        median = 0.0;
        low.clear();
        high.clear();
    }

    public static void main(String[] args) {
        MedianTwoSortedArrays heap = new MedianTwoSortedArrays();
        System.out.println("[1, 3] and [2] median is "
                + heap.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));

        System.out.println("[1, 2] and [3, 4] median is "
                + heap.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

}
