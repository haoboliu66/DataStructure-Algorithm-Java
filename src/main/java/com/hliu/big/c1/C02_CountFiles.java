package com.hliu.big.c1;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class C02_CountFiles {

    static String path = "/Users/liuhaobo/Trimester2/Java-DataStructure-Algorithm/src/src.main.java.com.hliu.advanced/c1/kmp";

    public static void main(String[] args) {
        int count = countFiles(path);
        System.out.println(count);
        for (File file : list) {
            System.out.println(file);
        }
    }

    static List<File> list = new ArrayList<>();

    public static int countFiles(String path) {

        File file = new File(path);
        if (!file.isDirectory()) return 0;
        int count = 0;
        Queue<File> fileQueue = new LinkedList<>();
        fileQueue.offer(file);
        while (!fileQueue.isEmpty()) {
            int size = fileQueue.size();
            for (int i = 0; i < size; i++) {
                File curFile = fileQueue.poll();
                if (curFile.isFile()) {
                    list.add(curFile);
                    count++;
                }
                if (curFile.isDirectory()) {
                    for (File f : curFile.listFiles()) {
                        fileQueue.offer(f);
                    }
                }
            }
        }
        return count;
    }

}
