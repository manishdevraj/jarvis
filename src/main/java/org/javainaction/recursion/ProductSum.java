package org.javainaction.recursion;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a special array calculate the sum of its products sum
 *
 * [] is depth 1
 * [[]] is depth 2
 *
 * [5, 2, [7, -1], 3, [6, [-13, 8], 4]]
 */
public class ProductSum {
    private static int productSum(List<Object> test) {
        int multiplier  = 1;
        return productSumRecursive(test, multiplier);
    }

    private static int productSumRecursive(List<Object> list, int multiplier) {
        int sum = 0;
        for (Object element : list) {
            if (element instanceof Integer) {
                sum += (Integer) element;
            } else {
                sum += productSumRecursive((List<Object>) element, multiplier + 1);
            }
        }
        return sum * multiplier;
    }

    public static void main(String[] args) {
        List<Object> test =
                new ArrayList<>(
                        Arrays.asList(
                                5,
                                2,
                                new ArrayList<Object>(Arrays.asList(7, -1)),
                                3,
                                new ArrayList<Object>(
                                        Arrays.asList(6, new ArrayList<Object>(Arrays.asList(-13, 8)), 4))));
        System.out.println("[5, 2, [7, -1], 3, [6, [-13, 8], 4]] product sum is " + productSum(test));
    }


}
