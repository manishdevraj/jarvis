package org.javainaction.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implement BST traversals technique
 *
 * Pre order traversal
 * In order traversal
 * Post order traversal
 */
public class BSTTraversal {

    public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
        return inOrder(tree, array);
    }

    public static List<Integer> inOrder(BST tree, List<Integer> array) {
        if (tree == null) return array;

        inOrder(tree.left, array);
        array.add(tree.value);
        inOrder(tree.right, array);

        return array;
    }
    public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
        return preOrder(tree, array);
    }

    public static List<Integer> preOrder(BST tree, List<Integer> array) {
        if (tree == null) return array;

        array.add(tree.value);
        preOrder(tree.left, array);
        preOrder(tree.right, array);

        return array;
    }

    public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
        return postOrder(tree, array);
    }

    public static List<Integer> postOrder(BST tree, List<Integer> array) {
        if (tree == null) return array;

        postOrder(tree.left, array);
        postOrder(tree.right, array);
        array.add(tree.value);

        return array;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
    
    public static void main(String[] args) {
        var root = new BST(10);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.right = new BST(5);
        root.right = new BST(15);
        root.right.right = new BST(22);

        var inOrder = Arrays.asList(1, 2, 5, 5, 10, 15, 22);
        var preOrder = Arrays.asList(10, 5, 2, 1, 5, 15, 22);
        var postOrder = Arrays.asList(1, 2, 5, 5, 22, 15, 10);

        System.out.println("inOrderTraverse ->" + inOrderTraverse(root, new ArrayList<>()));
        System.out.println("preOrderTraverse ->" + preOrderTraverse(root, new ArrayList<>()));
        System.out.println("postOrderTraverse ->" + postOrderTraverse(root, new ArrayList<>()));
    }
}
