package com.hliu.random.leetcode.graph;

import java.util.*;

public class M_Problem_0332_ReconstructItinerary {

    private static class Node implements Comparable<Node> {
        String name;
        int in;

        public Node(String name) {
            this.name = name;
            in = 0;
        }

        @Override
        public int compareTo(Node n) {
            return this.name.compareTo(n.name);
        }
    }


    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> ans = new ArrayList<>();
        Map<Node, TreeSet<Node>> graph = new HashMap<>();
        Map<String, Node> nodeMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!nodeMap.containsKey(from)) {
                nodeMap.put(from, new Node(from));
            }
            if (nodeMap.containsKey(to)) {
                nodeMap.put(to, new Node(to));
            }
            nodeMap.get(to).in++;

            if (!graph.containsKey(nodeMap.get(from))) {
                graph.put(nodeMap.get(from), new TreeSet<>());
            }
            Set<Node> adjs = graph.get(nodeMap.get(from));
            adjs.add(nodeMap.get(to));
        }
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(nodeMap.get("JFK"));
        String next = null;
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            ans.add(cur.name);
            Set<Node> adjs = graph.get(cur);
            for (Node n : adjs) {
                next = n.name;
                stack.push(n);
                break;
            }
            graph.get(cur).remove(nodeMap.get(next));
        }
        return ans;
    }

}
