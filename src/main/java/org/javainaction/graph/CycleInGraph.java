package org.javainaction.graph;

import java.util.Arrays;

/**
 * Given unweighted directed graph with at least one node. Find out if graph contains cycle
 *
 * For this question cycle is defined as any number of vertices including one vertex that is connected to the chain.
 *
 * List input is an adjacency list that represents graph. number of vertices is equal to length of the edges
 * where at each i contains ith vertices outbound edge. Each edge is a positive number, note that edges are directed
 * meaning we can only travel in one direction
 * @see DetectCycles2DGrid
 */
public class CycleInGraph {
    public static boolean cycleInGraph(int[][] edges) {
        int numberOfNodes = edges.length;
        boolean[] visited = new boolean[numberOfNodes];
        boolean[] currentlyInStack = new boolean[numberOfNodes];

        for (int node = 0 ; node < numberOfNodes; node++) {
            if (visited[node]) {
                continue;
            }
            boolean isCycle = isCycleFromNode(node, edges, visited, currentlyInStack);
            if (isCycle) return true;
        }
        return false;
    }

    public static boolean isCycleFromNode(int node, int[][] edges, boolean[] visited,
                                   boolean[] currentlyInStack) {
        visited[node] = true; //to avoid visited vertices
        currentlyInStack[node] = true; //to maintain if we are still visiting

        boolean isCycle = false;
        int[] neighbors = edges[node];
        for (int neighbor :neighbors) {
            if (!visited[neighbor]) {
                isCycle = isCycleFromNode(neighbor, edges, visited, currentlyInStack);
            }

            if (isCycle) return true;
            else if(currentlyInStack[neighbor]) return true; //for any visiting vertex we will have cycle
        }
        currentlyInStack[node] = false; //remove from visiting veterx information
        return false;
    }

    public static void main(String[] args) {
        int[][] input = new int[][] {
                        {1, 3},
                        {2, 3, 4},
                        {0},
                        {},
                        {2, 5},
                        {}
                };
        boolean expected = true;
        var actual = cycleInGraph(input);
        System.out.println(Arrays.deepToString(input)  + " has cycle? : " + actual);

        input = new int[][] {
                {1, 2},
                {2},
                {},
                };

        actual = cycleInGraph(input);
        System.out.println(Arrays.deepToString(input)  + " has cycle? : " + actual);

    }
}
