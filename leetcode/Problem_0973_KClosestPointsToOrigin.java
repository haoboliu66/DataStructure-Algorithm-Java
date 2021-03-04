package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0973_KClosestPointsToOrigin {
/*
 kth Problem
 */

    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {-2, 2}};
        int[][] arr1 = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
        int[][] res = kClosest1(arr1, k);
        int[][] res1 = kClosest2(arr1, k);
        System.out.println(equalMatrix(res, res1));
    }

    public static boolean equalMatrix(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int[][] kClosest1(int[][] points, int k) {
        if (points == null || k <= 0) {
            return null;
        }
        if (k > points.length) {
            return null;
        }

        NodeComparator comparator = new NodeComparator();
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(comparator);

        for (int i = 0; i < k; i++) {
            maxHeap.add(new Node(points[i][0], points[i][1]));
        }
        for (int i = k; i < points.length; i++) {
            Node node = new Node(points[i][0], points[i][1]);
            System.out.println(comparator.compare(node, maxHeap.peek()));
            if (comparator.compare(node, maxHeap.peek()) > 0) {
                maxHeap.poll();
                maxHeap.add(node);
            }
        }
        int[][] res = new int[k][2];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            Node n = maxHeap.poll();
            int[] arr = {n.x, n.y};
            res[index++] = arr;
        }

        return res;
    }



    public static int[][] kClosest2(int[][] points, int k) {
        if (points == null || k <= 0) {
            return null;
        }
        if (k > points.length) {
            return null;
        }
        Node[] nodes = new Node[points.length];
        for (int i = 0; i < points.length; i++) {
            nodes[i] = new Node(points[i][0], points[i][1]);
        }
        Node kthNode = process(nodes, 0, nodes.length - 1, k - 1);

        NodeComparator1 comparator = new NodeComparator1();
        int[][] res = new int[k][2];
        int index = 0;

        for (int[] point : points) {
            Node node = new Node(point[0], point[1]);
            if (comparator.compare(node, kthNode) <= 0) {
                res[index++] = new int[]{node.x, node.y};
            }
        }

        return res;
    }

    public static Node process(Node[] nodes, int L, int R, int index) {
        while (L < R) {
            Node pivot = nodes[L + (int) (Math.random() * (R - L + 1))];
            int[] range = partition(nodes, L, R, pivot);
            if (index >= range[0] && index <= range[1]) {
                return nodes[index];
            } else if (index < range[0]) {
                R = range[0] - 1;
            } else {
                L = range[1] + 1;
            }
        }
        return nodes[L];
    }

    public static int[] partition(Node[] nodes, int L, int R, Node pivot) {
        int less = L - 1;
        int more = R + 1;
        int index = L;
        NodeComparator1 comparator = new NodeComparator1();
        while (index < more) {
            if (comparator.compare(nodes[index], pivot) > 0) {
                swap(nodes, index, --more);
            } else if (comparator.compare(nodes[index], pivot) < 0) {
                swap(nodes, index++, ++less);
            } else {
                index++;
            }
        }
        return new int[]{less + 1, more - 1};
    }


    public static void swap(Node[] nodes, int i, int j) {
        Node tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }

    public static class NodeComparator1 implements Comparator<Node> {

        @Override
        public int compare(Node n1, Node n2) {

            return (n1.x * n1.x + n1.y * n1.y) - (n2.x * n2.x + n2.y * n2.y);

        }
    }


    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node n1, Node n2) {

            return (n2.x * n2.x + n2.y * n2.y) - (n1.x * n1.x + n1.y * n1.y);

        }
    }

}
