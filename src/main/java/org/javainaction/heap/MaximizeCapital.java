package org.javainaction.heap;

import java.util.PriorityQueue;

/**
 * Given a set of investment projects with their respective profits, we need to find the most profitable projects.
 * We are given an initial capital and are allowed to invest only in a fixed number of projects. Our goal is to choose
 * projects that give us the maximum profit.
 *
 * We can start an investment project only when we have the required capital. Once a project is selected, we can assume
 * that its profit has become our capital.
 *
 * Example 1:
 *
 * Input: Project Capitals=[0,1,2], Project Profits=[1,2,3], Initial Capital=1, Number of Projects=2
 * Output: 6
 * Explanation:
 *
 * With initial capital of ‘1’, we will start the second project which will give us profit of ‘2’. Once we selected
 * our first project, our total capital will become 3 (profit + initial capital).
 * With ‘3’ capital, we will select the third project, which will give us ‘3’ profit.
 * After the completion of the two projects, our total capital will be 6 (1+2+3).
 *
 * Example 2:
 *
 * Input: Project Capitals=[0,1,2,3], Project Profits=[1,2,3,5], Initial Capital=0, Number of Projects=3
 * Output: 8
 * Explanation:
 *
 * With ‘0’ capital, we can only select the first project, bringing out capital to 1.
 * Next, we will select the second project, which will bring our capital to 3.
 * Next, we will select the fourth project, giving us a profit of 5.
 * After selecting the three projects, our total capital will be 8 (1+2+5).
 */
public class MaximizeCapital {
    public static int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
        int n = profits.length;
        PriorityQueue<Integer> minCapitalHeap = new PriorityQueue<>(n, (i1, i2) -> capital[i1] - capital[i2]);
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>(n, (i1, i2) -> profits[i2] - profits[i1]);

        //start with lowest capital
        for (int i = 0; i < n; i++){
            minCapitalHeap.offer(i);
        }

        int availableCapital = initialCapital;
        //number of projects we can pick
        while (numberOfProjects-- > 0) {
            //if we have capital then pick them ups
            // as we can see we are not going back to previous project once we have more capital to maximize the profit
            while(!minCapitalHeap.isEmpty() && capital[minCapitalHeap.peek()] <= availableCapital) {
                maxProfitHeap.add(minCapitalHeap.poll());
            }
            //we could not pick any more profits
            if (maxProfitHeap.isEmpty()) break;

            //we pick single maximum profit for each iteration this ensures we don't do running sum of all profits
            availableCapital += profits[maxProfitHeap.poll()];
        }

        return availableCapital;
    }

    public static void main(String[] args) {
        int result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1);
        System.out.println("Maximum capital: " + result);
        result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0);
        System.out.println("Maximum capital: " + result);
        result = MaximizeCapital.findMaximumCapital(new int[] { 2, 1, 0 }, new int[] { 3, 2, 1 }, 2, 1);
        System.out.println("Maximum capital: " + result);
        result = MaximizeCapital.findMaximumCapital(new int[] { 2, 2, 2 }, new int[] { 10, 20, 30 }, 2, 2);
        System.out.println("Maximum capital: " + result);
    }
}
