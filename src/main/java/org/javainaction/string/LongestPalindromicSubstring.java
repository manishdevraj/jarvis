package org.javainaction.string;

public class LongestPalindromicSubstring {
    //O(n^2) time | O(1) space
    public static String longestPalindromicSubstring(String str) {
        int[] currentLongest = {0, 1};
        for (int i = 1 ; i < str.length(); i++ ) {
            int[] odd = getLongestPalindromAt(str, i - 1, i + 1);
            int[] even = getLongestPalindromAt(str, i - 1, i);
            int[] longest = odd[1] - odd[0] > even[1] - even[0] ?
                    odd : even;
            currentLongest = currentLongest[1] - currentLongest[0]
                    > longest[1] - longest[0] ?
                    currentLongest : longest;
        }

        return str.substring(currentLongest[0], currentLongest[1]);
    }

    public static int[] getLongestPalindromAt(String str, int start, int end) {
        while (start >=0  && end < str.length()) {
            if (str.charAt(start) != str.charAt(end)) {
                break;
            }
            start--;
            end++;
        }
        return new int[] {start + 1, end};
    }
}
