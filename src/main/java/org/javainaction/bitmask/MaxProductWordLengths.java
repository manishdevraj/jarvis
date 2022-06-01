package org.javainaction.bitmask;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 * Example 2:
 *
 * Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 * Example 3:
 *
 * Input: words = ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 *
 *
 * Constraints:
 *
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] consists only of lowercase English letters.
 */
public class MaxProductWordLengths {

    public static int maxProduct(String[] words) {
        int maxLength = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (noCommonLetters(words[i], words[j]))
                    maxLength = Math.max(maxLength, words[i].length() * words[j].length());
            }
        }
        return maxLength;
    }

    /**
     * Bitmasks :
     * O
     * (
     * L
     * 1
     * +
     * L
     * 2
     * )
     * O(L
     * 1
     *  +L
     * 2
     *  ) time
     *
     * More elegant and fast solution would be to use bitmasks.
     *
     * Words contain only lower case letters and hence an absence or presence of each letter in a word could be encoded with a bitmask of 26 elements. Let's set bit number 0 equal to 1 if character a is present in the word, and to 0 otherwise. Now bit number 1. Let's set it equal to 1 if character b is present in the word, and to 0 otherwise. And so on and so forth, till the bit number 26 which is equal to 1 if z is present in the word.
     *
     * fig
     *
     * How to set n-th bit? Use standard bitwise trick : n_th_bit = 1 << n.
     * How to compute bitmask for a word? Iterate over the word, letter by letter, compute bit number corresponding to that letter n = (int)ch - (int)'a', and add this n-th bit n_th_bit = 1 << n into bitmask bitmask |= n_th_bit.
     * fig
     *
     * This way one could compute two bitmasks, character by character, in
     * O
     * (
     * L
     * 1
     * +
     * L
     * 2
     * )
     * O(L
     * 1
     *  +L
     * 2
     *  ) time. Then the word comparison itself could be done in one operation and in a constant time.
     */
    public static int bitNumber(char ch) {
        return (int)ch - (int)'a';
    }

    public static boolean noCommonLetters(String s1, String s2) {
        int bitmask1 = 0, bitmask2 = 0;
        for (char ch : s1.toCharArray())
            bitmask1 |= 1 << bitNumber(ch);
        for (char ch : s2.toCharArray())
            bitmask2 |= 1 << bitNumber(ch);

        return (bitmask1 & bitmask2) == 0;
    }

    private boolean noCommonLettersNaive(final String str, final Set<Character> charSet) {
        for (char c : str.toCharArray()) {
            if (charSet.contains(c)) return false;
        }

        return true;
    }

    private Set<Character> prepareSet(final String str) {
        Set<Character> charSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            charSet.add(c);
        }

        return charSet;
    }

    public static void main(String[] args) {
        System.out.println("[\"abcw\",\"baz\",\"foo\",\"bar\",\"xtfn\",\"abcdef\"] = > " + maxProduct(new String[]{
                "abcw","baz","foo","bar","xtfn","abcdef"
        }));
    }
}
