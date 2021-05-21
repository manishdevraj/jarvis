package org.javainaction.linkedlist;

/**
 * Given a linked list with values in sorted order but with duplicate element
 * Remove duplicate nodes with O(1) space complexity by modifying linked list in-place without creating new element
 */
public class RemoveDuplicateFromLinkedList {

    /**
     * Greedy approach is to use some kind of hashtable to keep track of duplicates
     *
     * @param linkedList
     * @return
     */
    public static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
        LinkedList left = linkedList;
        while (left != null) {
            LinkedList right = left.next;
            while (right != null && left.value == right.value) {
                right = right.next;
            }
            left.next = right;
            left = right;
        }
        return linkedList;
    }

    public static void main(String[] args) {
        LinkedList head = new LinkedList(1);
        head.next = new LinkedList (1);
        head.next.next = new LinkedList (3);
        head.next.next.next = new LinkedList (4);
        head.next.next.next.next = new LinkedList (4);
        head.next.next.next.next.next = new LinkedList (5);
        head = removeDuplicatesFromLinkedList(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }



    private static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
        }
    }
}
