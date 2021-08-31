package org.javainaction.bt.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node values
 * of each path equals ‘S’.
 *
 * Problem 1: Given a binary tree, return all root-to-leaf paths.
 *
 * Solution: We can follow a similar approach. We just need to remove the “check for the path sum”.
 *
 * Problem 2: Given a binary tree, find the root-to-leaf path with the maximum sum.
 *
 * Solution: We need to find the path with the maximum sum. As we traverse all paths, we can keep track of the path
 * with the maximum sum.
 * @see CountAllPathSum
 * @see PathSum3
 */
public class FindAllTreePaths {
    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathsRecursive(root, sum, currentPath, allPaths);
        return allPaths;
    }

    public static void findPathsRecursive(TreeNode node, int sum,
                                          List<Integer> currentPath,
                                          List<List<Integer>> allPaths) {

        if (node == null) return;

        currentPath.add(node.val);

        if (node.val == sum && node.left == null && node.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            findPathsRecursive(node.left, sum - node.val, currentPath, allPaths);
            findPathsRecursive(node.right, sum - node.val, currentPath, allPaths);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = FindAllTreePaths.findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
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
