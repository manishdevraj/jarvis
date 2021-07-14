package org.javainaction.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window in s which will
 * contain all the characters in t. If there is no such window in s that covers all characters in t,
 * return the empty string "".
 *
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window
 * in s. Also not that this should work for duplicate characters in pattern
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * @see MinimumWindowSubstring where pattern has unique characters vs pattern here can have duplicate character
 * @see org.javainaction.string.SmallestSubStringContaining
 */
public class MinWindowSubString {
    public static String minWindow(String s, String t) {
        // corner case
        if(s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";

        // construct model
        int minLeft = 0;
        int minRight = 0;
        int min = s.length() + 1;
        boolean flag = false;

        Map<Character, Integer> map = new HashMap<>();
        int count = t.length(); // the number of characters that I need to match
        for(char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

        // unfixed sliding window, 2 pointers
        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++){
            char rightChar = s.charAt(windowEnd);
            if(map.containsKey(rightChar)){
                map.put(rightChar, map.get(rightChar) - 1);
                if(map.get(rightChar) >= 0) count--; // if still unmatched characters, then count--
            }

            // if found a substring
            while(count == 0 && windowStart <= windowEnd){
                int curLen = windowEnd - windowStart + 1;
                if(curLen <= min){
                    minLeft = windowStart;
                    minRight = windowEnd;
                    min = curLen;
                }
                // shrink left pointer
                char leftChar = s.charAt(windowStart++);
                if(map.containsKey(leftChar)){
                    map.put(leftChar, map.get(leftChar) + 1);
                    if(map.get(leftChar) >= 1) count++;
                }
            }
        }

        return min > s.length() ? "" : s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        System.out.println("Min window between { ADOBECODEBANC, ABC } : " + minWindow("ADOBECODEBANC","ABC"));
        System.out.println("Min window between { a, a } : " + minWindow("a","a"));
        System.out.println("Min window between { bdab, ab } : " + minWindow("bdab","ab"));
        System.out.println("Min window between { dcbefebce, fd } : " + minWindow("dcbefebce","fd"));
        System.out.println("Min window between {abcd$ef$axb$c$, $$abf} :" + minWindow("abcd$ef$axb$c$", "$$abf"));
        System.out.println("Min window between { adcad, aca } :" + minWindow("adcad", "aca"));
    }
}
