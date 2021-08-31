package org.javainaction.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string and a pattern, find out if the string contains any permutation of the pattern.
 *
 * Permutation is defined as the re-arranging of the characters of the string.
 * For example, “abc” has the following six permutations:
 *
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 * If a string has ‘n’ distinct characters it will have
 * n
 * !
 * n! permutations.
 *
 * Example 1:
 *
 * Input: String="oidbcaf", Pattern="abc"
 * Output: true
 * Explanation: The string contains "bca" which is a permutation of the given pattern.
 * Example 2:
 *
 * Input: String="odicf", Pattern="dc"
 * Output: false
 * Explanation: No permutation of the pattern is present in the given string as a substring.
 * Example 3:
 *
 * Input: String="bcdxabcdy", Pattern="bcdyabcdx"
 * Output: true
 * Explanation: Both the string and the pattern are a permutation of each other.
 * Example 4:
 *
 * Input: String="aaacb", Pattern="abc"
 * Output: true
 * Explanation: The string contains "acb" which is a permutation of the given pattern.
 */
public class StringPermutation {
    public static boolean findPermutation(String str, String pattern) {
        if(str == null || pattern == null) return false;

        int[] patternMap = new int[26];
        for (char c : pattern.toCharArray()) patternMap[c - 'a']++;

        //make sure we have start at 0 and then end at at least pattern length so we have right size to compare
        for (int start = 0, end = pattern.length(); end <= str.length(); start++, end++) {
            String substring = str.substring(start, end);
            if (isStringPatternMatch(substring, patternMap)) return true;
        }

        return false;
    }

    public static boolean isStringPatternMatch(String substring, int[] patternMap) {
        for (char c : substring.toCharArray()) {
            if (patternMap[c - 'a'] == 0) return false;
        }
        return true;
    }

    public static boolean findPermutationSlidingWindow(String str, String pattern) {
        if(str == null || pattern == null) return false;

        int windowStart = 0, matched = 0;
        Map<Character, Integer> patternFreq = new HashMap<>();
        //create pattern frequency map
        for (char chr : pattern.toCharArray())
            patternFreq.put(chr, patternFreq.getOrDefault(chr, 0) + 1);

        // our goal is to match all the characters from the 'patternFreq' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (patternFreq.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                patternFreq.put(rightChar, patternFreq.get(rightChar) - 1);
                if (patternFreq.get(rightChar) == 0) // character is completely matched
                    matched++;
            }

            //if we have found match for all pattern frequency then we found our solution
            if (matched == patternFreq.size())
                return true;

            //if we are going beyond length of the pattern then shrink the window by one character
            if (windowEnd >= pattern.length() - 1) {
                char leftChar = str.charAt(windowStart++);
                if (patternFreq.containsKey(leftChar)) {
                    if (patternFreq.get(leftChar) == 0)
                        matched--; // before putting the character back, decrement the matched count
                    // put the character back for matching
                    patternFreq.put(leftChar, patternFreq.get(leftChar) + 1);
                }
            }
        }

        return false;
    }



    public static void main(String[] args) {
        System.out.println(findPermutation("oidbcaf", "abc"));
        System.out.println(findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println(findPermutation("odicf", "dc"));
        System.out.println(findPermutation("aaacb", "abc"));

        //Sliding window approach
        System.out.println(findPermutationSlidingWindow("oidbcaf", "abc"));
        System.out.println(findPermutationSlidingWindow("bcdxabcdy", "bcdyabcdx"));
        System.out.println(findPermutationSlidingWindow("odicf", "dc"));
        System.out.println(findPermutationSlidingWindow("aaacb", "abc"));
    }
}
