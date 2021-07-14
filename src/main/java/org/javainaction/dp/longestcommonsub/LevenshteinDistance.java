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
 * @see EditDistance
 */
public class LevenshteinDistance {
    public static int levenshteinDistance(String str1, String str2) {
        // O(nm) time | O(nm) space
        int[][] edits = new int[str2.length() + 1][str1.length() + 1];
        // We add empty char at start of row and column to be able to
        // go diagonal for first character
        // here we need to update memo with increasing sequence of numbers because when comparing
        //"" with "" "a" we need one delete to make it equal,  when comparing
        //"" with "" "a" "b" we need two delete to make it equal
        for (int i = 0 ; i < str2.length() + 1; i++) {
            for (int j = 0; j < str1.length() + 1; j++){
                edits[i][j] = j;
            }
            edits[i][0] = i;
        }

        for (int i = 1 ; i < str2.length() + 1; i++) {
            for (int j = 1; j < str1.length() + 1; j++){
                // We start char at row - 1 and column - 1
                // because we have padding at start of both as blank
                if(str2.charAt(i - 1) == str1.charAt(j - 1)) {
                    edits[i][j] = edits[i - 1][j - 1];
                } else {
                    edits[i][j] = 1 + Math.min(edits[i - 1][j - 1], //replace
                            Math.min(edits[i - 1][j],//delete
                                    edits[i][j - 1])); //insert
                }
            }
        }
        return edits[str2.length()][str1.length()];
    }

    // O(nm) time | O(min(n,m)) space
    public static int levenshteinDistanceLessSpace(String str1, String str2) {
        // O(nm) time | O(min(n,m)) space
        String small = str1.length() < str2.length() ? str1 : str2;
        String big = str1.length() >= str2.length() ? str1 : str2;

        int[] evenEdits = new int[small.length() + 1];
        int[] oddEdits = new int[small.length() + 1];
        for (int j = 0; j < small.length() + 1; j++){
            evenEdits[j] = j;
        }

        int[] currentEdits;
        int[] previousEdits;
        for (int i = 1 ; i < big.length() + 1; i++) {
            //to swap edits array at each pass
            if(i % 2 == 1) {
                currentEdits = oddEdits;
                previousEdits = evenEdits;
            } else{
                currentEdits = evenEdits;
                previousEdits = oddEdits;
            }
            //firs row & column in matrix is ""
            currentEdits[0] = i;
            for (int j = 1; j < small.length() + 1; j++){
                //We start char at row - 1 and column - 1
                //because we have padding at start of both as blank
                if(big.charAt(i - 1) == small.charAt(j - 1)) {
                    currentEdits[j] = previousEdits[j - 1];
                } else {
                    currentEdits[j] = 1 + Math.min(previousEdits[j - 1],
                            Math.min(previousEdits[j], currentEdits[j - 1]));
                }
            }
        }
        return big.length() % 2 == 0 ? evenEdits[small.length()] : oddEdits[small.length()];
    }

    public static void main(String[] args) {
        System.out.println(levenshteinDistance("abc", "yabd"));
        System.out.println(levenshteinDistance("bat", "but"));
        System.out.println(levenshteinDistance("abdca", "cbda"));
        System.out.println(levenshteinDistance("passpot", "ppsspqrt"));

        System.out.println(levenshteinDistanceLessSpace("abc", "yabd"));
        System.out.println(levenshteinDistanceLessSpace("bat", "but"));
        System.out.println(levenshteinDistanceLessSpace("abdca", "cbda"));
        System.out.println(levenshteinDistanceLessSpace("passpot", "ppsspqrt"));
    }

}
