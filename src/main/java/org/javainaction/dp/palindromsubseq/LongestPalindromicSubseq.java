package org.javainaction.dp.palindromsubseq;

/**
 * Given a sequence, find the length of its Longest Palindromic Subsequence (LPS). In a palindromic subsequence,
 * elements read the same backward and forward.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
 * changing the order of the remaining elements.
 *
 * Example 1:
 *
 * Input: "abdbca"
 * Output: 5
 * Explanation: LPS is "abdba".
 * Example 2:
 *
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "ddd".
 * Example 3:
 *
 * Input: = "pqr"
 * Output: 1
 * Explanation: LPS could be "p", "q" or "r".
 */
public class LongestPalindromicSubseq {
    public static void main(String[] args) {
        LongestPalindromicSubseq lps = new LongestPalindromicSubseq();
        System.out.println(lps.findLPSLength("abdbca"));
        System.out.println(lps.findLPSLength("cddpd"));
        System.out.println(lps.findLPSLength("pqr"));

        System.out.println(lps.findLPSLengthBottomup("abdbca"));
        System.out.println(lps.findLPSLengthBottomup("cddpd"));
        System.out.println(lps.findLPSLengthBottomup("pqr"));
    }

    public int findLPSLength(String st) {
        Integer[][] dp = new Integer[st.length()][st.length()];
        return findLPSLengthRecursive(dp, st, 0, st.length() - 1);
    }

    private int findLPSLengthRecursive(Integer[][] dp, String st, int startIdx, int endIdx) {
        if (startIdx > endIdx) return 0;

        // every sequence with one element is a palindrome of length 1
        if (startIdx == endIdx) return 1;

        if (dp[startIdx][endIdx] == null) {
            if (st.charAt(startIdx) == st.charAt(endIdx)) {
                // case 1: elements at the beginning and the end are the same
                dp[startIdx][endIdx] = 2 + findLPSLengthRecursive(dp, st, startIdx + 1, endIdx - 1);
            } else {
                // case 2: skip one element either from the beginning or the end
                int lpsBottom = findLPSLengthRecursive(dp, st, startIdx + 1, endIdx);
                int lpsLeft = findLPSLengthRecursive(dp, st, startIdx, endIdx - 1);
                dp[startIdx][endIdx] = Math.max(lpsBottom, lpsLeft);
            }
        }
        return dp[startIdx][endIdx];
    }

    private int findLPSLengthBottomup(String str) {
        int[][] dp = new int[str.length()][str.length()];

        for (int i = 0; i < str.length(); i++) {
            dp[i][i] = 1;
        }

        for (int startIdx = str.length() - 1; startIdx >= 0; startIdx--) {
            for (int endIdx = startIdx + 1; endIdx < str.length(); endIdx++) {
                if (str.charAt(startIdx) == str.charAt(endIdx)) {
                    dp[startIdx][endIdx] = 2 + dp[startIdx + 1][endIdx - 1];
                } else {
                    dp[startIdx][endIdx] = Math.max(dp[startIdx + 1][endIdx], dp[startIdx][endIdx - 1]);
                }
            }
        }
        return dp[0][str.length() - 1];
    }
}
