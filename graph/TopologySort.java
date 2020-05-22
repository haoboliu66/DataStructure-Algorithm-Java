package graph;

import java.util.*;

/**
 * @author andy-liu
 * @date 2020/5/21 - 8:28 AM
 */
public class TopologySort {

    // 前提是有向无环图, 所以肯定至少有一个in-degree为0的节点作为起点
    public static <V> List<Node<V>> sortedTopology(Graph<V> graph) {
        if (graph == null) {
            return new ArrayList<>();
        }
        HashMap<Node<V>, Integer> inDegreeMap = new HashMap<>();
        Queue<Node<V>> zeroInDegreeQueue = new LinkedList<>();
        ArrayList<Node<V>> res = new ArrayList<>();
        for (Node<V> node : graph.nodes.values()) {
            if (node.in == 0) {
                zeroInDegreeQueue.add(node);
            }
            inDegreeMap.put(node, node.in);
        }
        while (!zeroInDegreeQueue.isEmpty()) {
            Node<V> cur = zeroInDegreeQueue.poll();
            res.add(cur);
            for (Node<V> node : cur.neighbours) {
                inDegreeMap.put(node, inDegreeMap.get(node) - 1);
                if (inDegreeMap.get(node) == 0) {
                    zeroInDegreeQueue.add(node);
                }
            }
        }
        return res;
    }


    public static <V> List<Node<V>> sortedTopology1(Graph<V> graph) {
        if (graph == null) {
            return new ArrayList<>();
        }
        Queue<Node<V>> zeroInDegreeQueue = new LinkedList<>();
        ArrayList<Node<V>> res = new ArrayList<>();
        for (Node<V> node : graph.nodes.values()) {
            if (node.in == 0) {
                zeroInDegreeQueue.add(node);
            }
        }
        while (!zeroInDegreeQueue.isEmpty()) {
            Node<V> cur = zeroInDegreeQueue.poll();
            res.add(cur);
            for (Node<V> node : cur.neighbours) {
                if (--node.in == 0) {
                    zeroInDegreeQueue.add(node);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node8 = new Node<>(8);
        Node<Integer> node9 = new Node<>(9);
        Node<Integer> node10 = new Node<>(10);

        Node[] nodess = {node1,node2,node3,node4,node5,node6,node7,node8,node9,node10};
        Graph<Integer> graph = new Graph<>();
        for(int i=0; i<nodess.length;i++){
            graph.nodes.put(i + 1,nodess[i]);
        }
        Edge<Integer> edge1 = new Edge<>(1,node1,node2);
        Edge<Integer> edge2 = new Edge<>(1,node1,node3);
        Edge<Integer> edge3 = new Edge<>(1,node2,node3);
        Edge<Integer> edge4 = new Edge<>(1,node3,node4);
        Edge<Integer> edge5 = new Edge<>(1,node3,node5);
        Edge<Integer> edge6 = new Edge<>(1,node4,node5);
        Edge<Integer> edge7 = new Edge<>(1,node5,node6);
        Edge<Integer> edge8 = new Edge<>(1,node6,node7);
        Edge<Integer> edge9 = new Edge<>(1,node8,node6);
        Edge<Integer> edge10 = new Edge<>(1,node9,node1);
        Edge<Integer> edge11 = new Edge<>(1,node10,node1);
        Edge[] edges = {edge1,edge2,edge3,edge4,edge5,edge6,edge7,edge8,edge9,edge10,edge11};
        for(int i=0; i<edges.length;i++){
            graph.edges.add(edges[i]);
        }
        List<Node<Integer>> res1 = sortedTopology(graph);
        List<Node<Integer>> res2 = sortedTopology1(graph);
        System.out.println(res1);
        System.out.println(res2);

    }



}