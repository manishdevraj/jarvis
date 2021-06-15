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
        int[] longest = {0, 1};
        int startIdx = 0;
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(lastSeen.containsKey(c)) {
                startIdx = Math.max(startIdx, lastSeen.get(c) + 1);
            }
            if (longest[1] - longest[0] < i + 1 - startIdx) {
                longest = new int[] {startIdx, i + 1};
            }
            lastSeen.put(c, i);
        }
        return str.substring(longest[0], longest[1]);
    }
}
