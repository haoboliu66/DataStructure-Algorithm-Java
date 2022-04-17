package fundamental.trie;


import java.util.HashMap;
import java.util.Map;

public class TrieTree {

    public static class Node {
        int pass;
        int end;
        public Node[] nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node[26];
        }
    }


    public static class Trie {
        private Node head = new Node();


        public void add(String word) {
            if (word == null) {
                return;
            }
            Node cur = head;
            cur.pass++;
            char[] str = word.toCharArray();
            //遍历word新建Node
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (cur.nexts[path] == null) {  //如果没有路
                    cur.nexts[path] = new Node(); // 新建一条路
                }
                //有路了
                cur = cur.nexts[path]; //移动到下一个Node
                cur.pass++; // 每到一个Node pass就++
            }
            cur.end++; //移动到最后一个Node, end++
        }

        public void delete(String word) {
            if (search(word) == 0) return;
            Node cur = head;
            cur.pass--;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (--cur.nexts[path].pass == 0) {
                    cur.nexts[path] = null;
                    return;
                }
                // 无需根据end设置, 因为如果end=1, 那么pass一定也是1
                cur = cur.nexts[path];
            }
            cur.end--;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node cur = head;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int c = str[i] - 'a';
                if (cur.nexts[c] == null) {
                    return 0;
                }
                cur = cur.nexts[c];
            }
            return cur.end;
        }


        public int prefixCount(String word) {
            if (word == null) {
                return 0;
            }
            Node cur = head;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (cur.nexts[path] == null) {
                    return 0;
                }
                cur = cur.nexts[path];
            }
            return cur.pass;
        }
    }

    // for test
    public static void rightInsert(Map<String, Integer> map, String word) {
        if (!map.containsKey(word)) {
            map.put(word, 1);
        } else {
            map.put(word, map.get(word) + 1);
        }
    }

    public static int rightSearch(Map<String, Integer> map, String word) {
        return map.getOrDefault(word, 0);
    }

    public static void rightDelete(Map<String, Integer> map, String word) {
        if (!map.containsKey(word)) {
            return;
        }
        map.put(word, map.get(word) - 1);
    }

    public static int rightPrefixCount(Map<String, Integer> map, String prefix) {
        int count = 0;
        for (String s : map.keySet()) {
            if (s.startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }

    public static String[] generateRandomStringArray(int len) {
        int n = (int) (Math.random() + 1) * len;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = generateRandomString((int) (Math.random() * len));
        }
        return arr;
    }

    private static String generateRandomString(int len) {
        int n = (int) (Math.random() + 1) * len;
        char[] str = new char[n];
        for (int i = 0; i < n; i++) {
            // [0, 25]
            str[i] = (char) ((int) (Math.random() * 5) + 'a');
        }
        return new String(str);
    }


    public static void main(String[] args) {
        int len = 10, times = 10000;
        String[] arr;
        String prefix;
        System.out.println("Started");
        for (int i = 0; i < times; i++) {
            Trie trie = new Trie();
            Map<String, Integer> map = new HashMap<>();
            arr = generateRandomStringArray(len);
            prefix = generateRandomString(arr[0].length());
            for (String s : arr) {
                trie.add(s);
                rightInsert(map, s);
            }
            if (trie.prefixCount(prefix) != rightPrefixCount(map, prefix)) {
                System.out.println("Oops - prefixCount1");
                break;
            }
            if (trie.search(prefix) != rightSearch(map, prefix)) {
                System.out.println("Oops - search1");
                break;
            }
            String random = arr[(int) (Math.random() * arr.length)];
            trie.delete(random);
            rightDelete(map, random);

            if (trie.search(random) != rightSearch(map, random)) {
                System.out.println("Oops - search2");
                System.out.println(trie.search(random));
                System.out.println(rightSearch(map, random));
                break;
            }
            if (trie.prefixCount(random) != rightPrefixCount(map, random)) {
                System.out.println("Oops - prefixCount2");
                System.out.println(trie.prefixCount(random));
                System.out.println(rightPrefixCount(map, random));
                break;
            }

        }
        System.out.println("Done");

    }


}