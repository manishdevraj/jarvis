package org.javainaction.dp.longestcommonsub;

/**
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 *
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 *
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
 * Example 2:
 *
 * Input: nums = [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length.
 * One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
 * Example 3:
 *
 * Input: nums = [1,2,3,4,5,6,7,8,9]
 * Output: 2
 *
 * @see LongestAlternatingSubseq
 */
public class WiggleSubsequence {
    //O(n^2) time | O(n) space
    public int wiggleMaxLength(int[] nums) {
        int index = 1;
        //remove any duplicate from wiggle sequence
        while (index < nums.length && nums[index - 1] == nums[index]) index++;
        //if we have all duplicates
        if (index == nums.length) return 1;

        //stores the LAS ending at 'i' such that the last two elements are in ascending order
        int[] up = new int[nums.length];
        //stores the LAS ending at 'i' such that the last two elements are in descending order
        int[] down = new int[nums.length];
        /**
         *
         *   Here as we can see that from UP sequence the length is 5
         *   [1 -> 7 is a up sequence so starts with 1 and then we have wiggle up to 5 elements]
         *   UP     0  1  1  3  3  5
         *           /  \  /  \  /
         *   DOWN   0  0  2  2  4  4
         *
         *   Here as we can see that from DOWN sequence the length is 4
         *   [1 -> 7 is not a down sequence so starts with 0 and then we have wiggle up to 4 elements]
         *   UP     0  1  1  3  3  5
         *            \  /  \  /  \
         *   DOWN   0  0  2  2  4  4
         *
         */
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i ; j++) {
                if (nums[i] > nums[j]) {
                    // if nums[i] is BIGGER than nums[j] then we will consider the LAS ending at 'j' where the
                    // last two elements were in DESCENDING order
                    up[i] = Math.max(up[i], 1 + down[j]);
                } else if (nums[i] < nums[j]) {
                    // if nums[i] is SMALLER than nums[j] then we will consider the LAS ending at 'j' where the
                    // last two elements were in ASCENDING order
                    down[i] = Math.max(down[i], 1 + up[j]);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    public static void main(String[] args) {
        var obj = new WiggleSubsequence();
        int[] nums = {1,7,4,9,2,5};
        System.out.println(obj.wiggleMaxLength(nums));
        nums = new int[]{0, 0}; //expected 1
        System.out.println(obj.wiggleMaxLength(nums));
        nums = new int[]{1,1,7,4,9,2,5}; //expected 6
        System.out.println(obj.wiggleMaxLength(nums));
    }
}
