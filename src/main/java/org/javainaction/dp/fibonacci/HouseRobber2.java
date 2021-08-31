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
 * @see org.javainaction.dp.HouseRobber3
 * @see HouseThief
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
        //as we just need to exclude adjacent house
        //two pointers prev steal and current steal allows us to track which we need to pick
        //[2, 5, 1, 3, 6, 2, 4] with previous and current steal both pointing at 0
        //[n - 2] = previous steal, [n - 1] = last steal, [n] is current steal
        //i=0 lastMaxSteal = 2 as there was nothing stole previously and previous max is still 0
        //i=1 lastMaxSteal = 5, we either keep previous steal + curr wealth or pick last steal
        // [n - 2] steal + [n] steal or [n - 1] steal
        //i=2 current steal = 5
        //i=3 we can steal 5 + 3 = 8
        //i=4 we can steal previous and current 5 + 6 = 11
        //i=5 current steal = 11
        //i=6 current steal = 11 + 4 = 15
        int prevMaxSteal = 0, lastMaxSteal = 0;
        while (start < end) {
            int temp = lastMaxSteal;
            lastMaxSteal = Math.max(wealth[start] + prevMaxSteal, lastMaxSteal);
            prevMaxSteal = temp;
            start++;
        }
        return lastMaxSteal;
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
