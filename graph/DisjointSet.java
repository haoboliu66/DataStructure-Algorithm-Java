package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/5/20 - 7:05 AM
 */
public class DisjointSet {

    public static class Node<V>{
        V value;

        public Node(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static class UnionSet<V>{
        HashMap<V,Node<V>> nodes; // 存进去的元素和他对应的包装元素
        HashMap<Node<V>,Integer> sizeMap; //存的是以每个key为代表的集合对应的Size
        HashMap<Node<V>,Node<V>> parents; //存的是每个元素和其上面一个元素的映射

        public UnionSet(List<V> values) {
            nodes = new HashMap<>();
            sizeMap = new HashMap<>();
            parents = new HashMap<>();
            for(V val : values){
                Node node = new Node(val);
                nodes.put(val,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> stack = new Stack<>();
            while(cur != parents.get(cur)){
                stack.push(cur);
                cur = parents.get(cur);
            }
            // 长链扁平化(优化)
            while(!stack.isEmpty()){
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V value1, V value2){
            if(!nodes.containsKey(value1) || !nodes.containsKey(value2)){
                return false;
            }
            // find corresponding wrapper node
            Node<V> node1 = nodes.get(value1);
            Node<V> node2 = nodes.get(value2);
            return findFather(node1) == findFather(node2);
        }

        public void union(V value1, V value2){
            if(!nodes.containsKey(value1) || !nodes.containsKey(value2)){
                return;
            }
            Node<V> node1 = nodes.get(value1);
            Node<V> node2 = nodes.get(value2);
            Node<V> head1 = findFather(node1);
            Node<V> head2 = findFather(node2);
            // 找出两个集合哪个Size大,哪个Size小, 用小的挂在大的上面
            Node<V> larger = sizeMap.get(head1) >= sizeMap.get(head2)? head1: head2;
            Node<V> smaller = larger == head1? head2: head1;
            parents.put(smaller,larger);
            sizeMap.put(larger,sizeMap.get(larger) + sizeMap.get(smaller));
            sizeMap.remove(smaller);
        }
    }



}
