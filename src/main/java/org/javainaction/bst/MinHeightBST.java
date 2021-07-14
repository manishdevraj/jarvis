package org.javainaction.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a function to build a BST from a sorted array with minimum height possible
 *
 * {1, 2, 5, 7, 10, 13, 14, 15, 22}
 *
 * Sample Output:
 *
 *                 10
 *            2         14
 *         1    5    13   15
 *                7          22
 */
public class MinHeightBST {
    // O(nlog(n)) time | O(n) space where n is the length of the array;
    public static BST minHeightBst(List<Integer> array) {
        if (array == null || array.size() == 0 ) return null;

        return buildMinHeightBst(0, array.size() - 1, array, null);
    }

    public static BST buildMinHeightBst(int left, int right, List<Integer> array, BST root) {
        if (right < left) return null;

        int middle = left + (right - left) / 2;
        int valueToInsert = array.get(middle);

        if (root == null) {
            root = new BST(valueToInsert);
        } else {
            root.insert(valueToInsert);

        }
        buildMinHeightBst(left, middle - 1, array, root);
        buildMinHeightBst(middle + 1, right, array, root);
        return root;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 5, 7, 10, 13, 14, 15, 22);
        var tree = minHeightBst(array);

        System.out.println("is valid BST " + validateBst(tree));
        System.out.println("Height of the tree " + getTreeHeight(tree));

        var inOrder = inOrderTraverse(tree, new ArrayList<Integer>());
        List<Integer> expected = Arrays.asList(1, 2, 5, 7, 10, 13, 14, 15, 22);
        System.out.println("In order traversal " + inOrder);
    }

    static boolean validateBst(BST tree) {
        return validateBst(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean validateBst(BST tree, int minValue, int maxValue) {
        if (tree.value < minValue || tree.value >= maxValue) {
            return false;
        }
        if (tree.left != null && !validateBst(tree.left, minValue, tree.value)) {
            return false;
        }
        if (tree.right != null && !validateBst(tree.right, tree.value, maxValue)) {
            return false;
        }
        return true;
    }

    static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
        if (tree.left != null) {
            inOrderTraverse(tree.left, array);
        }
        array.add(tree.value);
        if (tree.right != null) {
            inOrderTraverse(tree.right, array);
        }
        return array;
    }

    static int getTreeHeight(BST tree) {
        return getTreeHeight(tree, 0);
    }

    static int getTreeHeight(BST tree, int height) {
        if (tree == null) return height;
        int leftTreeHeight = getTreeHeight(tree.left, height + 1);
        int rightTreeHeight = getTreeHeight(tree.right, height + 1);
        return Math.max(leftTreeHeight, rightTreeHeight);
    }
}
