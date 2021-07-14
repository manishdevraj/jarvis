package org.javainaction.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Given an array of time intervals (start, end) for classroom lectures (possibly overlapping),
 * find the minimum number of rooms required.
 *
 * For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
 */
public class MinClassroom {
     public static int minClassroomRequired(int[][] lectures){
        int minClassRoom = 0;

        Arrays.sort(lectures, Comparator.comparing((int[] lecture) -> lecture[0]));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int[] lecture : lectures) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek() <= lecture[0])
                priorityQueue.poll();

            priorityQueue.offer(lecture[1]);
            minClassRoom = Math.max(minClassRoom, priorityQueue.size());
        }

        return minClassRoom;
    }

    public static void main(String[] arg) {
        int[][] lectures = {{30, 75}, {0, 50}, {60, 50}};
        System.out.println(minClassroomRequired(lectures));
    }
}
