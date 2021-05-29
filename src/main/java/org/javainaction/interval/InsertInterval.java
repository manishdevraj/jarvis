package org.javainaction.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct
 * position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.
 *
 * Example 1:
 *
 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
 * Output: [[1,3], [4,7], [8,12]]
 * Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].
 * Example 2:
 *
 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
 * Output: [[1,3], [4,12]]
 * Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].
 * Example 3:
 *
 * Input: Intervals=[[2,3],[5,7]], New Interval=[1,4]
 * Output: [[1,4], [5,7]]
 * Explanation: After insertion, since [1,4] overlaps with [2,3], we merged them into one [1,4].
 */

public class InsertInterval {

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" +
                    "start=" + start +
                    ", end=" + end +
                    ']';
        }
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> mergedIntervals = new ArrayList<>();

        int i = 0;

        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            mergedIntervals.add(intervals.get(i++));

        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            i++;
        }

        mergedIntervals.add(newInterval);

        while (i < intervals.size())
            mergedIntervals.add(intervals.get(i++));

        return mergedIntervals;
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]){
            result.add(intervals[i]);
            i++;
        }

        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            // we could mutate newInterval here also
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // add the union of intervals we got
        result.add(newInterval);

        // add all the rest
        while (i < intervals.length){
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);

    }

    public static void main(String[] args) {
        List<Interval> input = Arrays.asList(new Interval(1, 3), new Interval(5, 7), new Interval(8, 12));
        System.out.println("{[1, 3], [5, 7], [8, 12]} after inserting the new interval [4, 6]: ");
        InsertInterval.insert(input, new Interval(4, 6)).forEach(System.out::println);

        input = Arrays.asList(new Interval(1, 3), new Interval(5, 7), new Interval(8, 12));
        System.out.println("{[1, 3], [5, 7], [8, 12]} after inserting the new interval [4, 10]: ");
        InsertInterval.insert(input, new Interval(4, 10)).forEach(System.out::println);

        input = Arrays.asList(new Interval(2, 3), new Interval(5, 7));
        System.out.println("{[2, 3], [5, 7]} after inserting the new interval [1, 4]: ");
        InsertInterval.insert(input, new Interval(1, 4)).forEach(System.out::println);

        var interval = new int[][]{{1, 3}, {6, 9}};
        System.out.println("{[1, 3], [6, 9]} after inserting the new interval [2, 5]: ");
        var output = insert(interval, new int[]{2, 5});
        System.out.println(Arrays.deepToString(output).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        var interval2 = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        System.out.println("{ {1,2}, {3,5}, {6,7}, {8,10}, {12,16} } after inserting the new interval [4, 8]: ");
        output = insert(interval2, new int[]{4, 8});
        System.out.println(Arrays.deepToString(output).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        var interval3 = new int[][]{{1, 5}};
        System.out.println("{[1, 5]} after inserting the new interval [6, 8]: ");
        output = insert(interval3, new int[]{6, 8});
        System.out.println(Arrays.deepToString(output).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));


    }
}
