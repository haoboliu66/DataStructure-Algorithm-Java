package src.main.java.advanced.c3.class6;

import java.util.Arrays;

public class C01_MaxSubArrayXOR {

    /*
     子数组的最大异或和 => 使用Trie计算可以得到想要的数组前缀异或和
     */

    // 传统思维: 以[i]结尾求最大的异或和
    public static int maxSubArrayXor0(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int sum = 0, max = Integer.MIN_VALUE;
        Trie trie = new Trie();
        trie.add(0);  // 垫一个0
        for (int i = 0; i < n; i++) {
            sum ^= arr[i];   // 持续累加计算0...i的异或和
            int pre = trie.findMaxMatch(sum);  // 找到一个前缀, 两者异或的结果最大
            max = Math.max(max, pre);
            trie.add(sum);
        }

        return max;
    }

    private static class Node {
        Node[] nexts;

        public Node() {
            nexts = new Node[2];
        }
    }

    private static class Trie {
        Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int shift = 31; shift >= 0; shift--) {
                int path = (num >> shift) & 1;
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
        }

        // 在前缀树上找一条路径, 与num异或起来, 结果最大
        public int findMaxMatch(int num) {
            Node cur = head;
            int ans = 0;
            for (int shift = 31; shift >= 0; shift--) {
                int path = (num >> shift) & 1;
                int expected = shift == 31 ? path : path ^ 1;
                int realPath = cur.nexts[expected] != null ? expected : expected ^ 1;

                ans |= (realPath ^ path) << shift;

                cur = cur.nexts[realPath];
            }
            return ans;
        }
    }

    // for test
    public static int right(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int res = arr[i];
            max = Math.max(max, res);
            for (int j = i + 1; j < arr.length; j++) {
                res ^= arr[j];
                max = Math.max(res, max);
            }
        }

        return max;
    }

    public static int maxXorSubarrayZuo(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 准备一个前缀异或和数组eor
        // eor[i] = arr[0...i]的异或结果
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成eor数组，eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (Math.random() > 0.5 ? (-1) : 1) * (int) (Math.random() * (maxValue + 1));
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
            int res1 = maxSubArrayXor0(arr);
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
