package org.javainaction.string;

import java.util.Arrays;

/**
 * Given a string s and an integer k, return the length of the longest substring of s such that the 
 * frequency of each character in this substring is greater than or equal to k.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LongSubWithAtKRepeatingChar {
    public static void main(String[] args){
        System.out.println("'aaabb' The longest substring is: " + longestSubstring("aaabb", 3));
        System.out.println("'ababbc' The longest substring is: " + longestSubstring("ababbc", 2));
    }
    public static int longestSubstring(String s, int k) {
        boolean[] freq = new boolean[26];
        int[] freqMap = new int[26];
        int uniqueChars = 0;

        for(int c : s.toCharArray()) {
            if (!freq[c-'a']) {
                uniqueChars++;
                freq[c-'a'] = true;
            }
            //freqMap[c-'a']++;
        }

        int max = 0;
        for (int cur = 1; cur <= uniqueChars; cur++) {
            Arrays.fill(freqMap, 0);
            int start = 0, end = 0, index = 0, unique = 0, kCount = 0;

            while (end < s.length()) {
                if (unique <= cur) {
                    /**
                     * If the number of unique character in the sliding window is less than or equal to currUnique,
                     * expand the window from the right by adding a character to the end of the window given by windowEnd
                     */
                    index = s.charAt(end) - 'a';
                    if (freqMap[index] == 0) unique++;
                    freqMap[index]++;
                    if (freqMap[index] == k) kCount++;
                    end++;
                } else {
                    /**
                     * Otherwise, shrink the window from the left by removing a character from the start of the window
                     * given by windowStart.
                     */
                    index = s.charAt(start) - 'a';
                    if (freqMap[index] == k) kCount--;
                    freqMap[index]--;
                    if (freqMap[index] == 0) unique--;
                    start++;
                }

                /**
                 * Keep track of the number of unique characters in the current sliding window having at least k
                 * frequency given by kCount. Update the result if all the characters in the window have at least k
                 * frequency.
                 */
                if (unique == cur && unique == kCount) {
                    max = Math.max(max, end - start);
                }
            }
        }

        return max;
    }
}
