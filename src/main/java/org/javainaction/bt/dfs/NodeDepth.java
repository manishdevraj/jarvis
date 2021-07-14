package org.javainaction.bt.dfs;

/**
 * The distance between binary tree and it's root is called node depth.
 *
 * Given a binary tree, return the sum of all of its node depths
 *                   1
 *               2      3
 *            4   5   6   7
 *          8  9
 *
 * The output would be 16
 *
 * Depth of node 2 and 3 is : 1
 * Depth of node 4, 5, 6, 7 is : 2
 * Etc.
 *
 */
public class NodeDepth {

    private static int nodeDepths(BinaryTree root) {
        return findNodeDepth(root, 0);
    }

    private static int findNodeDepth(BinaryTree root, int depth) {
        if (root == null) return 0;
        return depth
                + findNodeDepth(root.left, depth + 1)
                + findNodeDepth(root.right, depth + 1);
    }

    public static void main(String[] args) {
        var root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.left.left = new BinaryTree(4);
        root.left.left.left = new BinaryTree(8);
        root.left.left.right = new BinaryTree(9);
        root.left.right = new BinaryTree(5);
        root.right = new BinaryTree(3);
        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        int actual = nodeDepths(root);
        System.out.println(root);
        System.out.println("Node depth sum " + actual);
    }

    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
