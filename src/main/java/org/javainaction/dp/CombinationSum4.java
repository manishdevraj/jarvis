package org.javainaction.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * Example 2:
 *
 * Input: nums = [9], target = 3
 * Output: 0
 */
public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        return combinationsRecursive(new HashMap<>(), nums, target);
    }

    private int combinationsRecursive(Map<Integer, Integer> memo, int[] nums, int target) {
        if (target < 0) return 0;
        if (target == 0) return 1;
        if (memo.containsKey(target)) return memo.get(target);
        int result = 0;
        for (int num : nums) {
            int ways = combinationsRecursive(memo, nums, target - num);
            memo.put(target - num, ways);
            result += ways;
        }
        return result;
    }

    public static void main(String[] args) {
        var obj = new CombinationSum4();
        System.out.println("{1, 2, 3} for target = 4 : " + obj.combinationSum4(new int[]{1, 2, 3}, 4));
        System.out.println("{9} for target = 3 : " + obj.combinationSum4(new int[]{9}, 3));
    }
}
