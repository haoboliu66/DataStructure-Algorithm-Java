package leetcode.unionfind;

import java.util.*;

public class M_Problem_0261_GraphValidTree {

    /* Similar: 323. Number of Connected Components in an Undirected Graph  */

    // Solution with UnionFind
    public static boolean validTree(int n, int[][] edges) {
        if (n == 1) return true;
        UnionFind2 uf = new UnionFind2(n);

        for (int[] e : edges) {
            int from = e[0];
            int to = e[1];

            if (uf.isSameSet(from, to)) { // cycle detection
                return false;
            }
            uf.union(from, to);
        }
        // No forest(multiple separate tree)
        return uf.size() == 1;
    }

    // Solution with DFS
    public static boolean validTree2(int n, int[][] edges) {
        if (n == 1) return true;

        return true;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        boolean res = validTree(n, edges);
        System.out.println(res);
    }

    /*  UnionFind with Array   */
    private static class UnionFind2 {
        int[] parentMap;
        int[] sizeMap;
        int size;

        public UnionFind2(int n) {
            parentMap = new int[n]; //parentMap[i] => 元素i的parent是parentMap[i]
            sizeMap = new int[n]; // sizeMap[i] => 以i为头的set, size是sizeMap[i]
            size = n;
            for (int i = 0; i < n; i++) {
                sizeMap[i] = 1;
                parentMap[i] = i;
            }
        }

        public int size() {
            return size;
        }

        public int findFather(int cur) {
            // parentMap[cur] => parent的index
            List<Integer> list = new ArrayList<>();
            while (parentMap[cur] != cur) {
                list.add(cur);
                cur = parentMap[cur];
            }
            for (int i : list) {
                parentMap[i] = cur;
            }
            return cur;
        }

        public boolean isSameSet(int a, int b) {
            int father1 = findFather(a);
            int father2 = findFather(b);

            return father1 == father2;
        }

        public void union(int a, int b) {
            int head1 = findFather(a);
            int head2 = findFather(b);
            if (head1 == head2) {
                return;
            }
            // sizeMap [i]    以i为头的set, size为sizeMap[i]
            int size1 = sizeMap[head1];
            int size2 = sizeMap[head2];
            int larger = size1 > size2 ? head1 : head2;
            int smaller = larger == head1 ? head2 : head1;

            sizeMap[larger] = size1 + size2;
            sizeMap[smaller] = 0;
            // parentMap[i]    [i]的parent是parentMap[i]
            parentMap[smaller] = larger;
            size--;
        }
    }

    /*  UnionFind with Map  */
    private static class UnionFind {
        Map<Integer, Node> nodeMap;
        Map<Node, Node> parentMap;
        Map<Node, Integer> sizeMap;

        public UnionFind(int n) {
            nodeMap = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                Node node = new Node(i);
                nodeMap.put(i, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public int size() {
            return sizeMap.size();
        }

        private Node findFather(Node cur) {
            Stack<Node> stack = new Stack<>();
            while (parentMap.get(cur) != cur) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }


        public boolean isSameSet(int a, int b) {
            if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) {
                return false;
            }
            Node node1 = findFather(nodeMap.get(a));
            Node node2 = findFather(nodeMap.get(b));

            return node1 == node2;
        }

        public void union(int a, int b) {
            if (isSameSet(a, b)) {
                return;
            }
            Node node1 = findFather(nodeMap.get(a));
            Node node2 = findFather(nodeMap.get(b));
            System.out.println("sizeMap: " + sizeMap);
            if (node1 != node2) {
                System.out.println("node1: " + node1);
                System.out.println("node2: " + node2);
                int size1 = sizeMap.get(node1);
                int size2 = sizeMap.get(node2);
                Node larger = size1 > size2 ? node1 : node2;
                Node smaller = larger == node1 ? node2 : node1;
                sizeMap.put(larger, size1 + size2);
                sizeMap.remove(smaller);
                parentMap.put(smaller, larger);
            }

        }
    }

    private static class Node {
        int val;

        public Node(int v) {
            val = v;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

}
