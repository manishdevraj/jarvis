package org.javainaction.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string of round, curly, and square open and closing brackets, return whether the brackets are
 * balanced (well-formed).
 *
 * For example, given the string "([])[]({})", you should return true.
 *
 * Given the string "([)]" or "((()", you should return false.
 *
 * A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
 * We consider two brackets to be matching if the first element is an open-bracket, e.g., (, {, or [, and the second bracket is a close-bracket of the same type, e.g., ( and ), [ and ], and { and } are the only pairs of matching brackets.
 * Furthermore, a sequence of brackets is said to be balanced if the following conditions are met:
 * The sequence is empty, or
 * The sequence is composed of two, non-empty, sequences both of which are balanced, or
 * The first and last brackets of the sequence are matching, and the portion of the sequence without the first and last elements is balanced.
 * You are given a string of brackets. Your task is to determine whether each sequence of brackets is balanced. If a string is balanced, return true, otherwise, return false
 * Signature
 * bool isBalanced(String s)
 * Input
 * String s with length between 1 and 1000
 * Output
 * A boolean representing if the string is balanced or not
 * Example 1
 * s = {[()]}
 * output: true
 * Example 2
 * s = {}()
 * output: true
 * Example 3
 * s = {(})
 * output: false
 * Example 4
 * s = )
 * output: false
 * @see org.javainaction.string.LongestBalancedSubstring
 */
public class BalanceBraces {
    public static boolean areBracesBalanced(String input,
                                            String openingBrackets,
                                            String closingBrackets,
                                            Map<Character, Character> matchingBrackets) {

        if (input == null || input.length() == 0) return false;

        Stack<Character> brackets = new Stack<>();

        for (char c : input.toCharArray()) {
            if (openingBrackets.indexOf(c) != -1) {
                brackets.add(c);
            } else if (closingBrackets.indexOf(c) != -1) {
              if (brackets.isEmpty()) return false;
              if (!matchingBrackets.get(c).equals(brackets.pop())) return false;
            }
        }
        return brackets.isEmpty();
    }

    public static void main(String[] args){
        String openingBrackets = "([{";
        String closingBrackets = "}])";

        Map<Character, Character> matchingBrackets = new HashMap<>();
        matchingBrackets.put(')', '(');
        matchingBrackets.put('}', '{');
        matchingBrackets.put(']', '[');

        System.out.println("([])[]({}) is balanced? :  " + areBracesBalanced("([])[]({})",
                openingBrackets, closingBrackets, matchingBrackets));

        System.out.println("([)] is balanced? :  " + areBracesBalanced("([)]",
                openingBrackets, closingBrackets, matchingBrackets));
    }
}
