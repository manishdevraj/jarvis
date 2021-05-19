package org.javainaction.string;

import java.util.*;

class LongestComSubsequence {

    public static void main(String[] args) {
        String str1 = "ZXVVYZW";
        String str2 = "XKYKZPW";
        System.out.println(longestCommonSubsequence(str1, str2));
    }
    //O(nm) time | O(nm) space
    public static List<Character> longestCommonSubsequence(String str1, String str2) {
        int[][] len = new int[str2.length() + 1][str1.length() + 1];
        for (int i = 1; i < str2.length() + 1; i++) {
            for (int j = 1; j < str1.length() + 1; j++) {
                if (str2.charAt(i - 1) == str1.charAt(j - 1)) {
                    len[i][j] = len[i - 1][j - 1] + 1;
                } else {
                    len[i][j] = Math.max(len[i - 1][j], len[i][j - 1]);
                }
            }
        }
        return buildSequences(len, str1);
    }

    public static List<Character> buildSequences(int[][] len,
                                                 String str) {
        List<Character> sequence = new ArrayList<>();
        int i = len.length - 1;
        int j = len[0].length - 1;
        while (i != 0 && j != 0) {
            if (len[i][j] == len[i - 1][j]) {
                i--;
            } else if (len[i][j] == len[i][j - 1]) {
                j--;
            } else {
                sequence.add(0, str.charAt(j - 1));
                i--;
                j--;
            }
        }
        return sequence;
    }
}


