package org.javainaction.dp.palindromsubseq;

/**
 * Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings.
 * Otherwise, return false.
 *
 * A string is said to be palindrome if it the same string when reversed.
 *
 *
 * Example 1:
 *
 * Input: s = "abcbdd"
 * Output: true
 * Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
 * Example 2:
 *
 * Input: s = "bcbddxy"
 * Output: false
 * Explanation: s cannot be split into 3 palindromes.
 * @see LongestPalindromicSubstring
 */
public class PalindromePartitioning4 {

    public boolean checkPartitioning(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                int len = j - i + 1;
                //case 1: single letter
                if (len == 1) dp[i][j] = true;
                else {
                    boolean isPalindrome = s.charAt(i) == s.charAt(j);
                    //case 2: two letters
                    if(len == 2)
                        dp[i][j] = isPalindrome;
                    //is palindrome if memo from left and right is palindrome
                    else
                        dp[i][j] = isPalindrome && dp[i + 1][j - 1];
                }
            }
        }

        // iterate every mid and then check: left, mid and right
        for (int i = 1; i < s.length(); i++) {
            for (int j = i; j < s.length() - 1; j++){
                //we check 3 parts that might make it is 3 part palindrome
                if (dp[0][i - 1] && dp[i][j] && dp[j + 1][s.length() - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var obj = new PalindromePartitioning4();
        System.out.println("Palindrome partitioning " + obj.checkPartitioning("abcbdd"));
        System.out.println("Palindrome partitioning " + obj.checkPartitioning("bcbddxy"));
    }
}
