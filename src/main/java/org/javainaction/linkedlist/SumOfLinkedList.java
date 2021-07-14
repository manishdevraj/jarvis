package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * {2, 4, 7, 1}
 *
 * {9, 4, 5}
 *
 * 1742 + 549 = 2291
 *
 * Output : [1, 9, 2, 2]
 *
 * @see AddTwoNumbers2
 */
public class SumOfLinkedList {
    public static LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
        LinkedList head = new LinkedList(0);
        LinkedList current = head;
        int sum = 0;

        while (linkedListOne != null || linkedListTwo != null || sum != 0) {

            int digitOne = linkedListOne != null ? linkedListOne.value : 0;
            int digitTwo = linkedListTwo != null ? linkedListTwo.value : 0;

            sum += digitOne + digitTwo;
            LinkedList node = new LinkedList((sum) % 10);
            sum = sum / 10;

            current.next = node;
            current = node;

            linkedListOne = (linkedListOne != null) ? linkedListOne.next : null;
            linkedListTwo = (linkedListTwo != null) ? linkedListTwo.next : null;
        }


        return head.next;
    }

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
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

    public static void main(String[] args) {
        LinkedList listOne = createLinkedList(new int[]{2, 4, 7, 1});
        LinkedList listTwo = createLinkedList(new int[]{9, 4, 5});
        System.out.println("{2, 4, 7, 1} and {9, 4, 5} sum is : " + sumOfLinkedLists(listOne, listTwo));
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
}
