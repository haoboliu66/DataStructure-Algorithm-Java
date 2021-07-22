package fundamental.graph.mst;

import fundamental.graph.Graph;

import java.util.*;

public class Kruskal {

    public static class UnionSet<V>{

        HashMap<Graph.Node<V>,Integer> sizeMap; //存的是以每个key为代表的集合对应的Size
        HashMap<Graph.Node<V>, Graph.Node<V>> parents; //存的是每个元素和其上面一个元素的映射

        public UnionSet() {
            sizeMap = new HashMap<>();
            parents = new HashMap<>();
        }

        public void makeSets(Collection<Graph.Node<V>> nodes){
            sizeMap.clear();
            parents.clear();
            for(Graph.Node<V> node : nodes){
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Graph.Node<V> findFather(Graph.Node<V> cur){
            Stack<Graph.Node<V>> stack = new Stack<>();
            while(cur != parents.get(cur)){
                stack.push(cur);
                cur = parents.get(cur);
            }
            while(!stack.isEmpty()){
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(Graph.Node<V> node1, Graph.Node<V> node2){
            if(!parents.containsKey(node1) || !parents.containsKey(node2)){
                return false;
            }
            return findFather(node1) == findFather(node2);
        }

        public void union(Graph.Node<V> node1, Graph.Node<V> node2){
            if(!parents.containsKey(node1) || !parents.containsKey(node2)){
                return;
            }
            Graph.Node<V> head1 = findFather(node1);
            Graph.Node<V> head2 = findFather(node2);
            // 找出两个集合哪个Size大,哪个Size小, 用小的挂在大的上面
            Graph.Node<V> larger = sizeMap.get(head1) >= sizeMap.get(head2)? head1: head2;
            Graph.Node<V> smaller = larger == head1? head2: head1;
            parents.put(smaller,larger);
            sizeMap.put(larger,sizeMap.get(larger) + sizeMap.get(smaller));
            sizeMap.remove(smaller);
        }
    }

    public static <V> Set<Graph.Edge<V>> kruskalMST(Graph<V> graph){
        Comparator<Graph.Edge<V>> edgeComparator = (o1, o2) -> o1.weight - o2.weight;

        UnionSet<V> unionSet = new UnionSet<>();
        PriorityQueue<Graph.Edge<V>> queue = new PriorityQueue<>(edgeComparator);
        unionSet.makeSets(graph.nodes.values());//先把每一个node构成单独的集合
        Set<Graph.Edge<V>> result = new HashSet<>();
//        for(Edge<V> e: fundamental.graph.edges){//遍历把图中所有边加入小根堆
//            queue.add(e);
//        }
        queue.addAll(graph.edges);//图中所有的边加入小根堆
        while(!queue.isEmpty()){
            Graph.Edge<V> edge = queue.poll();// 选出图中目前已知最小的边
            if(!unionSet.isSameSet(edge.from,edge.to)){//如果这条边的两头node不在一个集合中
                unionSet.union(edge.from,edge.to);//合并两头的Node成一个集合
                result.add(edge);//这条边加入结果集
            }
        }
        return result;
    }
}
