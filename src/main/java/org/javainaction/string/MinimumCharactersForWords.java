package org.javainaction.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Given a list of words, return the minimum number of characters that are needed to create every single string.
 *
 * Word may not contain spaces but can have special characters.
 *
 * Input {"this", "that", "did", "deed", "them!", "a"}
 * Output {'t', 't', 'h', 'i', 's', 'a', 'd', 'd', 'e', 'e', 'm', '!'}
 *
 * Remember we cannot reuse same character more than its occurrence meaning -
 * we can create "this" using 't','h','i','s' but now to make "that"
 * we need 't','h','i','s', 'a', 't'
 * See second 't' as we need 2 t in "that" we can use first one from "this" but not reuse same t more than once
 *
 * @see RansomNote
 * @see GenerateDocument
 */
public class MinimumCharactersForWords {
    public char[] minimumCharactersForWords(String[] words) {
        var maxFrequencies = new HashMap<Character, Integer>();

        for (String word : words) {
            //store frequency map for each word

            var frequencies = new HashMap<Character, Integer>();
            for (char c : word.toCharArray()) {
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }

            //user local frequency map to update global frequencies
            frequencies.forEach((key, value) -> {
                if (maxFrequencies.containsKey(key)) {
                    //if we found key in global frequencies, we need to how much of the local frequencies needed
                    //and how much was in global freq, we need to store max of it
                    //like in case of "that" global will have [t: 1] but now since this new word needs 2 t
                    //update [t: 2]
                    maxFrequencies.put(key, Math.max(value, maxFrequencies.get(key)));
                } else {
                    maxFrequencies.put(key, value);
                }
            });
        }
        var output = new ArrayList<Character>();
        maxFrequencies.forEach((key, value) -> {
            for (int i = 0; i < value; i++) output.add(key);
        });

        char[] result = new char[output.size()];
        for (int i = 0; i < output.size(); i++) result[i] = output.get(i);

        return result;
    }

    public static void main(String[] args) {
        String[] words = new String[] {"this", "that", "did", "deed", "them!", "a"};
        char[] expected = new char[] {'t', 't', 'h', 'i', 's', 'a', 'd', 'd', 'e', 'e', 'm', '!'};
        var actual = new MinimumCharactersForWords().minimumCharactersForWords(words);
        System.out.println(Arrays.toString(actual));
    }
}
