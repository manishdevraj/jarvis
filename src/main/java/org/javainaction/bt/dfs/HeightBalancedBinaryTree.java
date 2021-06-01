package org.javainaction.bt.dfs;

/**
 * Write a function to find if height of a binary tree is balanced or not.
 *
 * A height of the tree is said to be balanced if height of its left subtree and right subtree have
 * at most difference of 1
 *
 * Also node that a tree can be still balanced tree even if there are either no left or right tree available
 * but constraint is met
 *
 */
public class HeightBalancedBinaryTree {
    //O(n) time | O(h) space
    public static boolean heightBalancedBinaryTree(BinaryTree tree) {
        return checkIfBalanced(tree).isBalanced;
    }

    public static TreeInfo checkIfBalanced(BinaryTree currentNode) {
        if (currentNode == null)
            return new TreeInfo(0, true);

        TreeInfo leftTreeInfo = checkIfBalanced(currentNode.left);
        TreeInfo rightTreeInfo = checkIfBalanced(currentNode.right);

        //if either is unbalanced then nothing is balanced so check all subtrees
        boolean isBalanced = leftTreeInfo.isBalanced && rightTreeInfo.isBalanced
                && Math.abs(leftTreeInfo.height - rightTreeInfo.height) <= 1;

        return new TreeInfo(Math.max(leftTreeInfo.height, rightTreeInfo.height) + 1, isBalanced);
    }

    static class TreeInfo {
        public int height;
        public boolean isBalanced;

        public TreeInfo(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(1);
        root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(5);
        root.right.right = new BinaryTree(6);
        root.left.right.left = new BinaryTree(7);
        root.left.right.right = new BinaryTree(8);
        boolean expected = true;
        var actual = heightBalancedBinaryTree(root);
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
