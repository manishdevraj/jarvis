package org.javainaction.bst;

import java.util.*;

/**
 * Give a preorder of a BST, construct its original BST
 *
 * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree),
 * construct the tree and return its root.
 *
 * It is guaranteed that there is always possible to find a binary search tree with the given requirements for
 * the given test cases.
 *
 * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less
 * than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
 *
 * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses
 * Node.right.
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 * Example 2:
 *
 * Input: preorder = [1,3]
 * Output: [1,null,3]
 *
 * @see SerializeDeserializeBST where deserialize uses the same concept
 */
public class ReconstructBSTFromPreOrder {

    //O(n) time | O(n) space
    public BST reconstructBstDfsMain(List<Integer> preOrderTraversalValues) {
        if (preOrderTraversalValues == null || preOrderTraversalValues.size() == 0)
            return null;
        Queue<Integer> queue = new LinkedList<>(preOrderTraversalValues);
        return reconstructBstDfs(queue, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    public BST reconstructBstDfs(Queue<Integer> queue, int minValue,
                                 int maxValue){

        if (queue.isEmpty()) return null;

        int current = queue.peek();
        //if values are out of bounds then not part of this node's subtree
        if (current < minValue || current >= maxValue) return null;
        BST node = new BST(queue.poll());
        //user pre order to build BST
        node.left = reconstructBstDfs(queue, minValue, current);
        node.right = reconstructBstDfs(queue, current, maxValue);
        return node;
    }

    //O(n^2) time | O(n) space
    public BST reconstructBst(List<Integer> preOrderTraversalValues) {
        if (preOrderTraversalValues.size() == 0) return null;

        int current = preOrderTraversalValues.get(0);
        int rightRootIndex = preOrderTraversalValues.size();

        //same are earlier example but instead of comparing bounds we check next root right index and divide
        // tree between left tree : between 1... right index
        // right tree : 4... size of pre order array
        //find first root index of current node
        // [8, 5, 1, 7, 10, 12] //value
        // [0, 1, 2, 3, 4, 5] //index

        //[8, 5, 1, 7, 10, 12]
        //iteration 1:... cur : 0 and right index : 4, leftTree(1,4), rightTree(4, 6) => yields BST(8)
        // [5, 1, 7]
        //iteration 1.1:... cur : 0 and right index : 2, leftTree(1,2), rightTree(2, 2) => yields BST(5)
        // [1, 7]
        //iteration 1.2:... cur : 0 and right index : 1, leftTree(1,1), rightTree(1, 1) => yields BST(1)
        // [7]
        //iteration 1.3:... cur : 0 and right index : 1  => yields BST(7)
        // [10, 12]
        //iteration 2:... cur : 0 and right index : 1, leftTree(1,1), rightTree(1, 1) => yields BST(10)
        // [7]
        //iteration 1.2:... cur : 0 and right index : 1  => yields BST(12)
        
        for (int i = 1; i < preOrderTraversalValues.size(); i++) {
            int value = preOrderTraversalValues.get(i);
            if (value >= current) {
                rightRootIndex = i;
                break;
            }
        }

        //left sub tree is between current and right root index
        BST leftSubtree = reconstructBst(preOrderTraversalValues.subList(1, rightRootIndex));
        //right sub tree is between right root index and remaining order
        BST rightSubtree = reconstructBst(preOrderTraversalValues.subList(rightRootIndex, preOrderTraversalValues.size()));

        BST bst = new BST(current);
        bst.left = leftSubtree;
        bst.right = rightSubtree;

        return bst;
    }

    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public List<Integer> getDfsOrder(BST node, List<Integer> values) {
        values.add(node.value);
        if (node.left != null) {
            getDfsOrder(node.left, values);
        }
        if (node.right != null) {
            getDfsOrder(node.right, values);
        }
        return values;
    }

    public static void main(String[] args) {
        List<Integer> preOrderTraversalValues =
                new ArrayList<>(Arrays.asList(10, 4, 2, 1, 3, 17, 19, 18));
        BST tree = new BST(10);
        tree.left = new BST(4);
        tree.left.left = new BST(2);
        tree.left.left.left = new BST(1);
        tree.left.right = new BST(3);
        tree.right = new BST(17);
        tree.right.right = new BST(19);
        tree.right.right.left = new BST(18);
        List<Integer> expected = new ReconstructBSTFromPreOrder().getDfsOrder(tree, new ArrayList<>());
        var actual = new ReconstructBSTFromPreOrder().reconstructBst(preOrderTraversalValues);
        List<Integer> actualValues = new ReconstructBSTFromPreOrder().getDfsOrder(actual, new ArrayList<>());
        System.out.println("Reconstructed BST partition method " + actualValues);

        actual = new ReconstructBSTFromPreOrder().reconstructBstDfsMain(preOrderTraversalValues);
        actualValues = new ReconstructBSTFromPreOrder().getDfsOrder(actual, new ArrayList<>());
        System.out.println("Reconstructed BST DFS method " + actualValues);
    }
}
