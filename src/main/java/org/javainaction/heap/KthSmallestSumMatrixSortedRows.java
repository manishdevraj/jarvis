package org.javainaction.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.
 *
 * You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest
 * array sum among all possible arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 5
 * Output: 7
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
 * Example 2:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 9
 * Output: 17
 * Example 3:
 *
 * Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * Output: 9
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
 * Example 4:
 *
 * Input: mat = [[1,1,10],[2,2,9]], k = 7
 * Output: 12
 *
 */
public class KthSmallestSumMatrixSortedRows {
    /**
     Calculate max priority queue of size k for the first row.

     Add the rest rows one by one to the max priority queue and make sure that max priority queue size is less than
     or equal to k.
     */
    //O(m * n * k * log(k)) time | O(k) time
    public int kthSmallest(int[][] mat, int k) {
        //we need two max heap because we need to pick single elements from each list
        PriorityQueue<Integer> previous = new PriorityQueue<>(Collections.reverseOrder());
        //add smallest element which is 0
        //this ensures we are able to add first array as previous while iterating
        previous.offer(0);

        //for every row from matrix
        for (int[] row : mat) {
            //collect sum of previous max with every element
            //store that in next max list up to k size
            PriorityQueue<Integer> next = new PriorityQueue<>(Collections.reverseOrder());
            //add each previous element with current row which would result in larger sum
            //as we are adding larger sum first we will be safely be able to remove them when window K is reached
            for (int prev : previous) {
                for (int current : row) {
                    next.offer(current + prev);
                }
            }
            //remove all extra max elements beyond k
            while (next.size() > k) next.poll();
            //next now can be use for next operations
            previous = next;
        }
        return previous.isEmpty() ? -1 : previous.peek();
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 3, 11 }, { 2, 4, 6 }};
        int result = new KthSmallestSumMatrixSortedRows().kthSmallest(matrix, 5);
        System.out.println(Arrays.deepToString(matrix));
        System.out.println("K=5 smallest number is: " + result);

        result = new KthSmallestSumMatrixSortedRows().kthSmallest(matrix, 9);
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(" K=9 smallest number is: " + result);

        matrix = new int[][]{ { 1, 1, 10 }, { 2, 2, 9 }};
        result = new KthSmallestSumMatrixSortedRows().kthSmallest(matrix, 7);
        System.out.println(Arrays.deepToString(matrix));
        System.out.println("K=7 smallest number is: " + result);
    }
}
