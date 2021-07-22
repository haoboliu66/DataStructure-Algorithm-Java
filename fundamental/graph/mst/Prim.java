package fundamental.graph.mst;

import fundamental.graph.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
public class Prim {

    public static <V> Set<Graph.Edge<V>> primMST(Graph.Node<V> node) {
        Comparator<Graph.Edge<V>> edgeComp = new Comparator<Graph.Edge<V>>() {
            @Override
            public int compare(Graph.Edge<V> o1, Graph.Edge<V> o2) {
                return o1.weight - o2.weight;
            }
        };
        Set<Graph.Edge<V>> result = new HashSet<>(); // 存放结果Set
        PriorityQueue<Graph.Edge<V>> priorityQueue = new PriorityQueue<>(edgeComp);//小根堆,用于返回边
        HashSet<Graph.Node<V>> visitedNode = new HashSet<>();//已解锁的点
        HashSet<Graph.Edge<V>> visitedEdge = new HashSet<>();//已解锁的边

        for(Graph.Edge<V> edge: node.edges){
            if(!visitedEdge.contains(edge)){
                visitedEdge.add(edge); //记录初始情况下解锁的边
                priorityQueue.add(edge);// 先解锁初始node的所有相邻边
            }
        }

        while (!priorityQueue.isEmpty()) {
            Graph.Edge<V> edge = priorityQueue.poll();//取出已解锁的边中最小的边
            result.add(edge);//把最小的边加入result
            Graph.Node<V> toNode; // 这条最小边连着的点, 可能是一个新的Node
            toNode = edge.to;

            if (!visitedNode.contains(toNode)) { // 如果这个新node还没被解锁
                visitedNode.add(toNode);// 记录下该node已解锁

                for (Graph.Edge<V> edge1 : toNode.edges) { // 遍历该新node所有相邻的边

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
