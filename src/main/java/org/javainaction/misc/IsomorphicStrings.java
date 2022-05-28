package org.javainaction.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 */
public class IsomorphicStrings {
    public static boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        return transformStringToIndex(s).equals(transformStringToIndex(t));
    }

    public static String transformStringToIndex(String str) {
        Map<Character, Integer> indexMapping = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char srcChar = str.charAt(i);

            if (!indexMapping.containsKey(srcChar)) {
                indexMapping.put(srcChar, i);
            }

            builder.append(indexMapping.get(srcChar));
            builder.append(",");
        }

        return builder.toString();
    }
    public static void main(String[] args) {
        System.out.println("egg and add are isomorphic ? " + isIsomorphic("egg", "add"));
        System.out.println("foo and bar are isomorphic ? " + isIsomorphic("foo", "bar"));
        System.out.println("paper and title are isomorphic ? " + isIsomorphic("paper", "title"));
    }
}
