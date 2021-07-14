package org.javainaction.graph;

import java.util.*;

/**
 * An undirected, connected tree with n nodes labelled 0...n-1 and n-1 edges are given.
 *
 * The ith edge connects nodes edges[i][0] and edges[i][1] together.
 *
 * Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.
 *
 * Example 1:
 *
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation:
 * Here is a diagram of the given tree:
 *   0
 *  / \
 * 1   2
 *    /|\
 *   3 4 5
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
 * Note: 1 <= n <= 10000
 */
public class SumDistancesTree {
    /**
     * What if given a tree, with a certain root 0?
     * In O(N) we can find sum of distances in tree from root and all other nodes.
     * Now for all N nodes?
     * Of course, we can do it N times and solve it in O(N^2).
     *
     * When we move our root from one node to its connected node,
     * one part of nodes get closer, one the other part get further.
     *
     * If we know exactly how many nodes in both parts, we can solve this problem.
     *
     * With one single traversal in tree, we should get enough information for it and
     * don't need to do it again and again.
     *
     *
     * Explanation
     *
     * Let's solve it with node 0 as root.
     *
     * Initial an array of hashset tree, tree[i] contains all connected nodes to i.
     * Initial an array count, count[i] counts all nodes in the subtree i.
     * Initial an array of res, res[i] counts sum of distance in subtree i.
     *
     * Post order dfs traversal, update count and res:
     * count[root] = sum(count[i]) + 1
     * res[root] = sum(res[i]) + sum(count[i])
     *
     * Pre order dfs traversal, update res:
     * When we move our root from parent to its child i, count[i] points get 1 closer to root, n - count[i]
     * nodes get 1 farther to root.
     * res[i] = res[root] - count[i] + N - count[i]
     *
     * return res, done.
     */
    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        //count all nodes in subtree
        int[] count = new int[n];
        //counts of sum of distances in subtree
        int[] dist = new int[n];
        // nodes and their connections in set
        List<Set<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            //undirected edge
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        postOrder(tree, 0, -1, count, dist);
        preOrder(tree, 0, -1, count, dist);

        return dist;
    }

    public static void postOrder(List<Set<Integer>> tree, int node,
                          int parent, int[] count, int[] dist) {
        for (int child : tree.get(node)) {
            //skip parent node
            if (child == parent) continue;

            postOrder(tree, child, node, count, dist);

            count[node] += count[child];
            dist[node] += dist[child] + count[child];
        }
        count[node]++;
    }

    public static void preOrder(List<Set<Integer>> tree, int node,
                         int parent, int[] count, int[] dist) {
        for (int child : tree.get(node)) {
            if (child == parent) continue;

            dist[child] = dist[node] - count[child] + count.length - count[child];

            preOrder(tree, child, node, count, dist);
        }
    }

    public static void main(String[] args) {
        int[][] input = {
                {0, 1},
                {0, 2},
                {2, 3},
                {2, 4},
                {2, 5}
        };
        //6
        //[[0,1],[0,2],[2,3],[2,4],[2,5]]
        System.out.println(Arrays.deepToString(input) );
        System.out.println("---------------------------");
        //[8,12,6,10,10,10]
        System.out.println(Arrays.toString(sumOfDistancesInTree(6, input)));
    }
}
