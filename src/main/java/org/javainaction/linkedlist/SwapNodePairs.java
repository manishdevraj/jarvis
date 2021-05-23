package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 */
public class SwapNodePairs {

    /**
     * O(n) time | O(1) space
     * If we use recursion then O(n) space would be used
     * @param head
     * @return
     */

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode previous = dummy;

        while (previous.next != null && previous.next.next != null) {
            ListNode firstNode = previous.next;
            ListNode secondNode = previous.next.next;

            // prev -> firstNode -> secondNode -> x
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            previous.next = secondNode;
            // prev -> secondNode-> firstNode -> x

            //point last pair tail to first node;
            previous = firstNode;
        }
        return dummy.next;
    }

    public static ListNode nodeSwap(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode nextNode = head.next;
        head.next = nodeSwap(head.next.next);
        nextNode.next = head;

        //next node (bottom of the stack) has become new head
        return nextNode;
    }

    public static void main(String[] args) {
        ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("1, 2, 3, 4, 5, 6 after swapping pairs : " + swapPairs(head));

        head = createLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("1, 2, 3, 4, 5, 6 7 after swapping pairs : " + swapPairs(head));

        head = createLinkedList(new int[]{1});
        System.out.println("1, 2, 3, 4, 5, 6 7 after swapping pairs : " + swapPairs(head));
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
}
