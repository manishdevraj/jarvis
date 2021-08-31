package org.javainaction.dp.knapsack;

import java.util.Arrays;

/**
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two s
 * ubsets such that the sum of elements in both subsets is equal.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * @see SubsetSum
 * @see TargetSum
 * @see MinSubsetSumDiff where we are finding close to equal partitions, here we find two equal partition
 * @see PartitionKEqualSumSubsets where we need to partition into k subsets
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
          int sum = Arrays.stream(nums).sum();
          //we cannot make equal partition using odd elements
          if (sum % 2 != 0) return false;
          //we need to find target such that target * 2 makes sum
          int target = sum / 2;
          Boolean[][] dp = new Boolean[nums.length][target + 1];
          return canPartitionRecursive(dp, nums, target, 0);
    }

    private boolean canPartitionRecursive(Boolean[][] dp, int[] nums, int target, int index) {
        if (target == 0) return true;
        if (nums.length <= index || target < 0) return false;

        if (dp[index][target] != null) return dp[index][target];
        boolean partitionOne = false;

        if (nums[index] <= target)
            partitionOne = canPartitionRecursive(dp, nums, target - nums[index], index + 1);

        boolean partitionTwo = canPartitionRecursive(dp, nums, target, index + 1);

        dp[index][target] = partitionOne || partitionTwo;
        return dp[index][target];
    }

    public static void main(String[] args) {
        var obj = new PartitionEqualSubsetSum();
        System.out.println("Can we partition subset equally " +  obj.canPartition(new int[]{1, 5, 11, 5}));
        System.out.println("Can we partition subset equally " +  obj.canPartition(new int[]{1, 2, 3, 5}));
    }
}
