package org.javainaction.linkedlist;

public class ReverseEveryKAlternateElem {
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
        boolean isTurn = true;
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
            // skip 'k' nodes
            for (int i = 0; current != null && i < k; ++i) {
                previous = current;
                current = current.next;
            }

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

        ListNode result = ReverseEveryKAlternateElem.reverse(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}
