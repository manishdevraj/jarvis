package org.javainaction.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ letters with any
 * letter,
 * find the length of the longest substring having the same letters after replacement.
 *
 * Example 1:
 *
 * Input: String="aabccbb", k=2
 * Output: 5
 * Explanation: Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".
 * Example 2:
 *
 * Input: String="abbcb", k=1
 * Output: 4
 * Explanation: Replace the 'c' with 'b' to have a longest repeating substring "bbbb".
 */
public class CharacterReplacement {
    public static int findLength(String str, int k) {
        int windowStart = 0, maxLength = 0, maxRepeatLetterCount = 0;
        Map<Character, Integer> letterFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            letterFrequencyMap.put(rightChar, letterFrequencyMap.getOrDefault(rightChar, 0) + 1);
            maxRepeatLetterCount = Math.max(maxRepeatLetterCount, letterFrequencyMap.get(rightChar));

            // current window size is from windowStart to windowEnd, overall we have a letter which is
            // repeating 'maxRepeatLetterCount' times, this means we can have a window which has one letter
            // repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
            // if the remaining letters are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' letters
            if (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {
                char leftChar = str.charAt(windowStart);
                letterFrequencyMap.put(leftChar, letterFrequencyMap.get(leftChar) - 1);
                windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static int findMaxLength(String str, int k) {
        if (str == null || str.length() == 0) return 0;
        int[] freq = new int[26];
        int windowStart = 0;
        int count = 0;
        int max = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            count = Math.max(count, ++freq[str.charAt(windowEnd) - 'a']);
            //if we have all repeating characters like say 'aaaaa' then it will never go out of bounds for K repetition
            // as (4 - 0 + 1 - 5 = 0 > 2) will not add up
            // but let us say we have 'aabcc' then we need to slide window because
            // as (4 - 0 + 1 - 2 = 3 > 2) means we have more than k distinct elements
            if (windowEnd - windowStart + 1 - count > k) {
                freq[str.charAt(windowStart) - 'a']--;
                windowStart++;
            }

            max = Math.max(max, windowEnd - windowStart + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(CharacterReplacement.findLength("aabccbb", 2));
        System.out.println(CharacterReplacement.findLength("abbcb", 1));
        System.out.println(CharacterReplacement.findLength("abccde", 1));

        System.out.println(CharacterReplacement.findMaxLength("aabccbb", 2));
        System.out.println(CharacterReplacement.findMaxLength("abbcb", 1));
        System.out.println(CharacterReplacement.findMaxLength("abccde", 1));
    }
}
