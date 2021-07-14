package org.javainaction.topologicalsort;

import java.util.*;

/**
 * Given an array of jobs and their dependencies list where 0 is parent of 1st index job
 * Find topological sort such that we can execute jobs without having any conflicts.
 *
 * Input
 * Jobs : {1, 2, 3, 4}
 * Deps : {1, 2}, {1, 3}, {3, 2}, {4, 2}, {4, 3}
 *
 * Output:
 *
 * [1,4,3,2] or [4,1,3,2]
 */
public class JobOrder {
    public static List<Integer> topologicalSortMapV2(List<Integer> jobs, List<Integer[]> deps) {
        List<Integer> sortedOrder = new ArrayList<>();
        if (jobs == null || jobs.size() == 0)
            return jobs;

        // a. Initialize the graph
        HashMap<Integer, Integer> inDegree = new HashMap<>(); // count of incoming edges for every vertex
        HashMap<Integer, List<Integer>> graph = new HashMap<>(); // adjacency list graph
        for (Integer job : jobs) {
            inDegree.put(job, 0);
            graph.put(job, new ArrayList<>());
        }

        // b. Build the graph
        for (Integer[] dependency : deps) {
            int parent = dependency[0], child = dependency[1];
            graph.get(parent).add(child); // put the child into it's parent's list
            inDegree.put(child, inDegree.get(child) + 1); // increment child's inDegree
        }

        // c. Find all sources i.e., all vertices with 0 in-degrees
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0)
                sources.add(entry.getKey());
        }

        // d. For each source, add it to the sortedOrder and subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        while (!sources.isEmpty()) {
            int vertex = sources.poll();
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex); // get the node's children to decrement their in-degrees
            for (int child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0)
                    sources.add(child);
            }
        }

        // if sortedOrder doesn't contain all tasks, there is a cyclic dependency between tasks, therefore, we
        // will not be able to schedule all tasks
        return sortedOrder.size() == jobs.size() ? sortedOrder : new ArrayList<>();
    }

    public static List<Integer> topologicalSortMap(List<Integer> jobs, List<Integer[]> deps) {
        //graph with its prerequisite list
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> topologicalSort = new ArrayList<>();

        jobs.forEach(j -> {
            graph.put(j, new ArrayList<>());
        });

        for (Integer[] pre : deps) {
            graph.get(pre[1]).add(pre[0]);
        }


        Queue<Integer> sources = new LinkedList<>(jobs);
        Set<Integer> visited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();
        while (!sources.isEmpty()) {
            Integer job = sources.poll();
            boolean isCycle = dfsFindCycle(job, topologicalSort, visited, visiting, graph);
            if (isCycle) return new ArrayList<>();
        }
        return topologicalSort;
    }

    private static boolean dfsFindCycle(Integer job, List<Integer> topologicalSort, Set<Integer> visited,
                                        Set<Integer> visiting,
                                        HashMap<Integer, List<Integer>> graph) {
        if (visited.contains(job)) return false;
        if (visiting.contains(job)) return true;

        visiting.add((job));
        for (Integer prerequisite : graph.get(job)) {
            boolean isCycle = dfsFindCycle(prerequisite, topologicalSort, visited, visiting, graph);
            if (isCycle) return true;
        }
        visiting.remove(job);
        visited.add(job);
        topologicalSort.add(job);
        return false;
    }

    // O(j + d) time | O(j + d) space
    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        JobGraph jobGraph = createJobGraph(jobs, deps);
        return getOrderedJobs(jobGraph);
    }

    public static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]> deps) {
        JobGraph graph = new JobGraph(jobs);
        for (Integer[] dep : deps) {
            graph.addPreReqs(dep[1], dep[0]);
        }
        return graph;
    }

    public static List<Integer> getOrderedJobs(JobGraph graph) {
        List<Integer> orderedJobs = new ArrayList<>();
        List<JobNode> nodes = new ArrayList<>(graph.nodes);

        while (nodes.size() > 0) {
            JobNode node = nodes.get(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
            boolean containsCycle = depthFirstTraverse(node, orderedJobs);
            if (containsCycle) return new ArrayList<>();
        }
        return orderedJobs;
    }

    public static boolean depthFirstTraverse(JobNode node, List<Integer> orderedJobs) {
        if (node.visited) return false;
        if (node.visiting) return true;
        node.visiting = true;
        for (JobNode preReqNode : node.preReqs) {
            boolean containsCycle = depthFirstTraverse(preReqNode, orderedJobs);
            if (containsCycle) return true;
        }
        node.visited = true;
        node.visiting = false;
        orderedJobs.add(node.job);
        return false;
    }

    static class JobGraph {
        public List<JobNode> nodes = new ArrayList<>();
        public Map<Integer, JobNode> graph = new HashMap<>();

        public JobGraph(List<Integer> jobs){
            for (Integer job : jobs) {
                addNode(job);
            }
        }

        public void addPreReqs(Integer job, Integer preReq) {
            JobNode jobNode = getNode(job);
            JobNode preReqNode = getNode(preReq);
            jobNode.preReqs.add(preReqNode);
        }

        public void addNode(Integer job) {
            graph.put(job, new JobNode(job));
            nodes.add(graph.get(job));
        }

        public JobNode getNode(Integer job) {
            if (!graph.containsKey(job)) addNode(job);
            return graph.get(job);
        }
    }

    static class JobNode {
        public Integer job;
        public List<JobNode> preReqs = new ArrayList<>();
        public boolean visited;
        public boolean visiting;

        public JobNode(Integer job) {
            this.job = job;
        }
    }

    public static void main(String[] args) {
        var jobs = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        var depsArray = new Integer[][] {{1, 2}, {1, 3}, {3, 2}, {4, 2}, {4, 3}};
        var deps = new ArrayList<Integer[]>(Arrays.asList(depsArray));

        var order = JobOrder.topologicalSort(jobs, deps);
        System.out.println("Job order :" + order);
        System.out.println("Is valid topological order? " + isValidTopologicalOrder(order, jobs, deps));

        order = JobOrder.topologicalSortMapV2(jobs, deps);
        System.out.println("Job order :" + order);
        System.out.println("Is valid topological order? " + isValidTopologicalOrder(order, jobs, deps));
    }

    static boolean isValidTopologicalOrder(List<Integer> order, List<Integer> jobs, List<Integer[]> deps) {
        var visited = new HashMap<Integer, Boolean>();
        for (Integer candidate : order) {
            for (Integer[] dep : deps) {
                if (candidate.equals(dep[0]) && visited.containsKey(dep[1])) return false;
            }
            visited.put(candidate, true);
        }
        for (Integer job : jobs) {
            if (!visited.containsKey(job)) return false;
        }
        return order.size() == jobs.size();
    }

}
