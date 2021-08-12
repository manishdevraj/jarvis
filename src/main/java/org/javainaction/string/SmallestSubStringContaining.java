package org.javainaction.string;

import org.javainaction.slidingwindow.MinWindowSubStringWithDuplicateChars;

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
 * @see MinWindowSubStringWithDuplicateChars with duplicate characters
 * @see org.javainaction.slidingwindow.StringAnagrams where we are essentially finding min window substring
 * @see org.javainaction.slidingwindow.MinimumWindowSubstring with unique characters
 */
public class SmallestSubStringContaining {
    public static String findSmallestSubstring(String str, String pattern) {
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
                if (charFrequencyMap.get(rightChar) == 0) {
                    // character is completely matched
                    matched++;
                }

            }

            while (matched == charFrequencyMap.size()){
                if ((windowEnd - windowStart) + 1 < minLength ) {
                    minLength = (windowEnd - windowStart) + 1;
                    solutionStart = windowStart;
                }
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
        return minLength > str.length() ? "" : str.substring(solutionStart, solutionStart + minLength);
    }

    public static void main(String[] args) {
        System.out.println(smallestSubstringContaining("abcd$ef$axb$c$", "$$abf"));
        System.out.println(findSmallestSubstring("abcd$ef$axb$c$", "$$abf"));
    }

    //O(b + s) time and O(b + s) space where b is length of big string
    //and s is length of small string
    public static String smallestSubstringContaining(String bigString, String smallString) {
        Map<Character, Integer> stringMap = buildStringMap(smallString);
        int[] substringBounds = getSubstringBounds(bigString, stringMap);
        int start = substringBounds[0];
        int end = substringBounds[1];
        if (end == Integer.MAX_VALUE) return "";
        return bigString.substring(start, end + 1);
    }


    public static int[] getSubstringBounds(String bigString,
                                           Map<Character, Integer> stringMap) {

        int[] substringBounds = new int[]{0, Integer.MAX_VALUE};
        Map<Character, Integer> substringCharCounts = new HashMap<>();
        int numUniqueChars = stringMap.size();
        int numUniqueCharsDone = 0;
        int leftIdx = 0;
        int rightIdx = 0;
        // Move the righIdx to the right in string until we have counted
        // all of the characters from string map
        while (rightIdx < bigString.length()) {
            char rightChar = bigString.charAt(rightIdx);
            if (!stringMap.containsKey(rightChar)) {
                rightIdx++;
                continue;
            }

            incrementCharCount(rightChar, substringCharCounts);

            if (substringCharCounts.get(rightChar) ==
                    stringMap.get(rightChar)) {
                numUniqueCharsDone++;
            }

            // Move the leftIdx to the right in string until we exhausted
            // any characters from string map between right and left idx.
            // update substring bounds accordingly.
            while (leftIdx <= rightIdx && numUniqueChars == numUniqueCharsDone) {
                substringBounds =
                        getSmallerBounds(leftIdx,
                                rightIdx,
                                substringBounds[0],
                                substringBounds[1]);

                char leftChar = bigString.charAt(leftIdx);
                if (!stringMap.containsKey(leftChar)) {
                    leftIdx++;
                    continue;
                }

                if (substringCharCounts.get(leftChar) ==
                        stringMap.get(leftChar)) {
                    numUniqueCharsDone--;
                }
                substringCharCounts.put(leftChar, substringCharCounts.get(leftChar) - 1);
                leftIdx++;
            }
            rightIdx++;
        }
        return substringBounds;
    }

    public static int[] getSmallerBounds(int i, int j, int x, int y) {
        return j - i < y - x
                ? new int[] {i, j}
                : new int[] {x, y};
    }

    public static Map<Character, Integer> buildStringMap(String smallString){
        Map<Character, Integer> stringMap = new HashMap<>();
        for (char c : smallString.toCharArray()) {
            incrementCharCount(c, stringMap);
        }
        return stringMap;
    }

    public static void incrementCharCount(char c,
                                          Map<Character, Integer> map) {
        if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
        } else {
            map.put(c, 1);
        }
    }
}
