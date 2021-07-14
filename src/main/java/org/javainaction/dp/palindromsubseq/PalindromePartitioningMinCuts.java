package org.javainaction.dp.palindromsubseq;

import java.util.Arrays;

/**
 * Given a string, we want to cut it into pieces such that each piece is a palindrome.
 * Write a function to return the minimum number of cuts needed.
 *
 * Example 1:
 *
 * Input: "abdbca"
 * Output: 3
 * Explanation: Palindrome pieces are "a", "bdb", "c", "a".
 * Example 2:
 *
 * Input: = "cddpd"
 * Output: 2
 * Explanation: Palindrome pieces are "c", "d", "dpd".
 * Example 3:
 *
 * Input: = "pqr"
 * Output: 2
 * Explanation: Palindrome pieces are "p", "q", "r".
 * Example 4:
 *
 * Input: = "pp"
 * Output: 0
 * Explanation: We do not need to cut, as "pp" is a palindrome.
 * @see LongestPalindromicSubstring
 */
public class PalindromePartitioningMinCuts {

    public int findMPPCuts(String st) {
        // isPalindrome[i][j] will be 'true' if the string from index 'i' to index 'j' is a palindrome
        boolean[][] isPalindrome = new boolean[st.length()][st.length()];

        // every string with one character is a palindrome
        for (int i = 0; i < st.length(); i++)
            isPalindrome[i][i] = true;

        // populate isPalindrome table
        for (int start = st.length() - 1; start >= 0; start--) {
            for (int end = start + 1; end < st.length(); end++) {
                if (st.charAt(start) == st.charAt(end)) {
                    // if it's a two character string or if the remaining string is a palindrome too
                    if (end - start == 1 || isPalindrome[start + 1][end - 1]) {
                        isPalindrome[start][end] = true;
                    }
                }
            }
        }

        // now lets populate the second table, every index in 'cuts' stores the minimum cuts needed
        // for the substring from that index till the end
        int[] cuts = new int[st.length()];
        for (int start = st.length() - 1; start >= 0; start--) {
            int minCuts = st.length(); // maximum cuts
            for (int end = st.length() - 1; end >= start; end--) {
                if (isPalindrome[start][end]) {
                    // we can cut here as we got a palindrome
                    // also we dont need any cut if the whole substring is a palindrome

                    minCuts = (end == st.length() - 1) ? 0 : Math.min(minCuts, 1 + cuts[end + 1]);
                }
            }
            cuts[start] = minCuts;
        }

        return cuts[0];
    }

    public int palindromePartitioningMinCuts(String str) {
        boolean[][] palindromes = new boolean[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if (i == j)
                    palindromes[i][j] = true;
            }
        }

        for (int len = 2; len < str.length() + 1; len++) {
            for (int i = 0; i < str.length() - len + 1; i++) {
                int j = i + len - 1;
                boolean isCharSame = str.charAt(i) == str.charAt(j);
                if (len == 2) {
                    palindromes[i][j] = isCharSame;
                } else {
                    palindromes[i][j] = isCharSame
                            && palindromes[i + 1][j - 1];
                }
            }
        }

        int[] cuts = new int[str.length()];
        Arrays.fill(cuts, Integer.MAX_VALUE);
        for (int i = 0; i < str.length(); i++) {
            if (palindromes[0][i]) {
                cuts[i] = 0;
            } else {
                cuts[i] = cuts[i - 1] + 1;
                for (int j = 1; j < i; j++) {
                    if (palindromes[j][i] && cuts[j - 1] + 1 < cuts[i]) {
                        cuts[i] = cuts[j - 1] + 1;
                    }
                }
            }
        }
        return cuts[str.length() - 1];
    }

    public static void main(String[] args) {
        PalindromePartitioningMinCuts palindromePartitioningMinCuts = new PalindromePartitioningMinCuts();
        System.out.println(palindromePartitioningMinCuts.findMPPCuts("abdbca"));
        System.out.println(palindromePartitioningMinCuts.findMPPCuts("cdpdd"));
        System.out.println(palindromePartitioningMinCuts.findMPPCuts("pqr"));
        System.out.println(palindromePartitioningMinCuts.findMPPCuts("pp"));
        System.out.println(palindromePartitioningMinCuts.findMPPCuts("madam"));
        System.out.println("----------------------------------------------------");
        System.out.println(palindromePartitioningMinCuts.palindromePartitioningMinCuts("abdbca"));
        System.out.println(palindromePartitioningMinCuts.palindromePartitioningMinCuts("cdpdd"));
        System.out.println(palindromePartitioningMinCuts.palindromePartitioningMinCuts("pqr"));
        System.out.println(palindromePartitioningMinCuts.palindromePartitioningMinCuts("pp"));
        System.out.println(palindromePartitioningMinCuts.palindromePartitioningMinCuts("madam"));
    }
}
