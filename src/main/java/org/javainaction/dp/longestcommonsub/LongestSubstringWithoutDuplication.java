package org.javainaction.dp.longestcommonsub;

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
        int left = 0;
        for (int right = 0; right < str.length(); right++){
            char rightChar = str.charAt(right);
            // if we have seem character at right then slide the window
            // to maximum of last seen index of right char or left value
            // "cleme" in this case left 0 and right 4 now we could slide the left to 1 or since we know that
            // e is repeated at 2 then slide our window at 2 + 1 which is at m and see if we hve unique string from here
            if(lastSeen.containsKey(rightChar)) {
                left = Math.max(left, lastSeen.get(rightChar) + 1);
            }

            //check if window is long enough typically we would always max between last max and (right - left + 1)
            //but since we need to know the actual string we need to store their indices
            if (longest[1] - longest[0] < right + 1 - left) {
                longest = new int[] {left, right + 1};
            }
            lastSeen.put(rightChar, right);
        }
        return str.substring(longest[0], longest[1]);
    }

    public static void main(String[] args) {
        System.out.println("Longest sunbsting without duplicates "
                + longestSubstringWithoutDuplication("clementisacap"));
    }
}
