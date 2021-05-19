package org.javainaction.dp.longestcommonsub;

/**
 * Given two sequences ‘s1’ and ‘s2’, write a method to find the length of the shortest sequence which has ‘s1’
 * and ‘s2’ as subsequences.
 *
 * Example 2:
 *
 * Input: s1: "abcf" s2:"bdcf"
 * Output: 5
 * Explanation: The shortest common super-sequence (SCS) is "abdcf".
 * Example 2:
 *
 * Input: s1: "dynamic" s2:"programming"
 * Output: 15
 * Explanation: The SCS is "dynprogrammicng".
 */
public class ShortestCommonSubsequence {
    public int shortestCommonSubseqLength(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for(int i = 0; i <= s1.length(); i++)
            dp[i][0] = i;

        for(int i= 0; i <= s2.length(); i++)
            dp[0][i] = i;

        for (int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    private int findSCSLengthRecursive(Integer[][] dp, String s1, String s2, int i1, int i2) {
        // if we have reached the end of a string, return the remaining length of the other string,
        // as in this case we have to take all of the remaining other string
        if(i1 == s1.length())
            return s2.length()-i2;
        if(i2 == s2.length())
            return s1.length()-i1;

        if(dp[i1][i2] == null) {
            if(s1.charAt(i1) == s2.charAt(i2))
                dp[i1][i2] = 1 + findSCSLengthRecursive(dp, s1, s2, i1+1, i2+1);
            else {
                int length1 = 1 + findSCSLengthRecursive(dp, s1, s2, i1, i2+1);
                int length2 = 1 + findSCSLengthRecursive(dp, s1, s2, i1+1, i2);
                dp[i1][i2] = Math.min(length1, length2);
            }
        }

        return dp[i1][i2];
    }

    public static void main(String[] args) {
        ShortestCommonSubsequence scs = new ShortestCommonSubsequence();
        System.out.println(scs.shortestCommonSubseqLength("abcf", "bdcf"));
        System.out.println(scs.shortestCommonSubseqLength("dynamic", "programming"));
    }
}
