package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.
 *
 * For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 2 -> 8 -> 10, return the node with value 8.
 *
 * In this example, assume nodes with the same value are the exact same node objects.
 *
 * Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.
 */
public class LinkedListIntersection {
    static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LinkedList that = (LinkedList) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static LinkedList findIntersectionWithoutLeg(LinkedList headA, LinkedList headB) {
        if (headA == null || headB == null) return null;
        LinkedList nodeA = headA;
        LinkedList nodeB = headB;
        while (nodeA != nodeB) {
            /**
             * this hack because above not equality is not working
             * remove this when Linked list can be equated using ==
             */
            if (nodeA != null && nodeB != null && nodeA.value == nodeB.value) return nodeA;
            //if we traverse both list one after other, and if they are intersecting then we will reach intersection
            //or else we will come out of it by traversing both list
            // ( a + b ) == ( b + a )
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return null;
    }

    public static LinkedList findIntersection(LinkedList listOne, LinkedList listTwo) {
        int countOne = getCount(listOne);
        int countTwo = getCount(listTwo);
        LinkedList intersect;
        if (countOne > countTwo) {
            intersect = getIntersection(listOne, listTwo, Math.abs(countOne - countTwo));
        } else {
            intersect = getIntersection(listTwo, listOne, Math.abs(countOne - countTwo));
        }
        return intersect;
    }

    public static LinkedList getIntersection(LinkedList listOne, LinkedList listTwo, int difference) {
        while (listOne != null && difference != 0) {
            listOne = listOne.next;
            difference--;
        }
        while(listOne != null
                && listTwo != null) {
            if (listOne.value == listTwo.value) {
                return listOne;
            }
            listOne = listOne.next;
            listTwo = listTwo.next;
        }
        return null;
    }

    public static int getCount(LinkedList node){
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public static void main(String[] args) {
        LinkedList result = findIntersectionWithoutLeg(createLinkedList(new int[]{3, 7, 8, 10}),
                createLinkedList(new int[]{99, 1, 2, 8, 10}));
        var output = result != null ? result.value : null;
        System.out.println("List {3, 7, 8, 10} and {99, 1, 2, 8, 10} are intersecting at -> " + output);

        result = findIntersectionWithoutLeg(createLinkedList(new int[]{1}), createLinkedList(new int[]{1}));
        output = result != null ? result.value : null;
        System.out.println("List {1} and {1} are intersecting at -> " + output);

        result = findIntersection(createLinkedList(new int[]{3, 7, 8, 10}),
                createLinkedList(new int[]{99, 1, 2, 8, 10}));
        output = result != null ? result.value : null;
        System.out.println("List {3, 7, 8, 10} and {99, 1, 2, 8, 10} are intersecting at -> " + output);

        result = findIntersection(createLinkedList(new int[]{1}), createLinkedList(new int[]{1}));
        output = result != null ? result.value : null;
        System.out.println("List {1} and {1} are intersecting at -> " + output);
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
