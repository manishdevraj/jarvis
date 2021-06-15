package org.javainaction.dp.longestcommonsub;

/**
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 *
 * In one step, you can delete exactly one character in either string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Example 2:
 *
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 */
public class DeleteOpTwoStrings {
    //O(m * n) time | O(m * n) space
    public static int minDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        //remove value dp[m][n] from each string as our cost to delete
        return s1.length() + s2.length() - 2 * dp[s1.length()][s2.length()];
    }

    public static void main(String[] args){
        System.out.println("Min delete distance for 'sea' and 'eat' : " + minDistance("sea", "eat"));
        System.out.println("Min delete distance for 'delete' and 'leet' : " + minDistance("delete", "leet"));
    }

}
