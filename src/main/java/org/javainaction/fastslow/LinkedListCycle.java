package org.javainaction.fastslow;

/**
 * Given the head of a Singly LinkedList that contains a cycle, write a function to find the starting node of the cycle.
 */
public class LinkedListCycle {
    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    // F = X -> d + p
    // S = 2X -> 2d + 2p
    // t = 2d + p
    // r = t - p - d
    // r = 2d + p - p - d = d
    // move F to head and travel till d
    // O(n) time | O(1) space
    public static ListNode findCycleStart(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
            if (slowPointer == fastPointer) break;
        }

        slowPointer = head;

        while (slowPointer != fastPointer) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }

        return slowPointer;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycle.findCycleStart(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycle.findCycleStart(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + LinkedListCycle.findCycleStart(head).value);
    }
}
