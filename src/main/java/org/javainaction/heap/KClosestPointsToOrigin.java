package org.javainaction.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
 * @see KClosestPointOrigin
 */
public class KClosestPointsToOrigin {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * We need to find the distance between two points. The distance between two points on a plane is the Euclidean
         * distance. The formula to find the distance [x1,y1] and [x2,y2] is
         * in our case since we are computing distance form origin [x1, y1] always would be (0,0)
         *
         * distance = sqrt((y2 — y1) * (y2 — y1) + (x2 — x1) * (x2 — x1))
         *
         * distance = sqrt((y2 — 0) * (y2 — 0) + (x2 — 0) * (x2 — 0))
         * Meaning we are computing sqrt of [y2 * y2] + [X2 * X2]
         * That means closing point from origin will have smaller square root of [y2 * y2] + [X2 * X2]
         *
         * We sort using this fact such that [b2 * b2] + [b1 * b1] - [a2 * a2] + [a1 * a1], as we are using max heap
         */
        public int distFromOrigin() {
            // ignoring sqrt
            return (x * x) + (y * y);
        }
    }

    public static List<Point> findClosestPoints(Point[] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>
                ((a, b) -> (b.distFromOrigin() - a.distFromOrigin()));

        if (points == null || points.length == 0) return null;

        for (Point point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) maxHeap.remove();
        }

        // the heap has 'k' points closest to the origin, return them in a list
        return new ArrayList<>(maxHeap);
    }

    public static void main(String[] args) {
        Point[] points = new Point[] { new Point(1, 3), new Point(3, 4), new Point(2, -1) };
        List<Point> result = KClosestPointsToOrigin.findClosestPoints(points, 2);
        System.out.print("Here are the k points closest the origin: ");
        for (Point p : result)
            System.out.print("[" + p.x + " , " + p.y + "] ");
    }
}
