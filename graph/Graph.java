package graph;

import java.util.*;

/**
 * @author andy-liu
 * @date 2020/5/21 - 12:14 AM
 */
public class Graph<V> {

    public HashMap<Integer,Node<V>> nodes;
    public Set<Edge<V>> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }


}
