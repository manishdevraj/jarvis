package org.javainaction.string;

import java.util.HashMap;

/**
 * You are given string "characters" and "document"
 * You are able to generate document using characters string if number of unique characters count in character is
 * greater or equal to unique characters in document.
 *
 * Input: Characters "Bste!hetsi ogEAxpelrt x "
 * Document "AlgoExpert is the Best!"
 *
 * Output true
 * @see FirstNonRepeatingCharacter
 * @see RansomNote
 */
public class GenerateDocument {
    public boolean generateDocument(String characters, String document) {
        var frequencies = new HashMap<Character, Integer>();

        //keep frequencies from characters
        for (char c : characters.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        //if document is not found in characters frequencies meaning we cannot make document from characters
        for (char c : document.toCharArray()) {
            //as we are updating frequencies our check will be also to confirm we have lost the count of frequencies
            if (!frequencies.containsKey(c) || frequencies.get(c) == 0) return false;

            frequencies.put(c, frequencies.get(c) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        String characters = "Bste!hetsi ogEAxpelrt x ";
        String document = "AlgoExpert is the Best!";
        boolean expected = true;
        var actual = new GenerateDocument().generateDocument(characters, document);
        System.out.println(characters + " can be used to create " + document  + " : " + actual);
    }
}
