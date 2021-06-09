package org.javainaction.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Give a preorder of a BST, construct its original BST
 *
 * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
 *
 * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
 *
 * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
 *
 * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 * Example 2:
 *
 * Input: preorder = [1,3]
 * Output: [1,null,3]
 *
 */
public class ReconstructBSTFromPreOrder {
    //O(n^2) time | O(n) space
    public BST reconstructBst(List<Integer> preOrderTraversalValues) {
        if (preOrderTraversalValues.size() == 0) return null;

        int current = preOrderTraversalValues.get(0);
        int rightRootIndex = preOrderTraversalValues.size();

        //find first root index of current node
        for (int i = 1; i < preOrderTraversalValues.size(); i++) {
            int value = preOrderTraversalValues.get(i);
            if (value >= current) {
                rightRootIndex = i;
                break;
            }
        }

        //left sub tree is between current and right root index
        BST leftSubtree = reconstructBst(preOrderTraversalValues.subList(1,
                rightRootIndex));
        //right sub tree is between right root index and remaining order
        BST rightSubtree = reconstructBst(preOrderTraversalValues.subList(rightRootIndex,
                preOrderTraversalValues.size()));

        BST bst = new BST(current);
        bst.left = leftSubtree;
        bst.right = rightSubtree;

        return bst;
    }

    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public List<Integer> getDfsOrder(BST node, List<Integer> values) {
        values.add(node.value);
        if (node.left != null) {
            getDfsOrder(node.left, values);
        }
        if (node.right != null) {
            getDfsOrder(node.right, values);
        }
        return values;
    }

    public static void main(String[] args) {
        List<Integer> preOrderTraversalValues =
                new ArrayList<Integer>(Arrays.asList(10, 4, 2, 1, 3, 17, 19, 18));
        BST tree = new BST(10);
        tree.left = new BST(4);
        tree.left.left = new BST(2);
        tree.left.left.left = new BST(1);
        tree.left.right = new BST(3);
        tree.right = new BST(17);
        tree.right.right = new BST(19);
        tree.right.right.left = new BST(18);
        List<Integer> expected = new ReconstructBSTFromPreOrder().getDfsOrder(tree, new ArrayList<>());
        var actual = new ReconstructBSTFromPreOrder().reconstructBst(preOrderTraversalValues);
        List<Integer> actualValues = new ReconstructBSTFromPreOrder().getDfsOrder(actual, new ArrayList<>());
        System.out.println("Reconstructed BST " + actualValues);
    }
}