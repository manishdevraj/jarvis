package org.javainaction.dp.longestcommonsub;

/**
 * We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
 *
 * Now, we may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
 *
 * nums1[i] == nums2[j];
 * The line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting lines cannot intersect even at the endpoints: each number can only belong
 * to one connecting line.
 *
 * Return the maximum number of connecting lines we can draw in this way.
 *
 *
 * Example 1:
 *
 *
 * Input: nums1 = [1,4,2], nums2 = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from nums1[1]=4 to nums2[2]=4 will intersect the line
 * from nums1[2]=2 to nums2[1]=2.
 *
 * 1   4    2
 * |    \
 * |     \
 * 1  2   4
 *
 * Example 2:
 *
 * Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * Output: 3
 * Example 3:
 *
 * Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * Output: 2
 * @see LongestCommonSubsequence
 */
public class UncrossedLines {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int maxLength = 0;
        //the idea is to find the common subsequence that will match between two array
        //this also tells us that if we have order shuffled then we cannot find common sub sequence that is crossing
        //path making it look like cross lines across two array
        //technically we can form two lines like below both are not crossing each other, but as we can see both at any
        //given point talks about longest possible sub sequence possible at i
        //reason second line is not possible as we would have always found way to connect 4 with 4 before we start with
        //2 with 2... if we flip who is number one and number two possibly we could do second as well
        //property of LCS that when two elements are same we consider their last element's value + 1 or we take
        //values from their either sub sequence meaning skipping either 4 from one or 2 from other
        /**
         * 1   4    2
         * |    \
         * |     \
         * 1  2   4
         */

        //Or

        /**
         * 1   4   2
         * |     /
         * |    /
         * 1   2   4
          */
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        var obj = new UncrossedLines();
        System.out.println("nums1 = [1,4,2], nums2 = [1,2,4] uncrossed lines : "
                + obj.maxUncrossedLines(new int[]{1, 4, 2}, new int[]{1, 2, 4}));
        System.out.println("nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2] uncrossed lines  : "
                + obj.maxUncrossedLines(new int[]{2,5,1,2,5}, new int[]{10,5,2,1,5,2}));

    }
}
