package advanced.top;

public class Problem_0208_ImplementTrie {

    public static class Trie {

        public static class Node {
            char c;
            int pass;
            int end;
            Node[] nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }

            public Node(char c) {
                this.c = c;
                nexts = new Node[26];
                pass = 0;
                end = 0;
            }
        }

        Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            char[] str = word.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; i++) {

                if (cur.nexts[str[i] - 'a'] == null) {
                    cur.nexts[str[i] - 'a'] = new Node(str[i]);
                }
                cur = cur.nexts[str[i] - 'a'];
                cur.pass++;
            }
            cur.end++;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            char[] str = word.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; i++) {

                if (cur.nexts[str[i] - 'a'] == null) {
                    return false;
                }
                cur = cur.nexts[str[i] - 'a'];
            }
            return cur.end > 0;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            char[] str = prefix.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; i++) {

                if (cur.nexts[str[i] - 'a'] == null) {
                    return false;
                }
                cur = cur.nexts[str[i] - 'a'];
            }
            return true;
        }
    }

}
