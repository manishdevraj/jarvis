package org.javainaction.design;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 *
 * Constraints:
 *
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class MinStack {
    Stack<Element> stack = new Stack<>();

    private static class Element {
        private final int value;
        private final int min;

        public Element(int value, int min) {
            this.value = value;
            this.min = min;
        }

        public int getValue(){
            return this.value;
        }

        public int getMin(){
            return this.min;
        }
    }

    public MinStack() {

    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Element(val, val));
        } else {
            int minValue = Math.min(stack.peek().getMin(), val);
            stack.push(new Element(val, minValue));
        }
    }

    public void pop() {
        if (!stack.isEmpty())
            stack.pop();
    }

    public int top() {
        if (stack.isEmpty())
            return Integer.MIN_VALUE;

        return stack.peek().getValue();
    }

    public int getMin() {
        if (stack.isEmpty())
            return Integer.MIN_VALUE;

        return stack.peek().getMin();
    }
}
