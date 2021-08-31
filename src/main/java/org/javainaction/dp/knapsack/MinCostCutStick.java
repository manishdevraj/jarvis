package org.javainaction.dp.knapsack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a wooden stick of length n units. The stick is labelled from 0 to n.
 * For example, a stick of length 6 is labelled as follows:
 *
 *
 * Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
 *
 * You should perform the cuts in order, you can change the order of the cuts as you wish.
 *
 * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
 * When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of
 * the stick before the cut). Please refer to the first example for a better explanation.
 *
 *  Stick
 *  0-----1-----2-----3-----4-----5-----6-----7
 *
 *  cut at 3
 *
 *  0-----1-----2-----3     3-----4-----5-----6-----7
 *
 *  cut at 5
 *
 *  0-----1-----2-----3     3-----4-----5       5-----6-----7
 *
 *  cut at 1
 *
 *  0-----1     1-----2-----3     3-----4-----5       5-----6-----7
 *
 *  cut at 4
 *
 *  0-----1     1-----2-----3     3-----4       4-----5       5-----6-----7
 *
 * Return the minimum total cost of the cuts.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, cuts = [1,3,4,5]
 * Output: 16
 * Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:
 *
 * The first cut is done to a rod of length 7 so the cost is 7.
 * The second cut is done to a rod of length 6 (i.e. the second part of the first cut),
 * the third is done to a rod of length 4 and the last cut is to a rod of length 3.
 * The total cost is 7 + 6 + 4 + 3 = 20.
 * Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16
 * (as shown in the example photo 7 + 4 + 3 + 2 = 16).
 * Example 2:
 *
 * Input: n = 9, cuts = [5,6,1,4,2]
 * Output: 22
 * Explanation: If you try the given cuts ordering the cost will be 25.
 * There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1]
 * has total cost = 22 which is the minimum possible.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^6
 * 1 <= cuts.length <= min(n - 1, 100)
 * 1 <= cuts[i] <= n - 1
 * All the integers in cuts array are distinct.
 */
public class MinCostCutStick {

    /**
     * To make it simpler, we add two sentinel values to cuts - left and right edges of the stick.
     * Then, we sort the cuts so we can easily identify all cuts between two points. DFS can help us find the most
     * efficient sequence of cuts. To avoid re-computation, we memoise the best answer for stick between cuts[i]
     * and cuts[j] in dp[i][j].
     *
     * You can see the first cut at points 1 (or 4) results in total cost of 13 (5 + 0 + 8).
     * If we first cut at point 2 (or 3), the cost will be 12 (5 + 2 + 5).
     */
    public int minCost(int n, int[] cuts) {
        //1 <= cuts.length <= min(n - 1, 100)
        Integer[][] memoize = new Integer[102][102];
        var c = Arrays.stream(cuts).boxed().collect(Collectors.toList());
        c.add(0);
        c.add(n);
        Collections.sort(c);
        return minCostRecursive(memoize, c, 0, c.size() - 1);
    }

    public int minCostRecursive(Integer[][] memoize, List<Integer> cuts, int i, int j) {
        //nothing left to cut
        if (j - i <= 1) return 0;

        if (memoize[i][j] != null) return memoize[i][j];

        memoize[i][j] = Integer.MAX_VALUE;

        for (int k = i + 1; k < j; ++k) {
            //cost is cut at stick length
            int cutCost = cuts.get(j) - cuts.get(i);

            memoize[i][j] = Math.min(memoize[i][j], cutCost
                    +   minCostRecursive(memoize, cuts, i, k)
                    +   minCostRecursive(memoize, cuts, k, j));
        }

        return memoize[i][j];
    }

    public static void main(String[] args) {
        var obj = new MinCostCutStick();
        System.out.println("Minimum cost to cut a stick " + obj.minCost(7, new int[]{1,3,4,5}));
    }
}
