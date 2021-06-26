package org.javainaction.topologicalsort;

import java.util.*;

/**
 * In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.
 *
 * For convenience, we'll call the person with label x, simply "person x".
 *
 * We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of valid observations.
 *
 * Also, we'll say quiet[x] = q if person x has quietness q.
 *
 * Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]), among all people who definitely have equal to or more money than person x.
 *
 *
 *
 * Example 1:
 *
 * Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
 * Output: [5,5,2,5,4,5,6,7]
 * Explanation:
 * answer[0] = 5.
 * Person 5 has more money than 3, which has more money than 1, which has more money than 0.
 * The only person who is quieter (has lower quiet[x]) is person 7, but
 * it isn't clear if they have more money than person 0.
 *
 * answer[7] = 7.
 * Among all people that definitely have equal to or more money than person 7
 * (which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x])
 * is person 7.
 *
 * The other answers can be filled out with similar reasoning.
 */
public class LoudAndRich {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < quiet.length; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] rich : richer) {
            graph.get(rich[1]).add(rich[0]);
        }

        int[] output = new int[quiet.length];
        Arrays.fill(output, -1);

        for (int p = 0; p < quiet.length; p++) {
            dfsLoudAndRich(output, graph, quiet, p);
        }

        return output;
    }

    /**
     * Now dfs(person) is either person, or min(dfs(child) for child in person). That is to say, the quietest person
     * in the subtree is either the person itself, or the quietest person in some subtree of a child of person.
     *
     * We can cache values of dfs(person) as answer[person], when performing our post-order traversal of the graph.
     * That way, we don't repeat work. This technique reduces a quadratic time algorithm down to linear time.
     */
    public int dfsLoudAndRich(int[] output, Map<Integer,
            List<Integer>> graph,
                              int[] quiet,
                              int person) {
        if (output[person] >= 0) return output[person];
        output[person] = person;
        for (int child : graph.get(person)) {
            int newPerson = dfsLoudAndRich(output, graph, quiet, child);
            if (quiet[newPerson] < quiet[output[person]]) {
                output[person] = newPerson;
            }
        }
        return output[person];
    }

    public static void main(String[] args) {
        int[][] richer = new int[][] {{1,0}, {2,1}, {3,1}, {3,7}, {4,3}, {5,3}, {6,3}};
        int[] quiet = new int[]{3,2,5,4,6,1,7,0};

        System.out.println("Quietest person is : " + Arrays.toString(new LoudAndRich().loudAndRich(richer, quiet)));
    }
}
