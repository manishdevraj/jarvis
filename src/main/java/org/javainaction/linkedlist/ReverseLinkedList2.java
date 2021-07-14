package org.javainaction.linkedlist;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the
 * nodes of the list from position left to position right, and return the reversed list.
 *
 * Example 1
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 * @see ReverseSubList
 */
public class ReverseLinkedList2 {

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        // create a dummy node to mark the head of this list
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //position previous before left count so that it will connect to reversed linked
        ListNode prev = dummy;
        for (int i = 0 ; i < left - 1; i++) {
            prev = prev.next;
        }

        // a pointer to the beginning of a sub-list that will be reversed
        ListNode start = prev.next;
        // a pointer to a node that will be reversed
        ListNode end = start.next;
        // 1 - 2 - 3 - 4 - 5 ; left = 2; right = 4 ---> prev = 1, start = 2, end = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for (int i = 0 ; i < right - left; i++) {
            //move start to end's next 2 -> 4 in first and 2 -> 5 in second iteration
            start.next = end.next;
            //move end to prev's next 3 -> 2 in first and 4 -> 3 in second iteration
            end.next = prev.next;
            //now move prev to new end 1 -> 3 in first and 1 -> 4 in second iteration
            prev.next = end;
            //move end to new location 4 in first and 5 in second iteration
            end = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; prev = 1, start = 2, end = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; prev = 1, start = 2, end = 5 (finish)

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("");
        ListNode result = reverseBetween(head, 2, 4);
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }

        System.out.println("");
        System.out.println("-------------------");

        System.out.println("");
        head = new ListNode(5);
        result = reverseBetween(head, 1, 1);

        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    private static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }
}
