package org.javainaction.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Binary tree with additional pointer to parent, write a function that can perform in order traversal using iteration
 * There will be an 'callback' function which will do this automatically by passing value of current node
 *
 *           1
 *        2     3
 *      4     6   7
 *        9
 *        callback : some call back function
 *
 * Output:
 *   callback(4)
 *   callback(9)
 *   callback(2)
 *   callback(1)
 *   callback(6)
 *   callback(3)
 *   callback(7)
 */
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

    public List<Integer> testArray = new ArrayList<Integer>();

    public Void testCallback(BinaryTree tree) {
        if (tree == null) {
            return null;
        }
        testArray.add(tree.value);
        return null;
    }

    public static void main(String[] args) {
        var root = new BinaryTree(1);
        root.left = new BinaryTree(2, root);
        root.left.left = new BinaryTree(4, root.left);
        root.left.left.right = new BinaryTree(9, root.left.left);
        root.right = new BinaryTree(3, root);
        root.right.left = new BinaryTree(6, root.right);
        root.right.right = new BinaryTree(7, root.right);

        var program = new IterativeInorderTraversal();
        program.testArray.clear();
        iterativeInOrderTraversal(root, program::testCallback);
        List<Integer> expected = Arrays.asList(new Integer[] {4, 9, 2, 1, 6, 3, 7});
        System.out.println("Iterative callback " + program.testArray);
    }
}
