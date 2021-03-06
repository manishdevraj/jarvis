package org.javainaction.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * A string s of lowercase English letters is given. We want to partition this string into as many parts as possible
 * so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 *
 *
 * Note:
 *
 * s will have length in range [1, 500].
 * s will consist of lowercase English letters ('a' to 'z') only.
 */
public class PartitionLabels {
    public static List<Integer> partitionLabels(String s) {
        int[] charPos = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charPos[s.charAt(i) - 'a'] = i;
        }

        int windowMax = 0;
        int left = 0;
        List<Integer> result = new ArrayList<>();

        for (int right = 0; right < s.length(); right++) {
            //window can be extended up to last occurrence of the character being found
            windowMax = Math.max(windowMax, charPos[s.charAt(right) - 'a']);
            //we found max window of the partition
            //we successfully reach at the max window, chop our partition at this point and start counting from next index
            if ( right == windowMax) {
                result.add(right - left + 1);
                left = right + 1;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij")
                + " # of partitions needed for 'ababcbacadefegdehijhklij' ");
    }
}
