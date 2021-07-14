package org.javainaction.sort;

import java.util.*;

/**
 Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices
 * such that for every directed edge (U, V) from vertex U to vertex V, U comes before V in the ordering.
 *
 * Given a directed graph, find the topological ordering of its vertices.
 *
 * Example 1:
 *
 * Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
 * Output: Following are the two valid topological sorts for the given graph:
 * 1) 3, 2, 0, 1
 * 2) 3, 2, 1, 0
 *     3
 *     2
 *     0
 *     1
 * Example 2:
 *
 * Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 4, 2, 3, 0, 1
 * 2) 4, 3, 2, 0, 1
 * 3) 4, 3, 2, 1, 0
 * 4) 4, 2, 3, 1, 0
 * 5) 4, 2, 0, 3, 1
 *     4
 *     2
 *     3
 *     0
 *     1
 * Example 3:
 *
 * Input: Vertices=7, Edges=[6, 4], [6, 2], [5, 3], [5, 4], [3, 0], [3, 1], [3, 2], [4, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 5, 6, 3, 4, 0, 1, 2
 * 2) 6, 5, 3, 4, 0, 1, 2
 * 3) 5, 6, 4, 3, 0, 2, 1
 * 4) 6, 5, 4, 3, 0, 1, 2
 * 5) 5, 6, 3, 4, 0, 2, 1
 * 6) 5, 6, 3, 4, 1, 2, 0
 *
 * There are other valid topological ordering of the graph too.
 */
public class TopologicalSort {
    
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
        List<Integer> result = TopologicalSort.topologicalSort(Arrays.asList(0, 1, 2, 3),
                Arrays.asList( new Integer[] { 3, 2 }, new Integer[] { 3, 0 },
                        new Integer[] { 2, 0 }, new Integer[] { 2, 1 }));
        System.out.println(result);
    }
}
