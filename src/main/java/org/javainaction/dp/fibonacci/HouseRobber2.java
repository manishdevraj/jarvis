package org.javainaction.dp.fibonacci;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system
 * connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of
 * money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *
 * Input: nums = [0]
 * Output: 0
 */
public class HouseRobber2 {
    /**
     * Since House[1] and House[n] are adjacent, they cannot be robbed together. Therefore, the problem becomes
     * to rob either House[1]-House[n-1] or House[2]-House[n], depending on which choice offers more money.
     * Now the problem has degenerated to the House Robber, which is already been solved.
     */
    public int rob(int[] wealth) {
        if (wealth.length == 1) return wealth[0];
        return Math.max(findMaxSteal(wealth, 0, wealth.length - 1), findMaxSteal(wealth, 1, wealth.length));
    }

    //Iterative + memo (bottom-up)
    //O(n) time | O(1) space
    private int findMaxSteal(int[] wealth, int start, int end) {
        int prevMax = 0, currMax = 0;
        while (start < end) {
            int temp = currMax;
            currMax = Math.max(wealth[start] + prevMax, currMax);
            prevMax = temp;
            start++;
        }
        return currMax;
    }

    public static void main(String[] args) {
        var houseRobber = new HouseRobber2();
        int[] wealth = {2, 5, 1, 3, 6, 2, 4};
        System.out.println(houseRobber.rob(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(houseRobber.rob(wealth));
        wealth = new int[]{2, 3, 2};
        System.out.println(houseRobber.rob(wealth));
        wealth = new int[]{1, 2, 3, 1};
        System.out.println(houseRobber.rob(wealth));
    }
}
