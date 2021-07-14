package org.javainaction.dp.longestcommonsub;

/**
 * Find the Longest common substring length between two given strings
 */
public class LongestCommonSubstring {
    public int findLCSLength(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int maxLCSLength = 0;
        //starting 1 because we are comparing blank string at the start
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                //if we have a match then return that string
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLCSLength = Math.max(maxLCSLength, dp[i][j]);
                }
            }
        }
        return maxLCSLength;
    }

    public int findLCSTopdown(String strOne, String strTwo) {
        Integer[][][] dp = new Integer[strOne.length() + 1][strTwo.length() + 1][];
        return findLCSLengthRecursive(dp, strOne, strTwo, 0, 0, 0);
    }

    private int findLCSLengthRecursive(Integer[][][] dp, String strOne, String strTwo,
                                              int indexOne, int indexTwo, int count) {
        if(indexOne == strOne.length() || indexTwo == strTwo.length())
            return count;

        //if we have computed the problem then return that
        if (dp[indexOne][indexTwo][count] != null) return dp[indexOne][indexTwo][count];

        int matchingCount = count;

        //if both characters match then we have lcs of 1 at least, try finding another lcs by moving both string
        //by one pointer
        if(strOne.charAt(indexOne) == strTwo.charAt(indexTwo))
            matchingCount = findLCSLengthRecursive(dp, strOne, strTwo, indexOne + 1,
                    indexTwo + 1, count + 1);

        //count excluding string two character
        int countOne = findLCSLengthRecursive(dp, strOne, strTwo, indexOne + 1, indexTwo, 0);
        //count excluding string one character
        int countTwo = findLCSLengthRecursive(dp, strOne, strTwo, indexOne, indexTwo + 1, 0);

        //we either have matching from both string with same index values
        //or we had match with 2nd letter from string one with 1st letter of string two
        //or we had match with 2nd letter from string two with 1st letter of string one
        //get the maximum lcs of them
        dp[indexOne][indexTwo][count] = Math.max(matchingCount, Math.max(countTwo, countOne));

        return dp[indexOne][indexTwo][count];
    }

    public static void main(String[] args) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        System.out.println(lcs.findLCSLength("abdca", "cbda"));
        System.out.println(lcs.findLCSLength("passport", "ppsspt"));

        System.out.println(lcs.findLCSTopdown("abdca", "cbda"));
        System.out.println(lcs.findLCSTopdown("passport", "ppsspt"));
    }
}
