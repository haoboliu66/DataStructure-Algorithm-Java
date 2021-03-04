package graph;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DFS {

    public static <V> void depthFirstSearch(Node<V> node){
        if(node == null){
            return;
        }
        Stack<Node<V>> stack = new Stack<>();
        HashSet<Node<V>> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node);
        while(!stack.isEmpty()){
            Node<V> cur = stack.pop();
            List<Node<V>> adjacents = cur.neighbours;
            for(Node<V> next: adjacents){
                if(!set.contains(next)){
                    stack.push(cur); // keep cur SBTNode for backtracking
                    stack.push(next);
                    set.add(next);
                    process(next);
                    break;
                }
            }
        }
    }

    public static <V> void depthFirstSearch1(Node<V> node){
        if(node == null){
            return;
        }
        Stack<Node<V>> stack = new Stack<>();
        HashSet<Node<V>> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        while(!stack.isEmpty()){
            Node<V> cur = stack.pop();
            process(cur);
            List<Node<V>> adjacents = cur.neighbours;
            for(Node<V> next: adjacents){
                if(!set.contains(next)){
                    stack.push(next);
                    set.add(next);
                }
            }
        }
    }

    private static void process(Node cur) {
        System.out.println(cur);
    }
}
