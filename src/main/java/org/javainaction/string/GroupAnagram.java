package org.javainaction.string;

import java.util.*;

/**
 * String where order doesn't matter.
 */
public class GroupAnagram {
    // O(w * n * log(n)) time | O(wn) space where w is number of words and
    //n is length of longest word
    public static List<List<String>> groupAnagrams(List<String> words) {
        Map<String, List<String>> anagramMap = new HashMap<>();

        for(String word : words) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);

            if (anagramMap.containsKey(key)) {
                anagramMap.get(key).add(word);
            } else {
                anagramMap.put(key, new ArrayList<String>(Arrays.asList(word)));
            }
        }

        List<List<String>> result = new ArrayList<>(anagramMap.size());
        for(List<String> group : anagramMap.values()) {
            result.add(group);
        }
        return result;
    }
}
