package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 */
public class GenerateParenthesesRecursive {
    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<>();
        char[] parenthesesString = new char[2 * num];
        generateValidParenthesesRecursive(num, 0, 0, parenthesesString, 0, result);
        return result;
    }

    private static void generateValidParenthesesRecursive (int num, int openCount, int closeCount,
                                                           char[] parenthesesString, int index, List<String> result) {
        if (openCount == num && closeCount == num) {
            result.add(String.valueOf(parenthesesString));
        } else {
            if (openCount < num) {
                parenthesesString[index] = '(';
                generateValidParenthesesRecursive(num, openCount + 1, closeCount,
                        parenthesesString, index + 1, result);
            }

            if (openCount > closeCount) {
                parenthesesString[index] = ')';
                generateValidParenthesesRecursive(num, openCount, closeCount + 1,
                        parenthesesString, index + 1, result);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("All combinations of balanced parentheses for 2 are: "
                + generateValidParentheses(2));
        System.out.println("All combinations of balanced parentheses for 3 are: "
                + generateValidParentheses(3));
    }
}
