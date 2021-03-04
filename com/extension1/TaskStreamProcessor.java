package com.extension1;

import java.util.Arrays;

public class TaskStreamProcessor {


    public static void main(String[] args) {

        String s = "a\tb\tc";
        String[] str = s.split("\\t");
        System.out.println(str.length);
        System.out.println(Arrays.toString(str));

    }
}
