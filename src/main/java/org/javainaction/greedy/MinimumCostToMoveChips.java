package org.javainaction.greedy;

/**
 * We have n chips, where the position of the ith chip is position[i].
 *
 * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
 *
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 * Return the minimum cost needed to move all the chips to the same position.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: position = [1,2,3]
 * Output: 1
 * Explanation: First step: Move the chip at position 3 to position 1 with cost = 0.
 * Second step: Move the chip at position 2 to position 1 with cost = 1.
 * Total cost is 1.
 * Example 2:
 *
 *
 * Input: position = [2,2,2,3,3]
 * Output: 2
 * Explanation: We can move the two chips at position  3 to position 2. Each move has cost = 1. The total cost = 2.
 * Example 3:
 *
 * Input: position = [1,1000000000]
 * Output: 1
 *
 */
public class MinimumCostToMoveChips {
    public static int minCostToMoveChips(int[] position) {
        int evenCost = 0;
        int oddCost = 0;
        // Notice that we have two types of costs:
        // Costs 0 when moving to position[i] + 2 or position[i] - 2.
        // Costs 1 when moving to position[i] + 1 or position[i] - 1.
        // Since move to position[i] + 2 or position[i] - 2 is free, it is natural to think that firstly moving
        // chips as close as possible, with 0 cost.

        /**
         * As for the final position of those chips pile, there are only two possibilities:
         *
         *   The final position is at the even position, or
         *   The final position is at the odd position.
         *   In the first case, we at least need to cost odd_cnt to move all the chips at the odd positions to the even positions. Similarly, in the second case, we at least need to cost even_cnt.
         *
         *  Therefore, min(even_cnt, odd_cnt) is the smallest possible cost.
         */
        for (int coinPos : position) {
            if (coinPos % 2 == 0) evenCost++;
            else oddCost++;
        }
        return Math.min(evenCost, oddCost);
    }

    public static void main(String[] args) {
        System.out.println("{1, 2, 3} min cost to move chips " + minCostToMoveChips(new int[]{1, 2, 3}));
        System.out.println("{2, 2, 2, 3, 3} min cost to move chips " + minCostToMoveChips(new int[]{2, 2, 3, 3, 3}));
    }
}
