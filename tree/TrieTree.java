package tree;


import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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
        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node cur = root;
            char[] str = word.toCharArray();
            int index = 0;
            //遍历word新建Node
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                cur.pass++;
                if (cur.nexts[index] == null) {  //如果没有路
                    cur.nexts[index] = new Node(); // 新建一条路
                }
                //有路了
                cur = cur.nexts[index]; //移动到下一个Node
                cur.pass++; // 每到一个Node pass就++
            }
            cur.end++; //移动到最后一个Node, end++
        }


        public void delete(String word) {


        }


    }


}