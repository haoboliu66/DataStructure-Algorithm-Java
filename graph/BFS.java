package graph;

import java.util.*;

/**
 * @author andy-liu
 * @date 2020/5/21 - 7:59 AM
 */
public class BFS {

    public static <V> void BreathFirstSearch(Node<V> node){
        if(node == null){
            return;
        }
        Queue<Node<V>> queue = new LinkedList<>();
        Set<Node<V>> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()){
            Node<V> cur = queue.poll();
            process(cur);  // some process;  e.g print
            List<Node<V>> adjacents = cur.neighbours;
            for(Node<V> next: adjacents){
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    private static void process(Node cur) {
        System.out.println(cur);
    }
}
