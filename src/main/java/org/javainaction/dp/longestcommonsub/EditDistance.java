package org.javainaction.dp.longestcommonsub;

/**
 * Given strings s1 and s2, we need to transform s1 into s2 by deleting, inserting, or replacing characters.
 * Write a function to calculate the count of the minimum number of edit operations.
 *
 * Example 1:
 *
 * Input: s1 = "bat"
 *        s2 = "but"
 * Output: 1
 * Explanation: We just need to replace 'a' with 'u' to transform s1 to s2.
 * Example 2:
 *
 * Input: s1 = "abdca"
 *        s2 = "cbda"
 * Output: 2
 * Explanation: We can replace first 'a' with 'c' and delete second 'c'.
 * Example 3:
 *
 * Input: s1 = "passpot"
 *        s2 = "ppsspqrt"
 * Output: 3
 * Explanation: Replace 'a' with 'p', 'o' with 'q', and insert 'r'.
 *
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * @see LevenshteinDistance
 */
public class EditDistance {

    public int findMinOperations(String str1, String str2) {
        int[][] edits = new int[str1.length() + 1][str2.length() + 1];

        // here we need to update memo with increasing sequence of numbers because when comparing
        //"" with "" "a" we need one delete to make it equal,  when comparing
        //"" with "" "a" "b" we need two delete to make it equal
        // If the strings have a matching character, we can recursively match for the remaining lengths
        for (int i = 0; i <= str1.length(); i++) {
            edits[i][0] = i;
        }

        // here we need to update memo with increasing sequence of numbers because when comparing
        //"" with "" "a" we need one delete to make it equal,  when comparing
        //"" with "" "a" "b" we need two delete to make it equal
        // If the strings have a matching character, we can recursively match for the remaining lengths
        for (int i = 0; i <= str2.length(); i++) {
            edits[0][i] = i;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                // If the strings have a matching character, we can recursively match for the remaining lengths
                if (str1.charAt(i - 1) == str2.charAt(j  - 1)) {
                    edits[i][j] = edits[i - 1][j - 1]; //get last match score
                } else {
                    //try to find if we can match then by either replacing, deleting or inserting character
                    //take minimum cost
                    edits[i][j] = 1 + Math.min(edits[i - 1][j - 1], //replace
                                    Math.min(edits[i][j - 1], //insert
                                     edits[i - 1][j])); //delete;
                }

            }
        }

        return edits[str1.length()][str2.length()];
    }

    public static void main(String[] args) {
        EditDistance editDisatnce = new EditDistance();
        System.out.println(editDisatnce.findMinOperations("bat", "but"));
        System.out.println(editDisatnce.findMinOperations("abdca", "cbda"));
        System.out.println(editDisatnce.findMinOperations("passpot", "ppsspqrt"));
    }
}
