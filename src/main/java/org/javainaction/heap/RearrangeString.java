package org.javainaction.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string, find if its letters can be rearranged in such a way that no two same characters come next to each other.
 *
 * Example 1:
 *
 * Input: "aappp"
 * Output: "papap"
 * Explanation: In "papap", none of the repeating characters come next to each other.
 * Example 2:
 *
 * Input: "Programming"
 * Output: "rgmrgmPiano" or "gmringmrPoa" or "gmrPagimnor", etc.
 * Explanation: None of the repeating characters come next to each other.
 * Example 3:
 *
 * Input: "aapa"
 * Output: ""
 * Explanation: In all arrangements of "aapa", atleast two 'a' will come together e.g., "apaa", "paaa".
 */
public class RearrangeString {

    public static String rearrangeString(String str) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> e2.getValue() - e1.getValue());

        // add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entrySet());

        Map.Entry<Character, Integer> previousEntry = null;
        StringBuilder resultString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // add the previous entry back in the heap if its frequency is greater than zero
            if (previousEntry != null && previousEntry.getValue() > 0)
                maxHeap.offer(previousEntry);
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            previousEntry = currentEntry;
        }

        // if we were successful in appending all the characters to the result string, return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("aappp rearranged string: " + RearrangeString.rearrangeString("aappp"));
        System.out.println("Programming rearranged string: " + RearrangeString.rearrangeString("Programming"));
        System.out.println("aapa rearranged string: " + RearrangeString.rearrangeString("aapa"));
        System.out.println("aaab rearranged string: " + RearrangeString.rearrangeString("aaab"));
    }
}
