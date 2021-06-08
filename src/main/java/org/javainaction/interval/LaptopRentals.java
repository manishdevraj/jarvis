package org.javainaction.interval;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list of intervals during which school student needs a laptop that they need to rent out.
 *
 * No two studies can share same laptop during an overlapping period but they can start using as soon as it is released
 * by previous student
 *
 * {0, 2}, {1, 4}, {4, 6}, {0, 4}, {7, 8}, {9, 11}, {3, 10}
 *
 * Sample output: 3
 */
public class LaptopRentals {
    public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
        if (times.size() == 0) {
            return 0;
        }

        List<Integer> startTimes = times.stream()
                .map(interval -> interval.get(0))
                .sorted()
                .collect(Collectors.toList());
        Collections.sort(times, (a, b) -> Integer.compare(a.get(0), b.get(0)));
        int minimumLaptops = 0;

        PriorityQueue<ArrayList<Integer>> minHeap
                = new PriorityQueue<>((a, b) -> Integer.compare(a.get(1), b.get(1)));

        for (ArrayList<Integer> interval : times) {
            while (!minHeap.isEmpty() && interval.get(0) >= minHeap.peek().get(1)) {
                minHeap.poll();
            }
            minHeap.offer(interval);
            minimumLaptops = Math.max(minimumLaptops, minHeap.size());
        }
        return minimumLaptops;
    }

    //O(nlog(n)) time | O(n) space where n is number of times
    public int laptopRentalsWithoutHeap(ArrayList<ArrayList<Integer>> times) {
        if (times.size() == 0) return 0;

        List<Integer> startTimes = times.stream()
                .map(interval -> interval.get(0))
                .sorted()
                .collect(Collectors.toList());

        List<Integer> endTimes = times.stream()
                .map(interval -> interval.get(1))
                .sorted()
                .collect(Collectors.toList());

        int i = 0;
        int j = 0;
        int usedLaptops = 0;

        while (i < startTimes.size()) {
            if (startTimes.get(i) >= endTimes.get(j)) {
                usedLaptops--;
                j++;
            }
            usedLaptops++;
            i++;
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
