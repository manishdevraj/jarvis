package org.javainaction.dp;


public class LevenshteinDistance {
    public static int levenshteinDistance(String str1, String str2) {
        // O(nm) time | O(nm) space
        int[][] edits = new int[str2.length() + 1][str1.length() + 1];
        // We add empty char at start of row and column to be able to
        // go diagonal for first character
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
                    edits[i][j] = 1 + Math.min(edits[i - 1][j - 1],
                            Math.min(edits[i - 1][j], edits[i][j - 1]));
                }
            }
        }
        return edits[str2.length()][str1.length()];
    }

    public static void main(String[] args) {
        System.out.println(levenshteinDistance("abc", "yabd"));
        System.out.println(levenshteinDistance("bat", "but"));
        System.out.println(levenshteinDistance("abdca", "cbda"));
        System.out.println(levenshteinDistance("passpot", "ppsspqrt"));
    }

}
