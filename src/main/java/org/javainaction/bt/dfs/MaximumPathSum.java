package org.javainaction.bt.dfs;

import java.util.ArrayDeque;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
 * connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass
 * through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 */
public class MaximumPathSum {
    private static int globalMaximumSum;

    public static int findMaximumPathSum(TreeNode root) {
        globalMaximumSum = Integer.MIN_VALUE;
        findMaximumPathSumRecursive(root);
        return globalMaximumSum;
    }

    private static int findMaximumPathSumRecursive(TreeNode currentNode) {
        if (currentNode == null)
            return 0;

        int maxPathSumFromLeft = findMaximumPathSumRecursive(currentNode.left);
        int maxPathSumFromRight = findMaximumPathSumRecursive(currentNode.right);

        // ignore paths with negative sums, since we need to find the maximum sum we should
        // ignore any path which has an overall negative sum.
        maxPathSumFromLeft = Math.max(maxPathSumFromLeft, 0);
        maxPathSumFromRight = Math.max(maxPathSumFromRight, 0);

        // maximum path sum at the current node will be equal to the sum from the left subtree +
        // the sum from right subtree + val of current node
        int localMaximumSum = maxPathSumFromLeft + maxPathSumFromRight + currentNode.val;

        // update the global maximum sum
        globalMaximumSum = Math.max(globalMaximumSum, localMaximumSum);

        // maximum sum of any path from the current node will be equal to the maximum of
        // the sums from left or right subtrees plus the value of the current node
        return Math.max(maxPathSumFromLeft, maxPathSumFromRight) + currentNode.val;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.insert(new int[] {2, 3, 4, 5, 6, 7}, 0);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(tree));

        tree = new TreeNode(-1);
        tree.insert(new int[] {-3}, 0);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(tree));

        tree = new TreeNode(1);
        tree.insert(new int[] {2, 3}, 0);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(tree));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public void insert(int[] values, int i) {
            if (i >= values.length) {
                return;
            }
            ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
            queue.addLast(this);
            while (queue.size() > 0) {
                TreeNode current = queue.pollFirst();
                if (current.left == null) {
                    current.left = new TreeNode(values[i]);
                    break;
                }
                queue.addLast(current.left);
                if (current.right == null) {
                    current.right = new TreeNode(values[i]);
                    break;
                }
                queue.addLast(current.right);
            }
            insert(values, i + 1);
        }
    }
}
