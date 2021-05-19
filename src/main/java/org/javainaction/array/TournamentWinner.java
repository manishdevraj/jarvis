package org.javainaction.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Find winning team
 * Competitions [Team A, Team B] where it denotes [Home team, Away team]
 * Results denotes winner {0 , 0, 1} where 1 denotes home team won and 0 denotes away team won
 * Find winner with maximum points won, where winner gains 3 points and looser team gets 0 points.
 * There are no ties
 *
 */
public class TournamentWinner {
    public final Integer POINTS = 3;
    public final int HOME_TEAM = 1;

    public String tournamentWinner(
            ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        Map<String, Integer> winnerTeams = new HashMap<>();

        String currentBestTeam = "";
        winnerTeams.put(currentBestTeam, 0);
        for(int i = 0; i < competitions.size(); i++) {
            String winner = results.get(i) == HOME_TEAM ? competitions.get(i).get(0) :
                    competitions.get(i).get(1);
            if (winnerTeams.containsKey(winner)){
                winnerTeams.put(winner, winnerTeams.get(winner) + POINTS);
            } else {
                winnerTeams.put(winner, POINTS);
            }
            if (winnerTeams.get(winner) > winnerTeams.get(currentBestTeam)) {
                currentBestTeam = winner;
            }
        }
        return currentBestTeam;
    }


}
