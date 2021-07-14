package org.javainaction.string;

import java.util.HashMap;

/**
 * Given a string return the index of first non repeating character
 *
 * Given a string s, return the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 *
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 *
 * Input: s = "aabb"
 * Output: -1
 *
 * @see GenerateDocument
 */
public class FirstNonRepeatingCharacter {
    public int firstNonRepeatingCharacter(String string) {
        var frequencies = new HashMap<Character, Integer>();

        for (char c : string.toCharArray()) {
             frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < string.length(); i++) {
            //if it is first character with frequency count as 1
            if (frequencies.get(string.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstNonRepeatingCharacter().firstNonRepeatingCharacter("abcdcaf"));
    }
}
