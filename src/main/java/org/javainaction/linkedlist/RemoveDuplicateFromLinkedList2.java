package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
 * from the original list. Return the linked list sorted as well.
 *
 * In this example we remove all values and not keep single distinct values from duplicate
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 *
 *
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 */
public class RemoveDuplicateFromLinkedList2 {


    public static LinkedList deleteDuplicates(LinkedList head) {
        LinkedList dummy = new LinkedList(-1);
        dummy.next = head;
        LinkedList left = dummy;
        LinkedList right = head;
        while (right != null) {
            //we compare current value and next value and left pointer acts as pre cursor
            while (right.next != null && right.value == right.next.value) {
                right = right.next;

            }

            // if condition proves that we didn't find duplicate so we just move left pointer
            // or else we move it to right.next pointer removing all possible duplicate values
            if (left.next == right) {
                left = left.next;
            } else {
                left.next = right.next;
            }

            right = right.next;

        }
        return dummy.next;
    }

    public static void main(String[] args) {
        LinkedList head = createLinkedList(new int[]{1,2,3,3,4,4,5});
        System.out.println("{1,2,3,3,4,4,5} after removing all duplicates : " + head);
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

    private static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
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
}
