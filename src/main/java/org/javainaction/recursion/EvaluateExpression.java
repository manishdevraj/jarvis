package org.javainaction.recursion;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Given an expression containing digits and operations (+, -, *), find all possible ways in which the expression
 * can be evaluated by grouping the numbers and operators using parentheses.
 *
 * Example 1:
 *
 * Input: "1+2*3"
 * Output: 7, 9
 * Explanation: 1+(2*3) => 7 and (1+2)*3 => 9
 * Example 2:
 *
 * Input: "2*3-4-5"
 * Output: 8, -12, 7, -7, -3
 * Explanation: 2*(3-(4-5)) => 8, 2*(3-4-5) => -12, 2*3-(4-5) => 7, 2*(3-4)-5 => -7, (2*3)-4-5 => -3
 */
public class EvaluateExpression {

    // memoization map
    static Map<String, List<Integer>> map = new HashMap<>();

    public static List<Integer> diffWaysToEvaluateExpression(String input) {
        if (map.containsKey(input)) return map.get(input);

        List<Integer> result = new ArrayList<>();
        Function<String, Boolean> operatorExpression = (S) -> S.contains("*") || S.contains("-") || S.contains("+");

        if (!operatorExpression.apply(input)) {
            result.add(Integer.parseInt(input));
        } else {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                //each split at a operator is going to divide expression to be evaluated into two parts
                //until we get to express a (+-*) b , we will have list of integers
                //we use case statement to execute both sides of integers to get result
                if (!Character.isDigit(c)) {
                    List<Integer> leftValues = diffWaysToEvaluateExpression(input.substring(0, i));
                    List<Integer> rightValues = diffWaysToEvaluateExpression(input.substring(i + 1));
                    for (int left : leftValues) {
                        for (int right : rightValues) {
                            switch (c) {
                                case '*' : result.add(left * right); break;
                                case '-' : result.add(left - right); break;
                                case '+' : result.add(left + right); break;
                            }
                        }
                    }
                }
            }
        }
        map.put(input, result);
        return result;
    }

    public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '+' ) {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                for (Integer p1 : part1Ret) {
                    for (Integer p2 :   part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+': c = p1+p2;
                                break;
                            case '-': c = p1-p2;
                                break;
                            case '*': c = p1*p2;
                                break;
                        }
                        ret.add(c);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(input));
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("1+2*3 evaluations: " + diffWaysToEvaluateExpression("1+2*3"));
        System.out.println("2*3-4-5 evaluations: " + diffWaysToEvaluateExpression("2*3-4-5"));

        System.out.println("1+2*3 evaluations: " + diffWaysToCompute("1+2*3"));
        System.out.println("2*3-4-5 evaluations: " + diffWaysToCompute("2*3-4-5"));
    }
}
