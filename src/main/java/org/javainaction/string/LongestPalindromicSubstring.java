package org.javainaction.string;

/**
 * Write a function to return a string's longest palindrome
 *
 * Input: abaxyzzyxf
 *
 * Output: xyzzyx
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 * Example 3:
 *
 * Input: s = "a"
 * Output: "a"
 * Example 4:
 *
 * Input: s = "ac"
 * Output: "a"
 */
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

    //O (n^3) time | O(1) space
    public static String longestPalindromicSubstringO3(String str) {
        String longest = "";
        for (int i = 0 ; i < str.length(); i++ ){
            for ( int j = i; j < str.length(); j++) {
                String subString = str.substring(i , j + 1);
                if (subString.length() > longest.length()
                        && isPalindrome(subString)) {
                    longest = subString;
                }
            }
        }
        return longest;
    }

    public static boolean isPalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromicSubstring("abaxyzzyxf"));
    }
}
