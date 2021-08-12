package org.javainaction.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Given a 2D matrix with size M x N and can be square when M = N written the zigzag output of the given input array
 * [
 * [1, 3, 4, 10],
 * [2, 5, 9, 11],
 * [6, 8, 12, 15],
 * [7, 13, 14, 16]
 *
 * Outtput: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 */
public class ZigzagTraverse {
    //O(n) time | O(n) space where n is total number of elements
    private static List<Integer> zigzagTraverse(List<List<Integer>> array) {
        //check bounds
        if (array == null || array.size() == 0) return new ArrayList<>();

        int height = array.size() - 1;
        int width = array.get(0).size() - 1;

        BiFunction<Integer, Integer, Boolean> isOutOfBounds = (i, j) -> i < 0 || i > height || j < 0 || j > width;

        int row = 0;
        int col = 0;
        boolean isGoingDown = true;
        var result = new ArrayList<Integer>();
        while (!isOutOfBounds.apply(row, col)) {
            //add value
            result.add(array.get(row).get(col));
            if (isGoingDown) {
                if (col == 0 || row == height) {
                    isGoingDown = false;
                    if (row == height) {
                        //we are at the bottom, go one position right
                        col++;
                    } else {
                        //col is 0
                        //we are at the left bounds, just go down straight line
                        row++;
                    }
                } else {
                    //we are going down diagonally backwards
                    row++;
                    col--;
                }
            } else {
                if (row == 0 || col == width) {
                    isGoingDown = true;
                    if (col == width) {
                        //we are right bounds, just go down straight line
                        row++;
                    } else {
                        //row is 0
                        //we are at the top, go one position right
                        col++;
                    }
                } else {
                    //we are going up diagonally forward
                    row--;
                    col++;
                }

            }
        }
        return result;
    }

    public static void main(String[] arg) {
        var test = new ArrayList<List<Integer>>();
        test.add(new ArrayList<Integer>(Arrays.asList(1, 3, 4, 10)));
        test.add(new ArrayList<Integer>(Arrays.asList(2, 5, 9, 11)));
        test.add(new ArrayList<Integer>(Arrays.asList(6, 8, 12, 15)));
        test.add(new ArrayList<Integer>(Arrays.asList(7, 13, 14, 16)));
        var expected =
                new ArrayList<Integer>(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
        test.forEach(System.out::println);
        System.out.println(zigzagTraverse(test));
    }
}
