package org.javainaction.slidingwindow;

import java.util.Arrays;

/**
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 *
 * Return 0 if there is no such subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * Example 2:
 *
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * Example 3:
 *
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 * Example 4:
 *
 * Input: nums = [1,1,0,0,1,1,1,0,1]
 * Output: 4
 * Example 5:
 *
 * Input: nums = [0,0,0]
 * Output: 0
 *
 * @see ReplacingOnes
 * @see MaxConsecutiveOnes3
 */
public class LongestSubarrayof1WithOneDel {
    public static int longestSubarray(int[] arr) {
        int i = 0, j = 0, k = 1;
        //sliding window technique
        for(; i < arr.length && j < arr.length; ++j) {
            //we keep on iterating j, we see every iteration i and j is added by 1
            //which makes the distance between j and i same.
            //The distance between j and i would change again if there is a longer subarray exist.

            //if we find 0's then we found our replacement
            if (arr[j] == 0) k--;
            //if we find we ran out of replacement and we got new possible 0's replacement
            //shrink our left pointer i and undo replacement
            if (k < 0 && arr[i++] == 0) k++;
        }

        //as we replace 0' once we need to exclude that replacement from original length of 1's
        return j - i - 1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}) + " : "
                + longestSubarray(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}));
        System.out.println(Arrays.toString(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}) + " : "
                + longestSubarray(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}));
        System.out.println(Arrays.toString(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}) + " : "
                + longestSubarray(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}));
        System.out.println(Arrays.toString(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}) + " : "
                + longestSubarray(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}));
    }


}
