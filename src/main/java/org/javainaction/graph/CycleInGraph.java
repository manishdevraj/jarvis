package org.javainaction.graph;

import java.util.Arrays;

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
        visited[node] = true;
        currentlyInStack[node] = true;

        boolean isCycle = false;
        int[] neighbors = edges[node];
        for (int neighbor :neighbors) {
            if (!visited[neighbor]) {
                isCycle = isCycleFromNode(neighbor, edges, visited, currentlyInStack);
            }

            if (isCycle) return true;
            else if(currentlyInStack[neighbor]) return true;
        }
        currentlyInStack[node] = false;
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
