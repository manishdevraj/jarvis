package org.javainaction.fastslow;

/**
 * Given the head of a Singly LinkedList that contains a cycle, write a function to find the starting node of the cycle.
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
 * is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
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
