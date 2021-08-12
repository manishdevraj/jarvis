package org.javainaction.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an matrix of size M x N write a function that returns element in spiral order from top left to right and
 * top right to bottom right, bottom right to bottom left and bottom left to top left.
 *
 *         {
 *           {1, 2, 3, 4},
 *           {12, 13, 14, 5},
 *           {11, 16, 15, 6},
 *           {10, 9, 8, 7},
 *         }
 *
 *  Output:
 *  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 *
 */
public class SpiralTraverse {

    //O(n) time | O(n) space where n is total number of elements in the array
    public static List<Integer> spiralTraverse(int[][] array) {
        if (array.length == 0) return new ArrayList<>();

        List<Integer> spiralList = new ArrayList<>();

        int startRow = 0;
        int startCol = 0;
        int endRow = array.length - 1;
        int endCol = array[0].length - 1;

        getSpiralArray(array, startRow, startCol, endRow, endCol, spiralList);

        return spiralList;
    }

    private static void getSpiralArray(int[][] array, int startRow, int startCol, int endRow, int endCol, List<Integer> spiralList) {
        //check bounds
        if (startRow > endRow || startCol > endCol) return;

        //going right hopping on to each columns
        for (int i = startCol; i <= endCol; i++) {
            spiralList.add(array[startRow][i]);
        }

        //at this point we are done with previous row so start from next row
        //going downwards hopping on to each row
        for (int i = startRow + 1; i <= endRow; i++) {
            spiralList.add(array[i][endCol]);
        }

        //at this point we are at the bottom right and ready to move left
        for (int i = endCol - 1; i >= startCol; i--) {
            //we need to only go up to last unvisited start row
            if (startRow == endRow) break;
            spiralList.add(array[endRow][i]);
        }

        //at this point we are at bottom left and ready to move up
        //also node that when we move up we only move just before start row as we already visited startRow in first
        //traversal
        for (int i = endRow - 1; i > startRow; i--) {
            //we need to only go up to last unvisited start column
            if (startCol == endCol) break;
            spiralList.add(array[i][startCol]);
        }

        getSpiralArray(array, startRow + 1, startCol + 1, endRow - 1, endCol - 1, spiralList);
    }

    public static void main(String[] args) {
        var input =
                new int[][] {
                        {1, 2, 3, 4},
                        {12, 13, 14, 5},
                        {11, 16, 15, 6},
                        {10, 9, 8, 7},
                };
        var expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        var actual = spiralTraverse(input);
        System.out.println(Arrays.deepToString(input));
        System.out.println(actual);
    }


}
