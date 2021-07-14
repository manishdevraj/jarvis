package org.javainaction.linkedlist;

import java.util.*;

/**
 * Write function that takes in two sorted linked list and return merge linked list in sorted order
 * this program takes O(n + m) time | O(1) space n is nodes in first list and m is nodes in second list
 *
 * For //O(nk Logk) time | O(k) space refer to Merge K sorted list example using Heap
 * @see org.javainaction.heap.MergeKSortedLists for Min heap example
 */
public class MergeTwoLinkedList {


    //O(n + m) time | O(1) space n is nodes in first list and m is nodes in second list
    public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
        if (headOne == null && headTwo == null) return null;
        if (headOne == null) return headTwo;
        if (headTwo == null) return headOne;

        LinkedList p1 = headOne;
        LinkedList p2 = headTwo;
        LinkedList p1Prev = null;
        while (p1 != null && p2 != null) {
            if (p1.value < p2.value) {
                p1Prev = p1;
                p1 = p1.next;
            } else {
                if (p1Prev != null ) p1Prev.next = p2;
                p1Prev = p2;
                p2 = p2.next;
                p1Prev.next = p1;
            }
        }
        if (p1 == null) {
            p1Prev.next = p2;
        }
        return headOne.value < headTwo.value ? headOne : headTwo;
    }

    //with this logic we can even merge k sorted linked list
    public static LinkedList mergeLinkedListsUsingHeap(LinkedList headOne, LinkedList headTwo) {
        PriorityQueue<LinkedList> priorityQueue = new PriorityQueue<>(new Comparator<LinkedList>() {
            @Override
            public int compare(LinkedList a, LinkedList b) {
                return a.value - b.value;
            }
        });
        LinkedList head = null;
        LinkedList tail = null;

        priorityQueue.add(headOne);
        priorityQueue.add(headTwo);

        while (!priorityQueue.isEmpty()) {
            LinkedList top = priorityQueue.peek();
            priorityQueue.remove();

            if (top != null && top.next != null) {
                priorityQueue.add(top.next);
            }

            //base case when we got empty list
            if (head == null) {
                head = top;
            } else {
                //point tail to popped element
                tail.next = top;
            }
            //move tail to popped element
            tail = top;
        }

        return head;
    }

    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
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
        LinkedList l1 = new LinkedList(2);
        l1.next = new LinkedList(6);
        l1.next.next = new LinkedList(8);

        LinkedList l2 = new LinkedList(3);
        l2.next = new LinkedList(6);
        l2.next.next = new LinkedList(7);


        System.out.println("{2, 6, 8} and {3, 6, 7} after merging : " + mergeLinkedLists(l1, l2));

        l1 = new LinkedList(2);
        l1.next = new LinkedList(6);
        l1.next.next = new LinkedList(8);

        l2 = new LinkedList(3);
        l2.next = new LinkedList(6);
        l2.next.next = new LinkedList(7);


        System.out.println("{2, 6, 8} and {3, 6, 7} after merging : " + mergeLinkedListsUsingHeap(l1, l2));

    }
}
