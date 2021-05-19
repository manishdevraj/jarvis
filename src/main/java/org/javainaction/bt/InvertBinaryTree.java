package org.javainaction.bt;

public class InvertBinaryTree {
    public static void invertBinaryTree(BinaryTree tree) {
        // O(n) time | O(d) space
        invertBinaryTreeRoot(tree);
    }

    public static void invertBinaryTreeRoot(BinaryTree root) {
        if (null == root) return;

        swap(root);
        invertBinaryTreeRoot(root.left);
        invertBinaryTreeRoot(root.right);
    }

    public static void swap(BinaryTree root) {
        BinaryTree temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
