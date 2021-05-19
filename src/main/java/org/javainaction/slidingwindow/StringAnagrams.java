package org.javainaction.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string and a pattern, find all anagrams of the pattern in the given string.
 *
 * Anagram is actually a Permutation of a string. For example, “abc” has the following six anagrams:
 *
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 * Write a function to return a list of starting indices of the anagrams of the pattern in the given string.
 *
 * Example 1:
 *
 * Input: String="ppqp", Pattern="pq"
 * Output: [1, 2]
 * Explanation: The two anagrams of the pattern in the given string are "pq" and "qp".
 * Example 2:
 *
 * Input: String="abbcabc", Pattern="abc"
 * Output: [2, 3, 4]
 * Explanation: The three anagrams of the pattern in the given string are "bca", "cab", and "abc".
 */
public class StringAnagrams {
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        if(str == null || pattern == null) return resultIndices;

        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) {
                    // character is completely matched
                    matched++;
                }

            }

            if (matched == charFrequencyMap.size()){
                resultIndices.add(windowStart);
            }

            if (windowEnd >= pattern.length() - 1) { // shrink the window by one character
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0) {
                        matched--; // before putting the character back, decrement the matched count
                    }
                    // put the character back for matching
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }
        return resultIndices;
    }

    public static void main(String[] args) {
        System.out.println(StringAnagrams.findStringAnagrams("ppqp", "pq"));
        System.out.println(StringAnagrams.findStringAnagrams("abbcabc", "abc"));
    }
}
