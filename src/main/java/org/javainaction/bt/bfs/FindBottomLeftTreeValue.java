package org.javainaction.bt.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,null,5,6,null,null,7]
 * Output: 7
 */
public class FindBottomLeftTreeValue {
    /**
     * One solution is to find height and go towards largest subtree from right to left in BFS
     * Instead try and flip the traverse from right to left in BFS traversal
     *
     *
     */
    public static int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;
        return bfsRightToLeft(root);
    }

    public static int bfsRightToLeft(TreeNode currentNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(currentNode);

        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (currentNode.right != null)
                queue.add(currentNode.right);
            if (currentNode.left != null)
                queue.add(currentNode.left);
        }

        // Because we are using going right to left in our BFS traversal
        // our current is pointing to left most value at the bottom
        return currentNode.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left  = new TreeNode(1);
        root.right = new TreeNode(3);
        
        System.out.println("{2, 1, 3} tree has bottom left value : " + findBottomLeftValue(root));

        root = new TreeNode(1);
        root.left  = new TreeNode(2);
        root.left.left = new TreeNode(4);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        root.right.left.left = new TreeNode(7);

        System.out.println("{1,2,3,4,null,5,6,null,null,7} tree has bottom left value : " + findBottomLeftValue(root));
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
