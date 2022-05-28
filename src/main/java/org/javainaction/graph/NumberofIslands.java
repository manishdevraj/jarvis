package org.javainaction.graph;

import java.util.Arrays;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberofIslands {
    public static int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int islands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;
                islands += exploreIslands(grid, i, j, visited);
            }
        }

        return islands;
    }

    public static int exploreIslands(char[][] grid, int i, int j, boolean[][] visited) {

        if (i >= 0 && j >=0
                && i < grid.length
                && j < grid[i].length
                && !visited[i][j]
                && grid[i][j] == '1') {

            visited[i][j] = true;

            exploreIslands(grid, i - 1, j, visited);
            exploreIslands(grid, i + 1, j, visited);
            exploreIslands(grid, i, j - 1, visited);
            exploreIslands(grid, i, j + 1, visited);
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        //int expected = 1;
        int output = numIslands(grid);
        System.out.println(Arrays.deepToString(grid));
        System.out.println("Number of islands : " + output);
    }
}
