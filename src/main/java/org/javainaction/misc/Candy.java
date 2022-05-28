package org.javainaction.misc;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 *
 *
 * Example 1:
 *
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *
 *
 * Constraints:
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 * @see MinRewards
 */
public class Candy {
    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        // O(n) time | O(n) space
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        //if we naively assign more candies than previous child when we have higher rating, then we might hit into
        //child whose rating is not higher, how do we back track our changes?
        //idea is assign more candies to each student when their rating is higher than previous student

        for (int i = 1; i < candies.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        //in next sweep from right to left, when child have higher rating than previous child, see if their current
        //rating is bigger than lower score child's rating + 1, and assign max of both
        //this way we avoid backtracking
        for (int i = candies.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        return IntStream.of(candies).sum();
    }
    public static void main(String[] args) {
        System.out.println(candy(new int[] {1, 0, 2}));
        System.out.println(candy(new int[] {1, 3, 2, 2, 1}));
    }
}
