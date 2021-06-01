package org.javainaction.heap;

import java.util.*;

/**
 * Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.
 *
 * Example 1:
 *
 * Input: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4]
 * Output: [1, 2, 3, 3, 4, 6, 6, 7, 8]
 * Example 2:
 *
 * Input: L1=[5, 8, 9], L2=[1, 7]
 * Output: [1, 5, 7, 8, 9]
 */

public class MergeKSortedLists {
    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode merge(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((n1, n2) -> n1.value - n2.value);

        for (ListNode node : lists) {
            if (node != null)
                minHeap.add(node);
        }

        ListNode newHead = null;
        ListNode newTail = null;

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            if (newHead == null) {
                newHead = newTail = node;
            } else {
                newTail.next = node;
                newTail = newTail.next;
            }

            if (node.next != null) {
                minHeap.add(node.next);
            }
        }

        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        ListNode result = MergeKSortedLists.merge(new ListNode[] { l1, l2, l3 });
        System.out.print("Here are the elements form the merged list: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}

