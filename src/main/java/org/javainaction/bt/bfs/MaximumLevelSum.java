package org.javainaction.bt.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximum.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 * Example 2:
 *
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 */
public class MaximumLevelSum {
    public static int maxLevelSum(TreeNode root) {
        int maxLevelSum = Integer.MIN_VALUE;
        if (root == null) return -1;
        int maxLevel = Integer.MIN_VALUE;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;
        while(!queue.isEmpty()) {
            int levelSum = 0; //reset the level sum
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    levelSum += node.val;
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
            }

            if (maxLevelSum < levelSum) {
                maxLevelSum = levelSum;
                maxLevel = level;
            }
            level++;
        }
        return maxLevel;
    }

    public static void main(String[] args) {
        //[1,7,0,7,-8,null,null]
        TreeNode root = new TreeNode(1, new
                TreeNode(7, new TreeNode(7), new TreeNode(-8)),
                new TreeNode(0));
        System.out.println(root);
        System.out.println(" with maximum sum level at " + maxLevelSum(root));

        //[989,null,10250,98693,-89388,null,null,null,-32127]
        root = new TreeNode(989,
                null,
                new TreeNode(10250,
                        new TreeNode(98693),
                        new TreeNode(-89388,
                                null,
                                new TreeNode(-32127))));
        System.out.println(root);
        System.out.println(" with maximum sum leve at " + maxLevelSum(root));

        root = new TreeNode(1, new
                TreeNode(7, new TreeNode(7), new TreeNode(8)),
                new TreeNode(0));
        System.out.println(root);
        System.out.println(" with maximum sum level at " + maxLevelSum(root));

    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
