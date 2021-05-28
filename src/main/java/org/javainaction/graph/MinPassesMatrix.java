package org.javainaction.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

        int minPasses = 0;

        while (!positiveQueue.isEmpty()) {
            int currentSize = positiveQueue.size();
            //this is to count the passes
            while (currentSize > 0) {
                Integer[] pos = positiveQueue.poll();
                int i = pos[0];
                int j = pos[1];

                if ( i > 0) {
                    int sign = matrix[i - 1][j] < 0 ? -1 : 1;
                    matrix[i - 1][j] *= sign;
                    if (sign == -1)
                        positiveQueue.offer(new Integer[]{i - 1, j});
                }
                if (i < matrix.length - 1) {
                    int sign = matrix[i + 1][j] < 0 ? -1 : 1;
                    matrix[i + 1][j] *= sign;
                    if (sign == -1)
                        positiveQueue.offer(new Integer[]{i + 1, j});
                }
                if (j > 0) {
                    int sign = matrix[i][j - 1] < 0 ? -1 : 1;
                    matrix[i][j - 1] *= sign;
                    if (sign == -1 )
                        positiveQueue.offer(new Integer[]{i, j - 1});
                }
                if (j < matrix[i].length - 1) {
                    int sign = matrix[i][j + 1] < 0 ? -1 : 1;
                    matrix[i][j + 1] *= sign;
                    if (sign == -1 )
                        positiveQueue.offer(new Integer[]{i, j + 1});
                }
                currentSize--;
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
