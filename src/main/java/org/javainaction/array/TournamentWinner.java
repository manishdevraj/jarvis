package org.javainaction.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Find winning team
 * Competitions [Team A, Team B] where it denotes [Home team, Away team]
 * Results denotes winner {0 , 0, 1} where 1 denotes home team won and 0 denotes away team won
 * Find winner with maximum points won, where winner gains 3 points and looser team gets 0 points.
 * There are no ties
 *
 */
public class TournamentWinner {
    public static final int POINTS = 3;
    public static final int HOME_TEAM = 1;

    public static String tournamentWinner(
            ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        Map<String, Integer> winnerTeams = new HashMap<>();

        //base case
        String currentBestTeam = "";
        winnerTeams.put(currentBestTeam, 0);


        // idea is we keep map of which team won and their score
        // every time we have winner we check if it is best than last winner
        for(int i = 0; i < competitions.size(); i++) {
            String winner = results.get(i) == HOME_TEAM ? competitions.get(i).get(0) :
                    competitions.get(i).get(1);

            winnerTeams.put(winner, winnerTeams.getOrDefault(winner, 0) + POINTS);

            //track best winner at each winning point
            if (winnerTeams.get(winner) > winnerTeams.get(currentBestTeam)) {
                currentBestTeam = winner;
            }
        }
        return currentBestTeam;
    }

    public static void main(String[] args) {
        var competitions = new ArrayList<ArrayList<String>>();
        ArrayList<String> competition1 = new ArrayList<String>(Arrays.asList("HTML", "C#"));
        ArrayList<String> competition2 = new ArrayList<String>(Arrays.asList("C#", "Python"));
        ArrayList<String> competition3 = new ArrayList<String>(Arrays.asList("Python", "HTML"));
        competitions.add(competition1);
        competitions.add(competition2);
        competitions.add(competition3);

        ArrayList<Integer> results = new ArrayList<Integer>(Arrays.asList(0, 0, 1));
        String expected = "Python";

        System.out.println("Tournament winner is " + tournamentWinner(competitions, results));
    }
}
