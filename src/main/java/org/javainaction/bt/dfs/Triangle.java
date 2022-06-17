package org.javainaction.bt.dfs;

import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 *
 *
 * Example 1:
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 *
 * Input: triangle = [[-10]]
 * Output: -10
 *
 *
 * Constraints:
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 *
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */
public class Triangle {
    public static int minimumTotal(List<List<Integer>> triangle) {
        Integer[][] memo = new Integer[triangle.size()][triangle.size()];
        return recursiveDfsMinPathSum(0, 0, memo, triangle);
    }

    private static int recursiveDfsMinPathSum(int level, int index,
                                       Integer[][] memo,
                                       List<List<Integer>> triangle) {
        if (memo[level][index] != null) return memo[level][index];

        if (level >= triangle.size() || index >= triangle.get(level).size()) return 0;

        int pathSum = triangle.get(level).get(index);
        if (level < triangle.size() - 1) {
            pathSum += Math.min(
                    recursiveDfsMinPathSum(level + 1, index, memo, triangle),//current index
                    recursiveDfsMinPathSum(level + 1, index + 1, memo, triangle)); //current index + 1;
        }

        memo[level][index] = pathSum;

        return memo[level][index];
    }
    public static void main(String[] args) {
        System.out.println("[[2],[3,4],[6,5,7],[4,1,8,3]] triangle path sum " + minimumTotal(List.of(
                List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8 ,3))));
        System.out.println("[[-10]] triangle path sum " + minimumTotal(List.of(List.of(-10))));
    }
}
