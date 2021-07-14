package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a program that takes in head of singly linked list and an integer k and rearranges linked list in place
 * such that all values with less than K to the left of K and all values greater than K to the right of K value.
 *
 * {3, 0, 5, 2, 1, 4} with K = 3 would yield
 *
 * {0, 2, 1, 3, 5, 4}
 */
public class RearrangeLinkedList {

    public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
        LinkedList smallHead = null;
        LinkedList smallTail = null;
        LinkedList middleHead = null;
        LinkedList middleTail = null;
        LinkedList bigHead = null;
        LinkedList bigTail = null;

        LinkedList node = head;
        while (node != null) {
            if (node.value == k) {
                NodePair midPair = growLinkedList(middleHead, middleTail, node);
                middleHead = midPair.head;
                middleTail = midPair.tail;
            } else if (node.value < k) {
                NodePair smallPair = growLinkedList(smallHead, smallTail, node);
                smallHead = smallPair.head;
                smallTail = smallPair.tail;
            } else {
                NodePair bigPair = growLinkedList(bigHead, bigTail, node);
                bigHead = bigPair.head;
                bigTail = bigPair.tail;
            }
            LinkedList prev = node;
            node = node.next;
            prev.next = null; //break link of the node we found out
        }

        NodePair firstPair = connectLinkedList(smallHead, smallTail, middleHead, middleTail);
        NodePair finalPair = connectLinkedList(firstPair.head, firstPair.tail, bigHead, bigTail);
        return finalPair.head;
    }

    public static NodePair connectLinkedList(LinkedList headOne, LinkedList tailOne,
                                             LinkedList headTwo, LinkedList tailTwo) {
        LinkedList newHead = headOne == null ? headTwo : headOne;
        LinkedList newTail = tailOne == null ? tailTwo : tailOne;


        if (tailOne != null) {
            //this is to make sure we traverse to end of second list when merge happened in previous iteration
            //when we connected 0 -> 2 -> 1 to 3 -> null we got tail still pointing to 1 -> 3
            //for mid to high merge we need to go up to 3 to make new trailing tail and then point 3 to
            //new head 5 -> 4
            while (tailOne.next != null) tailOne = tailOne.next;
            tailOne.next = headTwo;
        }
        return new NodePair(newHead, newTail);
    }

    public static NodePair growLinkedList(
            LinkedList head, LinkedList tail, LinkedList node){
        LinkedList newHead = head;

        if (newHead == null) newHead = node;
        if (tail != null) tail.next = node;

        return new NodePair(newHead, node);
    }

    static class NodePair {
        public LinkedList head;
        public LinkedList tail;

        public NodePair(LinkedList head, LinkedList tail) {
            this.head = head;
            this.tail = tail;
        }

    }

    public static void main(String[] args) {
        LinkedList head = createLinkedList( new int[] {3, 0, 5, 2, 1, 4});
        System.out.println("{3, 0, 5, 2, 1, 4} rearranging with 3 : " + rearrangeLinkedList(head, 3));
    }

    public static LinkedList createLinkedList(int[] array) {
        LinkedList node;
        LinkedList prev = null;
        LinkedList head = null;
        for (int value : array) {
            node = new LinkedList(value);
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

    private static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
        }

        @Override
        public String toString() {
            List<Integer> result = new ArrayList<>();
            result.add(value);
            LinkedList node = next;
            while (node != null) {
                result.add(node.value);
                node = node.next;
            }
            return Arrays.toString(result.toArray());
        }
    }
}
