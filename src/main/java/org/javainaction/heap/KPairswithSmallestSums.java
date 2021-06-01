package org.javainaction.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
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
 */
public class KPairswithSmallestSums {

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (k <= 0) return null;

        List<List<Integer>> result = new ArrayList<>(k);

        //Integer[] : number 1, number 2 and index of number 2 array
        PriorityQueue<Integer[]> priorityQueue =
                new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

        if (nums1 == null && nums1.length == 0 && nums2 == null && nums2.length == 0) return result;

        int l1 = nums1.length;
        int l2 = nums2.length;

        for (int i = 0; i < l1 && i < k; i++) {
            priorityQueue.offer(new Integer[]{nums1[i], nums2[0], 0});
        }

        while (k-- > 0 && !priorityQueue.isEmpty()) {
            Integer[] tuple = priorityQueue.poll();
            result.add(Arrays.asList(tuple[0], tuple[1]));

            if (tuple[2] == l2 - 1) continue;
            int num2Index = tuple[2] + 1;
            priorityQueue.offer(new Integer[]{tuple[0], nums2[num2Index], num2Index});
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array1 = new int[] { 1, 7, 11 };
        int[] array2 = new int[] { 2, 4, 6 };
        //expected [[1,2],[1,4],[1,6]]
        System.out.println("{ 1, 7, 11 } & { 2, 4, 6 } k = 3 smallest pairs: "
                + kSmallestPairs(array1, array2, 3));
    }
}
