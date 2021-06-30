package org.javainaction.string;

import java.util.HashMap;
import java.util.Map;
/**
 * Given a string find its longest substring without duplication
 * You can assume there can be only one longest substring
 *
 * Example: clementisacap
 * Output: mentisac
 */
public class LongestSubstringWithoutDuplication {
    // O(n) time | O(min(n,a)) space  where a is unique alphabets in string
    public static String longestSubstringWithoutDuplication(String str) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        //single letter could be longest as well
        int[] longest = {0, 1};
        int left = 0;
        for (int right = 0; right < str.length(); right++){
            char c = str.charAt(right);
            //slide the window to biggest of duplicate indices as lower of index will cause duplicate
            if (lastSeen.containsKey(c)) {
                left = Math.max(left, lastSeen.get(c) + 1);
            }
            //store longest indices left and right if they are biggest
            if (longest[1] - longest[0] < right - left + 1) {
                longest = new int[] {left, right + 1};
            }
            lastSeen.put(c, right);
        }
        return str.substring(longest[0], longest[1]);
    }

    public static void main(String[] args) {
        System.out.println(LongestSubstringWithoutDuplication
                .longestSubstringWithoutDuplication("clementisacap").equals("mentisac"));
    }
}
