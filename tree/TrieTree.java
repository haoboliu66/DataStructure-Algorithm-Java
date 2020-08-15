package tree;


import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author andy-liu
 * @date 2020/5/17 - 6:15 PM
 */
public class Trie {


    public static class Node {
        int pass;
        int end;
        public Node[] nexts;


        public Node(int pass, int end, Node[] nexts) {
            this.pass = pass;
            this.end = end;
            this.nexts = nexts;
        }
    }


    public static class Trie{
        
    }



}