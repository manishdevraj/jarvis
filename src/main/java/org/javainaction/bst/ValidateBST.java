package org.javainaction.bst;

/**
 * Find give a tree root node, if it is valid Binary search tree
 *
 * A binary tree is a valid BST if every node to its root is less than a root value
 * and every node to right is greater or equal to node's root value
 */
public class ValidateBST {
    public static boolean validateBst(BST tree) {
        return validateBSTRecursive(tree, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validateBSTRecursive(BST tree, long minValue, long maxValue) {
        if (tree == null) return true;
        //check bounds
        if (minValue > tree.value && maxValue <= tree.value) return false;
        //if either of node is not valid return false
        if (tree.left != null && !validateBSTRecursive(tree.left, minValue, tree.value)) return false;
        if (tree.right != null && !validateBSTRecursive(tree.right, tree.value, maxValue)) return false;

        //we match all test cases so node must valid BST
        return true;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        var root = new BST(10);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.right = new BST(5);
        root.right = new BST(15);
        root.right.left = new BST(13);
        root.right.left.right = new BST(14);
        root.right.right = new BST(22);

        System.out.println("is valid BST " + validateBst(root));
    }
}
