package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SolveSudoku {
    //O(n) | O(1) space = 9x9 board
    public ArrayList<ArrayList<Integer>> solveSudoku(ArrayList<ArrayList<Integer>> board) {
        char[][] b = new char[][]{};
        BiFunction<Character[], Character, Boolean> valueMatch
                = (chars, c) -> Arrays.stream(chars).anyMatch( v -> c.equals(v));
        valueMatch.apply(String.valueOf(b[0]).chars().mapToObj(c ->
                (char)c).toArray(Character[]::new),'c');

        solvePartialSudoku(0, 0, board);
        return board;
    }

    public boolean solvePartialSudoku(int row, int col, ArrayList<ArrayList<Integer>> board) {
        int currentRow = row;
        int currentCol = col;
        if (currentCol == board.get(currentRow).size()) {
            currentRow += 1;
            currentCol = 0;
            if (currentRow == board.size()) {
                return true;// we got a solved board
            }
        }

        if (board.get(currentRow).get(currentCol) == 0) {
            return tryDigitsAtPosition(currentRow, currentCol, board);
        }

        return solvePartialSudoku(currentRow, currentCol + 1, board);
    }

    public boolean tryDigitsAtPosition(int row, int col, ArrayList<ArrayList<Integer>> board){
        for (int digit = 1; digit < 10; digit++) {
            if (isValidAtPosition(digit, row, col, board)) {
                board.get(row).set(col, digit);
                if (solvePartialSudoku(row, col + 1, board)) {
                    return true;
                }
            }
        }

        board.get(row).set(col, 0);
        return false;
    }

    public boolean isValidAtPosition(int value,
                                     int row, int col,
                                     ArrayList<ArrayList<Integer>> board) {

        boolean isRowValid = !board.get(row).contains(value);
        boolean isColValid = true;

        for (int r = 0; r < board.size(); r++) {
            if (board.get(r).get(col) == value) isColValid = false;
        }

        if (!isRowValid || !isColValid) return false;

        int gridRowStart = (row / 3) * 3;
        int gridColStart = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int rowToCheck = gridRowStart + i;
                int colToCheck = gridColStart + j;
                int existingValue = board.get(rowToCheck).get(colToCheck);
                if (existingValue == value) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] inputValues =
                new int[][] {
                        {7, 8, 0, 4, 0, 0, 1, 2, 0},
                        {6, 0, 0, 0, 7, 5, 0, 0, 9},
                        {0, 0, 0, 6, 0, 1, 0, 7, 8},
                        {0, 0, 7, 0, 4, 0, 2, 6, 0},
                        {0, 0, 1, 0, 5, 0, 9, 3, 0},
                        {9, 0, 4, 0, 6, 0, 0, 0, 5},
                        {0, 7, 0, 3, 0, 0, 0, 1, 2},
                        {1, 2, 0, 0, 0, 7, 4, 0, 0},
                        {0, 4, 9, 2, 0, 6, 0, 0, 7}
                };
        int[][] expectedValues =
                new int[][] {
                        {7, 8, 5, 4, 3, 9, 1, 2, 6},
                        {6, 1, 2, 8, 7, 5, 3, 4, 9},
                        {4, 9, 3, 6, 2, 1, 5, 7, 8},
                        {8, 5, 7, 9, 4, 3, 2, 6, 1},
                        {2, 6, 1, 7, 5, 8, 9, 3, 4},
                        {9, 3, 4, 1, 6, 2, 7, 8, 5},
                        {5, 7, 8, 3, 9, 4, 6, 1, 2},
                        {1, 2, 6, 5, 8, 7, 4, 9, 3},
                        {3, 4, 9, 2, 1, 6, 8, 5, 7}
                };

        var input = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < inputValues.length; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < inputValues[i].length; j++) {
                row.add(inputValues[i][j]);
            }
            input.add(row);
        }

        var expected = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < expectedValues.length; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < expectedValues[i].length; j++) {
                row.add(expectedValues[i][j]);
            }
            expected.add(row);
        }

        var actual = new SolveSudoku().solveSudoku(input);
        System.out.println("Solved board ");
        //actual.stream().forEach(System.out::println);

        inputValues =
                new int[][] {
                        {0, 0, 4, 0, 0, 0, 2, 5, 0},
                        {2, 0, 0, 3, 8, 0, 1, 0, 0},
                        {1, 9, 0, 0, 0, 0, 0, 0, 4},
                        {0, 0, 0, 4, 0, 9, 0, 7, 0},
                        {0, 4, 0, 0, 0, 0, 0, 2, 0},
                        {0, 1, 0, 5, 0, 3, 0, 0, 0},
                        {9, 0, 0, 0, 0, 0, 0, 8, 5},
                        {0, 0, 5, 0, 3, 2, 0, 0, 7},
                        {0, 3, 1, 0, 0, 0, 9, 0, 0}
                };

        input = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < inputValues.length; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < inputValues[i].length; j++) {
                row.add(inputValues[i][j]);
            }
            input.add(row);
        }

        actual = new SolveSudoku().solveSudoku(input);
        System.out.println("Solved board ");
        //actual.stream().forEach(System.out::println);


        inputValues =
                new int[][] {
                        {0, 0, 0, 8, 0, 0, 0, 5, 0},
                        {0, 0, 6, 0, 0, 1, 4, 0, 0},
                        {0, 7, 0, 0, 0, 0, 9, 0, 0},
                        {0, 0, 2, 3, 0, 0, 0, 9, 0},
                        {0, 9, 0, 0, 7, 8, 6, 0, 0},
                        {0, 0, 3, 0, 0, 0, 0, 0, 2},
                        {0, 4, 1, 0, 0, 7, 0, 0, 8},
                        {0, 0, 5, 4, 0, 0, 0, 0, 0},
                        {0, 8, 0, 0, 1, 0, 0, 4, 0}
                };

        input = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < inputValues.length; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < inputValues[i].length; j++) {
                row.add(inputValues[i][j]);
            }
            input.add(row);
        }

        actual = new SolveSudoku().solveSudoku(input);
        System.out.println("Solved board ");
        actual.stream().forEach(System.out::println);
    }
}
