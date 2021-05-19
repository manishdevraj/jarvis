package org.javainaction.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiFunction;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]

 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 */
public class KClosestPointOrigin {
    public static void main(String[] args) {
        int[][] points = {{1, 3}, {-2, 2}};
        int K = 1;
        int[][] result = kClosestPoint(points, K);
        for(int[] point : result) {
            System.out.println("{ " + point[0] + " , " + point[1] + " }");
        }
    }

    public static int[][] kClosestPoint(int[][] points, int K){
        if(K == 0 || points.length == 0) return points;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>
                ((a,b) -> ((b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])));

        for(int[] point : points) {
            maxHeap.add(point);
            if(maxHeap.size() > K) maxHeap.remove();
        }

        int[][] result = new int[K][];
        while(K-- > 0) {
            result[K] = maxHeap.remove();
        }

        return result;
    }
}