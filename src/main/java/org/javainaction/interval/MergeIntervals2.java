package org.javainaction.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeIntervals2 {
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> mergedIntervals = new ArrayList<>();
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            if (current[0] <= prev[1]) {
                prev = new int[] {Math.min(prev[0], current[0]),  Math.max(prev[1], current[1])};
            } else {
                mergedIntervals.add(prev);
                prev = current;
            }
        }

        mergedIntervals.add(prev);

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        System.out.println("[[1,3],[2,6],[8,10],[15,18]] -> "
                + Arrays.deepToString(merge(new int[][]{{1, 3}, {2,6}, {8, 10}, {15, 18}})));
        System.out.println("[[0,4],[4,5] -> " + Arrays.deepToString(merge(new int[][]{{0, 4}, {4, 5}})));
    }
}
