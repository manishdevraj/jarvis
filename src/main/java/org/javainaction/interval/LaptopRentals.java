package org.javainaction.interval;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list of intervals during which school student needs a laptop that they need to rent out.
 *
 * No two students can share same laptop during an overlapping period but they can start using as soon as it is released
 * by previous student.
 *
 * Find minimum laptops that are needed
 *
 * {0, 2}, {1, 4}, {4, 6}, {0, 4}, {7, 8}, {9, 11}, {3, 10}
 *
 * Sample output: 3
 * @see org.javainaction.heap.MaximumCPULoad
 * @see MinimumMeetingRooms
 */
public class LaptopRentals {
    public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
        if (times.size() == 0) {
            return 0;
        }
        // because input is not sorted and we need linear ordering of usage of laptops
        times.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));

        int minimumLaptops = 0;

        //sorted by end time allows us to find overlapping intervals if any
        PriorityQueue<ArrayList<Integer>> minHeap
                = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));

        for (ArrayList<Integer> interval : times) {
            //when current student need laptop no earlier than previous student end then we can remove
            //need to have unique laptops
            //[0, 2] by previous student and [2, 4] by current then we can give first laptop to current student
            //min heap will contain single element indicating only one is needed between two of these students
            while (!minHeap.isEmpty() && interval.get(0) >= minHeap.peek().get(1)) {
                minHeap.poll();
            }
            minHeap.offer(interval);
            //size of heap denotes overlapping intervals of student usage and so is # of required laptops
            minimumLaptops = Math.max(minimumLaptops, minHeap.size());
        }
        return minimumLaptops;
    }

    //O(nlog(n)) time | O(n) space where n is number of times
    public int laptopRentalsWithoutHeap(ArrayList<ArrayList<Integer>> times) {
        if (times.size() == 0) return 0;
        //keep track of two sorted times
        //start and end times
        List<Integer> startTimes = times.stream()
                .map(interval -> interval.get(0))
                .sorted()
                .collect(Collectors.toList());

        List<Integer> endTimes = times.stream()
                .map(interval -> interval.get(1))
                .sorted()
                .collect(Collectors.toList());

        int start = 0;
        int end = 0;
        int usedLaptops = 0;

        while (start < startTimes.size()) {
            //when current student need laptop no earlier than previous student end then we can remove
            //need to have unique laptops
            //[0, 2] by previous student and [2, 4] by current then we can give first laptop to current student
            //decrement count of used laptop to indicate current occupancy
            if (startTimes.get(start) >= endTimes.get(end)) {
                usedLaptops--;
                end++;
            }
            usedLaptops++;
            start++;
        }

        return usedLaptops;
    }

    public static void main(String[] args) {
        int[][] times = new int[][] {{0, 2}, {1, 4}, {4, 6}, {0, 4}, {7, 8}, {9, 11}, {3, 10}};
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        for (int[] time : times) {
            input.add(new ArrayList(Arrays.asList(time[0], time[1])));
        }
        int expected = 3;
        var actual = new LaptopRentals().laptopRentals(input);
        System.out.println(Arrays.deepToString(times));
        System.out.println("Minimum laptops that are needed : " + actual);

        actual = new LaptopRentals().laptopRentalsWithoutHeap(input);
        System.out.println(Arrays.deepToString(times));
        System.out.println("Minimum laptops that are needed : " + actual);
    }
}
