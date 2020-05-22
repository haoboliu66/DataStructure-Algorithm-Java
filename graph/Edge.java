package graph;

/**
 * @author andy-liu
 * @date 2020/5/21 - 12:15 AM
 */
public class Edge<V> {

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
