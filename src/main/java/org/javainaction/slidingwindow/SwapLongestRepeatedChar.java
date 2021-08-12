package org.javainaction.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest
 * substring with repeated characters.
 *
 * Example 1:
 *
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'.
 * Then, the longest repeated character substring is "aaa", which its length is 3.
 * Example 2:
 *
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa",
 * which its length is 6.
 * Example 3:
 *
 * Input: text = "aaabbaaa"
 * Output: 4
 * Example 4:
 *
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
 * Example 5:
 *
 * Input: text = "abcdef"
 * Output: 1
 * @see LongestTurbulentSubarray
 */
public class SwapLongestRepeatedChar {
    public static int maxRepOpt1(String s) {
        if (s == null || s.length() == 0) return 0;
        var frequencies = new HashMap<Character, List<Integer>>();
        //store frequencies with their indices so we know if we are comparing adjacent pairs
        for (int i = 0; i < s.length(); i++) {
            var value = frequencies.getOrDefault(s.charAt(i), new ArrayList<>());
            value.add(i);
            frequencies.put(s.charAt(i), value);
        }

        int longest = 1;
        for (List<Integer> indices : frequencies.values()) {
            int maxLength = 1;
            int curLength = 1;
            int prevLength = 0;
            int size = indices.size();
            for (int i = 1; i < size; i++) {
                //contagious same characters
                if (indices.get(i) == indices.get(i - 1) + 1) curLength++;
                else {
                    // if they are not contiguous
                    // if both sides have the same char and are separated by only 1 char
                    prevLength =  indices.get(i) == indices.get(i - 1) + 2 ? curLength : 0;
                    // to reset the process/counter
                    curLength = 1;
                }
                maxLength = Math.max(maxLength, prevLength + curLength);
            }
            longest = Math.max(longest, maxLength + (maxLength < size ? 1 : 0));
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(" Longest repeating with 1 replacement " + maxRepOpt1("ababa"));
    }
}
