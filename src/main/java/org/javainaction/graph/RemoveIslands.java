package org.javainaction.graph;

import java.util.Arrays;

/**
 * You are given a 2D array where 1 represents land and 0 represents water
 * An island is defined with any number of 1's connected horizontally or vertically but not diagonally
 * Also any island is connected to 1 at border (top, left, right and bottom) are not considered to be an island
 *
 * Find all island in 2D array and replace them with 0 in final output
 * @see SurroundedRegions
 */
public class RemoveIslands {

    /**
     * Naive solution could be check each and every place in matrix and traverse DFS to find if they are island
     * and also connected to border 1's
     *
     *
     * Efficient solution is to traverse only 4 borders, if we find 1 then traverse DFS to find all possible 1's connected
     * to such boarder 1's. If found mark 'true' in our auxiliary data structure.
     *
     * @param matrix
     * @return
     */

    //O(w x h) time | O(w x h) space complexity
    //if we want to do it in O(1) space then we could replace boolean with a constant value say '2'
    public static int[][] removeIslands(int[][] matrix) {

        boolean[][] onesToBorder = new boolean[matrix.length][matrix[0].length];
        //mark and sweep
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //mark all 1 on the border
                boolean isBorder = i == 0 || j == 0 || i == matrix.length - 1 || j == matrix[i].length - 1;
                //we need to only traverse an island at the border
                if (!isBorder || matrix[i][j] == 0) continue;
                //DFS to each such border elements
                findOnesToBorder(matrix, i, j, onesToBorder);
            }
        }

        //fill all islands (1's) with 0's
        //notice we are skipping border rows and columns
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if(onesToBorder[i][j]) continue;
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    public static void findOnesToBorder(int[][] matrix, int i, int j,
                                 boolean[][] onesToBorder) {
        if (i >=0 && i < matrix.length
                && j >=0 && j < matrix[i].length
                && !onesToBorder[i][j] // do not visit the one's that are connected to border again
                && matrix[i][j] == 1) {
            // Any '1' that is not on the border and but it is connected to an '1' on the border is not an island
            // so we need to make sure we skip them as a part of our flipping process
            onesToBorder[i][j] = true;

            //traverse each node in 4 directions
            findOnesToBorder(matrix, i - 1, j, onesToBorder);
            findOnesToBorder(matrix, i + 1, j, onesToBorder);
            findOnesToBorder(matrix, i, j - 1, onesToBorder);
            findOnesToBorder(matrix, i, j + 1, onesToBorder);
        }
    }

    public static void main(String[] args) {
        int[][] input =
                new int[][] {
                        {1, 0, 0, 0, 0, 0},
                        {0, 1, 0, 1, 1, 1},
                        {0, 0, 1, 0, 1, 0},
                        {1, 1, 0, 0, 1, 0},
                        {1, 0, 1, 1, 0, 0},
                        {1, 0, 0, 0, 0, 1},
                };
        int[][] expected =
                new int[][] {
                        {1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 1, 1},
                        {0, 0, 0, 0, 1, 0},
                        {1, 1, 0, 0, 1, 0},
                        {1, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0, 1},
                };

        System.out.println(Arrays.deepToString(input));

        int[][] actual = removeIslands(input);

        System.out.println("Out put with wiped out island");
        System.out.println(Arrays.deepToString(actual));

    }
}
