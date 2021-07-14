package org.javainaction.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string, sort it based on the decreasing frequency of its characters.
 *
 * Example 1:
 *
 * Input: "Programming"
 * Output: "rrggmmPiano"
 * Explanation: 'r', 'g', and 'm' appeared twice, so they need to appear before any other character.
 * Example 2:
 *
 * Input: "abcbab"
 * Output: "bbbaac"
 * Explanation: 'b' appeared three times, 'a' appeared twice, and 'c' appeared only once.
 */
public class FrequencySort {
    public static String sortCharacterByFrequency(String str) {
        if (str == null || str.length() <= 1) return str;

        Map<Character, Integer> characterFrequencyMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            characterFrequencyMap.put(c, characterFrequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue());

        // add all characters to the max heap
        maxHeap.addAll(characterFrequencyMap.entrySet());

        StringBuilder outputBuilder = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()){
            //get each character and it's frequency so we can append it that many times
            Map.Entry<Character, Integer> charToken = maxHeap.poll();
            char c = charToken.getKey();
            int count = charToken.getValue();
            while (count != 0) {
                outputBuilder.append(c);
                count--;
            }
        }
        return outputBuilder.toString();
    }

    public static void main(String[] args) {
        String result = FrequencySort.sortCharacterByFrequency("Programming");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);

        result = FrequencySort.sortCharacterByFrequency("abcbab");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);
    }
}
