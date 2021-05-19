package org.javainaction.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    }

    public static void main(String[] args) {
        LinkedList listOne = createLinkedList(new int[]{3, 7, 8, 10});
        LinkedList listTwo = createLinkedList(new int[]{99, 1, 2, 8, 10});
        int countOne = getCount(listOne);
        int countTwo = getCount(listTwo);
        LinkedList intersect;
        if (countOne > countTwo) {
            intersect = getIntersection(listOne, listTwo, Math.abs(countOne - countTwo));
        } else {
            intersect = getIntersection(listTwo, listOne, Math.abs(countOne - countTwo));
        }

        System.out.println("List " + listOne + " and " + listTwo + " are intersecting at -> ");
        System.out.print(intersect != null ? intersect.value : null);
    }

    public static LinkedList getIntersection(LinkedList listOne, LinkedList listTwo, int difference) {
        while (listOne != null && difference != 0) {
            listOne = listOne.next;
            difference--;
        }
        while (listOne != null && listTwo != null) {
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
