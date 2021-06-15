package org.javainaction.dp.longestcommonsub;

import java.util.Arrays;

/**
 * Given a number sequence, find the length of its Longest Bitonic Subsequence (LBS).
 * A subsequence is considered bitonic if it is monotonically increasing and then monotonically decreasing.
 *
 * Example 1:
 *
 * Input: {4,2,3,6,10,1,12}
 * Output: 5
 * Explanation: The LBS is {2,3,6,10,1}.
 * Example 2:
 *
 * Input: {4,2,5,9,7,6,10,3,1}
 * Output: 7
 * Explanation: The LBS is {4,5,9,7,6,3,1}.
 *
 * Similar problem longest peak
 */
public class LongestBitonicSubseq {
    private int findLBSLength(int[] nums) {
        int[] longIncSubseq = new int[nums.length];
        int[] longDecSubseq = new int[nums.length];

        //every index is longest bitonic subsequence of length 1
        Arrays.fill(longIncSubseq, 1);
        Arrays.fill(longDecSubseq, 1);
        // find LDS for every index up to the beginning of the array
        //step:1 find the longest increasing subsequences till i
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    longIncSubseq[i] = Math.max(longIncSubseq[i], longIncSubseq[j] + 1);
                }
            }
        }

        // find LDS for every index up to the end of the array
        //step : 2 find longest decreasing subsequence starting from i
        for (int i = nums.length - 1; i >=0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    longDecSubseq[i] = Math.max(longDecSubseq[i], longDecSubseq[j] + 1);
                }
            }
        }

        /*step 3: now find longest bitonic subsequence
		but ensure that there something on the left and right of a particular index i
		inorder to make it a mountain  which means lis[i] > 1 and lds[i] > 1
		*/
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            maxLength = Math.max(maxLength, longIncSubseq[i] + longDecSubseq[i] - 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestBitonicSubseq lbs = new LongestBitonicSubseq();
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(lbs.findLBSLength(nums));
        nums = new int[]{4,2,5,9,7,6,10,3,1};
        System.out.println(lbs.findLBSLength(nums));
    }
}
