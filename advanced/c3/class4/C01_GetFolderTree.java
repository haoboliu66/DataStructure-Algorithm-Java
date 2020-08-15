package advanced.c3.class4;

import java.util.TreeMap;

/**
 * @author andy-liu
 * @date 2020/7/4 - 10:20 AM
 */
public class C01_GetFolderTree {

    static class Node {
        public String path;
        public TreeMap<String, Node> nextMap;

        public Node(String p) {
            path = p;
            nextMap = new TreeMap<>();
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
        Node head = new Node("");
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
        String res = "";
        for (int i = 1; i < n; i++) {
            res += "    ";
        }
        return res;
    }


    public static void main(String[] args) {
        String[] paths = {"b\\st", "d\\", "a\\d\\e", "a\\b\\c"};
        print(paths);

    }


}
