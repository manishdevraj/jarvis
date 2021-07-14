package org.javainaction.dp.longestcommonsub;


/**
 * two strings equal.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "sea", s2 = "eat"
 * Output: 231
 * Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 * Deleting "t" from "eat" adds 116 to the sum.
 * At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 * Example 2:
 *
 * Input: s1 = "delete", s2 = "leet"
 * Output: 403
 * Explanation: Deleting "dee" from "delete" to turn the string into "let",
 * adds 100[d] + 101[e] + 101[e] to the sum.
 * Deleting "e" from "leet" adds 101[e] to the sum.
 * At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 */
public class MinASCIIDeleteSumStrings {
    //O(m * n) time | O(m * n) space
    public static int minimumDeleteSum(String s1, String s2) {
        int[][] asciiDp = new int[s1.length() + 1][s2.length() + 1];

        //as we need to add ascii code point we have to go down at last column
        for (int i = s1.length() - 1; i >=0; i--) {
            asciiDp[i][s2.length()] = asciiDp[i + 1][s2.length()] + s1.codePointAt(i);
        }

        //as we need to add ascii code point we have to go right at last row
        for (int j = s2.length() - 1; j >=0; j--) {
            asciiDp[s1.length()][j] = asciiDp[s1.length()][j + 1] + s2.codePointAt(j);
        }

        for (int i = s1.length() - 1; i >=0; i--) {
            for (int j = s2.length() - 1; j >=0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    asciiDp[i][j] = asciiDp[i + 1][j + 1];
                } else {
                    //usually we would take min of two values delete operations and add one
                    //here we just need to compare operation with ascii value
                    asciiDp[i][j] = Math.min(asciiDp[i + 1][j] + s1.codePointAt(i),
                            asciiDp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return asciiDp[0][0];
    }



    public static void main(String[] args){
        System.out.println("Min ASCII delete sum for 'sea' and 'eat' : " + minimumDeleteSum("sea", "eat"));
        System.out.println("Min ASCII delete sum for 'delete' and 'leet' : " + minimumDeleteSum("delete", "leet"));
    }

}
