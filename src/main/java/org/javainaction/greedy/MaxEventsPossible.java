package org.javainaction.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 *
 * You can attend an event i at any day d where startTimei <= d <= endTimei.
 * Notice that you can only attend one event at any time d.
 *
 * Return the maximum number of events you can attend.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * Example 2:
 *
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 * Example 3:
 *
 * Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * Output: 4
 * Example 4:
 *
 * Input: events = [[1,100000]]
 * Output: 1
 * Example 5:
 *
 * Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * Output: 7
 *
 *
 * Constraints:
 *
 * 1 <= events.length <= 105
 * events[i].length == 2
 * 1 <= startDayi <= endDayi <= 105
 */
public class MaxEventsPossible {
    /**
     * #1. Sort the events based on starting day of the event
     * #2. Now once you have this sorted events, every day check what are the events that can start today
     * #3. for all the events that can be started today, keep their ending time in heap.
     *
     * - Wait why we only need ending times ?
     *
     * i) from today onwards, we already know this event started in the past and all we need to know is when this
     * event will finish
     *
     * ii) Also, another key to this algorithm is being greedy, meaning I want to pick the event which is going to
     * end the soonest.
     *
     * - So how do we find the event which is going to end the soonest?
     *
     * i) brute force way would be to look at all the event's ending time and find the minimum, this is probably ok
     * for 1 day but as we can only attend 1 event a day,
     *
     * we will end up repeating this for every day and that's why we can utilize heap(min heap to be precise) to
     * solve the problem of finding the event with earliest ending time
     *
     * #4. There is one more house cleaning step, the event whose ending time is in the past, we no longer can attend
     * those event
     * #5. Last but very important step, Let's attend the event if any event to attend in the heap.
     */
    public static int maxEvents(int[][] events) {
        if (events == null || events.length == 0) return 0;

        Arrays.sort(events, (a , b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int n = events.length;
        int maxEventCount = 0;
        int i = 0;
        for (int day = 1; day <= 100000; day++ ) {
            // Add new events that can attend on day
            while (i < n && events[i][0] == day) {
                minHeap.add(events[i++][1]);
            }
            // remove older closed events
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }
            // Use day 'd' to attend to the event that closes earlier
            if (!minHeap.isEmpty()) {
                maxEventCount++;
                minHeap.poll();
            }
        }
        return maxEventCount;
    }

    public static void main(String[] args) {
        int[][] events1 = {{1,2}, {2,3}, {3,4}, {1,2}};

        System.out.println();
        Arrays.stream(events1).forEach(e -> { System.out.print(Arrays.toString(e));});
        System.out.println(" : Max events can be attended " + maxEvents(events1));

        int[][] events2 = {{1,2}, {2,3}, {3,4}};
        System.out.println();
        Arrays.stream(events2).forEach(e -> { System.out.print(Arrays.toString(e));});
        System.out.println(" : Max events can be attended " + maxEvents(events2));

        int[][] events3 = {{1,4}, {4,4}, {2,2}, {3,4}, {1, 1}};
        System.out.println();
        Arrays.stream(events3).forEach(e -> { System.out.print(Arrays.toString(e));});
        System.out.println(" : Max events can be attended " + maxEvents(events3));

        int[][] events4 = {{1,100000}};
        System.out.println();
        Arrays.stream(events4).forEach(e -> { System.out.print(Arrays.toString(e));});
        System.out.println(" : Max events can be attended " + maxEvents(events4));

        int[][] events5 = {{1,1}, {1,2}, {1,3}, {1,4}, {1,5}, {1,6}, {1,7}};
        System.out.println();
        Arrays.stream(events5).forEach(e -> { System.out.print(Arrays.toString(e));});
        System.out.println(" : Max events can be attended " + maxEvents(events5));
    }
}
