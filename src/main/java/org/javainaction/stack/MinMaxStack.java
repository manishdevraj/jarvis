package org.javainaction.stack;

import java.util.LinkedList;

/**
 * Write a stack such that every time we call get min or get max functions on it
 * It shall return min and maximum out of the entire stack
 * [2, 4, 1, 3, 2]
 *
 * getMin should return 1
 * getMax should return 4
 *
 * insert(5)
 * insert(-1)
 * getMin should return -1
 * getMax should return 5
 *
 * pop()// -1
 * pop()// 5
 * pop()// 2
 * pop()// 3
 * pop()// 1
 * getMin should return 2
 * getMax should return 4
 */
public class MinMaxStack {
    // O(1) time | O(1) space

    static class Stack {
        int value;
        int minValue;
        int maxValue;

        public Stack(int value, int minValue, int maxValue) {
            this.value = value;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    static class Program {
        LinkedList<Stack> stack = new LinkedList<>();

        public int peek() {
            Stack stackElem = stack.peekLast();
            return stackElem == null ? -1 : stackElem.value;
        }

        public int pop() {
            Stack stackElem = stack.removeLast();
            return stackElem == null ? -1 : stackElem.value;
        }

        /**
         * Idea is to maintain min max as auxiliary information with each insert that cascades information to peek
         * element without us having to go back and pop bottom elements out
         */
        public void push(Integer number) {
            if (stack.size() == 0) {
                Stack stackElem = new Stack(number, number, number);
                stack.addLast(stackElem);
            } else {
                Stack prevElem = stack.peekLast();
                int newMin = Math.min(prevElem.minValue, number);
                int newMax = Math.max(prevElem.maxValue, number);
                Stack stackElem = new Stack(number, newMin, newMax);
                stack.addLast(stackElem);
            }
        }

        public int getMin() {
            Stack stackElem = stack.peekLast();
            return stackElem == null ? -1 : stackElem.minValue;
        }

        public int getMax() {
            Stack stackElem = stack.peekLast();
            return stackElem == null ? -1 : stackElem.maxValue;
        }
    }
}
