package org.javainaction.dp.knapsack;

import org.javainaction.dp.fibonacci.HouseThief;

import java.util.Arrays;

/**
 * Write a function that takes in array of positive integer and returns max subset sum that excludes adjacent elements
 *
 * [75, 105, 120, 75, 90, 135]
 * Output would be : 330 //75 + 120 + 135
 * Same as house thief problem
 * @see HouseThief
 */
public class MaxSubsetSumNoAdj {

    //Recursive memo (top-down)
    public static int findMaxSubsetNonAdjTopdown(int[] array) {
        var memo = new int[array.length];
        Arrays.fill(memo, -1);
        return findMaxSubsetNonAdjRecursive(memo, array, 0);
    }

    private static int findMaxSubsetNonAdjRecursive(int[] memo, int[] array, int currentIndex) {
        if( currentIndex >= array.length)
            return 0;

        if (memo[currentIndex] >= 0) return memo[currentIndex];

        // store from current slot and skip one to store next
        int storeCurrent = array[currentIndex] + findMaxSubsetNonAdjRecursive(memo, array, currentIndex + 2);
        // skip current number from the adjacent slot
        int skipCurrent = findMaxSubsetNonAdjRecursive(memo, array, currentIndex + 1);

        memo[currentIndex] = Math.max(storeCurrent, skipCurrent);
        return memo[currentIndex];
    }

    //Iterative + memo (bottom-up)
    //O(n) time | O(n) space
    public static int maxSubsetSumNonAdjIterativeBU(int[] array) {
        if (array.length == 0) return 0;
        int[] memo = new int[array.length + 1];
        memo[0] = 0;
        memo[1] = array[0];
        for (int i = 1; i < array.length; i++) {
            memo[i + 1] = Math.max(array[i] + memo[i - 1], memo[i]);
        }
        return memo[array.length];
    }

    // Iterative + 2 variables (bottom-up)
    //O(n) time | O(1) space
    public static int maxSubsetSumNoAdjacent(int[] array) {
        if(array.length == 0) return 0;
        else if(array.length == 1) return array[0];

        int start = 0;
        int end = array.length - 1;
        int previous = 0;
        int lastMax = 0;

        while (start <= end) {
            int hold = lastMax;
            lastMax = Math.max(array[start] + previous, lastMax);
            previous = hold;
            start++;
        }
        return lastMax;
    }

    public static void main(String[] args) {
        System.out.println(findMaxSubsetNonAdjTopdown(new int[] {75, 105, 120, 75, 90, 135}));
        System.out.println(findMaxSubsetNonAdjTopdown(new int[]{2, 5, 1, 3, 6, 2, 4}));
        System.out.println(findMaxSubsetNonAdjTopdown(new int[] {2, 10, 14, 8, 1}));

        System.out.println(maxSubsetSumNoAdjacent(new int[] {75, 105, 120, 75, 90, 135}));
        System.out.println(maxSubsetSumNoAdjacent(new int[]{2, 5, 1, 3, 6, 2, 4}));
        System.out.println(maxSubsetSumNoAdjacent(new int[] {2, 10, 14, 8, 1}));

        System.out.println(maxSubsetSumNonAdjIterativeBU(new int[] {75, 105, 120, 75, 90, 135}));
        System.out.println(maxSubsetSumNonAdjIterativeBU(new int[]{2, 5, 1, 3, 6, 2, 4}));
        System.out.println(maxSubsetSumNonAdjIterativeBU(new int[] {2, 10, 14, 8, 1}));


    }
}
