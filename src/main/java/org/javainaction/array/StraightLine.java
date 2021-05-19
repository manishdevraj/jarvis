package org.javainaction.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
 * Check if these points make a straight line in the XY plane.
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 *
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 *
 * Constraints:
 *
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates contains no duplicate point.
 */
public class StraightLine {
    /**
     * The point is if we take points p1(x, y), p2(x1, y1), p3(x3, y3), slopes of any two pairs is same
     * then p1, p2, p3 lies on same line.
     * slope from p1 and p2 is y - y1 / x - x1
     * slope from p2 and p3 is y2 - y1 / x2 - x1
     * if these two slopes equal, then p1, p2, p3 lies on same line.
     */

    public static void main(String[] args) {
        int[][] coordinatesOne = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
        int[][] coordinatesTwo = {{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}};
        System.out.println(Arrays.deepToString(coordinatesOne));
        System.out.println("Is on straight line : " + checkStraightLine(coordinatesOne));
        System.out.println(Arrays.deepToString(coordinatesTwo));
        System.out.println("Is on straight line : " + checkStraightLine(coordinatesTwo));
    }

    public static boolean onLine(int[] p1, int[] p2, int[] p3){
        int x = p1[0], y = p1[1], x1 = p2[0], y1 = p2[1], x2 = p3[0], y2 = p3[1];
        return ((y - y1) * (x2 - x1) == (y2 - y1) * (x - x1));
    }

    public static boolean checkStraightLine(int[][] coordinates) {
        // base case:- there are only two points, return true
        if (coordinates.length == 2) return true;
        // otherwise, check each point lies on line using above equation.

        for(int i=2;i<coordinates.length;i++){
            if(!onLine(coordinates[i], coordinates[0], coordinates[1]))
                return false;
        }
        return true;
    }
}
