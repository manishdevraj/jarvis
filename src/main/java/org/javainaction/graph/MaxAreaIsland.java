package org.javainaction.graph;

import java.util.Arrays;
import java.util.List;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid =
 * [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 */
public class MaxAreaIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, findMaxArea(grid, i, j));
                }
            }
        }

        return maxArea;
    }

    public static int findMaxArea(int[][] grid, int i, int j) {
        //all edge cases
        if (i >=0 && i < grid.length
                && j >=0 && j < grid[0].length
                && grid[i][j] == 1) {

            //we have done in place visited marked this can be also changed back to
            // visited[i][j] = true and don't traverse those are visited in if condition
            grid[i][j] = 0;

            //maximum area of island is itself + area from all its neighbours from (top, bottom, left, right)
            return 1 + findMaxArea (grid, i - 1, j) + findMaxArea (grid, i + 1, j)
                    + findMaxArea (grid, i, j - 1) + findMaxArea (grid, i, j + 1);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] input = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        //int expected = 6;
        int output = maxAreaOfIsland(input);
        System.out.println(Arrays.deepToString(input));
        System.out.println("Max island size : " + output);
    }
}
