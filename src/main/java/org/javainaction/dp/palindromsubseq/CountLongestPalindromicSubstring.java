package org.javainaction.dp.palindromsubseq;

/**
 * Given a string, find the total number of palindromic substrings in it.
 * Please note we need to find the total number of substrings and not subsequences.
 *
 * Example 1:
 *
 * Input: "abdbca"
 * Output: 7
 * Explanation: Here are the palindromic substrings, "a", "b", "d", "b", "c", "a", "bdb".
 * Example 2:
 *
 * Input: = "cddpd"
 * Output: 7
 * Explanation: Here are the palindromic substrings, "c", "d", "d", "p", "d", "dd", "dpd".
 * Example 3:
 *
 * Input: = "pqr"
 * Output: 3
 * Explanation: Here are the palindromic substrings,"p", "q", "r".
 * @see LongestPalindromicSubstring
 */
public class CountLongestPalindromicSubstring {
    public static int findCPS(String st) {
        // dp[i][j] will be 'true' if the string from index 'i' to index 'j' is a
        // palindrome
        boolean[][] dp = new boolean[st.length()][st.length()];
        int count = 0;

        // every string with one character is a palindrome
        for (int i = 0; i < st.length(); i++) {
            dp[i][i] = true;
            //we count even single letter strings too
            count++;
        }

        for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
                if (st.charAt(startIndex) == st.charAt(endIndex)) {
                    // if it's a two character string or if the remaining string is a palindrome too
                    if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
                        //we have some form of palindrome, just count that too, this is similar as
                        //find length of longest palindrome substring, we are just counting them
                        //here over end - start + 1
                        dp[startIndex][endIndex] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(findCPS("abdbca"));
        System.out.println(findCPS("cdpdd"));
        System.out.println(findCPS("pqr"));
        System.out.println(findCPS("abc"));
    }
}
