package org.javainaction.heap;

import java.util.PriorityQueue;

/**
 * Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of that array.
 *
 * Example 1:
 *
 * Input: [1, 3, 12, 5, 15, 11], and K1=3, K2=6
 * Output: 23
 * Explanation: The 3rd smallest number is 5 and 6th smallest number 15. The sum of numbers coming
 * between 5 and 15 is 23 (11+12).
 * Example 2:
 *
 * Input: [3, 5, 8, 7], and K1=1, K2=4
 * Output: 12
 * Explanation: The sum of the numbers between the 1st smallest number (3) and the 4th smallest
 * number (8) is 12 (5+7)
 */
public class SumOfElements {
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        int sum = 0;

        if (nums.length < k1 || nums.length < k2) return sum;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (a - b));
        for (int n : nums) {
            minHeap.add(n);
        }

        //REMOVE ALL ELEMENTS BEFORE K1 SMALLEST
        int offset = k1 + 1;
        while (!minHeap.isEmpty() && offset > 1){
            minHeap.poll();
            offset--;
        }

        offset = k2 - k1 - 1;

        //SUM ALL ELEMENTS BETWEEN K1 AND K2
        while (!minHeap.isEmpty() && offset > 0){
            sum += minHeap.poll();
            offset--;
        }

        return sum;
    }

    public static void main(String[] args) {
        int result = findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6);
        System.out.println("{ 1, 3, 12, 5, 15, 11 } Sum of all numbers between k1=3 and k2=6 smallest numbers: "
                + result);

        result = findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4);
        System.out.println(" { 3, 5, 8, 7 } Sum of all numbers between k1=1 and k2=4 smallest numbers: "
                + result);
    }
}
