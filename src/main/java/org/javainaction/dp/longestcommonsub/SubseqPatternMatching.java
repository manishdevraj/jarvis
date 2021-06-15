package org.javainaction.dp.longestcommonsub;

/**
 * Given a string and a pattern, write a method to count the number of times the pattern appears in the string as a
 * subsequence.
 *
 * Example 1: Input: string: “baxmx”, pattern: “ax”
 * Output: 2
 * Explanation: {baxmx, baxmx}.
 *
 * Example 2:
 *
 * Input: string: “tomorrow”, pattern: “tor”
 * Output: 4
 * Explanation: Following are the four occurences: {tomorrow, tomorrow, tomorrow, tomorrow}.
 */
public class SubseqPatternMatching {

    private int findSubseqPatternCount(String str, String pattern) {
        if (pattern == null || pattern.length() == 0) return 0;
        if (str == null || pattern.length() > str.length()) return 0;

        int[][] dp = new int[str.length() + 1][pattern.length() + 1];

        for (int i = 0 ; i < str.length(); i++) {
            dp[i][0] = 1;
        }

        for (int strIdx = 1; strIdx <= str.length(); strIdx++) {
            for (int patIdx = 1; patIdx <= pattern.length(); patIdx++) {
                if (str.charAt(strIdx - 1) == pattern.charAt(patIdx - 1)) {
                    dp[strIdx][patIdx] = dp[strIdx - 1][patIdx - 1];
                }
                dp[strIdx][patIdx] += dp[strIdx - 1][patIdx];
            }
        }

        return dp[str.length()][pattern.length()];
    }

    public int findSPMCountTopDown(String str, String pattern) {
        Integer[][] dp = new Integer[str.length() + 1][pattern.length() + 1];
        return findSPMCountRecursive(dp, str, pattern, 0, 0);
    }

    private int findSPMCountRecursive(Integer[][] dp, String str, String pat, int strIndex, int patIndex) {

        // if we have reached the end of the pattern
        if(patIndex == pat.length())
            return 1;

        // if we have reached the end of the string but pattern has still some characters left
        if(strIndex == str.length())
            return 0;

        if(dp[strIndex][patIndex] == null) {
            int c1 = 0;
            if(str.charAt(strIndex) == pat.charAt(patIndex))
                c1 = findSPMCountRecursive(dp, str, pat, strIndex + 1, patIndex + 1);
            int c2 = findSPMCountRecursive(dp, str, pat, strIndex + 1, patIndex);
            dp[strIndex][patIndex] = c1 + c2;
        }

        return dp[strIndex][patIndex];
    }

    public static void main(String[] args) {
        SubseqPatternMatching spm = new SubseqPatternMatching();
        System.out.println(spm.findSubseqPatternCount("baxmx", "ax"));
        System.out.println(spm.findSubseqPatternCount("tomorrow", "tor"));
        System.out.println(spm.findSPMCountTopDown("baxmx", "ax"));
        System.out.println(spm.findSPMCountTopDown("tomorrow", "tor"));

    }
}
