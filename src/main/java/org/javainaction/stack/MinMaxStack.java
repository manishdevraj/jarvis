package org.javainaction.stack;

import java.util.LinkedList;

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
