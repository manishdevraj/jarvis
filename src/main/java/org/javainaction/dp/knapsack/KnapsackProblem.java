package org.javainaction.dp.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * You are given two 2 array with items and their weights and total capacity that we need to fit the items with.
 * Your goal is to fit items in your knapsack without having the sum of their weights exceeds the knapsack capacity.
 *
 * write a function to give combined maximum values with the indices of items being picked.
 *
 * items: [[1, 2], [4, 3], [5, 6], [6, 7]]
 * capacity : 10
 *
 * Sample output : [10, [1, 3]] // items [4, 3] and [6, 7]
 */
public class KnapsackProblem {
    // O(nc) time | O(nc) space
    // if w <= capacity
    // values[i][j] = max (values[i-1][j], values [i-1][j-w] + v)
    // else  values[i][j] = values[i-1][j]
    public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
        // Write your code here.
        int[][] knapsackValues = new int[items.length + 1][capacity + 1];
        for(int i = 1; i < items.length + 1; i++) {
            int currentValue = items[i - 1][0];
            int currentWeight = items[i - 1][1];
            for(int c = 0; c < capacity + 1; c++) {
                if (currentWeight > c) {
                    knapsackValues[i][c] = knapsackValues[i-1][c];
                } else {
                    knapsackValues[i][c] = Math.max(
                            knapsackValues[i-1][c],
                            knapsackValues[i-1][c-currentWeight] + currentValue);
                }
            }
        }

        return getKnapsackItems(knapsackValues, items, knapsackValues[items.length][capacity]);
    }

    public static List<List<Integer>> getKnapsackItems(int[][] knapsackValues, int[][] items, int weight){
        List<List<Integer>> sequence = new ArrayList<>();
        List<Integer> totalWeight = new ArrayList<>();
        totalWeight.add(weight);
        sequence.add(totalWeight);
        sequence.add(new ArrayList<>());
        int i = knapsackValues.length - 1;
        int c = knapsackValues[0].length - 1;
        while (i > 0) {
            if (knapsackValues[i][c] == knapsackValues[i - 1][c]) {
                i--;
            } else{
                sequence.get(1).add(0, i - 1);
                c -= items[i - 1][1];
                i--;
            }
            if (c == 0) {
                break;
            }
        }
        return sequence;
    }

    public int solveKnapsack(int[][] items, int capacity) {
        int[] profits = Stream.of(items).mapToInt(a -> a[0]).toArray();
        int[] weights = Stream.of(items).mapToInt(a -> a[1]).toArray();
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return this.knapsackRecursive(dp, profits, weights, capacity, 0);
    }

    private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity,
                                  int currentIndex) {
        if (currentIndex >= profits.length || capacity <= 0) {
            return 0;
        }

        if (dp[currentIndex][capacity] != null)
            return dp[currentIndex][capacity];

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profitIncl = 0;
        if (weights[currentIndex] <= capacity) {
            profitIncl = profits[currentIndex] + knapsackRecursive(dp, profits, weights,
                    capacity - weights[currentIndex],
                    currentIndex + 1);
        }

        // recursive call after excluding the element at the currentIndex
        int profitExcl = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

        dp[currentIndex][capacity] = Math.max(profitIncl, profitExcl);

        return dp[currentIndex][capacity];
    }

    public static void main(String[] args) {
        int[][] input = {{1, 2}, {4, 3}, {5, 6}, {6, 7}};
        int[][] expected = {{10}, {1, 3}};
        System.out.println("Input : " + Arrays.deepToString(input));
        System.out.println("Output " + new KnapsackProblem().knapsackProblem(input, 10));
        System.out.println("Output " + new KnapsackProblem().solveKnapsack(input, 10));
    }
}
