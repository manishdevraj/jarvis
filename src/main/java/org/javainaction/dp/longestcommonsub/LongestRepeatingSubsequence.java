package org.javainaction.dp.longestcommonsub;

public class LongestRepeatingSubsequence {

    public static void main(String[] args) {
        LongestRepeatingSubsequence lrs = new LongestRepeatingSubsequence();
        System.out.println(lrs.findLongestRepeatingSeqLength("tomorrow"));
        System.out.println(lrs.findLongestRepeatingSeqLength("aabdbcec"));
        System.out.println(lrs.findLongestRepeatingSeqLength("fmff"));
    }

    private int findLRSLengthRecursive(String str, int i1, int i2) {
        if(i1 == str.length() || i2 == str.length())
            return 0;

        if(i1 != i2 && str.charAt(i1) == str.charAt(i2))
            return 1 + findLRSLengthRecursive(str, i1+1, i2+1);

        int c1 = findLRSLengthRecursive(str, i1, i2+1);
        int c2 = findLRSLengthRecursive(str, i1+1, i2);

        return Math.max(c1, c2);
    }

    private int findLongestRepeatingSeqLength(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        int maxLength = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (i != j & s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }
}
