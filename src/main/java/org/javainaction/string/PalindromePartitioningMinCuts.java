package org.javainaction.string;

import java.util.Arrays;

/**
 * @see org.javainaction.dp.palindromsubseq.PalindromePartitioning
 */
public class PalindromePartitioningMinCuts {

    // O(n^2) time | O(n^2) space
    public static int palindromePartitioningMinCuts(String str) {
        boolean[][] palindromes = new boolean[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if (i == j)
                    palindromes[i][j] = true;
            }
        }

        for (int len = 2; len < str.length() + 1; len++) {
            for (int i = 0; i < str.length() - len + 1; i++) {
                int j = i + len - 1;
                boolean isMatching = str.charAt(i) == str.charAt(j);
                if (len == 2) {
                    palindromes[i][j] = isMatching;
                } else {
                    palindromes[i][j] = isMatching
                            && palindromes[i + 1][j - 1];
                }
            }
        }

        int[] cuts = new int[str.length()];
        Arrays.fill(cuts, Integer.MAX_VALUE);
        for (int i = 0; i < str.length(); i++) {
            if (palindromes[0][i]) {
                cuts[i] = 0;
            } else {
                cuts[i] = cuts[i - 1] + 1;
                for (int j = 1; j < i; j++) {
                    if (palindromes[j][i] && cuts[j - 1] + 1 < cuts[i]) {
                        cuts[i] = cuts[j - 1] + 1;
                    }
                }
            }
        }
        return cuts[str.length() - 1];
    }
}
