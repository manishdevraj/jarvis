package org.javainaction.bt;

import java.util.*;

/**
 * Flatten binary structure is similar to double linked list where instead of left and right there would be prev and
 * next
 *
 * Write a function to flatten a binary tree to follow left to right order
 * If tree happens to be a valid BST then output would be sorted. This should happen in place with mutating original
 * tree
 *
 *                   1
 *               2       3
 *             4    5   6
 *                7  8
 *
 * Output:
 *     4<->2<->7<->5<->8<->1<->6<->3<->
 *
 *
 */
public class FlattenBinaryTreeInOrder {
    // O(n) time | O(n) space where n is number of nodes in a binary tree
    public static BinaryTree flattenBinaryTree(BinaryTree root) {
        List<BinaryTree> list = new ArrayList<>();
        inOrder(root, list);
        //connect as a linked list
        for (int i = 0 ; i < list.size() - 1; i++){
            BinaryTree leftNode = list.get(i);
            BinaryTree rightNode = list.get(i + 1);
            leftNode.right = rightNode;
            rightNode.left = leftNode;
        }
        return list.get(0);
    }

    public static List<BinaryTree> inOrder(BinaryTree tree, List<BinaryTree> array) {
        if (tree == null) return array;

        inOrder(tree.left, array);
        array.add(tree);
        inOrder(tree.right, array);

        return array;
    }

    // This is the class of the input root. Do not edit it.
    static class BinaryTree {
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    //O(n) time | O(d) space where n is nodes in tree and d is height of binary tree
    public static BinaryTree flattenBinaryTreeInPlace(BinaryTree root) {
        flatternTree(root);
        return getLeftMost(root);
    }

    public static BinaryTree[] flatternTree(BinaryTree node) {
        BinaryTree leftMost;
        BinaryTree rightMost;

        if (node.left == null) {
            leftMost = node;
        } else {
            BinaryTree[] leftAndRightMostNodes = flatternTree(node.left);
            connectNodes(leftAndRightMostNodes[1], node);
            leftMost = leftAndRightMostNodes[0];
        }

        if (node.right == null) {
            rightMost = node;
        } else {
            BinaryTree[] leftAndRightMostNodes = flatternTree(node.right);
            connectNodes(node, leftAndRightMostNodes[0]);
            rightMost = leftAndRightMostNodes[1];
        }

        return new BinaryTree[] {leftMost, rightMost};
    }

    public static void connectNodes(BinaryTree leftNode, BinaryTree rightNode){
        leftNode.right = rightNode;
        rightNode.left = leftNode;
    }

    public static BinaryTree getLeftMost(BinaryTree node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        var obj = new FlattenBinaryTreeInOrder();
        BinaryTree root = new BinaryTree(1);
        obj.insert(root, new int[] {2, 3, 4, 5, 6});
        root.left.right.left = new BinaryTree(7);
        root.left.right.right = new BinaryTree(8);
        BinaryTree leftMostNode = flattenBinaryTree(root);
        List<Integer> leftToRightToLeft = obj.leftToRightToLeft(leftMostNode);
        List<Integer> expected =
                new ArrayList<>(Arrays.asList(4, 2, 7, 5, 8, 1, 6, 3, 3, 6, 1, 8, 5, 7, 2, 4));
        System.out.println("Flatten binary tree : " + leftToRightToLeft);
    }

    public void insert(BinaryTree root, int[] values) {
        insert(root, values, 0);
    }

    public void insert(BinaryTree root, int[] values, int i) {
        if (i >= values.length) {
            return;
        }
        Deque<BinaryTree> queue = new ArrayDeque<BinaryTree>();
        queue.addLast(root);
        while (queue.size() > 0) {
            BinaryTree current = queue.pollFirst();
            if (current.left == null) {
                current.left = new BinaryTree(values[i]);
                break;
            }
            queue.addLast(current.left);
            if (current.right == null) {
                current.right = new BinaryTree(values[i]);
                break;
            }
            queue.addLast(current.right);
        }
        insert(root, values, i + 1);
    }

    public List<Integer> leftToRightToLeft(BinaryTree leftMost) {
        List<Integer> nodes = new ArrayList<Integer>();
        BinaryTree current = leftMost;
        while (current.right != null) {
            nodes.add(current.value);
            current = current.right;
        }
        nodes.add(current.value);
        while (current != null) {
            nodes.add(current.value);
            current = current.left;
        }
        return nodes;
    }
}
