package graph;

import java.util.*;

public class Graph<V> {

    public HashMap<Integer,Node<V>> nodes;
    public Set<Edge<V>> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }


}
