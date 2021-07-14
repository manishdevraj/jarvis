package org.javainaction.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary tree where node has an additional pointer 'parent'.
 *
 * Node's successor is the next node to be visited when traversing its tree with in-order traversal technique.
 *
 * If there no successor return null
 *
 *              1
 *            2   3
 *         4   5
 *       6
 *
 * Input = 5 successor is 1
 *
 */
public class FindSuccessor {

    /**
     * We can solve this in two ways :
     * 1. In order traversal and store each node in a list, then find node in list and return its successor
     *
     * 2. Because we have parent node - if node has right child then left most element in right subtree is a successor
     * if there is not right child then use parent pointer and visit back to the visited node which is immediate parent.
     * Parent of parent's node is successor which is 1 in above case.
     */

    //O(h) time | O(1)
    public static BinaryTree findSuccessorByParentPointer(BinaryTree tree, BinaryTree node) {
        if (node == null) return node;
        if (node.right != null) return getLeftLeafFromRightSubtree(node.right);
        return getRightMostParent(node);
    }

    public static BinaryTree getLeftLeafFromRightSubtree(BinaryTree current) {
        //if there is not leaf leaf in this tree then current node (right of input) would be successor
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public static BinaryTree getRightMostParent(BinaryTree current) {
        while (current.parent != null
                //we have already visited parent in case of 5 current is 5 and parent is 2 but right if 2 is self so
                //we try and find unvisited node as this is in order traversal if we are at right node,
                //then parent is already visited but find grand parent (1) that isn't yet
                && current.parent.right == current) {
            current = current.parent;
        }
        return current.parent;
    }

    //O(n) time | O(n) space
    public static BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
        List<BinaryTree> inorder = new ArrayList<>();
        getInOrderTraversal(tree, inorder);
        boolean nodeFound = false;
        for(BinaryTree current : inorder) {
            if (nodeFound) return current;
            if (current == node) nodeFound = true;
        }
        return null;
    }

    public static void getInOrderTraversal(BinaryTree tree, List<BinaryTree> inorder) {
        if (tree == null) return;
        getInOrderTraversal(tree.left, inorder);
        inorder.add(tree);
        getInOrderTraversal(tree.right, inorder);
    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.left.parent = root;
        root.right = new BinaryTree(3);
        root.right.parent = root;
        root.left.left = new BinaryTree(4);
        root.left.left.parent = root.left;
        root.left.right = new BinaryTree(5);
        root.left.right.parent = root.left;
        root.left.left.left = new BinaryTree(6);
        root.left.left.left.parent = root.left.left;
        BinaryTree node = root.left.right;
        BinaryTree expected = root;
        System.out.println("Find successor of node 5 by in order using O(n) space "
                + findSuccessor(root, node));
        System.out.println("Find successor of node 5 by parent pointer using O(1) space "
                + findSuccessorByParentPointer(root, node));
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public BinaryTree parent = null;

        public BinaryTree(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "value=" + value + '}';
        }
    }
}
