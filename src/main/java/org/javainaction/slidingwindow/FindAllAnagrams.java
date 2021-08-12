package org.javainaction.slidingwindow;

import java.util.*;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * @see MinimumWindowSubstring
 */
public class FindAllAnagrams {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagramIndices = new ArrayList<>();

        if (p == null || s == null) return anagramIndices;
        if (p.length() > s.length()) return anagramIndices;

        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : p.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int distinctCharCount = frequencyMap.size();

        for (int right = 0; right < s.length();right++) {
            //check every character for anagram
            char rightChar = s.charAt(right);

            //slide window
            //as order does not matter, check occurrence in frequency map
            if (frequencyMap.containsKey(rightChar)) {
                //mark one less occurrence of char
                frequencyMap.put(rightChar, frequencyMap.get(rightChar) - 1);
                //if it was last occurrence then decrement distinct count
                if (frequencyMap.get(rightChar) == 0)
                    distinctCharCount--;
            }
            //shrink window
            while (distinctCharCount == 0) {
                char leftChar = s.charAt(left);
                //add back character only if it was part of pattern
                if (frequencyMap.containsKey(leftChar)) {
                    frequencyMap.put(leftChar, frequencyMap.get(leftChar) + 1);
                    //mark as one more distinct
                    if (frequencyMap.get(leftChar) > 0) distinctCharCount++;
                }
                //check anagram length
                if (right - left + 1 == p.length())
                    anagramIndices.add(left);

                left++;
            }
        }
        return anagramIndices;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }
}
