package org.javainaction.linkedlist;

/**
 * Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position ‘p’ to ‘q’.
 *
 * Problem 1: Reverse the first ‘k’ elements of a given LinkedList.
 *
 * Solution: This problem can be easily converted to our parent problem; to reverse the first ‘k’ nodes of the list,
 * we need to pass p=1 and q=k.
 *
 * Problem 2: Given a LinkedList with ‘n’ nodes, reverse it based on its size in the following way:
 *
 * If ‘n’ is even, reverse the list in a group of n/2 nodes.
 * If n is odd, keep the middle node as it is, reverse the first ‘n/2’ nodes and reverse the last ‘n/2’ nodes.
 * Solution: When ‘n’ is even we can perform the following steps:
 *
 * Reverse first ‘n/2’ nodes: head = reverse(head, 1, n/2)
 * Reverse last ‘n/2’ nodes: head = reverse(head, n/2 + 1, n)
 * When ‘n’ is odd, our algorithm will look like:
 *
 * head = reverse(head, 1, n/2)
 * head = reverse(head, n/2 + 2, n)
 * Please note the function call in the second step. We’re skipping two elements as we will be skipping the middle
 * element.
 */
public class ReverseSubList {
    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverse(ListNode head, int p, int q) {
        if (p == q) return head;
        // after skipping 'p-1' nodes, current will point to 'p'th node
        ListNode current = head, previous = null;
        for (int i = 0; current != null && i < p  - 1; ++i) {
            previous = current;
            current = current.next;
        }

        // we are interested in three parts of the LinkedList,
        //part before index 'p', part between 'p' and
        // 'q', and the part after index 'q'
        ListNode lastNodeOfFirstPart = previous; // points to the node at index 'p-1'
        // after reversing the LinkedList 'current' will become the last node of the sub-list
        ListNode lastNodeOfSubList = current;
        ListNode next = null; // will be used to temporarily store the next node
        // reverse nodes between 'p' and 'q'
        for(int i = 0; current != null && i < q - p + 1; ++i) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // connect with the first part
        if (lastNodeOfFirstPart != null)
            lastNodeOfFirstPart.next = previous; // 'previous' is now the first node of the sub-list
        else // this means p == 1 i.e., we are changing the first node (head) of the LinkedList
            head = previous;

        // connect with the last part
        lastNodeOfSubList.next = current;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = ReverseSubList.reverse(head, 2, 4);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}
