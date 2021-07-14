package org.javainaction.string;

import java.util.*;

/**
 * String where order doesn't matter.
 * Anagram are strings where order does not matter, and are made up of same characters.
 *
 * We are required to find groups of anagram words from list
 */
public class GroupAnagram {
    // O(w * n * log(n)) time | O(wn) space where w is number of words and
    //n is length of longest word
    public static List<List<String>> groupAnagrams(List<String> words) {
        Map<String, List<String>> anagramMap = new HashMap<>();

        for(String word : words) {
            char[] charArray = word.toCharArray();
            //sort the word to know anagram
            Arrays.sort(charArray);
            String key = new String(charArray);

            //find the key if we have keys already then we found anagram for it
            if (anagramMap.containsKey(key)) {
                anagramMap.get(key).add(word);
            } else {
                //this may be the first of its occurrence but we still need to resend result for not anagrams too
                anagramMap.put(key, new ArrayList<>(Collections.singletonList(word)));
            }
        }

        List<List<String>> result = new ArrayList<>(anagramMap.size());
        result.addAll(anagramMap.values());
        return result;
    }

    public static void main(String[] args) {
        var words =
                new ArrayList<>(
                        Arrays.asList("yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"));
        var expected = new ArrayList<List<String>>();
        expected.add(new ArrayList<>(Arrays.asList("yo", "oy")));
        expected.add(new ArrayList<>(Arrays.asList("flop", "olfp")));
        expected.add(new ArrayList<>(Arrays.asList("act", "tac", "cat")));
        expected.add(new ArrayList<>(Arrays.asList("foo")));

        List<List<String>> output = GroupAnagram.groupAnagrams(words);
        for (List<String> innerList : output) {
            Collections.sort(innerList);
        }
        System.out.println(compare(expected, output));
    }

    public static boolean compare(List<List<String>> expected, List<List<String>> output) {
        if (expected.size() != output.size()) return false;

        for (List<String> group : expected) {
            Collections.sort(group);
            if (!output.contains(group)) return false;
        }

        return true;
    }
}
