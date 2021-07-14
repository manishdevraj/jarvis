package org.javainaction.graph;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Given a matrix with possibly unequal height, return minimum passes required to convert all negative numbers into
 * all positive numbers.
 *
 * Negative integers can be converted to positive integers only if one or more of its neighbours are positive numbers
 * Neighbours are defined by connected elements horizontally or vertically
 *
 * Note: 0 is neutral integer not positive or negative, it won't help conversion
 *
 * Input: [
 * [0, -1, -3, 2, 0],
 * [1, -2, -5, -1, -3],
 * [3, 0, 0, -4, -1]]
 *
 * Conversion: [
 * [0, 1, 3, 2, 0],
 * [1, 2, 5, 1, 3],
 * [3, 0, 0, 4, 1]]
 *
 * Output min passes : 3
 */

public class MinPassesMatrix {

    private static int minimumPassesOfMatrix(int[][] matrix) {
        Queue<Integer[]> positiveQueue = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    positiveQueue.add(new Integer[]{i, j});
                }
            }
        }

        //get all pairs of neighbours
        BiFunction<Integer, Integer, List<Integer[]>> neighboursFunction =
                (i, j) -> {
                    var neighbours = new ArrayList<Integer[]>();
                    if (i > 0) neighbours.add(new Integer[]{i - 1, j});
                    if (j > 0) neighbours.add(new Integer[]{i, j - 1});
                    if (i < matrix.length - 1) neighbours.add(new Integer[]{i + 1, j});
                    if (j < matrix[i].length - 1) neighbours.add(new Integer[]{i, j + 1});
                    return neighbours;
                };

        int minPasses = 0;

        while (!positiveQueue.isEmpty()) {
            int currentSize = positiveQueue.size();
            //this is to count the passes
            while (currentSize > 0) {
                Integer[] pos = positiveQueue.poll();
                if (pos != null) {
                    neighboursFunction.apply(pos[0], pos[1])
                            .stream()
                            .filter(pair -> !positiveQueue.contains(pair)) //only go for ones not already positive
                            .filter(pair -> matrix[pair[0]][pair[1]] < 0) //only for negative values elements
                            .forEach(pair -> {
                                matrix[pair[0]][pair[1]] *= -1; //we are sure that all numbers negative
                                positiveQueue.offer(pair); //add for future pass
                            });
                    currentSize--;
                }
            }
            //one round of positive numbers done
            minPasses++;
        }

        //check if all are negative
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < 0) {
                    return -1;
                }
            }
        }
        //we need to deduct 1 as we will have queue one time extra for N-1 pass
        //everything will be +ve but we cannot tell that without trying all values in
        //queue
        return minPasses - 1;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][] {{0, -1, -3, 2, 0}, {1, -2, -5, -1, -3}, {3, 0, 0, -4, -1}};
        System.out.println(Arrays.deepToString(matrix));
        int expected = 3;
        int actual = minimumPassesOfMatrix(matrix);
        System.out.println(Arrays.deepToString(matrix) + " converted in min passes : " + actual);
    }


}
