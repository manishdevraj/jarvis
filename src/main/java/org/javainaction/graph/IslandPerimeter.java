package org.javainaction.graph;

import java.util.Arrays;

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0
 * represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
 * and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
 * Determine the perimeter of the island.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 * Example 2:
 *
 * Input: grid = [[1]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0]]
 * Output: 4
 */
public class IslandPerimeter {
    public static int islandPerimeter(int[][] grid) {
        int landPerimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    landPerimeter += traverseLand(grid, i, j, 0);
                }

            }
        }
        return landPerimeter;
    }

    /**
     * the description of this problem implies there may be an "pattern" in calculating the perimeter of the islands.
     * and the pattern is islands * 4 - neighbours * 2, since every adjacent islands made two sides disappeared
     * (as picture below).
     *
     * +--+     +--+                   +--+--+
     * |  |  +  |  |          ->       |     |
     * +--+     +--+                   +--+--+
     *
     * 4 + 4 - ? = 6  -> ? = 2
     *
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public static int traverseLand(int[][] grid, int i, int j, int neighbours) {
        if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++;
        if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++;
        return 4 - neighbours * 2;
    }

    public static void main(String[] args) {
        int[][] input = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        //int expected = 16;
        int output = islandPerimeter(input);
        System.out.println(Arrays.deepToString(input));
        System.out.println("Island Perimeter : " + output);

        input = new int[][]{{1, 0}};
        //int expected = 4;
        output = islandPerimeter(input);
        System.out.println(Arrays.deepToString(input));
        System.out.println("Island Perimeter : " + output);
    }
}
