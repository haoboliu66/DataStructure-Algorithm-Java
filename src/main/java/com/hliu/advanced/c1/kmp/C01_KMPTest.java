package com.hliu.advanced.c1.kmp;

public class KMPTest {

    public static int indexOf(String s1, String s2){
        char[] str = s1.toCharArray();
        char[] match = s2.toCharArray();
        int x = 0, y = 0;
        int[] next = nextArray(match);
        while(x < str.length && y < match.length){
            if(str[x] == match[y]){
                x++;
                y++;
            }else if(next[y] != -1){
                y = next[y];
            }else{
                x++;
            }
        }

        return y == match.length? x - y: -1;
    }


    public static int[] nextArray(char[] str){
        if(str.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0, i = 2;
        while (i < str.length){
            if(str[cn] == str[i-1]){
                next[i++] = ++cn;
            }else if(next[cn] != -1){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }
        return next;
    }

}
