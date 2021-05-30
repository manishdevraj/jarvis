package org.javainaction.bt.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, connect each node with its level order successor. The last node of each level should point
 * to the first node of the next level.
 *
 * Tree
 *
 *              12
 *           7     1
 *        9   10      5
 *
 *        Becomes:
 *
 *              12 -> (7)
 *       ->  7  ->   1 -> (9)
 *     ->  9 -> 10  ->  5 -> (null)
 *
 */
public class ConnectAllSiblings {
    static  class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    //", left=" + left +
                    //", right=" + right +
                    ", next=" + next +
                    '}';
        }
    };

    public static void connect(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode previous = null;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (previous != null) {
                    previous.next = currentNode;
                }
                if (currentNode != null) previous = currentNode;

                if (currentNode.left != null)
                    queue.add(currentNode.left);
                if (currentNode.right != null)
                    queue.add(currentNode.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        System.out.println(root);

        ConnectAllSiblings.connect(root);

        // level order traversal using 'next' pointer
        TreeNode current = root;
        System.out.println("Traversal using 'next' pointer: " + root);
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}
