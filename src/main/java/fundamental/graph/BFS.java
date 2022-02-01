package src.main.java.fundamental.graph;

import java.util.*;

public class BFS {

    public static <V> void BreathFirstSearch(Graph.Node<V> node){
        if(node == null){
            return;
        }
        Queue<Graph.Node<V>> queue = new LinkedList<>();
        Set<Graph.Node<V>> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()){
            Graph.Node<V> cur = queue.poll();

            process(cur);  // some process;  e.g print

            List<Graph.Node<V>> adjacents = cur.neighbours;
            for(Graph.Node<V> next: adjacents){
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    private static void process(Graph.Node cur) {
        System.out.println(cur);
    }
}
