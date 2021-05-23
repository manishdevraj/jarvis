package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Follow up: Could you do this in one pass?
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 */
public class RemoveKthNodeFromEnd {
    public static void main(String[] args) {
        LinkedList list = createLinkedList(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        LinkedList list2 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        LinkedList list3 = createLinkedList(new int[]{1});
        LinkedList list4 = createLinkedList(new int[]{1, 2});

        removeKthNodeFromEnd(list, 10);
        System.out.println("{0, 1, 2, 3, 4, 5, 6, 7, 8, 9} after removal of 10th node " + list);

        list = createLinkedList(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        removeKthNodeFromEndV2(list, 10);
        System.out.println("{0, 1, 2, 3, 4, 5, 6, 7, 8, 9} after removal of 10th node " + list);

        removeKthNodeFromEnd(list2, 2);
        System.out.println("{1, 2, 3, 4, 5} after removal of 2nd node " + list2);

        list2 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        removeKthNodeFromEndV2(list2, 2);
        System.out.println("{1, 2, 3, 4, 5} after removal of 2nd node " + list2);
    }

    public static void removeKthNodeFromEndV2(LinkedList head, int k) {
        LinkedList start = new LinkedList(-1);
        start.next = head;
        LinkedList slow = start;
        LinkedList fast = start;

        for(int i = 1 ; i <= k + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }


        if(slow == start) {
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        } else {
            slow.next = slow.next.next;
        }
    }

    public static void removeKthNodeFromEnd(LinkedList head, int k) {
        LinkedList first = head;
        LinkedList second = head;
        int count = 1;
        while (second != null && count <=k) {
            second = second.next;
            count++;
        }

        if(second == null) {
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        }

        while (second.next != null ){
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;
    }

    static class LinkedList {
        int value;
        LinkedList next = null;
        public LinkedList(int value) {
            this.value = value;
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

    private static LinkedList createLinkedList(int[] array) {
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
