package org.javainaction.bst;

import java.util.List;

public class MinHeightBST {
    // O(nlog(n)) time | O(n) space where n is the lenfth of the array;
    public static BST minHeightBst(List<Integer> array) {
        if (array == null || array.size() == 0 ) return null;

        return buildMinHeightBst(0, array.size() - 1, array, null);
    }

    public static BST buildMinHeightBst(int i, int j, List<Integer> array, BST root) {
        if (j < i) return null;

        int middle = (i + j) / 2;
        int valueToInsert = array.get(middle);

        if (root == null) {
            root = new BST(valueToInsert);
        } else {
            root.insert(valueToInsert);

        }
        buildMinHeightBst(i, middle - 1, array, root);
        buildMinHeightBst(middle + 1, j, array, root);
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
}
