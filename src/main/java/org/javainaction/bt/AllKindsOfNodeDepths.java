package org.javainaction.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The distance between node in a binary tree and its root call node depth.
 * Given a Binary tree find all kinds of node depth
 * @see org.javainaction.bt.dfs.NodeDepth
 */
public class AllKindsOfNodeDepths {
    //O(nlog(n)) time | O(h) space
    public static int allKindsOfNodeDepths(BinaryTree root) {
        int sumOfAllNodes = 0;
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        //this to compute node depth of all nodes considering them as root
        while (!queue.isEmpty()) {
            BinaryTree node = queue.poll();
            if (node == null) continue;
            sumOfAllNodes += nodeDepth(node, 0); //for first iteration we got depth of root
            //now calculate node depth of all of children
            //continue until we no longer have nodes to compute
            queue.offer(node.left);
            queue.offer(node.right);
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
