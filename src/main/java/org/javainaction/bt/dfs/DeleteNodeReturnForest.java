package org.javainaction.bt.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 * Example 2:
 *
 * Input: root = [1,2,4,null,3], to_delete = [3]
 * Output: [[1,2,4]]
 */
public class DeleteNodeReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = new HashSet<>();
        List<TreeNode> output = new ArrayList<>();
        //track all nodes to be deleted
        for (int val : to_delete)
            toDelete.add(val);
        //for root parent is always deleted
        deleteNodesRecursive(root, toDelete, output, true);
        return output;
    }

    public TreeNode deleteNodesRecursive(TreeNode current, Set<Integer> toDelete,
                                         List<TreeNode> output, boolean isParentDeleted) {
        if (current == null) return null;
        //check if current node is part of deletion
        boolean deleted = toDelete.contains(current.val);

        //if current node is not being deleted
        // and it's parent is deleted then we have a broken link so add current node
        //for root parent is always deleted

        if (isParentDeleted && !deleted) output.add(current);

        //assign new nodes to current left and right
        //this is important to avoid stack overflow
        current.left = deleteNodesRecursive(current.left, toDelete, output, deleted);
        current.right = deleteNodesRecursive(current.right, toDelete, output, deleted);
        //if node is being deleted return null or keep current node as is

        return deleted ? null : current;
    }

    public static void main(String[] args) {
        var obj = new DeleteNodeReturnForest();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Forest after deleting nodes from tree");
        obj.delNodes(root,new int[]{3, 5}).forEach(System.out::println);

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        System.out.println("Forest after deleting nodes from tree");
        obj.delNodes(root,new int[]{3}).forEach(System.out::println);
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
}
