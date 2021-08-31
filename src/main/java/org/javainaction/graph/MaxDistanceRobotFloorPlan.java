package org.javainaction.graph;

import java.util.*;

/**
 * Given a m * n floor plan, where each room is either O (empty) or X (obstacle) or R (robot).
 * Given that robot can can move up, down, left or right from and to an empty room, with distance of 1, and it always
 * move using shortest distance possible, find all rooms that are farthest from the robot.
 *
 * 1. Path cannot contain obstacle X
 * 2. If there exist multiple path to the room then use optimal shortest distance
 *
 * Return list of rooms that has maximum distance from robot without crossing through obstacles.
 *
 * {'O','O','O','O','O'},
 * {'O','O','O','O','O'},
 * {'O','R','X','O','O'},
 * {'O','O','X','O','X'}
 *
 * Output: [[0, 4], [2, 4], [3, 3]]
 *
 * These are farthest from the robot
 * @see ShortestPathGridObstaclesElimination Where we used 3rd dimension to keep obstacle elimination count
 * @see org.javainaction.array.ShortestPathMaze Where we are not allowed to remove elimination
 */
public class MaxDistanceRobotFloorPlan {
    private static final int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public List<int[]> maximuDistanceRooms(char[][] floorPlan) {
        //find where is Robot in the floor plan
        int X = -1;
        int Y = -1;
        for (int i = 0; i < floorPlan.length; i++) {
            for (int j = 0; j < floorPlan[0].length; j++) {
                if (floorPlan[i][j] == 'R') {
                    X = i;
                    Y = j;
                    break;
                }
            }
        }
        //if we could not find robot, return empty result;
        if (X == -1 && Y == -1) return new ArrayList<>();

        //to keep track of visited rooms so we do not visit them again
        var visited = new boolean[floorPlan.length][floorPlan[0].length];
        //track maximum distance to the room
        var roomDistance = new int[floorPlan.length][floorPlan[0].length][1];
        var output = new ArrayList<int[]>();
        //to explore graph
        var roomsToExplore = new LinkedList<int[]>();

        int m = floorPlan.length;
        int n = floorPlan[0].length;
        roomsToExplore.offer(new int[]{X, Y});

        int distance = 0;
        //to find farthest rooms among all
        int globalMax = Integer.MIN_VALUE;
        int maxDistance = Integer.MIN_VALUE;

        while(!roomsToExplore.isEmpty()) {
            int queueSize = roomsToExplore.size();

            for (int q = 0; q < queueSize; q++) {
                int[] currentRoom = roomsToExplore.poll();
                if (currentRoom != null) {
                    int i = currentRoom[0];
                    int j = currentRoom[1];

                    //check bounds and if we have obstacle then try exploring other rooms
                    if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || floorPlan[i][j] == 'X')
                        continue;

                    visited[i][j] = true;

                    //update our global max and room local max
                    maxDistance = Math.max(maxDistance, distance);
                    globalMax = Math.max(globalMax, maxDistance);

                    //assign new max distance
                    roomDistance[i][j][0] = maxDistance;

                    //explore rooms in all directions
                    for(int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            //skip obstacles
                            if (floorPlan[x][y] == 'X') continue;
                            roomsToExplore.offer(new int[]{x, y});
                        }
                    }
                }
            }
            //once we are done exploring current queue, increase the distance
            distance++;
        }

        //find all rooms with max distance among all using global max
        for (int i = 0; i < roomDistance.length; i++) {
            for (int j = 0; j < roomDistance[0].length; j++) {
                if (roomDistance[i][j][0] == globalMax) {
                    output.add(new int[]{i, j});
                }
            }
        }
        return output;
    }


    public static void main(String[] args) {
        char[][] floorPlan = new char[][] {
                {'O','O','O','O','O'},
                {'O','O','O','O','O'},
                {'O','R','X','O','O'},
                {'O','O','X','O','X'}};
        var obj = new MaxDistanceRobotFloorPlan();

        System.out.println(Arrays.deepToString(floorPlan));
        System.out.println("Longest distance rooms : ");
        obj.maximuDistanceRooms(floorPlan).forEach(room -> System.out.println(Arrays.toString(room)));
    }
}
