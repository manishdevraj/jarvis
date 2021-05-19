package org.javainaction.dp.palindromsubseq;

/**
 * Given a string, find the length of its Longest Palindromic Substring (LPS). In a palindromic string, elements
 * read the same backward and forward.
 *
 * Example 1:
 *
 * Input: "abdbca"
 * Output: 3
 * Explanation: LPS is "bdb".
 * Example 2:
 *
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "dpd".
 * Example 3:
 *
 * Input: = "pqr"
 * Output: 1
 * Explanation: LPS could be "p", "q" or "r".
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        System.out.println(lps.findLPSLength("abdbca"));
        System.out.println(lps.findLPSLength("cddpd"));
        System.out.println(lps.findLPSLength("pqr"));

        System.out.println(lps.findLPSLengthBottomup("abdbca"));
        System.out.println(lps.findLPSLengthBottomup("cddpd"));
        System.out.println(lps.findLPSLengthBottomup("pqr"));

        System.out.println(lps.longestPalindromicSubstring("abdbca"));
        System.out.println(lps.longestPalindromicSubstring("cddpd"));
        System.out.println(lps.longestPalindromicSubstring("pqr"));
    }

    public int findLPSLength(String str) {
        Integer[][] dp = new Integer[str.length()][str.length()];
        return findLPSLengthRecursive(dp, str, 0, str.length() - 1);
    }

    private int findLPSLengthRecursive(Integer[][] dp, String str, int startIdx, int endIdx) {
        if (startIdx > endIdx) return 0;

        if (startIdx == endIdx) return 1;

        if (dp[startIdx][endIdx] == null) {
            // case 1: elements at the beginning and the end are the same
            if (str.charAt(startIdx) == str.charAt(endIdx)) {
                int remainingLength = endIdx - startIdx - 1;
                if (remainingLength == findLPSLengthRecursive(dp, str, startIdx + 1, endIdx - 1)) {
                    dp[startIdx][endIdx] = remainingLength + 2;
                    return dp[startIdx][endIdx];
                }
            }
            // case 2: skip one character either from the beginning or the end
            int bottomPalindrome = findLPSLengthRecursive(dp, str, startIdx + 1, endIdx);
            int leftPalindrome = findLPSLengthRecursive(dp, str, startIdx, endIdx - 1);
            dp[startIdx][endIdx] = Math.max(bottomPalindrome, leftPalindrome);
        }
        return dp[startIdx][endIdx];
    }

    private int findLPSLengthBottomup(String str) {
        boolean[][] dp = new boolean[str.length()][str.length()];

        // every string with one character is a palindrome
        for (int i = 0; i < str.length(); i++)
            dp[i][i] = true;

        int maxLength = 1;
        for (int startIdx = str.length() - 1; startIdx >= 0; startIdx--) {
            for (int endIdx = startIdx + 1; endIdx < str.length(); endIdx++) {
                if (str.charAt(startIdx) == str.charAt(endIdx)) {
                    // if it's a two character string or if the remaining string is a palindrome too
                    if (dp[startIdx + 1][endIdx - 1] || endIdx - startIdx == 1) {
                        dp[startIdx][endIdx] = true;
                        maxLength = Math.max(maxLength, endIdx - startIdx + 1);
                    }
                }
            }
        }
        return maxLength;
    }

    //O(n^2) time | O(1) space
    public String longestPalindromicSubstring(String str) {
        int[] currentLongest = {0, 1};
        for (int i = 1 ; i < str.length(); i++ ) {
            int[] odd = getLongestPalindromAt(str, i - 1, i + 1);
            int[] even = getLongestPalindromAt(str, i - 1, i);
            int[] longest = odd[1] - odd[0] > even[1] - even[0] ?
                    odd : even;
            currentLongest = currentLongest[1] - currentLongest[0]
                    > longest[1] - longest[0] ?
                    currentLongest : longest;
        }

        return str.substring(currentLongest[0], currentLongest[1]);
    }

    public int[] getLongestPalindromAt(String str, int start, int end) {
        while (start >=0  && end < str.length()) {
            if (str.charAt(start) != str.charAt(end)) {
                break;
            }
            start--;
            end++;
        }
        return new int[] {start + 1, end};
    }


}
