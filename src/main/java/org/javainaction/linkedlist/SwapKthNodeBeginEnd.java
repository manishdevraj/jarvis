package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a singly linked list, swap kth node from beginning with kth node from end. Swapping of data is not allowed,
 * only pointers should be changed.
 */
public class SwapKthNodeBeginEnd {
    public static int countNodes(Node node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    private static Node swapKthNode(Node head, int k) {
        if (head == null) return null;

        int count = countNodes(head);
        if (count < k) return null;
        if ( 2 * k - 1 == count) return head;

        Node left = head;
        Node leftPrev = null;

        for (int i = 1; i < k; i++) {
            leftPrev = left;
            left = left.next;
        }

        Node right = head;
        Node rightPrev = null;

        for (int i = 1; i < count - k + 1; i++) {
            rightPrev = right;
            right = right.next;
        }

        //swap node pointers if their previous element exist
        // 7 -> 1
        if (leftPrev != null) leftPrev.next = right;
        if (rightPrev != null) rightPrev.next = left;

        //swap places
        //8 -> 2
        //1 -> null
        Node temp = left.next;
        left.next = right.next;
        right.next = temp;

        if (k == 1) head = right; // as right would be new head
        if (k == count) head = left; //as left would be new head we are reversing entire list

        return head;
    }

    public static void main(String[] arg) {
        Node head = createLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println(head.toString() + " swapped at k = " + 1 + " " + swapKthNode(head, 1)) ;
    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            List<Integer> result = new ArrayList<>();
            result.add(value);
            Node node = next;
            while (node != null) {
                result.add(node.value);
                node = node.next;
            }
            return Arrays.toString(result.toArray());
        }
    }

    public static Node createLinkedList(int[] array) {
        Node node;
        Node prev = null;
        Node head = null;
        for (int value : array) {
            node = new Node(value);
            if (head == null) {
                head = node;
            }
            if (prev != null) {
                prev.next = node;
            }
            prev = node;
        }
        return head;
    }
}
