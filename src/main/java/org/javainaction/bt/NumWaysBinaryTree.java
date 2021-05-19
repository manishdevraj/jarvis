package org.javainaction.bt;

import java.util.HashMap;
import java.util.Map;

/**
 * Give a n, find number of ways of binary tree possible.
 */
public class NumWaysBinaryTree {
    //O(n^2) time | O(n) space
    public static int numberOfBinaryTreeTopologies(int n) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        return numberOfBinaryTreeTopologies(n, cache);
    }

    public static int numberOfBinaryTreeTopologies(int n,
                                                   Map<Integer, Integer> cache) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int numberOfTrees = 0;
        for (int leftTreeSize = 0; leftTreeSize < n; leftTreeSize++) {
            int rightTreeSize = n - 1 - leftTreeSize;
            int numOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize, cache);
            int numOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize, cache);
            numberOfTrees += numOfLeftTrees * numOfRightTrees;
        }
        cache.put(n, numberOfTrees);
        return numberOfTrees;
    }
}
