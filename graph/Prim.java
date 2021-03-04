package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
public class Prim {

    public static <V> Set<Edge<V>> primMST(Node<V> node) {
        Comparator<Edge<V>> edgeComp = new Comparator<Edge<V>>() {
            @Override
            public int compare(Edge<V> o1, Edge<V> o2) {
                return o1.weight - o2.weight;
            }
        };
        Set<Edge<V>> result = new HashSet<>(); // 存放结果Set
        PriorityQueue<Edge<V>> priorityQueue = new PriorityQueue<>(edgeComp);//小根堆,用于返回边
        HashSet<Node<V>> visitedNode = new HashSet<>();//已解锁的点
        HashSet<Edge<V>> visitedEdge = new HashSet<>();//已解锁的边

        for(Edge<V> edge: node.edges){
            if(!visitedEdge.contains(edge)){
                visitedEdge.add(edge); //记录初始情况下解锁的边
                priorityQueue.add(edge);// 先解锁初始node的所有相邻边
            }
        }

        while (!priorityQueue.isEmpty()) {
            Edge<V> edge = priorityQueue.poll();//取出已解锁的边中最小的边
            result.add(edge);//把最小的边加入result
            Node<V> toNode = edge.to; // 这条最小边连着的点, 可能是一个新的Node

            if (!visitedNode.contains(toNode)) { // 如果这个新node还没被解锁
                visitedNode.add(toNode);// 记录下该node已解锁

                for (Edge<V> edge1 : toNode.edges) { // 遍历该新node所有相邻的边

                    if (!visitedEdge.contains(edge1)) { // 如果有哪条边还没被解锁过
                        priorityQueue.add(edge1); // 加入小根堆并记录下已解锁
                        visitedEdge.add(edge1);
                    }
                }
            }
        }
        return result;
    }




}
