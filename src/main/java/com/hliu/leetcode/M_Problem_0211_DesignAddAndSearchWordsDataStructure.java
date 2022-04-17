package src.main.java.leetcode;

public class M_Problem_0211_DesignAddAndSearchWordsDataStructure {

    class WordDictionary {

        Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            char[] str = word.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; i++) {
                char c = str[i];
                if (cur.nexts[c] == null) {
                    cur.nexts[c] = new Node(c);
                }
                cur = cur.nexts[c];
            }
        }

        public boolean search(String word) {
            char[] str = word.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; ) {
                if (str[i] != '.') {
                    if (cur.nexts[str[i]] == null) return false;
                    cur = cur.nexts[str[i]];
                } else {

                    Node[] nexts = cur.nexts;
                    for (int n = 0; n < nexts.length; n++) {  //固定长度的循环
                        Node tmp = cur;
                        if (nexts[n] != null) {
                            tmp = tmp.nexts[n];
                            //沿着不为null的这条路继续向下检查，index从i + 1开始
                            for (int j = i + 1; j < str.length; j++) {
                                if (tmp.nexts[j] == null) {
                                    break;
                                }
                                tmp = tmp.nexts[j];
                            }
                        }
                    }


//                    char tmp = '.';
//                    for (char swap = 'a'; swap <= 'z'; swap++) {
//                        str[i] = swap;
//                        int j = i;
//                        while (j != str.length) {
//                            if (cur.nexts[str[j]] != null) {
//                                cur = cur.nexts[str[j]];
//                            }
//                            j++;
//                        }
//                    }
//                    str[i] = tmp;
                }
                i++;
            }

            return true;
        }

    }

    private class Node {
        char c;
        Node[] nexts;

        public Node() {
            nexts = new Node[128];
        }

        public Node(char c) {
            this.c = c;
            nexts = new Node[128];
        }
    }


}
