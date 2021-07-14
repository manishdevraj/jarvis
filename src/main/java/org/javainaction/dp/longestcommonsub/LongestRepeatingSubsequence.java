package org.javainaction.dp.longestcommonsub;

/**
 * Given a string, find the length of the longest repeating subsequence such that the two subsequences don’t have same
 * string character at the same position, i.e., any i’th character in the two subsequences shouldn’t have the same
 * index in the original string.
 *
 * Input: str = "abc"
 * Output: 0
 * There is no repeating subsequence
 *
 * Input: str = "aab"
 * Output: 1
 * The two subssequence are 'a'(first) and 'a'(second).
 * Note that 'b' cannot be considered as part of subsequence
 * as it would be at same index in both.
 *
 * Input: str = "aabb"
 * Output: 2
 *
 * Input: str = "axxxy"
 * Output: 2
 * @see LongestCommonSubsequence
 */
public class LongestRepeatingSubsequence {
    public int findLongRepeatingTopDown(String str) {
        return findLRSLengthRecursive(str, 0, 0);
    }
    private int findLRSLengthRecursive(String str, int i, int j) {
        if(i == str.length() || j == str.length())
            return 0;

        //we found match so increment both indices
        //also make sure we do not count same index
        if(i != j && str.charAt(i) == str.charAt(j))
            return 1 + findLRSLengthRecursive(str, i + 1, j + 1);

        //or we have counts from starting from different length
        int c1 = findLRSLengthRecursive(str, i, j + 1);
        int c2 = findLRSLengthRecursive(str, i + 1, j);

        return Math.max(c1, c2);
    }

    private int findLongestRepeatingSeqLength(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        int maxLength = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                //Unlike LCS we make sure the characters that are equal are not from same inject
                if (i != j & s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                /// store the value of LRS
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestRepeatingSubsequence lrs = new LongestRepeatingSubsequence();
        System.out.println(lrs.findLongestRepeatingSeqLength("tomorrow"));
        System.out.println(lrs.findLongestRepeatingSeqLength("aabdbcec"));
        System.out.println(lrs.findLongestRepeatingSeqLength("fmff"));

        System.out.println(lrs.findLongRepeatingTopDown("tomorrow"));
        System.out.println(lrs.findLongRepeatingTopDown("aabdbcec"));
        System.out.println(lrs.findLongRepeatingTopDown("fmff"));
    }
}
