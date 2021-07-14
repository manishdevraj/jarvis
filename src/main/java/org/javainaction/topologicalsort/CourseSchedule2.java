package org.javainaction.topologicalsort;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course
 * bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers,
 * return any of them. If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses
 * 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 */
public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> sortedCourses = new ArrayList<>();
        if (numCourses <= 0) return new int[]{};

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];


        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int parent = prerequisite[1];
            int child = prerequisite[0];
            graph.get(parent).add(child); // put the child into it's parent's list
            inDegree[child]++; // increment child's inDegree
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        int dependenciesCount = prerequisites.length;
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            sortedCourses.add(course);
            // get the node's children to decrement their in-degrees
            List<Integer> children = graph.get(course);
            for (int child : children) {
                dependenciesCount--;
                if (--inDegree[child] == 0) //we can attend course
                    queue.add(child);
            }
        }

        return dependenciesCount == 0 ? sortedCourses.stream().mapToInt(i -> i).toArray() : new int[]{};

    }

    public static void main(String[] args) {

        System.out.println("Course order: " +
                Arrays.toString(new CourseSchedule2().findOrder(
                        3, new int[][]{new int[]{0, 1}, new int[]{1, 2}})));

        System.out.println("Course order: " +
                Arrays.toString(new CourseSchedule2().findOrder(
                        2, new int[][]{new int[]{1, 0}})));

        System.out.println("Course order: " +
                Arrays.toString(new CourseSchedule2().findOrder(
                        4, new int[][]{new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 1},
                                new int[]{3, 2}})));
    }
}
