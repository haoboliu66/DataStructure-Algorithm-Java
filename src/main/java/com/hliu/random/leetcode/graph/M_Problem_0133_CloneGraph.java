package com.hliu.random.leetcode.graph;

import java.util.*;

public class M_Problem_0133_CloneGraph {

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // My Solution 1
    public static Node cloneGraph(Node node) {
        if (node == null) return node;
        Stack<Node> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        HashMap<Integer, Node> map = new HashMap<>();
        stack.push(node);
        visited.add(node.val);
        map.put(node.val, new Node(node.val));

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            List<Node> adjs = cur.neighbors;

            ArrayList<Node> copyAdjs = copyNodes(adjs, map); // copy all adjs

            Node copyCur; // copy cur with copyAjds
            if (map.containsKey(cur.val)) {
                copyCur = map.get(cur.val);
            } else {
                copyCur = new Node(cur.val, copyAdjs);
                map.put(cur.val, copyCur);
            }

            for (Node cp : copyAdjs) {
                cp.neighbors.add(copyCur);   // ajds reference back
            }

            for (Node n : adjs) {
                if (!visited.contains(n.val)) {
                    visited.add(n.val);
                    stack.push(n);
                }
            }
        }

        return map.get(node.val);
    }

    private static ArrayList<Node> copyNodes(List<Node> list, HashMap<Integer, Node> map) {
        ArrayList<Node> copy = new ArrayList<>();
        Node c;
        for (Node n : list) {
            if (!map.containsKey(n.val)) {
                c = new Node(n.val);
                map.put(n.val, c);
            } else {
                c = map.get(n.val);
            }
            copy.add(c);
        }
        return copy;
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);
        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n4.neighbors.add(n1);
        n4.neighbors.add(n3);
        Node res = cloneGraph(n1);
        System.out.println(n1);
        System.out.println(res);
    }



}