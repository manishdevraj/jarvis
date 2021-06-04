package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the
 * answer must have Node.val == 0.
 *
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],
 * [0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Example 2:
 *
 * Input: n = 3
 * Output: [[0,0,0]]
 */
public class AllPossibleFBT {
    static Map<Integer, List<TreeNode>> cache = new HashMap<>();
    public static List<TreeNode> allPossibleFBT(int n) {
        AtomicInteger nodeValue = new AtomicInteger(1);
        if (!cache.containsKey(n)) {
            List<TreeNode> result = new ArrayList<>();
            if (n  == 1) {
                result.add(new TreeNode(nodeValue.getAndIncrement()));
            } else if (n % 2 == 1) {
                //Every full binary tree with 3 or more nodes, has 2 children at its root.
                //there are no full binary trees with a positive, even number of nodes.
                for (int leftTreeSize = 0; leftTreeSize < n; leftTreeSize++) {
                    int rightTreeSize = n - 1 - leftTreeSize;
                    for (TreeNode left : allPossibleFBT(leftTreeSize)) {
                        for (TreeNode right : allPossibleFBT(rightTreeSize)) {
                            TreeNode child = new TreeNode(nodeValue.getAndIncrement());
                            child.left = left;
                            child.right = right;
                            result.add(child);
                        }
                    }
                }

            }
            cache.put(n, result);
        }
        return cache.get(n);
    }

    public static void main(String[] args) {
        System.out.println("All possible full binary tree for  n = " + 7);
        for (TreeNode node : allPossibleFBT(7)) {
            System.out.println(node);
        }

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
