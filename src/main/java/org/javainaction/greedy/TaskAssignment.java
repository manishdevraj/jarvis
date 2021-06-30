package org.javainaction.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a list of tasks and K workers such that there are always 2K tasks to complete.
 * Workers can complete tasks in parallel and total time to complete all tasks is longest time to complete a pair
 *
 * Find optimal task assignment for each task such that all tasks are completed as fast as possible. Return indices of
 * tasks that you are pairing.
 *
 * Tasks : [1, 3, 5, 3, 1, 4]
 * And K = 3
 *
 * Output :
 * [4, 2] : task[4] + task[2]  = 5 + 1 = 6
 * [0, 5] : task[0] + task[5]  = 1 + 4 = 5
 * [1, 3] : task[3] + task[3]  = 3 + 3 = 6
 *
 * Total 6 time will be taken to complete all taks
 *
 * Remember there can be multiple answers : [0, 2], [4, 5], [1,3] is correct too
 *
 *
 *  K will always be greater than zero
 */
public class TaskAssignment {
    public List<List<Integer>> taskAssignment(int k, List<Integer> tasks) {
        int[][] taskIndexPairs = new int[tasks.size()][2];

        int index = 0;
        for (Integer task : tasks) {
            taskIndexPairs[index] = new int[]{task, index++};
        }

        Arrays.sort(taskIndexPairs, (a, b) -> a[0] - b[0]);

        int left = 0;
        int right = tasks.size() - 1;
        var output = new ArrayList<List<Integer>>();
        //Ignored K workers as there are always 2K tasks meaning we can assume that we have 2 * K tasks which can be
        //considered as pairs of size tasks.size() / 2
        //if input is varying that K can be any number then we might need to iterate only up to k tuples
        while (left < right) {
            output.add(Arrays.asList(taskIndexPairs[left][1], taskIndexPairs[right][1]));
            left++;
            right--;
        }

        return output;
    }

    public static void main(String[] args) {
        var k = 3;
        var tasks = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 3, 1, 4));
        var expected = new ArrayList<ArrayList<Integer>>();
        var subarr = new ArrayList<Integer>(Arrays.asList(4, 2));
        var subarr2 = new ArrayList<Integer>(Arrays.asList(0, 5));
        var subarr3 = new ArrayList<Integer>(Arrays.asList(3, 1));
        expected.add(subarr);
        expected.add(subarr2);
        expected.add(subarr3);
        var actual = new TaskAssignment().taskAssignment(k, tasks);
        System.out.println(actual);
    }
}
