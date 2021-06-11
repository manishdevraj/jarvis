package org.javainaction.bst;

/**
 * Give only 3 nodes 1, 2, 3 but not actual binary search tree, find if
 * Either 1 is parent of 2 and 2 is parent of 3
 * Or 1 is child of 2 and 2 is child of 3
 */
public class ValidateThreeNodes {

    //O(h) time | O(1) space where h is height of the tree
    public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
        if (isDescendant(nodeTwo, nodeOne)) {
            return isDescendant(nodeThree, nodeTwo);
        }

        if (isDescendant(nodeTwo, nodeThree)) {
            return isDescendant(nodeOne, nodeTwo);
        }
        return false;
    }

    //Whether target is a descendant of the node
    public boolean isDescendant(BST node, BST target) {
        while (node != null && node != target) {
            node = (target.value < node.value) ? node.left : node.right;
        }
        return node == target;
    }

    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        var root = new BST(5);
        root.left = new BST(2);
        root.right = new BST(7);
        root.left.left = new BST(1);
        root.left.right = new BST(4);
        root.right.left = new BST(6);
        root.right.right = new BST(8);
        root.left.left.left = new BST(0);
        root.left.right.left = new BST(3);

        var nodeOne = root;
        var nodeTwo = root.left;
        var nodeThree = root.left.right.left;
        boolean expected = true;
        boolean actual = new ValidateThreeNodes().validateThreeNodes(nodeOne, nodeTwo, nodeThree);
        System.out.println(nodeOne + ", " + nodeTwo + ", " + nodeThree
                + " are descendant or ancestor of each other? " + actual);
    }
}
