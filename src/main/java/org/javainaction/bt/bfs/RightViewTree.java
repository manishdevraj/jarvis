package org.javainaction.bt.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Example 2:
 *
 * Input: root = [1,null,3]
 * Output: [1,3]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 */
public class RightViewTree {
    //BFS traversal
    public static List<Integer> traverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode != null) {
                    // if it is the last node of this level, add it to the result
                    if (i == levelSize - 1) result.add(currentNode.val);
                    // insert the children of current node in the queue
                    if (currentNode.left != null) queue.offer(currentNode.left);
                    if (currentNode.right != null) queue.offer(currentNode.right);
                }
            }
        }
        return result;
    }

    public static List<Integer> rightSideView(TreeNode root) {
        var result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    //DFS recursive
    public static  void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }

        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(4);
        root.left.right.left = new TreeNode(6);

        System.out.println("Right view is " + traverse(root));
        System.out.println("Right view is " + rightSideView(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
