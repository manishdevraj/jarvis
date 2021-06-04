package org.javainaction.bt.dfs;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * @see org.javainaction.graph.LowestCommonManager
 */
public class LowestCommonAncestor {
    static TreeNode result = null;
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfsTraversal(root, p, q);
        return result;
    }

    public static boolean dfsTraversal(TreeNode current, TreeNode p, TreeNode q) {
        if (current == null) return false;

        int leftFound = dfsTraversal(current.left, p, q) ? 1 : 0;
        int rightFound = dfsTraversal(current.right, p, q) ? 1 : 0;
        int oneFound = (current == p || current == q) ? 1 : 0;

        if (leftFound + rightFound + oneFound >= 2) {
            result = current;
        }
        return (leftFound + rightFound + oneFound > 0);
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
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        System.out.println("Lower common Ancestor of {2, 4} with root at 6: "
                + lowestCommonAncestor(root, root.left, root.left.right));

        root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.left = new TreeNode(8);

        System.out.println("Lower common Ancestor of {5, 1} with root at 3: "
                + lowestCommonAncestor(root, root.left, root.right));

    }
}
