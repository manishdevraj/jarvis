package org.javainaction.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 * @see LargestPairs
 */
public class KPairswithSmallestSums {

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (k <= 0) return null;

        List<List<Integer>> result = new ArrayList<>(k);

        //Integer[] : number 1, number 2 and index of number 2 array
        PriorityQueue<Integer[]> priorityQueue =
                new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

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

    public static List<List<Integer>> findKSmallestPairs(int[] nums1, int[] nums2, int k) {
        var maxHeap = new PriorityQueue<Integer[]>((p1, p2) -> (p2[0] + p2[1]) - (p1[0] + p1[1]));
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                if (maxHeap.size() < k) {
                    maxHeap.add(new Integer[]{nums1[i], nums2[j]});
                } else {
                    // if the sum of the two numbers from the two arrays is greater than the largest (top) element of
                    // the heap, we can 'break' here. Since the arrays are sorted in the descending order, we'll not be
                    // able to find a pair with a higher sum moving forward.
                    if (nums1[i] + nums2[j] > maxHeap.peek()[0] + maxHeap.peek()[1]) {
                        break;
                    } else { // else: we have a pair with a larger sum, remove top and insert this pair in the heap
                        maxHeap.poll();
                        maxHeap.add(new Integer[]{nums1[i], nums2[j]});
                    }
                }
            }
        }
        return maxHeap
                .stream()
                .map(pair -> new ArrayList<>(Arrays.asList(pair)))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] array1 = new int[] { 1, 7, 11 };
        int[] array2 = new int[] { 2, 4, 6 };
        //expected [[1,2],[1,4],[1,6]]
        System.out.println("{ 1, 7, 11 } & { 2, 4, 6 } k = 3 smallest pairs: "
                + kSmallestPairs(array1, array2, 3));

        System.out.println("{ 1, 7, 11 } & { 2, 4, 6 } k = 3 smallest pairs: "
                + findKSmallestPairs(array1, array2, 3));
    }
}
