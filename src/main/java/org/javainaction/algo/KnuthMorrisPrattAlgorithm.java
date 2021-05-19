package org.javainaction.algo;

import java.util.Arrays;

/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[])
 * that prints first occurrence of pat[] in txt[]. You may assume that n > m.
 * O(n + m) time | O(n + m) space
 */
public class KnuthMorrisPrattAlgorithm {

    public static void main(String[] args) {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        System.out.println(knuthMorrisPrattAlgorithm(txt, pat));
        System.out.println(knuthMorrisPrattAlgorithm("aedaefaef", "aef"));
    }
    public static int knuthMorrisPrattAlgorithm(String string, String substring) {
        // O(n + m) time  | O(m) space
        int[] pattern = buildPattern(substring);
        return doesItMatch(string, substring, pattern);
    }

    public static int[] buildPattern(String substring) {
        int[] pattern = new int[substring.length()];
        Arrays.fill(pattern, -1);
        int j = 0;
        int i = 1;

        while(i < substring.length()) {
            if (substring.charAt(i) == substring.charAt(j)) {
                pattern[i] = j;
                i++;
                j++;
            } else if(j > 0) {
                j = pattern[j - 1] + 1;
            } else {
                i++;
            }
        }
        return pattern;
    }

    public static int doesItMatch(String string, String substring, int[] pattern) {
        int j = 0;
        int i = 0;

        while(i + substring.length() - j <= string.length()) {
            if(string.charAt(i) == substring.charAt(j)) {
                if(j == substring.length() - 1) return i - j + 1;
                i++;
                j++;
            } else if(j > 0) {
                j = pattern[j - 1] + 1;
            } else {
                i++;
            }
        }
        return -1;
    }
}
