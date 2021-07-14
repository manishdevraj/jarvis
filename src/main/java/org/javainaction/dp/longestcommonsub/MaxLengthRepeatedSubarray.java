package org.javainaction.dp.longestcommonsub;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * Example 2:
 *
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 * @see LongestCommonSubstring
 */
public class MaxLengthRepeatedSubarray {
    public int findLength(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 2) return 0;
        int m = nums1.length;
        int n = nums2.length;
        int[][] memo = new int[m + 1][n + 1];
        int maxLength = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(nums1[i - 1] == nums2[j - 1]) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                } else{
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
                maxLength = Math.max(memo[i][j], maxLength);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("[1,2,3,2,1], and [3,2,1,4,7] max length repeating sub array " +
                new MaxLengthRepeatedSubarray().findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
    }
}
