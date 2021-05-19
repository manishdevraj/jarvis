package org.javainaction.bt.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Find the minimum depth of a binary tree. The minimum depth is the number of nodes along the shortest path from
 * the root node to the nearest leaf node.
 *
 * Similar Problems #
 * Problem 1: Given a binary tree, find its maximum depth (or height).
 *
 * Solution: We will follow a similar approach. Instead of returning as soon as we find a leaf node, we will keep
 * traversing for all the levels, incrementing maximumDepth each time we complete a level. Here is what the code will
 * look like:
 */
public class MinimumBinaryTreeDepth {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int findDepth(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int minDepth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            minDepth++;
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left == null && current.right == null) {
                    return minDepth;
                }

                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
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
        System.out.println("Tree Minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println("Tree Minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
    }
}
