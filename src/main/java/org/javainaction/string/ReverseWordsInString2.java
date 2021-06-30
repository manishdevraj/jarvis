package org.javainaction.string;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * Example 4:
 *
 * Input: s = "  Bob    Loves  Alice   "
 * Output: "Alice Loves Bob"
 * Example 5:
 *
 * Input: s = "Alice does not even like bob"
 * Output: "bob like even not does Alice"
 */
public class ReverseWordsInString2 {
    public String reverseWords(String s) {
        String string = s.trim();
        char[] chars = string.toCharArray();
        StringBuilder output = new StringBuilder();
        for (int right = chars.length - 1; right >=0;) {
            if (Character.isSpaceChar(chars[right])) {
                var space = new ArrayList<Character>();
                while (right >= 0 && Character.isSpaceChar(chars[right])) {
                    space.add(chars[right--]);
                }
                output.append(" ");
            } else {
                var word = new ArrayList<Character>();
                while (right >= 0 && !Character.isSpaceChar(chars[right])) {
                    word.add(chars[right--]);
                }
                Collections.reverse(word);
                word.forEach(output::append);
            }
        }
        return output.toString();
    }

    public static void main(String[] args) {
        String input = "a good   example";
        String expected = "a good   example";
        String actual = new ReverseWordsInString2().reverseWords(input);
        System.out.println(actual);
    }
}
