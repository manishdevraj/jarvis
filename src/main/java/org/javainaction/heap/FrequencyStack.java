package org.javainaction.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

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
 *
 * Input
 * ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
 * [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, null, 5, 7, 5, 4]
 *
 * Explanation
 * FreqStack freqStack = new FreqStack();
 * freqStack.push(5); // The stack is [5]
 * freqStack.push(7); // The stack is [5,7]
 * freqStack.push(5); // The stack is [5,7,5]
 * freqStack.push(7); // The stack is [5,7,5,7]
 * freqStack.push(4); // The stack is [5,7,5,7,4]
 * freqStack.push(5); // The stack is [5,7,5,7,4,5]
 * freqStack.pop(); // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
 * freqStack.pop(); // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
 * freqStack.pop(); // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
 * freqStack.pop(); // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
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


    AtomicInteger sequenceNumber = new AtomicInteger(0);
    PriorityQueue<Element> maxHeap =
            new PriorityQueue<>((elemOne, elemTwo) ->
            {
                return elemOne.frequency != elemTwo.frequency //when frequency is not same
                        ? elemTwo.frequency - elemOne.frequency //sort by frequency
                        : elemTwo.sequenceNumber - elemOne.sequenceNumber; //sort by sequence they were added in
            });
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    //every push maintain frequency and sequence number
    public void push(int num) {
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        maxHeap.offer(new Element(num, frequencyMap.get(num), sequenceNumber.getAndIncrement()));
    }

    public int pop() {
        if (maxHeap.isEmpty()) return -1;

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
