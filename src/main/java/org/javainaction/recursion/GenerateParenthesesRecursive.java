package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
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
 * @see GenerateDivTags
 */
public class GenerateParenthesesRecursive {
    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<>();
        char[] parenthesesString = new char[2 * num]; // we need 2 times both opening and closing to make it valid
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

    public static List<String> generateValidParenthesesV2(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesesRecursive(n, n,"", result);
        return result;
    }

    public static void generateParenthesesRecursive(int openParentheses, int closeParentheses,
                                         String expressionBuilder, List<String> result) {

        if (openParentheses > 0) {
            String newBuilder = expressionBuilder + "(";
            generateParenthesesRecursive(openParentheses - 1, closeParentheses, newBuilder, result);
        }

        if (openParentheses < closeParentheses) {
            String newBuilder = expressionBuilder + ")";
            generateParenthesesRecursive(openParentheses, closeParentheses - 1, newBuilder, result);
        }

        if (closeParentheses == 0) {
            result.add(expressionBuilder);
        }
    }

    public static void main(String[] args) {
        System.out.println("All combinations of balanced parentheses for 2 are: "
                + generateValidParentheses(2));
        System.out.println("All combinations of balanced parentheses for 3 are: "
                + generateValidParentheses(3));

        System.out.println("All combinations of balanced parentheses for 2 are: "
                + generateValidParenthesesV2(2));
        System.out.println("All combinations of balanced parentheses for 3 are: "
                + generateValidParenthesesV2(3));
    }
}
