package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andy-liu
 * @date 2020/5/21 - 12:15 AM
 */
public class Node<V> {

    public V value;
    public int in;
    public int out;
    public List<Node<V>> neighbours;
    public List<Edge<V>> edges;

    public Node(V value) {
        this.value = value;
        neighbours = new ArrayList<>();
        edges = new ArrayList<>();
        in = 0;
        out = edges.size();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return value != null ? value.equals(node.value) : node.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
