package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 *
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 * Example 2:
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 * Example 3:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 */
public class AddTwoNumbers2 {

    public static LinkedList addTwoNumbers(LinkedList linkedListOne, LinkedList linkedListTwo) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        //other approach could have been to reverse linked list but challenge there is to find longest linked list
        //instead if we use stack then we can keep on popping until one of the list is available to add number
        while (linkedListOne != null) {
            s1.push(linkedListOne.value);
            linkedListOne = linkedListOne.next;
        }

        while (linkedListTwo != null) {
            s2.push(linkedListTwo.value);
            linkedListTwo = linkedListTwo.next;
        }

        //head with dummy value of 0 so same can be used as a placeholder for remainder values, if any
        LinkedList head = new LinkedList(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int digitOne = s1.isEmpty() ? 0 : s1.pop();
            int digitTwo = s2.isEmpty() ? 0 : s2.pop();
            int sum = head.value + digitOne + digitTwo;
            head.value = sum % 10;
            LinkedList remainder = new LinkedList(sum / 10);
            remainder.next = head;
            head = remainder;
        }

        return head.value == 0 ? head.next : head;
    }
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            List<Integer> result = new ArrayList<>();
            result.add(value);
            LinkedList node = next;
            while (node != null) {
                result.add(node.value);
                node = node.next;
            }
            return Arrays.toString(result.toArray());
        }
    }

    public static void main(String[] args) {
        LinkedList listOne = createLinkedList(new int[]{7,2,4,3});
        LinkedList listTwo = createLinkedList(new int[]{5,6,4});
        System.out.println("{7,2,4,3} and {5,6,4} sum is : " + addTwoNumbers(listOne, listTwo));
    }

    public static LinkedList createLinkedList(int[] array) {
        LinkedList node;
        LinkedList prev = null;
        LinkedList head = null;
        for (int value : array) {
            node = new LinkedList(value);
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
