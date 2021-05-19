package org.javainaction.fastslow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.
 *
 * If the total number of nodes in the LinkedList is even, return the second middle node.
 *
 * Example 1:
 *
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> null
 * Output: 3
 * Example 2:
 *
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 * Output: 4
 * Example 3:
 *
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
 * Output: 4
 */



public class MiddleOfLinkedList {

    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            List<Integer> result = new ArrayList<>();
            result.add(value);
            ListNode node = next;
            while (node != null) {
                result.add(node.value);
                node = node.next;
            }
            return Arrays.toString(result.toArray());
        }
    }

    public static ListNode findMiddle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
            if (slowPointer == fastPointer) break;
        }
        return slowPointer;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);
    }
}
