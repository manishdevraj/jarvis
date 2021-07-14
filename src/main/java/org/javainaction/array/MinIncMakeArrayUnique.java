package org.javainaction.array;

import java.util.Arrays;

/**
 * Given an array of integers nums, a move consists of choosing any nums[i], and incrementing it by 1.
 *
 * Return the least number of moves to make every value in nums unique.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: 1
 * Explanation:  After 1 move, the array could be [1, 2, 3].
 * Example 2:
 *
 * Input: nums = [3,2,1,2,1,7]
 * Output: 6
 * Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 *
 *
 * Note:
 *
 * 0 <= nums.length <= 40000
 * 0 <= nums[i] < 40000
 *
 */
public class MinIncMakeArrayUnique {
    public static int minIncrementForUnique(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Arrays.sort(nums);

        int result = 0;
        int prev = nums[0];
        int expected = 0;
        for (int i = 1; i < nums.length; i++) {
            expected = prev + 1; //we at least need a value as a previous + 1 value
            //we either need no change to current number or need to find how much is difference between expectations
            //difference is number of increment by 1 needed to match the expectations.
            result += nums[i] > expected ? 0 : expected - nums[i];
            //move our previous number to maximum of expected or current number
            // to not worry about exact uniqueness of the number
            prev = Math.max(nums[i], expected);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("{1, 2, 2} min increment needed "
                + minIncrementForUnique(new int[]{1, 2, 2 }));
        System.out.println("{3, 2, 1, 2, 1, 7} min increment needed "
                + minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7}));
        System.out.println("{1, 2, 3} min increment needed "
                + minIncrementForUnique(new int[]{1, 2, 3 }));


    }
}
