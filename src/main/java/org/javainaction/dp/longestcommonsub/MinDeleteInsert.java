package org.javainaction.dp.longestcommonsub;

/**
 * Given strings s1 and s2, we need to transform s1 into s2 by deleting and inserting characters. Write a function to
 * calculate the count of the minimum number of deletion and insertion operations.
 *
 * Example 1:
 *
 * Input: s1 = "abc"
 *        s2 = "fbc"
 * Output: 1 deletion and 1 insertion.
 * Explanation: We need to delete {'a'} and insert {'f'} to s1 to transform it into s2.
 * Example 2:
 *
 * Input: s1 = "abdca"
 *        s2 = "cbda"
 * Output: 2 deletions and 1 insertion.
 * Explanation: We need to delete {'a', 'c'} and insert {'c'} to s1 to transform it into s2.
 * Example 3:
 *
 * Input: s1 = "passport"
 *        s2 = "ppsspt"
 * Output: 3 deletions and 1 insertion
 * Explanation: We need to delete {'a', 'o', 'r'} and insert {'p'} to s1 to transform it into s2.
 * @see LongestCommonSubsequence
 * @see DeleteOpTwoStrings
 */
public class MinDeleteInsert {
    private int findLCSLength(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        //also note that we will treat this operation as LCS
        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); //insert or delete
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public void findMDI(String s1, String s2) {
        int c1 = findLCSLength(s1, s2);
        //if we delete from string one length then that is delete cost
        //if we delete form string two length then that becomes insert cost
        System.out.println(s1 + " minimum deletions needed: " + (s1.length() - c1));
        System.out.println(s2 + " minimum insertions needed: " + (s2.length() - c1));
    }

    public static void main(String[] args) {
        MinDeleteInsert mdi = new MinDeleteInsert();
        mdi.findMDI("abc", "fbc");
        mdi.findMDI("abdca", "cbda");
        mdi.findMDI("passport", "ppsspt");
    }

}
