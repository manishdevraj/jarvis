package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 * THIS DIFFERENT CONDITION WHERE WE NEED TO LEAVE LESS THAN k NODES AS IS
 * <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * Example 3:
 *
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 * Example 4:
 *
 * Input: head = [1], k = 1
 * Output: [1]
 */
public class ReverseNodesInKGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k < 1 || head == null) return head;
        ListNode current = head, previous = null;

        while (true) {
            if (current == null) break;

            int count = 0;
            ListNode cur = current;

            /**
             * This check could be improved but need to change complete algorithm
             */
            while(cur != null){
                cur = cur.next;
                count++;
            }

            if (count >= k) {
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
                else if (lastNodeOfFirstPart == null) head = previous;

                //connect with next part
                if (lastNodeOfSubList != null)
                    lastNodeOfSubList.next = current;

                previous = lastNodeOfSubList;
            } else {
                break;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.println("{1, 2, 3, 4, 5} reverse every alternate K=3 elements : " + reverseKGroup(head, 3));

        head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.println("{1, 2, 3, 4, 5} reverse every alternate K=2 elements : " + reverseKGroup(head, 2));
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
