package org.javainaction.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given two sorted arrays in descending order, find ‘K’ pairs with the largest sum where each pair consists of numbers
 * from both the arrays.
 *
 * Example 1:
 *
 * Input: L1=[9, 8, 2], L2=[6, 3, 1], K=3
 * Output: [9, 3], [9, 6], [8, 6]
 * Explanation: These 3 pairs have the largest sum. No other pair has a sum larger than any of these.
 * Example 2:
 *
 * Input: L1=[5, 2, 1], L2=[2, -1], K=3
 * Output: [5, 2], [5, -1], [2, 2]
 * @see KPairswithSmallestSums
 */
public class LargestPairs {
    public static List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((p1, p2) -> (p1[0] + p1[1]) - (p2[0] + p2[1]));
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                if (minHeap.size() < k) {
                    minHeap.add(new int[] { nums1[i], nums2[j] });
                } else {
                    // if the sum of the two numbers from the two arrays is smaller than the smallest (top) element of
                    // the heap, we can 'break' here. Since the arrays are sorted in the descending order, we'll not be
                    // able to find a pair with a higher sum moving forward.
                    if (nums1[i] + nums2[j] < minHeap.peek()[0] + minHeap.peek()[1]) {
                        break;
                    } else { // else: we have a pair with a larger sum, remove top and insert this pair in the heap
                        minHeap.poll();
                        minHeap.add(new int[] { nums1[i], nums2[j] });
                    }
                }
            }
        }
        return new ArrayList<>(minHeap);
    }

    public static List<List<Integer>> kLargestPairs(int[] nums1, int[] nums2, int k) {
        if (k <= 0) return null;

        List<List<Integer>> result = new ArrayList<>(k);

        //Integer[] : number 1, number 2 and index of number 2 array
        PriorityQueue<Integer[]> priorityQueue =
                new PriorityQueue<>((a, b) -> (b[0] + b[1]) - (a[0] + a[1]));

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return result;

        int len1 = nums1.length;
        int len2 = nums2.length;

        //pair all elements from array 1 up to k with first element from array 2
        for (int i = 0; i < len1 && i < k; i++) {
            priorityQueue.offer(new Integer[]{nums1[i] , nums2[0], 0});
        }

        //if we found any pair and we have to still find k more pairs then continue
        while (k-- > 0 && !priorityQueue.isEmpty()) {
            Integer[] tuple = priorityQueue.poll();
            result.add(Arrays.asList(tuple[0], tuple[1]));

            if (tuple[2] == len2 - 1) continue;

            int nextIndex = tuple[2] + 1;
            priorityQueue.offer(new Integer[]{tuple[0], nums2[nextIndex], nextIndex});
        }

        return result;
    }

    public static void main(String[] args) {
        int[] l1 = new int[] { 9, 8, 2 };
        int[] l2 = new int[] { 6, 3, 1 };
        System.out.print("{ 9, 8, 2 } and { 6, 3, 1 } pairs with largest sum are: ");
        findKLargestPairs(l1, l2, 3).forEach(a -> System.out.print(Arrays.toString(a)));

        System.out.println();
        System.out.println("{ 9, 8, 2 } and { 6, 3, 1 } pairs with largest sum are: "
                + kLargestPairs(l1, l2, 3));
    }
}
