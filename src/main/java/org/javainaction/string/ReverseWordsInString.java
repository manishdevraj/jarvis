package org.javainaction.string;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given a string that might contain spaces you need to return a new string that is reverse words maintain
 * spaces between them.
 *
 * Input: AlgoExpert is the best!"
 * Output: "best! the is AlgoExpert";
 *
 * You are not allowed to use string split or reverse methods but you are allowed to use join method.
 */
public class ReverseWordsInString {
    public String reverseWordsInString(String string) {
        var words = new ArrayList<String>();
        int startOfWord = 0;
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char letter = chars[i];
            if (Character.isSpaceChar(letter)) {
                words.add(string.substring(startOfWord, i));
                startOfWord = i;
            } else if (Character.isSpaceChar(chars[startOfWord])) {
                words.add(" ");
                startOfWord = i;
            }
        }
        words.add(string.substring(startOfWord)); //edge case
        Collections.reverse(words);
        return String.join("", words);
    }

    public String reverseWordsInStringUsingStrinbBuilder(String string) {
        char[] chars = string.toCharArray();
        StringBuilder output = new StringBuilder();
        for (int right = chars.length - 1; right >=0;) {
            if (Character.isSpaceChar(chars[right])) {
                var space = new ArrayList<Character>();
                while (right >= 0 && Character.isSpaceChar(chars[right])) {
                    space.add(chars[right--]);
                }
                space.forEach(output::append);
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
        String input = "AlgoExpert is the best!";
        String expected = "best! the is AlgoExpert";
        String actual = new ReverseWordsInString().reverseWordsInString(input);
        System.out.println(actual);
        actual = new ReverseWordsInString().reverseWordsInStringUsingStrinbBuilder(input);
        System.out.println(actual);
    }
}
