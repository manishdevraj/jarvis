package org.javainaction.dp.palindromsubseq;

/**
 * Given a string, find the minimum number of characters that we can remove to make it a palindrome.
 *
 * Example 1:
 *
 * Input: "abdbca"
 * Output: 1
 * Explanation: By removing "c", we get a palindrome "abdba".
 * Example 2:
 *
 * Input: = "cddpd"
 * Output: 2
 * Explanation: Deleting "cp", we get a palindrome "ddd".
 * Example 3:
 *
 * Input: = "pqr"
 * Output: 2
 * Explanation: We have to remove any two characters to get a palindrome, e.g. if we
 * remove "pq", we get palindrome "r".
 *
 * Similar problems #
 * Here are a couple of similar problems:
 *
 * 1. Minimum insertions in a string to make it a palindrome #
 *
 * Will the above approach work if we make insertions instead of deletions?
 *
 * Yes, the length of the Longest Palindromic Subsequence is the best palindromic subsequence we can have.
 * Let’s take a few examples:
 *
 * Example 1:
 *
 * Input: "abdbca"
 * Output: 1
 * Explanation: By inserting “c”, we get a palindrome “acbdbca”.
 *
 * Example 2:
 *
 * Input: = "cddpd"
 * Output: 2
 * Explanation: Inserting “cp”, we get a palindrome “cdpdpdc”. We can also get a palindrome by inserting “dc”: “cddpddc”
 *
 * Example 3:
 *
 * Input: = "pqr"
 * Output: 2
 * Explanation: We have to insert any two characters to get a palindrome (e.g. if we insert “pq”, we get a
 * palindrome “pqrqp”).
 *
 * 2. Find if a string is K-Palindromic #
 *
 * Any string will be called K-palindromic if it can be transformed into a palindrome by removing at most ‘K’
 * characters from it.
 *
 * This problem can easily be converted to our base problem of finding the minimum deletions in a string to make
 * it a palindrome. If the “minimum deletion count” is not more than ‘K’, the string will be K-Palindromic.
 * @see LongestPalindromicSubseq we need to get length of LPS and deduct from string to find deletion required
 */
public class MinimumDeletionsLPS {

    public static int findMinimumDeletions(String st) {
        // subtracting the length of Longest Palindromic Subsequence from the length of
        // the input string to get minimum number of deletions
        return st.length() - findLPSLength(st);
    }

    public static int findLPSLength(String st) {
        // dp[i][j] stores the length of LPS from index 'i' to index 'j'
        int[][] dp = new int[st.length()][st.length()];

        // every sequence with one element is a palindrome of length 1
        for (int i = 0; i < st.length(); i++)
            dp[i][i] = 1;

        for (int start = st.length() - 1; start >= 0; start--) {
            for (int end = start + 1; end < st.length(); end++) {
                // case 1: elements at the beginning and the end are the same
                if (st.charAt(start) == st.charAt(end)) {
                    dp[start][end] = 2 + dp[start + 1][end - 1];
                } else { // case 2: skip one element either from the beginning or the end
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }
        }
        return dp[0][st.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(findMinimumDeletions("abdbca"));
        System.out.println(findMinimumDeletions("cddpd"));
        System.out.println(findMinimumDeletions("pqr"));
    }
}
