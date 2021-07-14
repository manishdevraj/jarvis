package org.javainaction.bt;

import java.util.*;

/**
 * Right sibling tree is obtained by making every node in binary tree with right/next pointer pointer to right
 * sibling instead of right child. A node's right sibling is the node immediately to the right on same level
 * or null if there is none.
 *
 * Once the transformation is complete some nodes may no longer have incoming link and would be unreachable like
 * 10 in below example
 *
 *
 *                      1
 *            2                 3
 *         4    5           6       7
 *      8   9     10     11      12   13
 *                     14
 *
 *                     Output:
 *
 *                     1-------
 *            2-----------------3
 *         4----5-----------6-------7
 *      8----9     10-----11      12------13
 *                     14-----
 *
 */
public class RightSiblingTree {
    // O(n) time | O(d) space where d is depth and n is numer of nodes
    public static BinaryTree rightSiblingTree(BinaryTree root) {
        mutate(root, null, false);
        return root;
    }

    public static void mutate(BinaryTree node, BinaryTree parent, boolean isLeftChild) {
        if (node == null) return;

        BinaryTree left = node.left;
        BinaryTree right = node.right;

        mutate(left, node, true);

        if (parent == null) {
            // no right sibling as we got no parent
            node.right = null;
        } else if (isLeftChild) {
            //make left point to parent right child
            node.right = parent.right;
        } else {
            //right pointers are tricky
            //we got no parent right child to use
            if (parent.right == null) {
                node.right = null;
            } else {
                //we need to make sure we do not self join as for right node parent.right would be node it self
                //we need to go 2 level up to jump into other side of the tree
                node.right = parent.right.left;
            }
        }

        mutate(right, node, false);
    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(1);
        insert(root, new int[] {2, 3, 4, 5, 6, 7, 8, 9});
        root.left.right.right = new BinaryTree(10);
        root.right.left.left = new BinaryTree(11);
        root.right.right.left = new BinaryTree(12);
        root.right.right.right = new BinaryTree(13);
        root.right.left.left.left = new BinaryTree(14);
        System.out.println(getDfsOrder(root));
        BinaryTree mutatedRoot = rightSiblingTree(root);
        List<Integer> actual = getDfsOrder(mutatedRoot);
        System.out.println("Right sibling tree");
        var expected = Arrays.asList(1, 2, 4, 8, 9, 5, 6, 11, 14, 7, 12, 13, 3, 6, 11, 14, 7, 12, 13);
        System.out.println(actual);
    }

    private static void insert(BinaryTree root, int[] values) {
        insert(root, values, 0);
    }

    private static void insert(BinaryTree root, int[] values, int i) {
        if (i >= values.length) {
            return;
        }
        Deque<BinaryTree> queue = new ArrayDeque<BinaryTree>();
        queue.addLast(root);
        while (queue.size() > 0) {
            BinaryTree current = queue.pollFirst();
            if (current.left == null) {
                current.left = new BinaryTree(values[i]);
                break;
            }
            queue.addLast(current.left);
            if (current.right == null) {
                current.right = new BinaryTree(values[i]);
                break;
            }
            queue.addLast(current.right);
        }
        insert(root, values, i + 1);
    }

    private static List<Integer> getDfsOrder(BinaryTree tree) {
        List<Integer> values = new ArrayList<Integer>();
        values.add(tree.value);
        if (tree.left != null) {
            values.addAll(getDfsOrder(tree.left));
        }
        if (tree.right != null) {
            values.addAll(getDfsOrder(tree.right));
        }
        return values;
    }

    private static class BinaryTree {
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
