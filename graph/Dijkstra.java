package graph;

import java.util.*;

/**
 * @author andy-liu
 * @date 2020/5/21 - 2:30 PM
 */
public class Dijkstra {

    public static class minHeap<V>{

        V[] heap;
        int heapSize;
        HashMap<V, Integer> indexMap;



    }

    public static class NodeRecord<V>{
        public Node<V> node;
        public int distance;

        public NodeRecord(Node<V> node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static <V> HashMap<Node<V>, Integer> dijkstraWithMinHeap(Node<V> start){
        HashMap<Node<V>,Integer> distanceMap = new HashMap<>();
        distanceMap.put(start,0);
        HashSet<Node<V>> nodeSet = new HashSet<>();
        PriorityQueue<Edge<V>> priorityQueue = new PriorityQueue<>();
        HashSet<Node<V>> visitedNode = new HashSet<>();
        visitedNode.add(start);
        List<Edge<V>> edges = start.edges;
        for(Edge<V> edge: edges){
            int distance = distanceMap.get(edge.from) + edge.weight;
            distanceMap.put(edge.to,distance);
            priorityQueue.add(edge);
        }

        while(!priorityQueue.isEmpty()){
            Edge<V> edge = priorityQueue.poll();
            for(Node<V> node: edge.from.neighbours){

            }

        }


        return distanceMap;
    }
}
