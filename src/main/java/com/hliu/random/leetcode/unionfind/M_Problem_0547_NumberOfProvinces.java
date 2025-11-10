package com.hliu.random.leetcode.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class M_Problem_0547_NumberOfProvinces {

    public static void main(String[] args) {
        int[][] m = {{1,1,0},{1,1,0},{0,0,1}};
        int res = findCircleNum(m);
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i + 1, j + 1);
                }
            }
        }
        return unionFind.size();
    }


    public static class UnionFind {

        private static class Node {
            int value;

            public Node(int v) {
                value = v;
            }

            @Override
            public String toString() {
                return "TreeNode{" +
                        "value=" + value +
                        '}';
            }
        }

        private Map<Integer, Node> nodesMap;
        private Map<Node, Integer> sizeMap;
        private Map<Node, Node> parent;

        public UnionFind(int N) {
            nodesMap = new HashMap<>();
            sizeMap = new HashMap<>();
            parent = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                Node n = new Node(i);
                nodesMap.put(i, n);
                sizeMap.put(n, 1);
                parent.put(n, n);
            }
        }

        public boolean isSameSet(int v1, int v2) {
            return findFather(nodesMap.get(v1)) == findFather(nodesMap.get(v2));
        }

        private Node findFather(Node cur) {
            Stack<Node> s = new Stack<>();
            while (cur != parent.get(cur)) {
                s.push(cur);
                cur = parent.get(cur);
            }
            while (!s.isEmpty()) {
                parent.put(s.pop(), cur);
            }
            return cur;
        }

        public int size() {
            System.out.println("size: " + sizeMap);
            System.out.println("parent: " + parent);
            return sizeMap.size();
        }


        public void union(int v1, int v2) {
            Node aHead = findFather(nodesMap.get(v1));
            Node bHead = findFather(nodesMap.get(v2));
            if (aHead == bHead) return;

            int aSize = sizeMap.get(aHead);
            int bSize = sizeMap.get(bHead);

            Node larger = aSize > bSize ? aHead : bHead;
            Node smaller = larger == aHead ? bHead : aHead;
            parent.put(smaller, larger);
            sizeMap.put(larger, aSize + bSize);
            sizeMap.remove(smaller);
        }


    }

}
