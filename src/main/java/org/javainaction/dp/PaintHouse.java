package org.javainaction.dp;

public class PaintHouse {
    /**
     * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
     * The cost of painting each house with a certain color is different. You have to paint all the houses such that
     * no two adjacent houses have the same color.
     *
     * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
     * For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting
     * house 1 with color green, and so on... Find the minimum cost to paint all houses
     * @param args
     */
    public static void main(String[] args){
        int[][] costs = new int[][] {
                {4, 0, 3},
                {8, 3, 8},
                {4, 5, 0},
                {3, 4, 4},
                {8, 8, 0}};
        System.out.println(minCost(costs));
    }

    //O(n) time | O(1) space
    public static int minCost(int[][] cost) {
        if (cost == null || cost.length == 0) return 0;

        for (int i = 1; i < cost.length; i++) {
            cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
            cost[i][1] += Math.min(cost[i - 1][0], cost[i - 1][2]);
            cost[i][2] += Math.min(cost[i - 1][1], cost[i - 1][0]);
        }
        return Math.min(cost[cost.length - 1][0],
                Math.min(cost[cost.length - 1][1],
                        cost[cost.length - 1][2]));
    }
}
