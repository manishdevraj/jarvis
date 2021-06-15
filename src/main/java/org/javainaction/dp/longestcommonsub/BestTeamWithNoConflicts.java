package org.javainaction.dp.longestcommonsub;

import java.util.Arrays;

/**
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest
 * overall score. The score of the team is the sum of scores of all the players in the team.
 *
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly
 * higher score than an older player. A conflict does not occur between players of the same age.
 *
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player,
 * respectively, return the highest overall score of all possible basketball teams.
 *
 *
 *
 * Example 1:
 *
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 * Example 2:
 *
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
 * Example 3:
 *
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players.
 */
public class BestTeamWithNoConflicts {
    public static int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] players = new int[n][2];
        for (int i = 0; i < n; i++) {
            players[i][0] = ages[i];
            players[i][1] = scores[i];
        }
        //sort the players by their age, if their age is same, sort them by score
        Arrays.sort(players, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] bestScores = new int[n];
        //first player's score is best score
        bestScores[0] = players[0][1];
        int maxScore = bestScores[0];
        //first player's score is max score
        for (int i = 1; i < n; i++) {
            bestScores[i] = players[i][1];
            //check all previous player
            for (int j = 0; j < i; j++) {
                //if previous player score is less than or equal to current player
                if (players[j][1] <= players[i][1]) {
                    bestScores[i] = Math.max(bestScores[i], bestScores[j] + players[i][1]);
                }
            }
            //store the maximum score at this point
            maxScore = Math.max(maxScore, bestScores[i]);
        }
        return maxScore;
    }

    public static void main(String[] args) {
        System.out.println(bestTeamScore(new int[]{1,3,5,10,15}, new int[]{1,2,3,4,5}));
        System.out.println(bestTeamScore(new int[]{4,5,6,5}, new int[]{2,1,2,1}));
        System.out.println(bestTeamScore(new int[]{1,2,3,5}, new int[]{8,9,10,1}));

       // [319776,611683,835240,602298,430007,574,142444,858606,734364,896074]
//[1,1,1,1,1,1,1,1,1,1]
    }
}
