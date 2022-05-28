package org.javainaction.misc;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * We have list of scores of student but not necessarily in sorted order. You need to fairly score them following below
 * rule.
 * 1. All students must receive at least one reward
 * 2. Any student must receive strictly more rewards than and adjacent student (left or right), with lower score
 * and must receive fewer rewards than adjacent student with higher score.
 *
 * Write a function to return min rewards that you need to give
 * Input: [8, 4, 2, 1, 3, 6, 7, 9, 5]
 *
 * Output: 25
 * [4, 3, 2, 1, 2, 3, 4, 5, 1]
 * @see Candy
 */
public class MinRewards {
    public static int minRewards(int[] scores) {
        // O(n) time | O(n) space
        int[] rewards = new int[scores.length];
        //each student needs at least one reward
        Arrays.fill(rewards, 1);

        //if we naively assign more rewards than previous student when we have higher score, then we might hit into
        //student whose score is not higher, how do we back track our changes?
        //idea is assign more rewards to each student when their score is higher than previous student

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }

        //in next sweep from right to left, when student have higher score than previous student, see if their current
        //score is bigger than lower score student's score + 1, and assign max of both
        //this way we avoid backtracking
        for (int i = scores.length - 2; i >= 0; i--) {
            if (scores[i] > scores[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }

        return IntStream.of(rewards).sum();
    }

    public static void main(String[] args) {
        System.out.println(minRewards(new int[] {8, 4, 2, 1, 3, 6, 7, 9, 5}));
    }
}
