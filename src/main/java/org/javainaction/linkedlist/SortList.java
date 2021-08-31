package org.javainaction.linkedlist;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 *
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 */
public class SortList {
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode middle = getMiddleNode(head);
        ListNode left = sortList(head);
        ListNode right = sortList(middle);
        return mergeList(left, right);
    }

    public static ListNode mergeList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;

        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
                tail = tail.next;
            } else {
                tail.next = right;
                right = right.next;
                tail = tail.next;
            }
        }

        if (left != null) {
            tail.next = left;
        } else {
            tail.next = right;
        }
        return dummy.next;
    }

    public static ListNode getMiddleNode(ListNode head) {
        ListNode slow = null;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow == null ? fast : slow.next;
            fast = fast.next.next;
        }
        //break middle node so as to avoid infinite loop
        ListNode middle = null;
        if (slow != null) {
            middle = slow.next;
            slow.next = null;
        }
        return middle;
    }

    public static void main(String[] args) {
        ListNode head = createLinkedList(new int[]{-1, 5, 3, 4, 0});
        System.out.println("-1,5,3,4,0 after sorting : " + sortList(head));
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
        int val = 0;
        ListNode next;

        ListNode(int value) {
            this.val = value;
        }
    }
    
}
