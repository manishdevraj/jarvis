package org.javainaction.dp.knapsack;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty
 * subsets whose sums are all equal.
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * @see PartitionEqualSubsetSum where we find two equal partition
 * @see MinSubsetSumDiff we are finding close to equal partitions
 */
public class PartitionKEqualSumSubsets {
    /**
     * Assume sum is the sum of nums[] . The dfs process is to find a subset of nums[] which sum equals to sum/k.
     * We use an array visited[] to record which element in nums[] is used. Each time when we get a cur_sum = sum/k,
     * we will start from position 0 in nums[] to look up the elements that are not used yet and find
     * another cur_sum = sum/k.
     *
     * An corner case is when sum = 0, my method is to use cur_num to record the number of elements in the current
     * subset. Only if cur_sum = sum/k && cur_num >0, we can start another look up process.
     */
    public boolean canPartition(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        //we need K partitions so our total number array needs to be divisible by K
        if (k <= 0 || sum % k != 0) return false;

        //find target such that target * k makes a total sum
        int target = sum / k;
        boolean[] seen = new boolean[nums.length];
        return canPartitionRecursive(seen, nums, target, 0, k, 0, 0);
    }

    private boolean canPartitionRecursive(boolean[] seen, int[] nums, int target, int start,
                                          int k, int sum, int currentNum) {
        //base case
        if (k == 1) return true;

        //we could divide partitions up to K
        //reset the number and sum as we need all K partitions with equal sum
        if (currentNum > 0 && target == sum)
            return canPartitionRecursive(seen, nums, target, 0, k - 1, 0, 0);

        for (int i = start; i < nums.length; i++) {
            //let us use unique elements
            if (!seen[i]) {
                seen[i] = true;
                //try next number
                if (canPartitionRecursive(seen, nums, target, i + 1, k,sum + nums[i], currentNum++))
                    return true;
                seen[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var obj = new PartitionKEqualSumSubsets();
        System.out.println("Can we partition subset equally with K=4 "
                +  obj.canPartition(new int[]{4,3,2,3,5,2,1}, 4));
    }
}
