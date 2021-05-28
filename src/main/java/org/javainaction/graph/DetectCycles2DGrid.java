package org.javainaction.graph;

import java.util.Arrays;

/**
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
 *
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
 *
 * Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
 *
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 * Output: true
 * Explanation: There are two valid cycles shown in different colors in the image below:
 *
 * Example 2:
 *
 *
 *
 * Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 * Output: true
 * Explanation: There is only one valid cycle highlighted in the image below:
 *
 * Example 3:
 *
 *
 *
 * Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
 * Output: false
 */
public class DetectCycles2DGrid {

    private static final int[] DIR_X = {1, -1, 0, 0};
    private static final int[] DIR_Y = {0, 0, 1, -1};

    public static boolean containsCycle(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        boolean isCycle = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (visited[i][j]) {
                    continue;
                }
                char parent = grid[i][j];
                isCycle |= isCycleFromNode(grid, i, j, -1, -1, visited, parent);

            }
        }
        return isCycle;
    }

    public static boolean isCycleFromNode(char[][] grid, int curX, int curY, int lastX, int lastY,
                                   boolean[][] visited,
                                   char origin) {

        visited[curX][curY] = true;
        boolean isCycle = false;
        for (int i = 0; i < 4; ++i) {
            int newX = curX + DIR_X[i];
            int newY = curY + DIR_Y[i];

            if (newX >=0 && newX < grid.length
                    && newY >=0 && newY < grid[newX].length
                    && !(newX == lastX && newY == lastY) //avoid old points
                    && grid[newX][newY] == origin) { //node value has to be same

                if (visited[newX][newY]) return true; //cycle found

                isCycle |= isCycleFromNode(grid, newX, newY, curX, curY, visited, origin);
            }
        }
        return isCycle;
    }

    public static void main(String[] args) {
        char[][] input = new char[][] {{'a','a','a','a'},{'a','b','b','a'},{'a','b','b','a'},{'a','a','a','a'}};
        var actual = containsCycle(input);
        System.out.println(Arrays.deepToString(input)  + " has cycle? : " + actual);

        input = new char[][] {{'c','c','c','a'},{'c','d','c','c'},{'c','c','e','c'},{'f','c','c','c'}};

        actual = containsCycle(input);
        System.out.println(Arrays.deepToString(input)  + " has cycle? : " + actual);

        input = new char[][] {{'a','b','b'},{'b','z','b'},{'b','b','a'}};
        actual = containsCycle(input);
        System.out.println(Arrays.deepToString(input)  + " has cycle? : " + actual);

    }
}
