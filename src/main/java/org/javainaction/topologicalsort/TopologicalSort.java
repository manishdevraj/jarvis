package org.javainaction.topologicalsort;

import java.util.*;

/**
 * Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices
 * such that for every directed edge (U, V) from vertex U to vertex V, U comes before V in the ordering.
 *
 * Given a directed graph, find the topological ordering of its vertices.
 *
 * Example 1:
 *
 * Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
 * Output: Following are the two valid topological sorts for the given graph:
 * 1) 3, 2, 0, 1
 * 2) 3, 2, 1, 0
 *     3
 *     2
 *     0
 *     1
 * Example 2:
 *
 * Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 4, 2, 3, 0, 1
 * 2) 4, 3, 2, 0, 1
 * 3) 4, 3, 2, 1, 0
 * 4) 4, 2, 3, 1, 0
 * 5) 4, 2, 0, 3, 1
 *     4
 *     2
 *     3
 *     0
 *     1
 * Example 3:
 *
 * Input: Vertices=7, Edges=[6, 4], [6, 2], [5, 3], [5, 4], [3, 0], [3, 1], [3, 2], [4, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 5, 6, 3, 4, 0, 1, 2
 * 2) 6, 5, 3, 4, 0, 1, 2
 * 3) 5, 6, 4, 3, 0, 2, 1
 * 4) 6, 5, 4, 3, 0, 1, 2
 * 5) 5, 6, 3, 4, 0, 2, 1
 * 6) 5, 6, 3, 4, 1, 2, 0
 *
 * There are other valid topological ordering of the graph too.
 * @see TaskScheduling
 * @see JobOrder
 */
public class TopologicalSort {
    public static List<Integer> sort(int vertices, int[][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();
        if (vertices <= 0)
            return sortedOrder;

        // a. Initialize the graph
        HashMap<Integer, Integer> inDegree = new HashMap<>(); // count of incoming edges for every vertex
        HashMap<Integer, List<Integer>> graph = new HashMap<>(); // adjacency list graph
        for (int i = 0; i < vertices; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        // b. Build the graph
        for (int[] edge : edges) {
            int parent = edge[0], child = edge[1];
            graph.get(parent).add(child); // put the child into it's parent's list
            inDegree.put(child, inDegree.get(child) + 1); // increment child's inDegree
        }

        // c. Find all sources i.e., all vertices with 0 in-degrees
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0)
                sources.add(entry.getKey());
        }

        // d. For each source, add it to the sortedOrder and subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        while (!sources.isEmpty()) {
            int vertex = sources.poll();
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex); // get the node's children to decrement their in-degrees
            for (int child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0)
                    sources.add(child);
            }
        }

        if (sortedOrder.size() != vertices) // topological sort is not possible as the graph has a cycle
            return new ArrayList<>();

        return sortedOrder;
    }

    public static void main(String[] args) {
        List<Integer> result = TopologicalSort.sort(4,
                new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(5, new int[][] { new int[] { 4, 2 }, new int[] { 4, 3 }, new int[] { 2, 0 },
                new int[] { 2, 1 }, new int[] { 3, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(7, new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
                new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
        System.out.println(result);
    }
}
