package com.extension2;

import java.util.*;

public class TaskStreamProcessor {

    public static void main(String[] args) {

//        Desk desk = new Desk();
//        desk.run();

        Set<List<Integer>> set = new HashSet<>();

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(1,2,3);
        List<Integer> list3 = Arrays.asList(6,3);
        List<Integer> list4 = Arrays.asList(6,3);
        set.add(list1);
        set.add(list2);
        set.add(list3);
        set.add(list4);
        System.out.println(set);
        List<List<Integer>> res = new ArrayList<>(set);
        System.out.println(res);
    }
}
