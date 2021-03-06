package org.javainaction.bst;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and
 * q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 *
 *
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 *
 *
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA
 * definition.
 * Example 3:
 *
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 */
public class LowestCommonAncestor {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while ( node != null) {
            //only when current node is less that both then we need to search on right
            if (node.val < p.val && node.val < q.val) {
                node = node.right;
            //only when current node is greater than both then we need to search on left
            } else if (node.val > p.val && node.val > q.val) {
                node = node.left;
            //else we have found the node
            } else {
                return node;
            }
        }
        return null;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        var output = lowestCommonAncestor(root, root.left, root.left.right);
        System.out.println("Lower common Ancestor of {2, 4} with root at 6: ");
        System.out.println(output != null ? output.val : null);

        output = lowestCommonAncestor(root, root.left, root.right.right);
        System.out.println("Lower common Ancestor of {4, 9} with root at 6: ");
        System.out.println(output != null ? output.val : null);

    }
}
