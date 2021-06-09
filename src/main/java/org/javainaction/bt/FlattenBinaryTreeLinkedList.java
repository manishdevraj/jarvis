package org.javainaction.bt;

import org.javainaction.bt.bfs.LevelAverage;

import javax.swing.tree.TreeNode;
import java.util.List;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in
 * the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [0]
 * Output: [0]
 */
public class FlattenBinaryTreeLinkedList {
    public void flatten(TreeNode root) {
        TreeNode current = root;
        // iteration 1 : cur:1, last:4, 1->2 and 4->5 and 1.left == null
        // iteration 2 : cur:2  last:3, 3->4 and 2->3 and 2.left == null
        // iteration 3: 3 doesn't have left, and is already in place
        // iteration 4: 4 doesn't have left, and is already in place
        // iteration 5: 5 doesn't have left, and is already in place
        while (current != null) {
            //we only need to go after left subtree
            if (current.left != null) {
                TreeNode last = current.left;
                //Find current node's last node that links to current node's right subtree
                while (last.right != null) last = last.right;
                //Use current node's left subtree to replace its right subtree(original right 
                //subtree is already linked by current node's last node
                last.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        var obj = new FlattenBinaryTreeLinkedList();
        obj.flatten(root);
        System.out.print("Flatten tree to linked list : " + root);
    }
}
