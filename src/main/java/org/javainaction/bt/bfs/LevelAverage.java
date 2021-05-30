package org.javainaction.bt.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, populate an array to represent the averages of all of its levels.
 *
 * Problem 1: Find the largest value on each level of a binary tree.
 *
 * Solution: We will follow a similar approach, but instead of having a running sum we will track the maximum
 * value of each level.
 *
 * maxValue = max(maxValue, currentNode.val)
 */
public class LevelAverage {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<Double> findLevelAverages(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode != null) {
                    // add the node's value to the running sum
                    levelSum += currentNode.val;
                    // insert the children of current node to the queue
                    if (currentNode.left != null) queue.offer(currentNode.left);
                    if (currentNode.right != null) queue.offer(currentNode.right);
                }
            }
            // append the current level's average to the result array
            result.add(levelSum / levelSize);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Double> result = LevelAverage.findLevelAverages(root);
        System.out.print("Level averages are: " + result);
    }
}
