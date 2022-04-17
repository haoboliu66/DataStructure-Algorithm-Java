package fundamental.graph;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        Graph.Node<Integer> a = new Graph.Node<>(1);
        Graph.Node<Integer> b = new Graph.Node<>(2);
        Graph.Node<Integer> c = new Graph.Node<>(3);
        Graph.Node<Integer> d = new Graph.Node<>(4);
        Graph.Node<Integer> e = new Graph.Node<>(5);
        Graph.Edge<Integer> e1 = new Graph.Edge<>(4, a, d);
        Graph.Edge<Integer> e2 = new Graph.Edge<>(6, a, c);
        Graph.Edge<Integer> e3 = new Graph.Edge<>(1, a, b);
        Graph.Edge<Integer> e4 = new Graph.Edge<>(2, b, c);
        Graph.Edge<Integer> e5 = new Graph.Edge<>(1, c, d);
        Graph.Edge<Integer> e6 = new Graph.Edge<>(5, d, e);
        Graph.Edge<Integer> e7 = new Graph.Edge<>(1, c, e);
        Graph.Edge<Integer> e8 = new Graph.Edge<>(4, b, e);
        a.edges.add(e1);
        a.edges.add(e2);
        a.edges.add(e3);

        b.edges.add(e4);
        b.edges.add(e8);

        c.edges.add(e5);
        c.edges.add(e7);

        d.edges.add(e6);
        Map<Graph.Node<Integer>, Integer> res = dijkstra(a, 5);
        System.out.println(res);
    }

    public static Map<Graph.Node<Integer>, Integer> dijkstra(Graph.Node<Integer> start, int size) {
        //int size = graph.nodes.size();
        Set<Graph.Node<Integer>> lockedNode = new HashSet<>();
        Map<Graph.Node<Integer>, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 0);
        //lockedNode.add(start);
        while (lockedNode.size() < size) {
            Graph.Node<Integer> minNode = getMinDistanceNode(distanceMap, lockedNode);
            List<Graph.Edge<Integer>> edges = minNode.edges;
            for (Graph.Edge<Integer> edge : edges) {
                Graph.Node<Integer> toNode = edge.to;
                int weight = edge.weight;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, weight + distanceMap.get(minNode));
                } else {
                    int shorterDistance = Math.min(distanceMap.get(toNode), weight + distanceMap.get(minNode));
                    distanceMap.put(toNode, shorterDistance);
                }
            }
            lockedNode.add(minNode);
        }

        return distanceMap;
    }

    // find the node not locked with minimum distance in the map
    public static Graph.Node<Integer> getMinDistanceNode(Map<Graph.Node<Integer>, Integer> distanceMap, Set<Graph.Node<Integer>> lockedNode) {
        Graph.Node<Integer> minNode = null;
        for (Map.Entry<Graph.Node<Integer>, Integer> entry : distanceMap.entrySet()) {
            if (!lockedNode.contains(entry.getKey())) {
                if (minNode == null) {
                    minNode = entry.getKey();
                }
                if (!lockedNode.contains(minNode)) {
                    minNode = entry.getValue() < distanceMap.get(minNode) ? entry.getKey() : minNode;
                }
            }
        }
        return minNode;
    }


    public static class minHeap<V> {

        V[] heap;
        int heapSize;
        HashMap<V, Integer> indexMap;

    }

    public static class NodeRecord<V> {
        public Graph.Node<V> node;
        public int distance;

        public NodeRecord(Graph.Node<V> node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static <V> HashMap<Graph.Node<V>, Integer> dijkstraWithMinHeap(Graph.Node<V> start) {
        HashMap<Graph.Node<V>, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 0);
        HashSet<Graph.Node<V>> nodeSet = new HashSet<>();
        PriorityQueue<Graph.Edge<V>> priorityQueue = new PriorityQueue<>();
        HashSet<Graph.Node<V>> visitedNode = new HashSet<>();
        visitedNode.add(start);
        List<Graph.Edge<V>> edges = start.edges;
        for (Graph.Edge<V> edge : edges) {
            int distance = distanceMap.get(edge.from) + edge.weight;
            distanceMap.put(edge.to, distance);
            priorityQueue.add(edge);
        }

        while (!priorityQueue.isEmpty()) {
            Graph.Edge<V> edge = priorityQueue.poll();
            for (Graph.Node<V> node : edge.from.neighbours) {

            }

        }

        return distanceMap;
    }
}
