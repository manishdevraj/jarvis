package org.javainaction.bt.dfs;

/**
 * Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given tree.
 * @see TreePathSum
 */
public class PathWithGivenSequence {
    public static boolean findPath(TreeNode root, int[] sequence) {
        if (root == null)
            return sequence.length == 0;
        return findPathSequence(root, sequence, 0);
    }

    public static boolean findPathSequence(TreeNode currentNode, int[] sequence, int index){
        if (currentNode == null) return false;

        if (index >= sequence.length - 1 && currentNode.val != sequence[index]) return false;

        if (index == sequence.length - 1 && currentNode.left == null && currentNode.right == null)
            return true;

        return findPathSequence(currentNode.left, sequence, index + 1)
                || findPathSequence(currentNode.right, sequence, index + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 0, 7 }));
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 1, 6 }));
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
