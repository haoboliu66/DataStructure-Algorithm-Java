package src.main.java.leetcode.binarysearch;

import java.util.*;

public class M_Problem_0378_KthSmallestElementInSortedMatrix {
    /*
    Given an n x n matrix where each of the rows and columns are sorted in ascending order,
    return the kth smallest element in the matrix.

    Note that it is the kth smallest element in the sorted order,
    not the kth distinct element.

    1 <= k <= n2 :   k is valid
     */
    public static int kthSmallest(int[][] matrix, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                list.add(matrix[i][j]);
            }
        }
        Collections.sort(list); // O(N^2 * logN^2)
        return list.get(k - 1);
    }

    /*
    min-heap solution1
     */
    public static int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        int n = matrix.length;
        // add the first row into min-heap
        for (int j = 0; j < n; j++) {
            minHeap.add(new Node(0, j, matrix[0][j]));
        }
        k--;   //如果求第k小，堆只能弹出k - 1数，所以k先--
        while (k != 0 && !minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            k--;

            // 如果当前cur已经到了last row
            if (cur.x == n - 1) {
                continue;
            }
            // 如果cur还没到last row，就把cur下面的元素加入堆
            minHeap.add(new Node(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
        }
        return minHeap.peek().val;
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        public int compareTo(Node n) {
            return this.val - n.val;
        }
    }

    /*
    min-heap solution2
     */
    public static int kthSmallest2_1(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];
        minHeap.offer(new Node(0, 0, matrix[0][0]));
        k--;
        while (k != 0) {
            Node cur = minHeap.poll();
            if (cur.x < n - 1 && !visited[cur.x + 1][cur.y]) {
                minHeap.add(new Node(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
                visited[cur.x + 1][cur.y] = true;
            }
            if (cur.y < n - 1 && !visited[cur.x][cur.y + 1]) {
                minHeap.add(new Node(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
                visited[cur.x][cur.y + 1] = true;
            }
            k--;
        }
        return minHeap.peek().val;
    }

    /*
    binary search solution
     */
    public static int kthSmallest3(int[][] matrix, int k) {
        int n = matrix.length;
        int hi = matrix[n - 1][n - 1], lo = matrix[0][0], mid;
        int count = k - 1;
        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);
            int num = countLessOrEqual(mid, matrix);
            if (num < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private static int countLessOrEqual(int mid, int[][] matrix) {
        int n = matrix.length, i = n - 1, j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > mid) {
                i--;
            } else {
                count += (i + 1);
                j++;
            }
        }
        return count;
    }


    public static int findKth(int[] arr, int k) {
        int lo = arr[0];
        int hi = arr[arr.length - 1];
        int mid;
        while (lo <= hi) {
            mid = lo + ((hi - lo) >> 1);
            int count = countNonBigger(arr, mid);
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public static int countNonBigger(int[] arr, int mid) {
        int i;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] > mid) break;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 9, 10, 11, 12, 13, 13, 15};
        int kth = findKth(arr, 6);
        System.out.println(kth);

    }

}
