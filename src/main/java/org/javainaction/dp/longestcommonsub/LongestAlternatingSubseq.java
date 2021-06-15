package org.javainaction.dp.longestcommonsub;

/**
 * Given a number sequence, find the length of its Longest Alternating Subsequence (LAS).
 * A subsequence is considered alternating if its elements are in alternating order.
 *
 * A three element sequence (a1, a2, a3) will be an alternating sequence if its elements hold one of the
 * following conditions:
 *
 * {a1 > a2 < a3 } or { a1 < a2 > a3}.
 * Example 1:
 *
 * Input: {1,2,3,4}
 * Output: 2
 * Explanation: There are many LAS: {1,2}, {3,4}, {1,3}, {1,4}
 * Example 2:
 *
 * Input: {3,2,1,4}
 * Output: 3
 * Explanation: The LAS are {3,2,4} and {2,1,4}.
 * Example 3:
 *
 * Input: {1,3,2,4}
 * Output: 4
 * Explanation: The LAS is {1,3,2,4}.
 */
public class LongestAlternatingSubseq {
    public int findLongestAltSubBottomUp(int[] nums) {
        if (nums.length < 2) return nums.length;
        //stores the LAS ending at 'i' such that the last two elements are in ascending order
        int[] up = new int[nums.length];
        //stores the LAS ending at 'i' such that the last two elements are in descending order
        int[] down = new int[nums.length];
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i ; j++) {
                if (nums[i] > nums[j]) {
                    // if nums[i] is BIGGER than nums[j] then we will consider the LAS ending at 'j' where the
                    // last two elements were in DESCENDING order
                    up[i] = Math.max(up[i], 1 + down[j]);
                    maxLength = Math.max(maxLength, up[i]);
                } else {
                    // if nums[i] is SMALLER than nums[j] then we will consider the LAS ending at 'j' where the
                    // last two elements were in ASCENDING order
                    down[i] = Math.max(down[i], 1 + up[j]);
                    maxLength = Math.max(maxLength, down[i]);
                }
            }
        }
        return maxLength;
    }
    //O(n2) time | O(n) space
    public int findLASLength(int[] nums) {
        if(nums.length == 0) return 0;
        //dp[i][0] = stores the LAS ending at 'i' such that the last two elements are in ascending order
        //dp[i][1] = stores the LAS ending at 'i' such that the last two elements are in descending order
        int[][] dp = new int[nums.length][2];
        int maxLength = 1;
        for(int i=0; i < nums.length; i++) {
            // every single element can be considered as LAS of length 1
            dp[i][0] = dp[i][1] = 1;
            for(int j=0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    // if nums[i] is BIGGER than nums[j] then we will consider the LAS ending at 'j' where the
                    // last two elements were in DESCENDING order
                    dp[i][0] = Math.max(dp[i][0], 1 + dp[j][1]);
                    maxLength = Math.max(maxLength, dp[i][0]);
                } else if (nums[i] != nums[j]) { // if the numbers are equal don't do anything
                    // if nums[i] is SMALLER than nums[j] then we will consider the LAS ending at 'j' where the
                    // last two elements were in ASCENDING order
                    dp[i][1] = Math.max(dp[i][1], 1 + dp[j][0]);
                    maxLength = Math.max(maxLength, dp[i][1]);
                }
            }
        }
        return maxLength;
    }

    public int findLASLengthTopdown(int[] nums) {
        // we have to start with two recursive calls, one where we will consider that the first element is
        // bigger than the second element and one where the first element is smaller than the second element
        return Math.max(findLASLengthRecursive(nums, -1, 0, true),
                findLASLengthRecursive(nums, -1, 0, false));
    }

    private int findLASLengthRecursive(int[] nums, int previousIndex, int currentIndex, boolean isAsc) {
        if(currentIndex == nums.length)
            return 0;

        int c1=0;
        // if ascending, the next element should be bigger
        if(isAsc) {
            if(previousIndex == -1 || nums[previousIndex] < nums[currentIndex])
                c1 = 1 + this.findLASLengthRecursive(nums, currentIndex, currentIndex + 1, !isAsc);
        } else { // if descending, the next element should be smaller
            if(previousIndex == -1 || nums[previousIndex] > nums[currentIndex])
                c1 = 1 + this.findLASLengthRecursive(nums, currentIndex, currentIndex + 1, !isAsc);
        }
        // skip the current element
        int c2 = this.findLASLengthRecursive(nums, previousIndex, currentIndex + 1, isAsc);
        return Math.max(c1,c2);
    }

    public static void main(String[] args) {
        LongestAlternatingSubseq las = new LongestAlternatingSubseq();
        int[] nums = {1,2,3,4};
        System.out.println(las.findLongestAltSubBottomUp(nums));
        System.out.println(las.findLASLength(nums));
        System.out.println(las.findLASLengthTopdown(nums));
        nums = new int[]{3,2,1,4};
        System.out.println(las.findLongestAltSubBottomUp(nums));
        System.out.println(las.findLASLength(nums));
        System.out.println(las.findLASLengthTopdown(nums));
        nums = new int[]{1,3,2,4};
        System.out.println(las.findLongestAltSubBottomUp(nums));
        System.out.println(las.findLASLength(nums));
        System.out.println(las.findLASLengthTopdown(nums));
    }
}
