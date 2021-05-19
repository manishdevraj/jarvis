package org.javainaction.bt;

import java.util.Stack;

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
}
