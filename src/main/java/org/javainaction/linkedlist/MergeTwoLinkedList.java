package org.javainaction.linkedlist;

/**
 * @see MergeKSortedLists for Min heap example
 */
public class MergeTwoLinkedList {
    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkedList l1 = new LinkedList(2);
        l1.next = new LinkedList(6);
        l1.next.next = new LinkedList(8);

        LinkedList l2 = new LinkedList(3);
        l2.next = new LinkedList(6);
        l2.next.next = new LinkedList(7);


        LinkedList result = mergeLinkedLists(l1, l2);
        System.out.print("Here are the elements form the merged list: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    //O(n + m) time | O(1) space n is nodes in first list and m is nodes in second list
    public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
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
        if (p1 == null) p1Prev.next = p2;
        return headOne.value < headTwo.value ? headOne : headTwo;
    }
}
