package org.javainaction.fastslow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find if linked list forms a palindrome linked list
 * {0, 1, 2, 2, 1, 0} forms a palindrome
 */
public class PalindromicLinkedList {

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

    public static boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //following allows us to find the middle node either when list is odd or even
        //where slow points to middle node
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //traverse from slow up to end of list to later match it with first half
        //this head is needed to traverse from reversed linked list
        ListNode headSecondHalf = reverseList(slow); // reverse the second half

        while (head != null && headSecondHalf != null) {
            //we did not find the matching value then it cannot make a palindrome
            if (head.value != headSecondHalf.value) return false;
            head = head.next;
            headSecondHalf = headSecondHalf.next;
        }
        return true;
    }

    private static ListNode reverseList(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));

        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + isPalindrome(head));
    }
}
