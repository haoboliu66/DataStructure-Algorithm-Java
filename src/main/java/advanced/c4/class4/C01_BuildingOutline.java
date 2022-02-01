package src.main.java.advanced.c4.class4;

import java.util.ArrayList;
import java.util.List;

public class C01_BuildingOutline {

    // https://leetcode.com/problems/the-skyline-problem/
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();

        return ans;
    }

    private static class Node {
        int x;
        boolean isAdd;
        int height;

        public Node(int x, boolean isAdd, int height) {
            this.x = x;
            this.isAdd = isAdd;
            this.height = height;
        }
    }
}
