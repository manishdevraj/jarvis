package org.javainaction.bt.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, connect each node with its level order successor. The last node of each level should point
 * to a null node.
 * Tree
 *
 *              12
 *           7     1
 *        9   10      5
 *
 *        Becomes:
 *
 *              12 -> null
 *         7  ->   1 -> null
 *       9 -> 10  ->  5 -> (null)
 */
public class ConnectLevelOrderSiblings {
    //O(n) time | O(w) space where w is max width of tree's level order
    public static void connect(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode previous = null; //reset last not at each level

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                //connect last node with current node
                if (previous != null) previous.next = currentNode;

                if (currentNode != null) {
                    //keep track of previous node for each iteration
                    previous = currentNode;
                    if (currentNode.left != null) queue.add(currentNode.left);
                    if (currentNode.right != null) queue.add(currentNode.right);
                }
            }
        }
    }

    //O(n) time |  O(1) space
    public static TreeNode connectDfs(TreeNode root) {
        dfs(root);
        return root;
    }

    private static void dfs(TreeNode curr) {
        if (curr == null) return;
        // point left child to right child of current node
        if (curr.left != null && curr.right != null) curr.left.next = curr.right;
        // point left child to parent's sibling's left child if we do not have right sibling
        if (curr.left != null && curr.right == null && curr.next != null) curr.left.next = curr.next.left;
        // point right child to parent's sibling's left child to cross tree side
        if (curr.right != null && curr.next != null) curr.right.next = curr.next.left;
        dfs(curr.left);
        dfs(curr.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        ConnectLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ConnectLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();

        root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        ConnectLevelOrderSiblings.connectDfs(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ConnectLevelOrderSiblings.connectDfs(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }

        // level order traversal using 'next' pointer
        void printLevelOrder() {
            TreeNode nextLevelRoot = this;
            while (nextLevelRoot != null) {
                TreeNode current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) {
                    System.out.print(current.val + " ");
                    if (nextLevelRoot == null) {
                        if (current.left != null)
                            nextLevelRoot = current.left;
                        else if (current.right != null)
                            nextLevelRoot = current.right;
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }
}
