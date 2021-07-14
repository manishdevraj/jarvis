package org.javainaction.bst;

import java.util.Stack;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 *
 *
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
 * @see MinDistanceBST
 */
public class MinAbsDiffBST {

    public int minDifference = Integer.MAX_VALUE;
    public BST prev = null;
    public int getMinimumDifference(BST root) {
        getMinDiffInOrder(root);
        return minDifference;
    }

    public void getMinDiffInOrder(BST root) {
        if (root == null) return;

        getMinDiffInOrder(root.left);
        
        if (prev != null) minDifference = Math.min(minDifference, root.value - prev.value);

        prev = root;
        getMinDiffInOrder(root.right);

    }

    public int getMinAbsDifference(BST root) {
        Stack<BST> stack = new Stack<>();
        BST current = root;
        BST prev = null;
        int minDifference = Integer.MAX_VALUE;
        //in order traversal using stack
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current); //put into stack as this will become inorder when popped out
                current = current.left; //keep going to left
            } else {
                current = stack.pop();
                if (prev != null) minDifference = Math.min(minDifference, prev.value - current.value);
                prev = current;
                current = current.right; //go to right
            }
        }
        return minDifference;
    }
    
    public static void main(String[] args) {
        var root = new BST(1);
        root.left = new BST(0);
        root.right = new BST(48);
        root.right.left = new BST(12);
        root.right.right = new BST(49);

        var expected = 1;
        var actual = new MinAbsDiffBST().getMinimumDifference(root);
        System.out.println("Min difference  " + actual);

        actual = new MinAbsDiffBST().getMinimumDifference(root);
        System.out.println("Min difference  " + actual);
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
