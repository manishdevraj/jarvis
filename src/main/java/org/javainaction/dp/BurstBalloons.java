package org.javainaction.dp;

/**
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an
 * array nums. You are asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
 * If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 *
 * Input: nums = [1,5]
 * Output: 10
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 */
public class BurstBalloons {
    /**
     * Be Naive First
     *
     * When I first get this problem, it is far from dynamic programming to me. I started with the most naive idea
     * the backtracking.
     *
     * We have n balloons to burst, which mean we have n steps in the game. In the i th step we have n-i balloons
     * to burst, i = 0~n-1. Therefore we are looking at an algorithm of O(n!).
     * Well, it is slow, probably works for n < 12 only.
     *
     * Of course this is not the point to implement it. We need to identify the redundant works we did in it and
     * try to optimize.
     *
     * Well, we can find that for any balloons left the maxCoins does not depends on the balloons already bursted.
     * This indicate that we can use memorization (top down) or dynamic programming (bottom up) for all the cases from
     * small numbers of balloon until n balloons. How many cases are there? For k balloons there are C(n, k) cases and
     * for each case it need to scan the k balloons to compare. The sum is quite big still.
     * It is better than O(n!) but worse than O(2^n).
     *
     * Better idea
     *
     * We then think can we apply the divide and conquer technique? After all there seems to be many self similar sub
     * problems from the previous analysis.
     *
     * Well, the nature way to divide the problem is burst one balloon and separate the balloons into 2 sub sections
     * one on the left and one one the right. However, in this problem the left and right become adjacent and have
     * effects on the maxCoins in the future.
     *
     * Then another interesting idea come up. Which is quite often seen in dp problem analysis.
     * That is reverse thinking. Like I said the coins you get for a balloon does not depend on the balloons already
     * burst. Therefore
     * instead of divide the problem by the first balloon to burst, we divide the problem by the last balloon to burst.
     *
     * Why is that? Because only the first and last balloons we are sure of their adjacent balloons before hand!
     *
     * For the first we have nums[i-1]*nums[i]*nums[i+1] for the last we have nums[-1]*nums[i]*nums[n].
     *
     * OK. Think about n balloons if i is the last one to burst, what now?
     *
     * We can see that the balloons is again separated into 2 sections. But this time since the balloon i is the
     * last balloon of all to burst, the left and right section now has well defined boundary and do not affect
     * each other! Therefore we can do either recursive method with memoization or dp.
     *
     * Final
     *
     * Here comes the final solutions. Note that we put 2 balloons with 1 as boundaries and also
     * burst all the zero balloons in the first round since they won't give any coins.
     * The algorithm runs in O(n^3) which can be easily seen from the 3 loops in dp solution.
     */

    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        //refill array only if coins of balloons is positive
        for (int x : iNums) if (x > 0) nums[n++] = x;
        //add sentinels to both end of the array to account for two edges
        nums[0] = nums[n++] = 1;

        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i)
                    dp[left][right] = Math.max(dp[left][right],
                            nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            }

        // memoize at the end
        /**
         * [
         * [0, 0, 3, 30, 159, 167],
         * [0, 0, 0, 15, 135, 159],
         * [0, 0, 0, 0, 40, 48],
         * [0, 0, 0, 0, 0, 40],
         * [0, 0, 0, 0, 0, 0],
         * [0, 0, 0, 0, 0, 0]
         * ]
         */
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        var obj = new BurstBalloons();
        System.out.println("Minimum cost to cut a stick " + obj.maxCoins(new int[]{3, 1, 5, 8}));
    }
}
