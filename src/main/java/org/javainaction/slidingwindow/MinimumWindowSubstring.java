package org.javainaction.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string and a pattern, find the smallest substring in the given string which has all the characters of
 * the given pattern.
 *
 * Note: pattern contains unique letters
 *
 * Example 1:
 *
 * Input: String="aabdec", Pattern="abc"
 * Output: "abdec"
 * Explanation: The smallest substring having all characters of the pattern is "abdec"
 * Example 2:
 *
 * Input: String="abdabca", Pattern="abc"
 * Output: "abc"
 * Explanation: The smallest substring having all characters of the pattern is "abc".
 * Example 3:
 *
 * Input: String="adcad", Pattern="abc"
 * Output: ""
 * Explanation: No substring in the given string has all characters of the pattern.
 * @see MinWindowSubString with duplicate characters
 * @see StringAnagrams where we are essentially finding min window substring
 */
public class MinimumWindowSubstring {
    public static String findSubstring(String str, String pattern) {
        if(str == null || pattern == null) return "";

        int windowStart = 0, matched = 0, solutionStart = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        int minLength = str.length() + 1;
        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) matched++;
            }

            while (matched == charFrequencyMap.size()) {
                if ((windowEnd - windowStart) + 1 < minLength) {
                    minLength = (windowEnd - windowStart) + 1;
                    solutionStart = windowStart;
                }
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    // before putting the character back, decrement the matched count
                    if (charFrequencyMap.get(leftChar) == 0)  matched--;
                    // put the character back for matching
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }

        }
        return minLength > str.length() ? "" : str.substring(solutionStart, solutionStart + minLength);
    }

    public static void main(String[] args) {
        System.out.println("Str : aabdec" + " pattern : abc :" + findSubstring("aabdec", "abc"));
        System.out.println("Str : abdabca" + " pattern : abc :" +findSubstring("abdabca", "abc"));
        System.out.println("Str : adcad" + " pattern : abc :" +findSubstring("adcad", "abc"));
        System.out.println("Str : abcd$ef$axb$c$" + " pattern : $$abf :" +findSubstring("abcd$ef$axb$c$", "$abf"));
        System.out.println("Str : adcad" + " pattern : aca :" +findSubstring("adcad", "aca"));
    }
}
