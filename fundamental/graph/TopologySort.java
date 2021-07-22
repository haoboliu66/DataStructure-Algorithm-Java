package fundamental.graph;

import java.util.*;

/*
Graph的
 */
public class TopologySort {

    // 前提是有向无环图, 所以肯定至少有一个in-degree为0的节点作为起点
    public static <V> List<Graph.Node<V>> sortedTopology(Graph<V> graph) {
        if (graph == null) {
            return new ArrayList<>();
        }
        HashMap<Graph.Node<V>, Integer> inMap = new HashMap<>();
        Queue<Graph.Node<V>> zeroQueue = new LinkedList<>();
        ArrayList<Graph.Node<V>> res = new ArrayList<>();
        for (Graph.Node<V> node : graph.nodes.values()) {
            if (node.in == 0) {
                zeroQueue.add(node);
            }
            inMap.put(node, node.in);
        }
        while (!zeroQueue.isEmpty()) {
            Graph.Node<V> cur = zeroQueue.poll();
            res.add(cur);
            for (Graph.Node<V> node : cur.neighbours) {
                inMap.put(node, inMap.get(node) - 1);
                if (inMap.get(node) == 0) {
                    zeroQueue.add(node);
                }
            }
        }
        return res;
    }


    public static <V> List<Graph.Node<V>> sortedTopology1(Graph<V> graph) {
        if (graph == null) {
            return new ArrayList<>();
        }
        Queue<Graph.Node<V>> zeroQueue = new LinkedList<>();
        ArrayList<Graph.Node<V>> res = new ArrayList<>();
        for (Graph.Node<V> node : graph.nodes.values()) {
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }
        while (!zeroQueue.isEmpty()) {
            Graph.Node<V> cur = zeroQueue.poll();
            res.add(cur);
            for (Graph.Node<V> node : cur.neighbours) {
                if (--node.in == 0) {
                    zeroQueue.add(node);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Graph.Node<Integer> node1 = new Graph.Node<>(1);
        Graph.Node<Integer> node2 = new Graph.Node<>(2);
        Graph.Node<Integer> node3 = new Graph.Node<>(3);
        Graph.Node<Integer> node4 = new Graph.Node<>(4);
        Graph.Node<Integer> node5 = new Graph.Node<>(5);
        Graph.Node<Integer> node6 = new Graph.Node<>(6);
        Graph.Node<Integer> node7 = new Graph.Node<>(7);
        Graph.Node<Integer> node8 = new Graph.Node<>(8);
        Graph.Node<Integer> node9 = new Graph.Node<>(9);
        Graph.Node<Integer> node10 = new Graph.Node<>(10);

        Graph.Node[] nodes = {node1, node2, node3, node4, node5, node6, node7, node8, node9, node10};
        Graph<Integer> graph = new Graph<>();
        for (int i = 0; i < nodes.length; i++) {
            graph.nodes.put(i + 1, nodes[i]);
        }
        Graph.Edge<Integer> edge1 = new Graph.Edge<>(1, node1, node2);
        Graph.Edge<Integer> edge2 = new Graph.Edge<>(1, node1, node3);
        Graph.Edge<Integer> edge3 = new Graph.Edge<>(1, node2, node3);
        Graph.Edge<Integer> edge4 = new Graph.Edge<>(1, node3, node4);
        Graph.Edge<Integer> edge5 = new Graph.Edge<>(1, node3, node5);
        Graph.Edge<Integer> edge6 = new Graph.Edge<>(1, node4, node5);
        Graph.Edge<Integer> edge7 = new Graph.Edge<>(1, node5, node6);
        Graph.Edge<Integer> edge8 = new Graph.Edge<>(1, node6, node7);
        Graph.Edge<Integer> edge9 = new Graph.Edge<>(1, node8, node6);
        Graph.Edge<Integer> edge10 = new Graph.Edge<>(1, node9, node1);
        Graph.Edge<Integer> edge11 = new Graph.Edge<>(1, node10, node1);
        Graph.Edge[] edges = {edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11};
        for (int i = 0; i < edges.length; i++) {
            graph.edges.add(edges[i]);
        }
        List<Graph.Node<Integer>> res1 = sortedTopology(graph);
        List<Graph.Node<Integer>> res2 = sortedTopology1(graph);
        System.out.println(res1);
        System.out.println(res2);

    }


}