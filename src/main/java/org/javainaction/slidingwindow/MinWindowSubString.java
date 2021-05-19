package org.javainaction.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window in s which will
 * contain all the characters in t. If there is no such window in s that covers all characters in t, return the empty string "".
 *
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 */
public class MinWindowSubString {
    public static void main(String[] args) {
        System.out.println("Min window between { ADOBECODEBANC, ABC } : " + MinWindowSubString.minWindow("ADOBECODEBANC","ABC"));
        System.out.println("Min window between { a, a } : " + MinWindowSubString.minWindow("a","a"));
        System.out.println("Min window between { bdab, ab } : " + MinWindowSubString.minWindow("bdab","ab"));
        System.out.println("Min window between { dcbefebce, fd } : " + MinWindowSubString.minWindow("dcbefebce","fd"));
    }

    public static String minWindow(String s, String t) {
        // corner case
        if(s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";

        // construct model
        int minLeft = 0;
        int minRight = 0;
        int min = s.length();
        boolean flag = false;

        Map<Character, Integer> map = new HashMap<>();
        int count = t.length(); // the number of characters that I need to match
        for(char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

        // unfixed sliding window, 2 pointers
        int i = 0;
        int j = 0;
        while(j < s.length()){
            char c = s.charAt(j);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) >= 0) count--; // if still unmatched characters, then count--
            }

            // if found a substring
            while(count == 0 && i <= j){
                // update global min
                flag = true;
                int curLen = j - i + 1;
                if(curLen <= min){
                    minLeft = i;
                    minRight = j;
                    min = curLen;
                }

                // shrink left pointer
                char leftC = s.charAt(i);
                if(map.containsKey(leftC)){
                    map.put(leftC, map.get(leftC) + 1);
                    if(map.get(leftC) >= 1) count++;
                }
                i++;
            }
            j++;
        }

        return flag ? s.substring(minLeft, minRight + 1): "";
    }
}
