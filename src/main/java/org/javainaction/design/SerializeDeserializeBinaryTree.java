package org.javainaction.design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
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
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 */
public class SerializeDeserializeBinaryTree {
    /**
     * Definition for a binary tree node.
     */
     public class TreeNode {
         int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }

    //[1,2,3,null,null,4,5]
    // Encodes a tree to a single string.
    private static String NULL = "null";
    private static String SEPERATOR = ",";

    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serializeDfs(root, builder);
        return builder.toString();
    }

    public void serializeDfs(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append(NULL).append(SEPERATOR);
        } else {
            builder.append(root.val).append(SEPERATOR);
            serializeDfs(root.left, builder);
            serializeDfs(root.right, builder);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;

        Queue<String> deserQueue = new LinkedList<>(Arrays.asList(data.split("\\,")));
        return deserializeDfs(deserQueue);
    }

    public TreeNode deserializeDfs(Queue<String> queue) {
        if (queue.isEmpty()) return null;
        String val = queue.poll();
        if (val.equals(NULL)) return null;

        int value = Integer.parseInt(val);
        TreeNode current = new TreeNode(value);
        current.left = deserializeDfs(queue);
        current.right = deserializeDfs(queue);

        return current;
    }
}
