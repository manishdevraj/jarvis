package org.javainaction.twopointers;

/**
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two
 * adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 * Example 2:
 *
 * Input: s = "azxxzy"
 * Output: "ay"
 */
public class RemoveAllAdjDuplicate {
    public static String removeDuplicates(String s) {
        int i = 0;
        char[] chars = s.toCharArray();

        for (int  j = 0; j < chars.length; j++, i++) {
            chars[i] = chars[j];
            if (i > 0 && chars[i - 1] == chars[i]) {
                i = i - 2;
            }
        }

        return new String(chars, 0, i);
    }

    public static void main(String[] args){
        System.out.println(removeDuplicates("abbaca"));
        System.out.println(removeDuplicates("azxxzy"));
    }
}
