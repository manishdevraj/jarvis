package org.javainaction.heap;

import java.util.*;

/**
 * You are given a list of tasks that need to be run, in any order, on a server.
 * Each task will take one CPU interval to execute but once a task has finished,
 * it has a cooling period during which it can’t be run again. If the cooling period for all tasks is ‘K’ intervals,
 * find the minimum number of CPU intervals that the server needs to finish all tasks.
 *
 * If at any time the server can’t execute any task then it must stay idle.
 *
 * Example 1:
 *
 * Input: [a, a, a, b, c, c], K=2
 * Output: 7
 * Explanation: a -> c -> b -> a -> c -> idle -> a
 * Example 2:
 *
 * Input: [a, b, a], K=3
 * Output: 5
 * Explanation: a -> b -> idle -> idle -> a
 *
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 */
public class TaskScheduler {
    public static int scheduleTasks(char[] tasks, int k) {
        Map<Character, Integer> taskFrequencyMap = new HashMap<>();
        for (char t : tasks)
            taskFrequencyMap.put(t, taskFrequencyMap.getOrDefault(t, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> e2.getValue() - e1.getValue());

        // add all characters to the max heap
        maxHeap.addAll(taskFrequencyMap.entrySet());
        int intervalCount = 0;
        while (!maxHeap.isEmpty()) {
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();
            int n = k + 1; // try to execute as many as 'k+1' tasks from the max-heap
            for (; n > 0 && !maxHeap.isEmpty(); n--) {
                intervalCount++;
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                if (currentEntry.getValue() > 1) {
                    currentEntry.setValue(currentEntry.getValue() - 1);
                    waitList.add(currentEntry);
                }
            }
            maxHeap.addAll(waitList); // put all the waiting list back on the heap
            if (!maxHeap.isEmpty())
                intervalCount += n; // we'll be having 'n' idle intervals for the next iteration
        }

        // if we were successful in appending all the characters to the result string, return it
        return intervalCount;
    }

    public static void main(String[] args) {
        char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 2));

        tasks = new char[] { 'a', 'b', 'a' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 3));
    }
}
