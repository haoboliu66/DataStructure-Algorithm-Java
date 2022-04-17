package fundamental.graph;

import java.util.*;

public class Graph<V> {

    public HashMap<V, Node<V>> nodes;
    public Set<Edge<V>> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    public static class Node<V> {

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
            return "SBTNode{" +
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

    public static class Edge<V> {

        public int weight;
        public Node<V> from;
        public Node<V> to;

        public Edge(int weight, Node<V> from, Node<V> to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
            from.out++;
            to.in++;
            from.neighbours.add(to);
        }
    }


}
