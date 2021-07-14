package org.javainaction.graph;

import java.util.Arrays;

/**
 * Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation:
 * Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped
 * to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 * Example 2:
 *
 * Input: board = [["X"]]
 * Output: [["X"]]
 * @see RemoveIslands
 */
public class SurroundedRegions {
    public static void solve(char[][] board) {
        boolean[][] zeroesToBorder = new boolean[board.length][board[0].length];
        //mark and sweep
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //idea is to find zeroes on the border and mark them
                boolean isBorder = i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1;
                //skip non border values and skip all X values
                if (!isBorder || board[i][j] == 'X') continue;
                findRegionsOnBorder(board, i, j, zeroesToBorder);
            }
        }

        //fill all non border connected regions with O's to X's
        //notice we are skipping border rows and columns
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if (zeroesToBorder[i][j]) continue;
                //sweep the 'O' that are not zeroes to the border
                if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public static void findRegionsOnBorder(char[][] board, int i, int j, boolean[][] zeroesToBorder) {
        if (i >=0 && i < board.length
                && j >=0 && j < board[i].length
                && !zeroesToBorder[i][j] //if not visited already
                && board[i][j] == 'O') { //and it is zero only
            // Any 'O' that is not on the border and but it is connected to an 'O' on the border
            // will NOT be flipped to 'X'
            zeroesToBorder[i][j] = true;
            findRegionsOnBorder(board, i - 1, j, zeroesToBorder);
            findRegionsOnBorder(board, i + 1, j, zeroesToBorder);
            findRegionsOnBorder(board, i, j - 1, zeroesToBorder);
            findRegionsOnBorder(board, i, j + 1, zeroesToBorder);
        }
    }

    public static void main(String[] args) {
        char [][] input = {
                {'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        System.out.println(Arrays.deepToString(input));
        solve(input);
        System.out.println("Flipping O to X that are surrounded by Xs");
        System.out.println(Arrays.deepToString(input));

        input = new char[][]{
                {'O','O','O','O'},{'O','O','O','O'},{'O','O','O','O'},{'O','O','O','O'}};
        System.out.println(Arrays.deepToString(input));
        solve(input);
        System.out.println("Flipping O to X that are surrounded by Xs");
        System.out.println(Arrays.deepToString(input));

        input = new char[][]{
                {'X','O','X','X'},{'X','O','X','X'},{'X','X','O','X'},{'X','X','X','X'}};
        System.out.println(Arrays.deepToString(input));
        solve(input);
        System.out.println("Flipping O to X that are surrounded by Xs");
        System.out.println(Arrays.deepToString(input));
    }
}
