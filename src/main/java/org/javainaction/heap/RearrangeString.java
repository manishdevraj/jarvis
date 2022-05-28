package org.javainaction.heap;

import java.util.*;

/**
 * Given a string, find if its letters can be rearranged in such a way that no two same characters come next to 
 * each other.
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
 * @see RearrangeStringKDistanceApart where we had to use queue to make it K distance apart
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
            //as we are not done with this item yet
            if (previousEntry != null && previousEntry.getValue() > 0) maxHeap.offer(previousEntry);
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            //keep this for next iteration so we can add back to heap
            //in normal circumstances we would have added the item into heap now but if frequencies are matching
            //we cannot guarantee which letter will be popped out we need uniqueness so we add it to him 
            //for next iteration that way we get chance of having unique
            //[P:3],[A:2] => now when p is appended and we have [P:2] and [A:2] if we add P now 
            //we cannot say which one will come out P or A, so we let second iteration happen and then add P
            //so we would have [P:2] and [A:1]
            previousEntry = currentEntry;
        }

        // if we were successful in appending all the characters to the result string, return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }

    public static String reorganizeString(String str) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
                (e1, e2) -> e2.getValue() - e1.getValue());

        // add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entrySet());

        //queue acts as a previous pointers with their order retained
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        int K = 2; //we need alternate elements at the best
        StringBuilder resultString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            queue.offer(currentEntry);


            //when we reach comfortable distance then we poll one element of queue and add back to heap to keep 
            //distance 
            //[M:2][P:2] so we add say M to result now [M:1][P:2]
            //we do not add M to heap yet as we need to reach k distances before we can add
            //when P is appended our string becomes MP and now can add M back to heap as we reached K distance from
            //first occurrence
            if (queue.size() == K) {
                Map.Entry<Character, Integer> prevEntry = queue.poll();
                if (prevEntry.getValue() > 0) maxHeap.add(prevEntry);
            }
        }

        // if we were successful in appending all the characters to the result string, return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("aappp rearranged string: " + rearrangeString("aappp"));
        System.out.println("Programming rearranged string: " + rearrangeString("Programming"));
        System.out.println("aapa rearranged string: " + rearrangeString("aapa"));
        System.out.println("aaab rearranged string: " + rearrangeString("aaab"));

        System.out.println("aappp rearranged string: " + reorganizeString("aappp"));
        System.out.println("Programming rearranged string: " + reorganizeString("Programming"));
        System.out.println("aapa rearranged string: " + reorganizeString("aapa"));
        System.out.println("aaab rearranged string: " + reorganizeString("aaab"));
    }
}
