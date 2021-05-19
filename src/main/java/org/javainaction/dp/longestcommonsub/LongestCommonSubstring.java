package org.javainaction.dp.longestcommonsub;

public class LongestCommonSubstring {
    public int findLCSLength(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int maxLCSLength = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLCSLength = Math.max(maxLCSLength, dp[i][j]);
                }
            }
        }
        return maxLCSLength;
    }

    private int findLCSLengthRecursive(Integer[][][] dp, String s1, String s2, int i1, int i2, int count) {
        if(i1 == s1.length() || i2 == s2.length())
            return count;

        if(dp[i1][i2][count] == null) {
            int c1 = count;
            if(s1.charAt(i1) == s2.charAt(i2))
                c1 = findLCSLengthRecursive(dp, s1, s2, i1+1, i2+1, count+1);
            int c2 = findLCSLengthRecursive(dp, s1, s2, i1, i2+1, 0);
            int c3 = findLCSLengthRecursive(dp, s1, s2, i1+1, i2, 0);
            dp[i1][i2][count] = Math.max(c1, Math.max(c2, c3));
        }

        return dp[i1][i2][count];
    }

    public static void main(String[] args) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        System.out.println(lcs.findLCSLength("abdca", "cbda"));
        System.out.println(lcs.findLCSLength("passport", "ppsspt"));
    }
}
