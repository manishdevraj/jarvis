package org.javainaction.bt;

import java.util.function.Function;

public class IterativeInorderTraversal {
    //O(n) time and O(1) space
    public static void iterativeInOrderTraversal(
            BinaryTree tree, Function<BinaryTree, Void> callback) {
        // Write your code here.
        BinaryTree previous = null;
        BinaryTree current = tree;

        while (current != null){
            BinaryTree next;
            if (previous == null || previous == current.parent) {
                if (current.left != null) {
                    next = current.left;
                } else {
                    //left leaf node that either has a right child
                    //if not then it goes back to its parent
                    callback.apply(current);
                    next = current.right != null ?
                            current.right : current.parent;
                }
            } else if (previous == current.left) {
                //we are going up where previous is rotated to curent left
                //child and that is why we need to check and do callback
                //due to in-order we didn't process this node yet
                callback.apply(current);
                next = current.right != null ?
                        current.right : current.parent;
            } else {
                //rotation of nodes at leaf end
                next = current.parent;
            }

            previous = current;
            current = next;
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;
        public BinaryTree parent;

        public BinaryTree(int value) {
            this.value = value;
        }

        public BinaryTree(int value, BinaryTree parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
