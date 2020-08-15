package advanced.c2.hashmap;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/7/22 - 9:23 AM
 */
public class C02_Islands {


    /*
    200. Number of Islands
     */

    public static int numberOfIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    res++;
                    infect(grid, i, j);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] grid, int i, int j) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j < grid[0].length || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }


    public static class Node<V> {
        V value;

        public Node(V val) {
            value = val;
        }
    }

    public static class UnionFindSet<V> {
        HashMap<V, Node<V>> nodes;
        HashMap<Node<V>, Node<V>> parents;
        HashMap<Node<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for (V value : list) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }

        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }

            return cur;
        }

        public boolean isSameSet(V value1, V value2) {
            if (!nodes.containsKey(value1) || !nodes.containsKey(value2)) {
                return false;
            }

            Node<V> head1 = nodes.get(value1);
            Node<V> head2 = nodes.get(value2);
            return findFather(head1) == findFather(head2);
        }


        public void union(V value1, V value2) {
            if (!nodes.containsKey(value1) || !nodes.containsKey(value2)) {
                return;
            }
//            Node<V> head1 = nodes.get(value1);
//            Node<V> head2 = nodes.get(value2);
            Node<V> head1 = parents.get(value1);
            Node<V> head2 = parents.get(value2);
            Node<V> larger = sizeMap.get(head1) >= sizeMap.get(head2) ? head1 : head2;
            Node<V> smaller = larger == head1 ? head2 : head1;

            parents.put(smaller, larger);
            sizeMap.put(larger, sizeMap.get(larger) + sizeMap.get(smaller));
            parents.remove(smaller);

        }


    }


}
