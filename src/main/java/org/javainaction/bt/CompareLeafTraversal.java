package org.javainaction.bt;

import java.util.Stack;

/**
 * Given two binary tree find if their leaf node traversal is same. Leaf node traversal is considered to be all nodes
 * from left to right who doesn't have any child node.
 * input1:
 *                          1
 *                   2        3
 *               4       5       6
 *                    7    8
 * input2:
 *                         1
 *                     2      3
 *                  4    7       5
 *                            8    6
 *
 *  Output: true because 4, 7, 8, 6 leaf node traversal of both trees match
 *
 *
 */
public class CompareLeafTraversal {
    //O(n+m) time and  O(h1 + h2) space
    public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {
        Stack<BinaryTree> stack1 = new Stack<>();
        Stack<BinaryTree> stack2 = new Stack<>();
        stack1.push(tree1);
        stack2.push(tree2);
        while (!stack1.isEmpty() && !stack2.isEmpty()){
            BinaryTree leaf1 = getLeafNode(stack1);
            BinaryTree leaf2 = getLeafNode(stack2);

            if (leaf1.value != leaf2.value) return false;
        }
        return stack1.size() == 0 && stack2.size() == 0;
    }

    private BinaryTree getLeafNode(Stack<BinaryTree> stack) {
        BinaryTree current = stack.pop();
        //until we get a leaf node either left or right
        while (!(current.left == null && current.right == null)) {
            if (current.right != null) stack.push(current.right);
            //purposely add left last so it gets pulled out of the stack first from left to right order
            if (current.left != null) stack.push(current.left);
            current = stack.pop();
        }
        return current;
    }

    //O(n+m) time and  O(Max(h1,h2)) space
    public boolean compareLeafTraversalInSpace(BinaryTree tree1, BinaryTree tree2) {
        BinaryTree leafNodeLinkedList1 = connectAllLeafNodes(tree1, null, null)[0];
        BinaryTree leafNodeLinkedList2 = connectAllLeafNodes(tree2, null, null)[0];

        BinaryTree currentOne = leafNodeLinkedList1;
        BinaryTree currentTwo = leafNodeLinkedList2;

        while (currentOne != null && currentTwo != null) {
            if (currentOne.value != currentTwo.value) return false;
            currentOne = currentOne.right;
            currentTwo = currentTwo.right;
        }

        return currentOne == null && currentTwo == null;
    }

    public BinaryTree[] connectAllLeafNodes(BinaryTree current,
                                            BinaryTree head, BinaryTree previous) {

        if (current == null) return new BinaryTree[] {head, previous};

        if ((current.left == null && current.right == null)){
            if (previous == null) head = current;
            else previous.right = current;

            previous = current;
        }
        BinaryTree[] nodes = connectAllLeafNodes(current.left, head, previous);
        BinaryTree leftHead = nodes[0];
        BinaryTree leftPrevious = nodes[1];

        return connectAllLeafNodes(current.right, leftHead, leftPrevious);
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree(1);
        tree1.left = new BinaryTree(2);
        tree1.right = new BinaryTree(3);
        tree1.left.left = new BinaryTree(4);
        tree1.left.right = new BinaryTree(5);
        tree1.right.right = new BinaryTree(6);
        tree1.left.right.left = new BinaryTree(7);
        tree1.left.right.right = new BinaryTree(8);

        BinaryTree tree2 = new BinaryTree(1);
        tree2.left = new BinaryTree(2);
        tree2.right = new BinaryTree(3);
        tree2.left.left = new BinaryTree(4);
        tree2.left.right = new BinaryTree(7);
        tree2.right.right = new BinaryTree(5);
        tree2.right.right.left = new BinaryTree(8);
        tree2.right.right.right = new BinaryTree(6);

        var expected = true;
        var actual = new CompareLeafTraversal().compareLeafTraversal(tree1, tree2);
        System.out.println("Both tree leaf node traversal is equal? : " + actual);

        System.out.println("Both tree leaf node traversal is equal? : "
                + new CompareLeafTraversal().compareLeafTraversalInSpace(tree1, tree2));
    }
}
