package org.javainaction.bt;

import java.util.Stack;

/**
 * The distance between node in a binary tree and its root call node depth.
 * Given a Binary tree find all kinds of node depth 
 */
public class AllKindsOfNodeDepths {
    //O(nlog(n)) time | O(h) space
    public static int allKindsOfNodeDepths(BinaryTree root) {
        int sumOfAllNodes = 0;
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTree node = stack.pop();
            if (node == null) continue;
            sumOfAllNodes += nodeDepth(node, 0);
            stack.push(node.left);
            stack.push(node.right);
        }
        return sumOfAllNodes;
    }

    public static int nodeDepth(BinaryTree currentNode, int depth){
        if (currentNode == null) return 0;

        int leftTreeDepth = nodeDepth(currentNode.left, depth + 1);
        int rightTreeDepth = nodeDepth(currentNode.right, depth + 1);
        depth += leftTreeDepth + rightTreeDepth;
        return depth;
    }

    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        var root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.left.left = new BinaryTree(4);
        root.left.left.left = new BinaryTree(8);
        root.left.left.right = new BinaryTree(9);
        root.left.right = new BinaryTree(5);
        root.right = new BinaryTree(3);
        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        int actual = allKindsOfNodeDepths(root);
        System.out.println(actual);
    }
}
