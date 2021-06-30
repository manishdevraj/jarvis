package org.javainaction.greedy;

import java.util.Arrays;

/**
 * Given amount time a specific query takes to execute, and we can execute queries in any order find the minimum
 * amount of time to execute all jobs
 *
 * Input: {5, 1, 4}
 * Output : 11 as we can execute query [1, 4, 5] with (0) + (1) + (1 + 4) = 6 time
 */
public class MinimumWaitingTime {
    ///O(nlog(n)) time | O(1) space
    public int minimumWaitingTime(int[] queries) {
        //execute smallest query first
        Arrays.sort(queries);

        int N = queries.length;
        int waitingTime = 0;

        for (int i = 0; i < N; i++) {
            int duration = queries[i];
            int pendingQueries = N - (i + 1);
            waitingTime += duration * pendingQueries;
        }
        return waitingTime;
    }

    public static void main(String[] args) {
        var obj = new MinimumWaitingTime();
        System.out.println(Arrays.toString(new int[] {3, 2, 1, 2, 6})
                + "min waiting time " + obj.minimumWaitingTime(new int[] {3, 2, 1, 2, 6}));

        System.out.println(Arrays.toString(new int[] {1, 4, 5})
                + " min waiting time " + obj.minimumWaitingTime(new int[] {1, 4, 5}));
    }
}
