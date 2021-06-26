package org.javainaction.topologicalsort;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
 * So it is impossible.
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();

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
            // get the node's children to decrement their in-degrees
            List<Integer> children = graph.get(course);
            for (int child : children) {
                dependenciesCount--;
                if (--inDegree[child] == 0) //we can attend course
                    queue.add(child);
            }
        }

        return dependenciesCount == 0;

    }

    public static void main(String[] args) {

        System.out.println("Can finish courses : " +
                new CourseSchedule().canFinish(2, new int[][] { new int[] { 1, 0 } }));

        System.out.println("Can finish courses : " +
                new CourseSchedule().canFinish(3,
                        new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } }));

        System.out.println("Can finish courses : " +
                new CourseSchedule().canFinish(2,
                        new int[][] { new int[] { 0, 1 }, new int[] { 1, 0 }}));
    }
}
