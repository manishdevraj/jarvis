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
 */
public class BalanceBraces {
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
}
