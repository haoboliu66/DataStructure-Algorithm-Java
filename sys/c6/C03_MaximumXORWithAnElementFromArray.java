package sys.c6;

import java.util.Arrays;

public class C03_MaximumXORWithAnElementFromArray {

    /*
    1707. Maximum XOR With an Element From Array
     */

    private static class Node {
        Node[] nexts = new Node[2];
        int minGoingAhead = Integer.MAX_VALUE;
    }

    private static class Trie {
        Node head = new Node();

        public void add(int num) {
            Node cur = head;
            cur.minGoingAhead = Math.min(cur.minGoingAhead, num);
            for (int shift = 30; shift >= 0; shift--) {
                int path = (num >> shift) & 1;
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
                cur.minGoingAhead = Math.min(cur.minGoingAhead, num);
            }
        }

        public int findMaxMatchWithCondition(int num, int upperBound) {
            Node cur = head;
            if (cur.minGoingAhead > upperBound) {
                return -1;
            }
            int ans = 0;
            for (int shift = 30; shift >= 0; shift--) {
                int path = (num >> shift) & 1;
                int expected = path ^ 1;
                int realPath =
                        (cur.nexts[expected] == null || cur.nexts[expected].minGoingAhead > upperBound) ?
                                expected ^ 1 : expected;

                ans |= (realPath ^ path) << shift;
                cur = cur.nexts[realPath];
            }
            return ans;
        }
    }

    public static int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] res = new int[n];
        Trie trie = new Trie();
        for (int num : nums) {
            trie.add(num);
        }
        for (int i = 0; i < n; i++) {
            int[] curQuery = queries[i];
            res[i] = trie.findMaxMatchWithCondition(curQuery[0], curQuery[1]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        int[][] queries = {{3, 1}, {1, 3}, {5, 6}};
        int[] res = maximizeXor(nums, queries);
        System.out.println(Arrays.toString(res));

    }

}
