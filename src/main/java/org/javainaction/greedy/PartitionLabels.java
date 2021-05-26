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
        int anchor = 0;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            windowMax = Math.max(windowMax, charPos[s.charAt(i) - 'a']);
            //we found max window of the partition
            if ( i == windowMax) {
                result.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij")
                + " # of partitions needed for 'ababcbacadefegdehijhklij' ");
    }
}
