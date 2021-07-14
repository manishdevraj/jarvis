package org.javainaction.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as
 * a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 *
 * Example 1:
 *
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 * @see IntervalsIntersection
 */
public class IntervalListIntersection {

    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0)
            return new int[][] {};

        int i = 0;
        int j = 0;
        int m = A.length;
        int n = B.length;
        List<int[]> intervals = new ArrayList<>();

        while (i < m && j < n) {
            // Let's check if A[i] intersects B[j].
            // startMax - the start point of the intersection
            // endMin - the endpoint of the intersection
            int startMax = Math.max(A[i][0], B[j][0]);
            int endMin = Math.min(A[i][1], B[j][1]);
            //this insures that there was an intersection
            if (startMax <= endMin) {
                intervals.add(new int[]{startMax, endMin});
            }
            //move the interval that ends earlier
            if (A[i][1] < B[j][1]) i++;
            else j++;
        }

        return intervals.toArray(new int[intervals.size()][]);
    }

    public static void main(String[] arg) {
        int[][] A = new int[][]{{0,2},{5,10},{13,23},{24,25}};
        int[][] B = new int[][]{{1,5},{8,12},{15,24},{25,26}};
        System.out.println(Arrays.deepToString(intervalIntersection(A, B)));
        //[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
    }
}
