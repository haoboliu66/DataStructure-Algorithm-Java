package src.main.java.sys.c6;

public class C02_MaximumXOROfTwoNumbersArray {

    /*
    421. Maximum XOR of Two Numbers in an Array
     */

    public int findMaximumXOR(int[] nums) {
        int max = 0;
        Trie trie = new Trie();
        trie.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int ans = trie.findMaxMatch(nums[i]);
            trie.add(nums[i]);
            max = Math.max(ans, max);

        }
        return max;
    }


    private class Node {
        Node[] next = new Node[2];
    }

    public class Trie {

        Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int shift = 30; shift >= 0; shift--) {

                int path = (num >> shift) & 1; // 0 or 1
                if (cur.next[path] == null) {
                    cur.next[path] = new Node();
                }
                cur = cur.next[path];
            }
        }

        public int findMaxMatch(int num) {
            int res = 0;
            Node cur = head;
            for (int shift = 30; shift >= 0; shift--) {
                int bit = (num >> shift) & 1;
                int expected = bit ^ 1;  //最好的结果, 就是 bit ^ 和自己不一样的值

                int realPath = cur.next[expected] != null ? expected : expected ^ 1;
                // 但是实际能获得的数字呢, 如果期待的路为null, 那只能走相反的路

                res |= ((bit ^ realPath) << shift); // 可以获得的值: bit ^ realPath的值
                cur = cur.next[realPath];

            }
            return res;
        }
    }

}
