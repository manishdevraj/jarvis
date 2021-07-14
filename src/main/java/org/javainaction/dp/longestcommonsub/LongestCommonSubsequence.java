package org.javainaction.dp.longestcommonsub;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * @see DeleteOpTwoStrings
 * @see MinDeleteInsert
 * @see org.javainaction.string.LongestComSubsequence
 */
public class LongestCommonSubsequence {
    private int findLCSLength(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int maxLength = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                //return maximum of if characters were matched or either of string characters matched
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }

    public int longComSubsequenceTopdownMemo(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        int m = text1.length(), n = text2.length();
        Integer[][] dp = new Integer[m + 1][n + 1];
        return lcsRecursive(text1, text2, 0, 0, dp);

    }

    private int lcsRecursive(String strOne, String strTwo, int i, int j, Integer[][] dp) {
        if (i == strOne.length() || j == strTwo.length()) return 0;

        if (dp[i][j]!= null) return dp[i][j];

        if (strOne.charAt(i) == strTwo.charAt(j))
            return 1 + lcsRecursive(strOne, strTwo, i + 1, j + 1, dp);

        dp[i][j] = Math.max(lcsRecursive(strOne, strTwo, i + 1, j, dp),
                lcsRecursive(strOne, strTwo, i , j + 1, dp));

        return dp[i][j];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println(lcs.findLCSLength("abdca", "cbda"));
        System.out.println(lcs.findLCSLength("passport", "ppsspt"));
        System.out.println(lcs.findLCSLength("gxtxayb", "aggtab"));

        System.out.println(lcs.longComSubsequenceTopdownMemo("abdca", "cbda"));
        System.out.println(lcs.longComSubsequenceTopdownMemo("passport", "ppsspt"));
        System.out.println(lcs.longComSubsequenceTopdownMemo("gxtxayb", "aggtab"));

    }
}
