package org.javainaction.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstrNoRepeatChars {
    public static void main(String[] args) {
        System.out.println(LongestSubstrNoRepeatChars.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(LongestSubstrNoRepeatChars.lengthOfLongestSubstring("bbbbb"));
        System.out.println(LongestSubstrNoRepeatChars.lengthOfLongestSubstring("pwwkew"));
        System.out.println(LongestSubstrNoRepeatChars.lengthOfLongestSubstring(" "));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        int max_length = Integer.MIN_VALUE;
        if (s == null || s.length() == 0) return 0;
        for(int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charFrequency.containsKey(c)) {
                left = Math.max(left, charFrequency.get(c) + 1);
            }
            charFrequency.put(c, right);
            max_length = Math.max(max_length, right - left + 1);
        }
        return max_length;
    }
}
