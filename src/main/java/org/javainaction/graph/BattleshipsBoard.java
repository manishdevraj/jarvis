package org.javainaction.graph;

import java.util.Arrays;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the
 * battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made
 * of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size.
 * At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * Output: 2
 * Example 2:
 *
 * Input: board = [["."]]
 * Output: 0
 */
public class BattleshipsBoard {
    public static int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //if board is '.' we don't have a battleship
                if (board[i][j] == '.') continue;

                //if battleship cannot be in adjacent cell
                //assuming 1 * K ( 1 row and k columns ) size of ship
                //we have already counted ship such as in certain row
                //when we were at i = 1 we counted them so skip i = 2 for 'X'
                //by checking top of board
                // .
                // x
                // x
                // x
                // .
                if (i > 0 && board[i - 1][j] == 'X') continue;

                //if battleship cannot be in adjacent cell
                //assuming K * 1 ( k rows and 1 column ) size of ship
                //we have already counted ship such as in certain column
                //when we were at j = 1 we counted them so skip j = 2 for 'X'
                //by checking left of board
                // . x x x .
                if (j > 0 && board[i][j - 1] == 'X') continue;

                //we don't care of total size of ship but just the count so
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char [][] input = {
                {'X','.','.','X'},
                {'.','.','.','X'},
                {'.','.','.','X'}};
        //int expected = 2;
        int output = countBattleships(input);
        System.out.println(Arrays.deepToString(input));
        System.out.println("Battleships on board : " + output);

        input = new char[][]{{'.'}};
        //int expected = 0;
        output = countBattleships(input);
        System.out.println(Arrays.deepToString(input));
        System.out.println("Battleships on board : " + output);
    }
}
