package org.javainaction.dp.knapsack;

import java.util.stream.IntStream;

/**
 * Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements
 * in both the subsets is equal.
 *
 * Example 1: #
 *
 * Input: {1, 2, 3, 4}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
 * Example 2: #
 *
 * Input: {1, 1, 3, 4, 7}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
 * Example 3: #
 *
 * Input: {2, 3, 4, 6}
 * Output: False
 * Explanation: The given set cannot be partitioned into two subsets with equal sum.
 *
 * @see PartitionEqualSubsetSum
 */
public class PartitionSet {
    private boolean canPartition(int[] num) {
        int sum = IntStream.of(num).sum();
        //we cannot make equal partition using odd elements
        if (sum % 2 != 0) return false;

        return canPartitionRecursive(num, sum / 2, 0);
    }

    private boolean canPartitionRecursive(int[] num, int sum, int currentIdx) {
        if (sum == 0) return true;

        if (currentIdx >= num.length || num.length == 0) return false;

        if (num[currentIdx] <= sum) {
            if (canPartitionRecursive(num, sum - num[currentIdx], currentIdx + 1))
                return true;
        }

        return canPartitionRecursive(num, sum, currentIdx + 1);
    }

    public static void main(String[] args) {
        PartitionSet ps = new PartitionSet();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));
    }
}
