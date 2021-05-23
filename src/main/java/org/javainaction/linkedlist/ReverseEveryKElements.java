package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.
 *
 * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 * {1, 2, 3, 4, 5, 6, 7, 8} with  K=3
 *
 * Output: [3, 2, 1, 6, 5, 4, 8, 7]
 * @see ReverseSubList
 */
public class ReverseEveryKElements {

    public static ListNode reverse(ListNode head, int k) {
        if (k < 1 || head == null) return head;
        ListNode current = head, previous = null;
        while (true) {
            if (current == null) break;
            ListNode lastNodeOfFirstPart = previous;
            ListNode lastNodeOfSubList = current;
            ListNode next = null;
            for (int i = 0; current != null && i < k; i++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            if (lastNodeOfFirstPart != null) lastNodeOfFirstPart.next = previous;
            else head = previous;

            // connect with the next part
            lastNodeOfSubList.next = current;

            previous = lastNodeOfSubList;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});

        System.out.println("{1, 2, 3, 4, 5} reverse every K=3 elements : " + reverse(head, 3));
        
        head = createLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});

        System.out.print("{1, 2, 3, 4, 5, 6, 7, 8} reverse every K=3 elements : " + reverse(head, 3));
    }

    private static class ListNode {
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

    public static ListNode createLinkedList(int[] array) {
        ListNode node;
        ListNode prev = null;
        ListNode head = null;
        for (int value : array) {
            node = new ListNode(value);
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
