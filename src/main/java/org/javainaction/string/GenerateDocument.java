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
 *
 */
public class GenerateDocument {
    public boolean generateDocument(String characters, String document) {
        var frequencies = new HashMap<Character, Integer>();

        for (char c : characters.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        for (char c : document.toCharArray()) {
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
