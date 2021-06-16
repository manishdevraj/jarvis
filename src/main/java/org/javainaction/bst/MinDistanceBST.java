package org.javainaction.bst;

import java.util.Stack;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two
 * different nodes in the tree.
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 * @see MinAbsDiffBST
 */
public class MinDistanceBST {

    public int minDiffInBST(BST root) {
        int min = Integer.MAX_VALUE;
        Stack<BST> stack = new Stack<>();
        BST cur = root, pre = null;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null)
                    min = Math.min(min, cur.value - pre.value);
                pre = cur;
                cur = cur.right;
            }
        }
        return min;
    }
    
    public static void main(String[] args) {
        var root = new BST(1);
        root.left = new BST(0);
        root.right = new BST(48);
        root.right.left = new BST(12);
        root.right.right = new BST(49);

        var expected = 13;
        var actual = new MinDistanceBST().minDiffInBST(root);
        System.out.println("Min distance in BST  " + actual);
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

}
