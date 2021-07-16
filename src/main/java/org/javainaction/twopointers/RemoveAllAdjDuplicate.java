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
        //index with distinct adjacent values
        int i = 0;
        //character array to manipulate in place
        char[] chars = s.toCharArray();

        for (int  j = 0; j < chars.length; j++, i++) {
            //assign ith place with jth character
            chars[i] = chars[j];

            //remember we always increment i even we found duplicate
            //because i = i - 2 makes sure we have distinct adjacent elements from 0 to i so
            //i + 1 makes sure we are trying for new location if we have distinct element
            //abbaca
            //at j = 2 [a, b, b....] i becomes 0 pointing at a
            //at j = 3, i becomes 1 and we assign next letter a at i [a, a, ....] and since they are same make i - 2
            //at j = 4, i becomes 0 and we assign [c,...]
            //at j = 5, i becomes 1 and we assign [c,a] and we reached end of string
            //so we return ca
            if (i > 0 && chars[i - 1] == chars[i]) {
                //go back 2 step if previous character and current character are same to account for adjacent length of 2
                i = i - 2;
            }
        }

        return new String(chars, 0, i);
    }

    public static void main(String[] args){
        System.out.println(removeDuplicates("abbaca"));
        System.out.println(removeDuplicates("azxxzy"));
        System.out.println(removeDuplicates("acaaabbbacdddd"));
        System.out.println(removeDuplicates("mississipie"));
    }
}
