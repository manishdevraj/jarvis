package org.javainaction.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a dictionary of words and a string made up of those words (no spaces), return the original sentence in a list.
 * If there is more than one possible reconstruction, return any of them. If there is no possible reconstruction,
 * then return null.
 *
 * For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string "thequickbrownfox",
 * you should return ['the', 'quick', 'brown', 'fox'].
 *
 * Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string "bedbathandbeyond",
 * return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].
 */
public class WordBreakProblem {

    //Try for every combination of words from i to end of the string
    private static List<String> wordBreak(Set<String> wordSet, String str, List<String> output) {
        int left = 0;
        for (int right = left + 1; right <= str.length(); right++) {
            String word = str.substring(left, right);
            if (wordSet.contains(word)) { //if we have found the word
                output.add(word);
                //shrink left, as we have already found word combination between left and right, shrink our window
                //to right
                left = right;
            }
        }
        return output;
    }

    public static void main(String[] arg) {
        String[] words = {"the", "quick", "brown", "fox"};
        String str = "thequickbrownfox";
        Set<String> wordSet = Stream.of(words).collect(Collectors.toSet());
        List<String> output = new ArrayList<>();
        wordBreak(wordSet, str, output);
        System.out.println(output);
    }
}
