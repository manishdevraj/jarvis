package org.javainaction.bt;

import java.util.HashMap;
import java.util.Map;

/**
 * Give a n, find number of ways of binary tree possible.
 * Input: N = 3
 *
 * Output: 5
 *
 *         ()
 * 1         ()
 *             ()
 *
 *       ()
 *  2       ()
 *       ()
 *
 *
 *        ()
 *  3  ()   ()
 *
 *
 *      ()
 *  4 ()
 *      ()
 *
 *
 *  5    ()
 *     ()
 *   ()
 *
 *
 * @see org.javainaction.recursion.AllPossibleFBT
 * @see org.javainaction.recursion.CountUniqueTrees
 */
public class NumWaysBinaryTree {
    //O(n^2) time | O(n) space
    public static int numberOfBinaryTreeTopologies(int n) {
        Map<Integer, Integer> cache = new HashMap<>();
        //there is one way to make a binary tree from zero nodes
        cache.put(0, 1);
        return numberOfBinaryTreeTopologies(n, cache);
    }

    public static int numberOfBinaryTreeTopologies(int n, Map<Integer, Integer> cache) {
        if (cache.containsKey(n))  return cache.get(n);

        int numberOfTrees = 0;
        //tree size is N = 1 + leftTreeSize + rightSize
        //so if we knew we are at root and given N
        //try each combination of leftTreeSizes starting from 0 up to N
        //we get rightTreeSize = N - 1 - leftTreeSize
        for (int leftTreeSize = 0; leftTreeSize < n; leftTreeSize++) {
            int rightTreeSize = n - 1 - leftTreeSize;
            int numOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize, cache);
            int numOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize, cache);
            //total trees are combinations of left total trees * combinations of right total trees
            numberOfTrees += numOfLeftTrees * numOfRightTrees;
        }
        cache.put(n, numberOfTrees);
        return numberOfTrees;
    }

    public static void main(String[] args){
        System.out.println("Number of ways of trees 3: " + numberOfBinaryTreeTopologies(3));
    }
}
