package src.main.java.sys.c20;

import java.util.LinkedList;

public class C04_CountDifferentPalindromicSubsequences {


    public static void main(String[] args) {
        char c = '中';
        LinkedList<Integer> arr = new LinkedList<>();
        while(c != 0){
            int right = ((c & 1) ^ 1);
            arr.addFirst(right);
            c >>= 1;
        }
        System.out.println(arr);
        // 0 0 1 1 , 0 0 0 1 , 1 1 0 1 , 0 0 1 0
    }

    public int countPalindromicSubsequences(String s) {


        return 0;
    }

}
