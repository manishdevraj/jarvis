package org.javainaction.bst;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 */
public class SerializeDeserializeBST {
    private static String SEPERATOR = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeDfs(root, sb);
        return sb.toString();
    }

    public void serializeDfs(TreeNode root, StringBuilder sb) {
        if (root == null) return;

        sb.append(root.val).append(SEPERATOR);
        serializeDfs(root.left, sb);
        serializeDfs(root.right, sb);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        Queue<String> queue =
                new LinkedList<>(Arrays.asList(data.split(SEPERATOR)));
        return deserializeDfs(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode deserializeDfs(Queue<String> queue, int minValue, int maxValue){
        if (queue.isEmpty()) return null;
        int value = Integer.parseInt(queue.peek());

        if (value < minValue || value > maxValue) return null;
        queue.poll();
        TreeNode current = new TreeNode(value);
        current.left = deserializeDfs(queue, minValue, value);
        current.right = deserializeDfs(queue, value, maxValue);
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
        TreeNode root = new TreeNode(2);
        root.left  = new TreeNode(1);
        root.right = new TreeNode(3);
        var obj = new SerializeDeserializeBST();
        String codec = obj.serialize(root);
        System.out.println("{2, 1, 3} serialized to : " + codec);
        System.out.println("{2, 1, 3} deserialized to : " + obj.deserialize(codec));
    }
}
