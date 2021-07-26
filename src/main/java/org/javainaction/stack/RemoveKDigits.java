package org.javainaction.stack;

import java.util.Stack;

/**
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer
 * after removing k digits from num.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {
    public static String removeKdigits(String num, int k) {
        //base case
        if (num == null || num.length() == 0 || k >= num.length()) return "0";

        Stack<Character> stack = new Stack<>();
        for (Character c : num.toCharArray()) {
            //remove all numbers that are greater than previous number in stack
            //because we can replace them with smaller num up to k iterations
            while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        //remove numbers if we have few replacements to be made
        //base case such as 2222
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        //construct number
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        //remove 0's from start
        while (sb.length() > 1 && sb.charAt(sb.length() - 1) == '0')
            sb.deleteCharAt(sb.length() - 1);

        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println("1432219 after K = 3 removals : " + removeKdigits("1432219", 3));
        System.out.println("10200 after K = 1 removals : " + removeKdigits("10200", 1));
        System.out.println("10 after K = 2 removals : " + removeKdigits("10", 2));
    }
}
