package src.main.java.sys.c6;

import java.util.Arrays;

public class C06_MaxTwoNumberAndOperation {

    // not done

    private static class Node {
        Node[] next = new Node[2];
    }

    public static class Trie {
        Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int shift = 30; shift >= 0; shift--) {
                int path = (num >> shift) & 1;
                if (cur.next[path] == null) {
                    cur.next[path] = new Node();
                }
                cur = cur.next[path];
            }
        }

        public int findMaxMatch(int num) {
            Node cur = head;
            int ans = 0;
            for (int shift = 30; shift >= 0; shift--) {
                int path = (num >> shift) & 1;
                int realPath = cur.next[1] != null ? 1 : 0;

                ans |= (realPath & path) << shift;
                cur = cur.next[realPath];
            }
            return ans;
        }
    }

    public static int getAndOperationMax(int[] arr) {
        Trie trie = new Trie();
        trie.add(arr[0]);
        int max = -1;
        for (int i = 1; i < arr.length; i++) {
            int res = trie.findMaxMatch(arr[i]);
            max = Math.max(max, res);
            trie.add(arr[i]);
        }
        return max;
    }


    // for test
    public static int right(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int a = arr[i];
            for (int j = i + 1; j < n; j++) {
                int ans = a ^ arr[j];
                max = Math.max(max, ans);
            }
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 20, max = 100, times = 150000;
        int[] arr;
        System.out.println("Go");
        for (int i = 0; i < times; i++) {
            arr = generateRandomArray(len, max);
            int right = right(arr);
            int res1 = getAndOperationMax(arr);
            if (right != res1) {
                System.out.println(Arrays.toString(arr));
                System.out.println(right);
                System.out.println(res1);
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }


}
