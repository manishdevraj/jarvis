package org.javainaction.graph;

import java.util.*;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2
 * indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 */
public class SmallestStringWithSwaps {
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        boolean[] visited = new boolean[s.length()];
        char[] output = s.toCharArray();

        //build adjacency both ways
        for (List<Integer> pair : pairs) {
            adjacency.computeIfAbsent(pair.get(0), x -> new ArrayList<>()).add(pair.get(1));
            adjacency.computeIfAbsent(pair.get(1), x -> new ArrayList<>()).add(pair.get(0));
        }

        for (int i = 0; i < s.length(); i++) {
            //check if not visited
            if (visited[i]) continue;

            var indices = new ArrayList<Integer>();
            //go dfs to find all possible paths from source
            dfs(adjacency, i, visited, indices);

            //copy clone
            var cloneIndices = new ArrayList<>(indices);
            //sort clone
            Collections.sort(cloneIndices);
            //sort indices with respect to alphabets
            indices.sort((a, b) -> Integer.compare(s.charAt(a), s.charAt(b)));
            //put the smallest character at the least index in current component
            for (int j = 0; j < cloneIndices.size(); j++) {
                int clonedIndex = cloneIndices.get(j);
                int lexicographicallySortedIndex = indices.get(j);
                output[clonedIndex] = s.charAt(lexicographicallySortedIndex);
            }
        }

        return new String(output);
    }

    private static void dfs(Map<Integer, List<Integer>> adjacency, int src,
                            boolean[] visited, ArrayList<Integer> indices) {
        //base case if have not source or already visited
        if (!adjacency.containsKey(src) || visited[src]) return;

        indices.add(src);
        visited[src] = true;

        //try all its adjacency neighbours
        for (int next : adjacency.get(src))
            dfs(adjacency, next, visited, indices);
    }

    public static void main(String[] args) {
        System.out.println("dcab smallest string after swaps: "
                + smallestStringWithSwaps("dcab", List.of(List.of(0, 3), List.of(1, 2))));

        System.out.println("dcab smallest string after swaps: "
                + smallestStringWithSwaps("dcab", List.of(List.of(0, 3), List.of(1, 2), List.of(0, 2))));

        System.out.println("cba smallest string after swaps: "
                + smallestStringWithSwaps("cba", List.of(List.of(0, 1), List.of(1, 2))));
    }
}
