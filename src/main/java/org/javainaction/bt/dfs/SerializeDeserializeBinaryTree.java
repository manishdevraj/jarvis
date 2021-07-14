package org.javainaction.bt.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can 
 * be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later 
 * in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily 
 * need to follow this format, so please be creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 * Example 4:
 *
 * Input: root = [1,2]
 * Output: [1,2]
 */
public class SerializeDeserializeBinaryTree {
    private static String SEPERATOR = ",";
    private static String NULL = "NULL";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeDfs(root, sb);
        return sb.toString();
    }

    public void serializeDfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEPERATOR);
        } else {
            sb.append(root.val).append(SEPERATOR);
            serializeDfs(root.left, sb);
            serializeDfs(root.right, sb);
        }
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        Queue<String> queue =
                new LinkedList<>(Arrays.asList(data.split(SEPERATOR)));
        return deserializeDfs(queue);
    }

    public TreeNode deserializeDfs(Queue<String> queue){
        if (queue.isEmpty()) return null;
        String val = queue.poll();
        if (val.equals(NULL)) return null;

        int value = Integer.parseInt(val);
        TreeNode current = new TreeNode(value);
        current.left = deserializeDfs(queue);
        current.right = deserializeDfs(queue);
        return current;
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
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left  = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        var obj = new SerializeDeserializeBinaryTree();
        String codec = obj.serialize(root);
        System.out.println("{1, 2, 3, 4, 5} serialized to : " + codec);
        System.out.println("{1, 2, 3, 4, 5} deserialized to : " + obj.deserialize(codec));
    }
}
