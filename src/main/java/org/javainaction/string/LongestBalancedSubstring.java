package org.javainaction.string;

import java.util.Stack;

/**
 * Return longest balanced substring of a string when there contains only ( and ) brackets
 *
 * Input: "(()))("
 * Output: 4 as (())
 *
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 */
public class LongestBalancedSubstring {
    //O(n) time | O(1) space
    public int longestBalancedSubstring(String string) {
        int maxLength = 0;
        int opening = 0;
        int closing = 0;
        //we need to go from left to right where balance is when we have
        //opening matching with a closed matched
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == '(') opening++;
            else closing++;

            //if we got some match
            if (opening == closing)
                maxLength = Math.max(maxLength, closing * 2);
            else if (closing > opening) {
                opening = 0;
                closing = 0;
            }
        }

        opening = 0;
        closing = 0;
        //we need to go from right to left where balance is when we have
        //closing matching with a open matched
        for (int i = string.length() - 1; i >= 0; i--) {
            char c = string.charAt(i);

            if (c == '(') opening++;
            else closing++;

            //if we got some match
            if (opening == closing)
                maxLength = Math.max(maxLength, opening * 2);
            else if (opening > closing) {
                opening = 0;
                closing = 0;
            }
        }

        return maxLength;
    }

    //O(n) time | O(n) space
    public int longestBalancedSubstringStack(String string) {
        int maxLength = 0;
        var openingStack = new Stack<Integer>();
        //to count first opening parenthesis
        openingStack.push(-1);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                openingStack.push(i);
            } else {
                //pop out
                openingStack.pop();
                if (openingStack.size() == 0) openingStack.push(i);
                else {
                    //if we have an element means distance between current index and last opening bracket index
                    //is our balanced length
                    int currLength = i - openingStack.peek();
                    maxLength = Math.max(maxLength, currLength);
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        var input = "(()))(";
        var expected = 4;
        var actual = new LongestBalancedSubstring().longestBalancedSubstring(input);
        System.out.println(actual);
        actual = new LongestBalancedSubstring().longestBalancedSubstringStack(input);
        System.out.println(actual);
    }
}
