package org.javainaction.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given an M by N matrix consisting of booleans that represents a board. Each True boolean represents a wall.
 * Each False boolean represents a tile you can walk on.
 *
 * Given this matrix, a start coordinate, and an end coordinate, return the minimum number of steps required to reach
 * the end coordinate from the start. If there is no possible path, then return null. You can move up, left, down,
 * and right. You cannot move through walls. You cannot wrap around the edges of the board.
 *
 * For example, given the following board:
 *
 * [[f, f, f, f],
 * [t, t, f, t],
 * [f, f, f, f],
 * [f, f, f, f]]
 * and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum number of steps required to reach the
 * end is 7, since we would need to go through (1, 2) because there is a wall everywhere else on the second row.
 * @see org.javainaction.graph.ShortestPathGridObstaclesElimination
 * @see org.javainaction.graph.MaxDistanceRobotFloorPlan
 */

public class ShortestPathMaze {

    private static int findShortestPathMaze(boolean[][] maze, Node start, Node end) {
        if (maze == null || maze.length == 0) return -1;

        int height = maze.length;
        int width = maze[0].length;
        boolean[][] visited = new boolean[height][width];

        if (!isValidBounds(end.point[0], end.point[1], height, width)) return -1 ;

        Queue<Node> neighbours = new LinkedList<>();
        neighbours.add(start);
        int minDistance = Integer.MAX_VALUE;
        while (!neighbours.isEmpty()) {
            Node node = neighbours.poll();
            int[] point = node.point;
            int i = point[0];
            int j = point[1];

            //are we at the destination
            if (isAtDestination(node, end)) {
                minDistance = Math.min(node.distance, minDistance);
            }

            if(visited[i][j]) continue;

            //did we hit the wall
            if (isWall(i, j, maze)) continue;

            visited[i][j] = true;

            List<Node> closestNeighbours = getNeighbours(node, maze, visited);
            neighbours.addAll(closestNeighbours);
        }
        return minDistance;
    }

    private static List<Node> getNeighbours(Node current,
                                             boolean[][] maze,
                                             boolean[][] visited) {
        int i = current.point[0];
        int j = current.point[1];

        int height = maze.length;
        int width = maze[0].length;
        List<Node> neighbours = new ArrayList<>();
        if (i > 0 && !maze[i-1][j] && !visited[i - 1][j]) {
            neighbours.add(new Node(new int[]{i - 1, j}, current.distance + 1));
        }
        if (i < height - 1 && !maze[i+1][j] && !visited[i + 1][j]) {
            neighbours.add(new Node(new int[]{i + 1, j}, current.distance + 1));
        }
        if (j > 0 && !maze[i][j - 1] && !visited[i][j - 1]) {
            neighbours.add(new Node(new int[]{i, j - 1}, current.distance + 1));
        }
        if (j < width - 1 && !maze[i][j + 1] && !visited[i][j + 1]) {
            neighbours.add(new Node(new int[]{i, j + 1}, current.distance + 1));
        }
        return neighbours;
    }

    private static boolean isValidBounds(int i, int j, int height, int width) {
        if (i < 0 || j < 0) return false;
        if (i > height || j > width) return false;
        return true;
    }

    private static boolean isWall(int i, int j, boolean[][] maze) {
        if (maze[i][j]) return true;
        return false;
    }

    private static boolean isAtDestination(Node source, Node destination) {
        if (source.point[0] == destination.point[0] && source.point[1] == destination.point[1]) return true;
        return false;
    }

    public static void main(String[] args) {
        boolean[][] maze = {{false, false, false, false},
                {true, true, false, true},
                {false, false, false, false},
                {false, false, false, false}};
        int[] start = {3, 0};
        int[] end = {0, 0};
        Node startNode = new Node(start, 0);
        Node endNode = new Node(end, 0);
        System.out.println(findShortestPathMaze(maze, startNode, endNode));
    }

    static class Node {
        int[] point;
        int distance;

        public Node(int[] point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }
}
