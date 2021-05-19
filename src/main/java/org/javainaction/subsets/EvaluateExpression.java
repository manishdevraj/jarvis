package org.javainaction.subsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

    public List<Integer> diffWaysToEvaluateExpression(String input) {
        if (map.containsKey(input)) return map.get(input);
        List<Integer> result = new ArrayList<>();

        if (!input.contains("*") && !input.contains("-") && !input.contains("+")) {
            result.add(Integer.parseInt(input));
        } else {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (!Character.isDigit(c)) {
                    List<Integer> leftValues = diffWaysToEvaluateExpression(input.substring(0, i));
                    List<Integer> rightValues = diffWaysToEvaluateExpression(input.substring(i + 1));
                    for (int left : leftValues) {
                        for (int right : rightValues) {
                            if (c == '*') result.add(left * right);
                            else if (c == '-') result.add(left - right);
                            else if (c == '+') result.add(left + right);
                        }
                    }
                }
            }
        }
        map.put(input, result);
        return result;
    }

    public static void main(String[] args) {
        EvaluateExpression ee = new EvaluateExpression();
        List<Integer> result = ee.diffWaysToEvaluateExpression("1+2*3");
        System.out.println("Expression evaluations: " + result);

        ee = new EvaluateExpression();
        result = ee.diffWaysToEvaluateExpression("2*3-4-5");
        System.out.println("Expression evaluations: " + result);
    }
}
