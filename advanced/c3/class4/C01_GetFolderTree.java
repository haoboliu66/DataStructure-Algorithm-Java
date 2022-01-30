package advanced.c3.class4;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class C01_GetFolderTree {

    static class TrieNode {
        public String path;
        public Map<String, TrieNode> nextMap;

        public TrieNode(String s) {
            path = s;
            nextMap = new HashMap<>();
        }
    }
    // 生成Trie
    public TrieNode printFolder(String[] paths) {
        TrieNode head = new TrieNode("");
        for (String p : paths) {
            String[] nodes = p.split("////");
            TrieNode cur = head;
            for (String s : nodes) {
                if (!cur.nextMap.containsKey(s)) {
                    TrieNode next = new TrieNode(s);
                    cur.nextMap.put(s, next);
                }
                TrieNode nextNode = cur.nextMap.get(s);
            }
        }
        return head;
    }


    static class Node {
        public String path;
        public TreeMap<String, Node> nextMap;

        public Node(String p) {
            path = p;
            nextMap = new TreeMap<>();
        }

        StringBuilder sb = new StringBuilder();

        @Override
        public String toString() {
            print(this);
            return sb.toString();
        }

        private void print(Node node) {
            if (node.nextMap.size() == 0) {
                sb.append(node.path);
                return;
            }
            for (Node n : node.nextMap.values()) {
                print(n);
            }
        }
    }

    public static void print(String[] folderPaths) {
        if (folderPaths == null || folderPaths.length == 0) {
            return;
        }
        Node head = generateFolderTree(folderPaths); //所有路径生成Trie
        printProcess(head, 0); //从head开始深度优先遍历打印folder path
    }

    private static Node generateFolderTree(String[] folderPaths) {
        Node head = new Node("HEAD");
        for (String folderPath : folderPaths) {
            String[] paths = folderPath.split("\\\\");
            Node cur = head;
            for (int i = 0; i < paths.length; i++) {
                if (!cur.nextMap.containsKey(paths[i])) {
                    //没有path, 创造path(把Node信息存在path的结束节点)
                    cur.nextMap.put(paths[i], new Node(paths[i]));
                }
                //移动到下一个节点
                cur = cur.nextMap.get(paths[i]);
            }
        }
        return head;
    }

    public static void printProcess(Node node, int level) {
        //因为0层的head节点中没有保存folder的path内容, 所以不需要输出
        if (level != 0) {
            System.out.println(get4nSpace(level) + node.path);
        }
        for (Node next : node.nextMap.values()) {
            printProcess(next, level + 1);
        }

    }

    public static String get4nSpace(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < n; i++) {
            res.append("    ");
        }
        return res.toString();
    }


    public static void main(String[] args) {
        String[] paths = {"b\\st", "d\\", "a\\d\\e", "a\\b\\c"};
//        print(paths);
        Node head = generateFolderTree(paths);
        System.out.println(head);
    }


    @Test
    public void testSplit() {
        String path = "a\\d\\e";
        String[] strs = path.split("\\\\");
        System.out.println(Arrays.toString(strs));

        String url = "www.baidu.com";
        String[] strs2 = url.split("\\.");
        System.out.println(Arrays.toString(strs2));
    }
}
