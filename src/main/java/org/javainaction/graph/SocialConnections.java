package org.javainaction.graph;

import java.util.*;

/**
 * Given a social graph of a person, find the nth connection from the source person
 *
 * Example
 *          Manish
 *        /       \
 *      Roy     Katherine
 *       /
 *    Niraj
 *
 *
 *  Output:
 *  2nd connection are : Niraj
 *  3rd connection are : null
 *  1st connections are : Roy, Katherine
 */
public class SocialConnections {

    /**
     * BFS solution
     */
    private static List<Person> findNthConnectionBfs(Person source, int n) {
        if (n < 1) return new ArrayList<>();

        Queue<Person> queue = new LinkedList<>();
        queue.offer(source);
        while (!queue.isEmpty()) {
            Person person = queue.poll();
            if (n == 1) {
                return person.connections;
            }
            if (person != null && person.connections != null) {
                queue.addAll(person.connections);
            }
            n--;
        }
        return new ArrayList<>();
    }

    private static List<Person> findNthConnectionDfs(Person source, int n) {
        var output = new ArrayList<Person>();
        if (n < 1) return output;

        return findNthConnectionRecursive(source, n, output);
    }

    /**
     * DFS Recursive solution
     */
    private static List<Person> findNthConnectionRecursive(Person source, int n, ArrayList<Person> output) {
        if (n == 0) {
            output.add(source);
        }

        if (source == null || source.connections == null) return output;

        for (Person friend : source.connections) {
            findNthConnectionRecursive(friend, n - 1, output);
        }

        return output;
    }

    public static void main(String[] args) {
        Person source = new Person("Manish",
                Arrays.asList(
                        new Person("Roy", Collections.singletonList(new Person("Niraj", null))),
                        new Person("Katherine", null))
        );

        System.out.println(findNthConnectionBfs(source, 2));
        System.out.println(findNthConnectionBfs(source, 3));
        System.out.println(findNthConnectionBfs(source, 1));

        System.out.println(findNthConnectionDfs(source, 2));
        System.out.println(findNthConnectionDfs(source, 3));
        System.out.println(findNthConnectionDfs(source, 1));
    }

    static class Person {
        String name;
        List<Person> connections;

        public Person(String name, List<Person> connections) {
            this.name = name;
            this.connections = connections;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", connections=" + connections +
                    '}';
        }
    }
}
