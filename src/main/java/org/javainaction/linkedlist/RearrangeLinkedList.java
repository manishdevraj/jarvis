package org.javainaction.linkedlist;

public class RearrangeLinkedList {

    public static void main(String[] args) {
        RearrangeLinkedList.LinkedList head = new RearrangeLinkedList.LinkedList(3);
        head.next = new RearrangeLinkedList.LinkedList (0);
        head.next.next = new RearrangeLinkedList.LinkedList (5);
        head.next.next.next = new RearrangeLinkedList.LinkedList (2);
        head.next.next.next.next = new RearrangeLinkedList.LinkedList (1);
        head.next.next.next.next.next = new RearrangeLinkedList.LinkedList (4);
        head = rearrangeLinkedList(head, 3);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }


    public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
        LinkedList smallHead = null;
        LinkedList smallTail = null;
        LinkedList middleHead = null;
        LinkedList middleTail = null;
        LinkedList bigHead = null;
        LinkedList bigTail = null;

        LinkedList node = head;
        while (node != null) {
            if (node.value == k) {
                NodePair midPair = growLinkedList(middleHead, middleTail, node);
                middleHead = midPair.head;
                middleTail = midPair.tail;
            } else if (node.value < k) {
                NodePair smallPair = growLinkedList(smallHead, smallTail, node);
                smallHead = smallPair.head;
                smallTail = smallPair.tail;
            } else if (node.value > k) {
                NodePair bigPair = growLinkedList(bigHead, bigTail, node);
                bigHead = bigPair.head;
                bigTail = bigPair.tail;
            }
            LinkedList prev = node;
            node = node.next;
            prev.next = null;
        }

        NodePair firstPair = connectLinkedList(smallHead, smallTail, middleHead, middleTail);
        NodePair finalPair = connectLinkedList(firstPair.head, firstPair.tail, bigHead, bigTail);
        return finalPair.head;
    }

    public static NodePair connectLinkedList(LinkedList headOne, LinkedList tailOne,
                                             LinkedList headTwo, LinkedList tailTwo) {
        LinkedList newHead = headOne == null ? headTwo : headOne;
        LinkedList newTail = tailOne == null ? tailTwo : tailOne;


        if (tailOne != null) {
            while (tailOne.next != null) tailOne = tailOne.next;
            tailOne.next = headTwo;
        }
        return new NodePair(newHead, newTail);
    }

    public static NodePair growLinkedList(
            LinkedList head, LinkedList tail, LinkedList node){
        LinkedList newHead = head;
        LinkedList newTail = node;

        if (newHead == null) newHead = node;
        if (tail != null) tail.next = node;

        return new NodePair(newHead, newTail);
    }

    static class NodePair {
        public LinkedList head;
        public LinkedList tail;

        public NodePair(LinkedList head, LinkedList tail) {
            this.head = head;
            this.tail = tail;
        }

    }
    static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
        }
    }


}
