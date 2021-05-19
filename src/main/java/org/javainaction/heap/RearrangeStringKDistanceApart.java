package org.javainaction.heap;
import java.util.*;

/**
 * Given a string and a number ‘K’, find if the string can be rearranged such that the same characters are at least ‘K’ distance apart from each other.
 *
 * Example 1:
 *
 * Input: "mmpp", K=2
 * Output: "mpmp" or "pmpm"
 * Explanation: All same characters are 2 distance apart.
 * Example 2:
 *
 * Input: "Programming", K=3
 * Output: "rgmPrgmiano" or "gmringmrPoa" or "gmrPagimnor" and a few more
 * Explanation: All same characters are 3 distance apart.
 * Example 3:
 *
 * Input: "aab", K=2
 * Output: "aba"
 * Explanation: All same characters are 2 distance apart.
 * Example 4:
 *
 * Input: "aappa", K=3
 * Output: ""
 * Explanation: We cannot find an arrangement of the string where any two 'a' are 3 distance apart.
 */
public class RearrangeStringKDistanceApart {
    public static String reorganizeString(String str, int k) {
        if (k <= 1)
            return str;

        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
                (e1, e2) -> e2.getValue() - e1.getValue());

        // add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entrySet());

        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        StringBuilder resultString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            queue.offer(currentEntry);
            if (queue.size() == k) {
                Map.Entry<Character, Integer> entry = queue.poll();
                if (entry.getValue() > 0)
                    maxHeap.add(entry);
            }
        }

        // if we were successful in appending all the characters to the result string, return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("mmpp", 2));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("Programming", 3));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("aab", 2));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("aappa", 3));
    }
}
