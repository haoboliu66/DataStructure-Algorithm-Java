package fundamental.unionfind;

import java.util.Map;
import java.util.Stack;

public class UnionFind<V> {

    private static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    Map<V, Node<V>> nodes;
    Map<Node<V>, Node<V>> parent;
    Map<Node<V>, Integer> sizeMap;

    public void union(V n1, V n2) {
        Node<V> head1 = findFather(nodes.get(n1));
        Node<V> head2 = findFather(nodes.get(n2));
        if (head1 == head2) return;
        // head1 and head2 are from 2 different sets
        Node<V> larger = sizeMap.get(head1) > sizeMap.get(head2) ? head1 : head2;
        Node<V> smaller = larger == head1 ? head2 : head1;
        int largerSize = sizeMap.get(larger);
        int smallerSize = sizeMap.get(smaller);
        parent.put(smaller, larger);
        sizeMap.put(larger, largerSize + smallerSize);
        sizeMap.remove(smaller);
    }

    private Node<V> findFather(Node<V> cur) {
        Stack<Node<V>> stack = new Stack<>();
        while (cur != parent.get(cur)) {
            stack.push(cur);
            cur = parent.get(cur);
        }
        while (!stack.isEmpty()) {
            parent.put(stack.pop(), cur);
        }
        return cur;
    }

    public boolean isSameSet(V n1, V n2) {
        return findFather(nodes.get(n1)) == findFather(nodes.get(n2));
    }


}
