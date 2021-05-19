package org.javainaction.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Design a class that simulates a Stack data structure, implementing the following two operations:
 *
 * push(int num): Pushes the number ‘num’ on the stack.
 * pop(): Returns the most frequent number in the stack. If there is a tie, return the number which was pushed later.
 * Example:
 *
 * After following push operations: push(1), push(2), push(3), push(2), push(1), push(2), push(5)
 *
 * 1. pop() should return 2, as it is the most frequent number
 * 2. Next pop() should return 1
 * 3. Next pop() should return 2
 */
public class FrequencyStack {
    static class Element {
        int number;
        int frequency;
        int sequenceNumber;

        public Element(int number, int frequency, int sequenceNumber) {
            this.number = number;
            this.frequency = frequency;
            this.sequenceNumber = sequenceNumber;
        }
    }

    static class ElementComparator implements Comparator<Element> {
        public int compare(Element e1, Element e2) {
            if (e1.frequency != e2.frequency)
                return e2.frequency - e1.frequency;
            // if both elements have same frequency, return the one that was pushed later
            return e2.sequenceNumber - e1.sequenceNumber;
        }
    }

    int sequenceNumber = 0;
    PriorityQueue<Element> maxHeap = new PriorityQueue<Element>(new ElementComparator());
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    public void push(int num) {
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        maxHeap.offer(new Element(num, frequencyMap.get(num), sequenceNumber++));
    }

    public int pop() {
        int num = maxHeap.poll().number;

        // decrement the frequency or remove if this is the last number
        if (frequencyMap.get(num) > 1)
            frequencyMap.put(num, frequencyMap.get(num) - 1);
        else
            frequencyMap.remove(num);

        return num;
    }

    public static void main(String[] args) {
        FrequencyStack frequencyStack = new FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }
}
