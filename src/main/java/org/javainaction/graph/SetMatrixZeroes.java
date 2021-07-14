package org.javainaction.graph;

import java.util.Arrays;
import java.util.List;

/**
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Follow up:
 *
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 *
 *
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */
public class SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {
        boolean colZero = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) colZero = true;
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) continue;
                if (matrix[i][j] == 0) {
                    // store state of zero int that row and column to make it count
                   matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        //reverse so we do not flip everything to zero
        for (int i = matrix.length - 1; i >= 0;  i--) {
            for (int j = matrix[i].length - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (colZero) matrix[i][0] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] input = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        //int[] [[1,0,1],[0,0,0],[1,0,1]]
        System.out.println(Arrays.deepToString(input) );
        setZeroes(input);
        System.out.println("---------------------------");
        System.out.println(Arrays.deepToString(input) );

        System.out.println("***************************");
        input = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        //[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
        System.out.println(Arrays.deepToString(input) );
        setZeroes(input);
        System.out.println("---------------------------");
        System.out.println(Arrays.deepToString(input) );
    }
}
