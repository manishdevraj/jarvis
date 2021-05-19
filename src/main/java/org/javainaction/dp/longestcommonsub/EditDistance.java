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
 */
public class EditDistance {

    public static void main(String[] args) {
        EditDistance editDisatnce = new EditDistance();
        System.out.println(editDisatnce.findMinOperations("bat", "but"));
        System.out.println(editDisatnce.findMinOperations("abdca", "cbda"));
        System.out.println(editDisatnce.findMinOperations("passpot", "ppsspqrt"));
    }

    private int findMinOperations(String str1, String str2) {
        int[][] edits = new int[str1.length() + 1][str2.length() + 1];

        // If the strings have a matching character, we can recursively match for the remaining lengths
        for (int i = 0; i <= str1.length(); i++) {
            edits[i][0] = i;
        }

        // If the strings have a matching character, we can recursively match for the remaining lengths

        for (int i = 0; i <= str2.length(); i++) {
            edits[0][i] = i;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                // If the strings have a matching character, we can recursively match for the remaining lengths
                if (str1.charAt(i - 1) == str2.charAt(j  - 1)) {
                    edits[i][j] = edits[i - 1][j - 1];
                } else {
                    edits[i][j] = 1 + Math.min(edits[i - 1][j - 1], //replace
                                    Math.min(edits[i][j - 1], //insert
                                     edits[i - 1][j])); //delete;
                }

            }
        }

        return edits[str1.length()][str2.length()];
    }
}
