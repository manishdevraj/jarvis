package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Count of Structurally Unique Binary Search Trees (hard) #
 * Given a number ‘n’, write a function to return the count of structurally unique Binary Search Trees (BST) that can
 * store values 1 to ‘n’.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: As we saw in the previous problem, there are 2 unique BSTs storing numbers from 1-2.
 * Example 2:
 *
 * Input: 3
 * Output: 5
 * Explanation: There will be 5 unique BSTs that can store numbers from 1 to 5.
 *
 * Given an integer n, return the number of structurally unique BST's (binary search trees)
 * which has exactly n nodes of unique values from 1 to n.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: 5
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 * @see AllPossibleFBT
 * @see org.javainaction.bt.NumWaysBinaryTree
 */
public class CountUniqueTrees {
    // O(n^2) time | O(n) space
    public int countTrees(int n) {
        List<Integer> count = new ArrayList<>();
        count.add(1);
        for (int treeSize = 1; treeSize < n + 1; treeSize++) {
            int numberOfTrees = 0;
            for (int leftTreeSize = 0; leftTreeSize < treeSize; leftTreeSize++) {
                int rightTreeSize = treeSize - leftTreeSize - 1;
                int noOfLeftTrees = count.get(leftTreeSize);
                int noOfRightTrees = count.get(rightTreeSize);
                numberOfTrees += noOfLeftTrees * noOfRightTrees;
            }
            count.add(numberOfTrees);
        }
        return count.get(n);
    }

    // O(n^2) time | O(n) space
    static Map<Integer, Integer> memoize = new HashMap<>();
    public int countTreesMemo(int n) {
        memoize.put(1, 1);
        return countTreesRecursion(n);
    }

    public int countTreesRecursion(int n) {
        if (memoize.containsKey(n)) return memoize.get(n);

        int count = 0;
        for (int i = 1; i <= n; i++) {
            // making 'i' root of the tree
            int countOfLeftSubtrees = countTrees(i - 1);
            int countOfRightSubtrees = countTrees(n - i);
            count += (countOfLeftSubtrees * countOfRightSubtrees);
        }
        memoize.put(n, count);
        return count;
    }

    public static void main(String[] args) {
        CountUniqueTrees ct = new CountUniqueTrees();
        System.out.println("Total trees with n = 3: " + ct.countTreesMemo(3));
        System.out.println("Total trees with n = 3: " + ct.countTrees(3));
    }
}
