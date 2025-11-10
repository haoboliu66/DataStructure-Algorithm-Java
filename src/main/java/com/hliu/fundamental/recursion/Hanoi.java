package com.hliu.fundamental.recursion;

import java.util.*;

public class Hanoi {

    public static class HanoiTower{

        public static void hanoi(int n, String from, String to, String other){
            if(n > 0){
                fun(n,from,to,other);
            }
        }

        public static void fun(int n, String from, String to, String other){
            if(n == 1){
                System.out.println("Move " + n + " From " + from + " To " + to);
                return;
            }
            fun(n - 1, from, other, to);
            System.out.println("Move " + n + " From " + from + " To " + to);
            fun(n - 1, other, to, from);
        }

        public static void main(String[] args) {
            hanoi(5,"left","right","mid");
        }


    }

    public static void main(String[] args) {
//        Map<Integer,Integer> map = new HashMap<>();
//        map.put(1,1);
//        map.put(2,2);
//        map.put(3,3);
//        map.put(4,4);
//        System.out.println(map.putIfAbsent(5, 100));
//        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
//        System.out.println((int)'l');
        Set<Character> set = new LinkedHashSet<>();
        set.add('l');
        set.add('e');
        set.add('e');
        set.add('t');
        set.add('c');
        System.out.println(set.iterator().next());
    }
}
