package org.javainaction.bst;

/**
 * Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 * @see FindKLargestBST
 */
public class FindKSmallestBST {

    /**
     * Approach is  to perform in order traversal and get the kth value from in order traversed list
     */
    public int findKthLargestValueInBst(BST tree, int k) {
        TreeInfo nodeInfo = new TreeInfo(0, -1);
        reverseInOrder(tree, nodeInfo, k);
        return nodeInfo.visitedValue;
    }

    public void reverseInOrder(BST node, TreeInfo nodeInfo, int k) {
        if (node == null || nodeInfo.visitedCount >= k) return;

        reverseInOrder(node.left, nodeInfo, k);
        if (nodeInfo.visitedCount < k) {
            nodeInfo.visitedCount++;
            nodeInfo.visitedValue = node.value;
            reverseInOrder(node.right, nodeInfo, k);
        }
    }

    static class TreeInfo {
        public int visitedCount;
        public int visitedValue;
        public TreeInfo(int visitedCount, int visitedValue){
            this.visitedCount = visitedCount;
            this.visitedValue =  visitedValue;
        }
    }

    // This is an input class. Do not edit.
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BST root = new BST(15);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.left.right = new BST(3);
        root.left.right = new BST(6);
        root.right = new BST(20);
        root.right.left = new BST(17);
        root.right.right = new BST(22);
        int k = 6;
        int expected = 15;
        var actual = new FindKSmallestBST().findKthLargestValueInBst(root, k);
        System.out.println("K = 6 smallest value in BST " + actual);

        actual = new FindKSmallestBST().findKthLargestValueInBst(root, 7);
        System.out.println("K = 7 smallest value in BST " + actual);
    }
}
