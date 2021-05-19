package org.javainaction.bt.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will represent a
 * number. Find the total sum of all the numbers represented by all paths.
 */
public class SumOfPathNumbers {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int findSumOfPathNumbers(TreeNode root) {
        List<Integer> sumOfPathNumbers = new ArrayList<>();
        return findRootToLeafPathNumbers(root, 0);
    }

    private static int findRootToLeafPathNumbers(TreeNode currentNode, int pathSum) {
        if (currentNode == null) return 0;

        pathSum = 10 * pathSum + currentNode.val;

        if (currentNode.left == null && currentNode.right == null) return pathSum;

        return findRootToLeafPathNumbers(currentNode.left, pathSum)
                + findRootToLeafPathNumbers(currentNode.right, pathSum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers(root));
    }
}
