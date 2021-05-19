package org.javainaction.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
