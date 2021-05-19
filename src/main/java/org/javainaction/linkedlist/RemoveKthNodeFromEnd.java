package org.javainaction.linkedlist;

public class RemoveKthNodeFromEnd {
    public static void removeKthNodeFromEnd(LinkedList head, int k) {
        LinkedList first = head;
        LinkedList second = head;
        int count = 1;
        while (second != null && count <=k) {
            second = second.next;
            count++;
        }

        if(second == null) {
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        }

        while (second.next != null ){
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;


    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
