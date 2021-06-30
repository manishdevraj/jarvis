package org.javainaction.string;

/**
 * Given an arbitrary ransom note string and another string containing letters from all the magazines,
 * write a function that will return true if the ransom note can be constructed from the magazines ; otherwise,
 * it will return false.
 *
 * Each letter in the magazine string can only be used once in your ransom note.
 *
 *
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 *
 * Constraints:
 *
 * You may assume that both strings contain only lowercase letters.
 *
 * @see MinimumCharactersForWords
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] mags = new int[26];
        for (char c : magazine.toCharArray()) {
            mags[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (--mags[c - 'a'] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        var obj = new RansomNote();
        System.out.println(obj.canConstruct("a", "b"));
        System.out.println(obj.canConstruct("aa", "aab"));
    }
}
