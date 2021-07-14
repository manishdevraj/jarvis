package org.javainaction.dp;

import java.util.Arrays;

/**
 * Given a 2D array with 1 representing block and 0 with free space, if we put water from a source column at 0th row
 * we need to find percentage of water accumulated at bottom row (imaginary buckets)
 *
 *                          {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
 *                         {1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
 *                         {0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0},
 *                         {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
 *                         {1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0},
 *                         {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0},
 *                         {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
 *
 *                Output {0.0, 0.0, 0.0, 25.0, 25.0, 0.0, 0.0}
 *
 * 1. Water can get trapped if there is no way to move down
 * 2. Water only move downwards
 * 3. Everytime water hits block it is split into half
 * 4. Return water in percentage
 */
public class WaterfallStreams {

    // O(w^2 * h) time | O(w) space where w and h are width and height of array
    public double[] waterfallStreams(double[][] array, int source) {
        double[] rowAbove = array[0];
        //we will use -1 to indicate water since the 1 is used for block
        rowAbove[source] = -1;

        for (int row = 1; row < array.length; row++) {

            double[] currentRow = array[row];

            for (int i = 0; i < rowAbove.length; i++) {
                double valueAbove = rowAbove[i];

                boolean hasWaterAbove = valueAbove < 0;
                boolean hasBlock = currentRow[i] == 1.0;

                if (!hasWaterAbove) continue;

                if(!hasBlock) {
                    //if there is no block then current col will have same water as above
                    currentRow[i] += valueAbove;
                    continue;
                }

                double splitWater = valueAbove / 2;
                //move water to right
                int right = i;
                while (right + 1 < rowAbove.length) {
                    right++;
                    if (rowAbove[right] == 1.0) break; // if we have block
                    if (currentRow[right] != 1.0) {
                        currentRow[right] += splitWater;
                        break;
                    }
                }

                //move water to left
                int left = i;
                while (left - 1 >= 0) {
                    left--;
                    if (rowAbove[left] == 1.0) break; // if we have block
                    if (currentRow[left] != 1.0) {
                        currentRow[left] += splitWater;
                        break;
                    }
                }
            }

            rowAbove = currentRow;
        }

        double[] finalPercentage = new double[rowAbove.length];

        for (int i = 0; i < rowAbove.length; i++) {
            double num = rowAbove[i];
            if (num == 0) finalPercentage[i] = num;
            else finalPercentage[i] = num * -100;
        }

        return finalPercentage;
    }

    public static void main(String[] args) {
        double[][] array =
                new double[][] {
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                        {1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                        {0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0},
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                        {1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0},
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0},
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                };
        var source = 3;
        double[] expected = {0.0, 0.0, 0.0, 25.0, 25.0, 0.0, 0.0};
        double[] actual = new WaterfallStreams().waterfallStreams(array, source);
        System.out.println(Arrays.deepToString(array));
        System.out.println("----------------------");
        System.out.println(Arrays.toString(actual));
    }
}
