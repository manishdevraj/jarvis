package org.javainaction.design;

import java.util.*;

/**
 * A webserver is running infinitely that writes each request line by line in following format :
 * timestamp requestUri statusCode elapsedTime
 *
 * 1000001 /access/api 200 150
 * 1000002 /access/api 300 1
 * 1000005 /error/html 500 50
 * 1000500 /read/api 200 100
 *
 * Output:
 *
 * 1000001 => 1 // request at this time is self
 * 1000002 => 2 //1st request would end at 1000151 so that would add up
 * 1000005 => 2 //2nd request would end at 1000003 but first still ends at 1000151
 * 1000500 => 1 //all request are completed by this time
 */
public class LogRequestParser {
    public static Map<Integer, Integer> parseLog(final List<String> logFileDetails) {
        Map<Integer, Integer> requestCountMap = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        for (String line : logFileDetails) {
            String[] token = line.split(" ");
            if (token.length == 4) {
                LogDetails logDetails = new LogDetails(
                        Integer.parseInt(token[0]),
                        token[1],
                        Integer.parseInt(token[2]),
                        Integer.parseInt(token[0]) + Integer.parseInt(token[3]));

                while (!minHeap.isEmpty() && minHeap.peek() < logDetails.timestamp) {
                    minHeap.poll();
                }
                minHeap.offer(logDetails.elapsedTime);
                requestCountMap.put(logDetails.timestamp, minHeap.size());
            }
        }
        return requestCountMap;
    }

    private static class LogDetails {
        private int timestamp;
        private String uri;
        private int statusCode;
        private int elapsedTime;

        public LogDetails(int timestamp, String uri, int statusCode, int elapsedTime) {
            this.timestamp = timestamp;
            this.uri = uri;
            this.statusCode = statusCode;
            this.elapsedTime = elapsedTime;
        }

    }

    public static void main(String[] args) {
        List<String> logFileDetails = Arrays.asList(
                "1000001 /access/api 200 150",
                "1000002 /access/api 300 1",
                "1000005 /error/html 500 50",
                "1000500 /read/api 200 100"
        );
        System.out.println("Log request counter " );
        parseLog(logFileDetails).entrySet().forEach(System.out::println);
    }
}
