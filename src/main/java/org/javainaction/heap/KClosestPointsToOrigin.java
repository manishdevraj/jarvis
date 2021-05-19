package org.javainaction.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

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
