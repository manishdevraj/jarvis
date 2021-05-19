package org.javainaction.linkedlist;

/**
 * Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.
 *
 * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 */
public class ReverseEveryKElements {
    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

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
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = ReverseEveryKElements.reverse(head, 3);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

}
