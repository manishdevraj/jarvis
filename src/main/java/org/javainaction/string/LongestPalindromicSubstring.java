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
 * @see org.javainaction.dp.palindromsubseq.LongestPalindromicSubstring
 */
public class LongestPalindromicSubstring {
    //O(n^2) time | O(1) space
    public static String longestPalindromicSubstring(String str) {
        int[] currentLongest = {0, 1};
        for (int i = 1 ; i < str.length(); i++ ) {
            //this is different than longest palindrome substring problem where we just want to know longest length
            //here we need to know actual substring so we need indices
            //key idea is to see if we have longest from i by either considering
            // its odd length i - 1, i and i + 1 or (say string aba) this would be of length 3
            // its even length i - 1 and i (say string aa) this would be of length 2
            //and check if it is palindrome
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

    public static void main(String[] args) {
        System.out.println(longestPalindromicSubstring("abaxyzzyxf"));
    }
}
