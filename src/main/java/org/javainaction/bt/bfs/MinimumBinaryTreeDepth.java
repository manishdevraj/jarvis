package org.javainaction.bt.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the minimum depth of a binary tree. The minimum depth is the number of nodes along the shortest path from
 * the root node to the nearest leaf node.
 *
 * Similar Problems #
 * Problem 1: Given a binary tree, find its maximum depth (or height).
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 *
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 *
 */
public class MinimumBinaryTreeDepth {
    public static int findDepth(TreeNode root) {
        if (root == null) return -1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int minDepth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            minDepth++;

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current != null) {
                    //return min depth as whichever leaf element is found, we got the min of all
                    if (current.left == null && current.right == null) {
                        return minDepth;
                    }
                    if (current.left != null) queue.offer(current.left);
                    if (current.right != null) queue.offer(current.right);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println("Tree minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
