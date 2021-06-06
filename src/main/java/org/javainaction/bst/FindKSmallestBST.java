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
 */
public class FindKSmallestBST {

    /**
     * One of the approach could be to perform in order traversal and get the kth value from in order traversed list
     * But to achieve same thing in O(h) space we do in order traversal and use a auxilliary class to keep track of
     * value and count of nodes visited
     *
     * O(h + k) time and O(h) space we need to at most traverse height of BST
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
        root.left.right = new BST(5);
        root.right = new BST(20);
        root.right.left = new BST(17);
        root.right.right = new BST(22);
        int k = 3;
        int expected = 17;
        var actual = new FindKSmallestBST().findKthLargestValueInBst(root, k);
        System.out.println("K = 3 smallest value in BST " + actual);
    }
}
