package org.javainaction.interval;

import java.util.*;

/**
 * For ‘K’ employees, we are given a list of intervals representing the working hours of each employee.
 * Our goal is to find out if there is a free interval that is common to all employees.
 * You can assume that each list of employee working hours is sorted on the start time.
 *
 * Example 1:
 *
 * Input: Employee Working Hours=[[[1,3], [5,6]], [[2,3], [6,8]]]
 * Output: [3,5]
 * Explanation: Both the employees are free between [3,5].
 * Example 2:
 *
 * Input: Employee Working Hours=[[[1,3], [9,12]], [[2,4]], [[6,8]]]
 * Output: [4,6], [8,9]
 * Explanation: All employees are free between [4,6] and [8,9].
 * Example 3:
 *
 * Input: Employee Working Hours=[[[1,3]], [[2,4]], [[3,5], [7,9]]]
 * Output: [5,7]
 * Explanation: All employees are free between [5,7].
 */
public class EmployeeFreeTime {
    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    static class EmployeeInterval {
        Interval interval; // interval representing employee's working hours
        int employeeIndex; // index of the list containing working hours of this employee
        int intervalIndex; // index of the interval in the employee list

        public EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
            this.interval = interval;
            this.employeeIndex = employeeIndex;
            this.intervalIndex = intervalIndex;
        }

        @Override
        public String toString() {
            return "EmployeeInterval{" +
                    "interval=" + interval +
                    ", employeeIndex=" + employeeIndex +
                    ", intervalIndex=" + intervalIndex +
                    '}';
        }
    }

    /**
     * Using priority queue (min heap)
     * @param schedule
     * @return
     */
    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>(schedule.size(),
                (a, b) -> Integer.compare(a.interval.end, b.interval.end));

        //all employees first interval to minHeap
        for (int k = 0; k < schedule.size(); k++) {
            //first interval, employee index, 0 as interval index
            minHeap.offer(new EmployeeInterval(schedule.get(k).get(0), k, 0));
        }

        Interval previousInterval = !minHeap.isEmpty() ? minHeap.peek().interval : null;
        while (!minHeap.isEmpty()) {
            EmployeeInterval currentInterval = minHeap.poll();
            if (previousInterval.end < currentInterval.interval.start) {
                //we found available space between two intervals
                result.add(new Interval(previousInterval.end, currentInterval.interval.start));
                previousInterval = currentInterval.interval;
            } else {
                //slide interval if current interval is ending later
                if (previousInterval.end < currentInterval.interval.end) {
                    previousInterval = currentInterval.interval;
                }
            }

            // if there are more intervals available for the same employee, add their next interval
            List<Interval> employeeSchedule = schedule.get(currentInterval.employeeIndex);
            if (employeeSchedule.size() > currentInterval.intervalIndex + 1) {
                minHeap.offer(new EmployeeInterval(
                        employeeSchedule.get(currentInterval.intervalIndex + 1),
                        currentInterval.employeeIndex,
                        currentInterval.intervalIndex + 1));
            }
        }
        return result;
    }

    /**
     * Without using Min heap but merge technique to find free slots
     */
    public static List<Interval> findEmployeeFreeTimeMerged(List<List<Interval>> schedule) {

        List<Interval> intervals = new ArrayList<>();
        //add all intervals from each employees
        schedule.forEach(intervals::addAll);

        //merge all intervals together
        List<Interval> mergeIntervals = mergeIntervals(intervals);

        List<Interval> result = new ArrayList<>();
        for (int i = 1; i < mergeIntervals.size(); i++) {
            Interval previous = mergeIntervals.get(i - 1);
            Interval current = mergeIntervals.get(i);
            //find non overlapping intervals and free slots
            if (previous.end < current.start) {
                result.add(new Interval(previous.end, current.start));
            }
        }
        return result;
    }

    /**
     * Merge intervals from all schedules to find overlapping and free interval slots
     */
    private static List<Interval> mergeIntervals(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return intervals;

        intervals.sort(Comparator.comparingInt(interval -> interval.start));
        List<Interval> mergeIntervals = new ArrayList<>();

        for (Interval current : intervals) {
            if (mergeIntervals.isEmpty()) {
                mergeIntervals.add(current);
            } else {
                //get top element as previous interval;
                Interval previous = mergeIntervals.get(mergeIntervals.size() - 1);
                if (previous.end >= current.start) {
                    //flatten interval such that we can go as back as lower value of two start intervals
                    //and as higher as max value of end intervals
                    //[1, 3], [2, 4] will be merged to [1, 4]
                    mergeIntervals.add(new Interval(
                            Math.min(previous.start, current.start),
                            Math.max(previous.end, current.end)
                    ));
                } else {
                    mergeIntervals.add(current);
                }
            }
        }

        return mergeIntervals;
    }

    public static void main(String[] args) {

        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result = findEmployeeFreeTime(input);

        System.out.println("Free intervals between {[{1, 3}, {5, 6}], [{2, 3}, {6, 8}]} : " );
        result.forEach(System.out::println);

        System.out.println("Free intervals between {[{1, 3}, {5, 6}], [{2, 3}, {6, 8}]} using merge technique : " );
        findEmployeeFreeTimeMerged(input).forEach(System.out::println);

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input.add(new ArrayList<>(Collections.singletonList(new Interval(2, 4))));
        input.add(new ArrayList<>(Collections.singletonList(new Interval(6, 8))));
        result = findEmployeeFreeTime(input);

        System.out.println("Free intervals between {[{1, 3}, {9, 12}], [{2, 4}], [{6, 8}]} : " );
        result.forEach(System.out::println);

        System.out.println("Free intervals between {[{1, 3}, {9, 12}], [{2, 4}], [{6, 8}]} using merge technique : " );
        findEmployeeFreeTimeMerged(input).forEach(System.out::println);


        input = new ArrayList<>();
        input.add(new ArrayList<>(Collections.singletonList(new Interval(1, 3))));
        input.add(new ArrayList<>(Collections.singletonList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = findEmployeeFreeTime(input);

        System.out.println("Free intervals between {[{1, 3}], [{2, 4}], [{3, 5}, {7, 9}]} : " );
        result.forEach(System.out::println);

        System.out.println("Free intervals between {[{1, 3}], [{2, 4}], [{3, 5}, {7, 9}]}using merge technique : " );
        findEmployeeFreeTimeMerged(input).forEach(System.out::println);
    }
}
