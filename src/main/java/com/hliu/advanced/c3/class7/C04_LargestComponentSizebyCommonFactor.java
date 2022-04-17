package src.main.java.advanced.c3.class7;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class C04_LargestComponentSizebyCommonFactor {

    // https://leetcode.com/problems/largest-component-size-by-common-factor/

    // TLE
    public static int largestComponentSize0(int[] arr) {
        UnionFind uf = new UnionFind(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (gcd(arr[i], arr[j]) != 1) {
                    uf.union(arr[i], arr[j]);
                }
            }
        }
        return uf.maxSize();
    }

    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }


    public static int largestComponentSize1(int[] arr) {
        Map<Integer, Integer> factorMap = new HashMap<>();
        UnionFind uf = new UnionFind(arr);
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            for (int f = 1; f <= Math.sqrt(x); f++) {
                if (x % f == 0) {

                    int factor2 = x / f;
                    if (f != 1) {
                        if (!factorMap.containsKey(f)) {
                            factorMap.put(f, arr[i]);
                        } else {
                            uf.union(arr[i], factorMap.get(f));
                        }
                    }
                    if (!factorMap.containsKey(factor2)) {
                        factorMap.put(factor2, arr[i]);
                    } else {
                        uf.union(arr[i], factorMap.get(factor2));
                    }
                }
            }
        }
        return uf.maxSize();
    }


    public static class UnionFind {
        Map<Integer, Integer> sizeMap;
        Map<Integer, Integer> parentMap;

        public UnionFind(int[] arr) {
            sizeMap = new HashMap<>();
            parentMap = new HashMap<>();
            for (int n : arr) {
                sizeMap.put(n, 1);
                parentMap.put(n, n);
            }
        }

        public int maxSize() {
            int max = 1;
            for (Map.Entry<Integer, Integer> entry : sizeMap.entrySet()) {
                max = Math.max(max, entry.getValue());
            }
            return max;
        }

        private int findFather(int cur) {
            Stack<Integer> stack = new Stack<>();
            while (cur != parentMap.get(cur)) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(int a, int b) {
            int f1 = findFather(a);
            int f2 = findFather(b);
            return f1 == f2;
        }

        public void union(int a, int b) {
            int father1 = findFather(a);
            int father2 = findFather(b);
            if (father1 == father2) return;

            int size1 = sizeMap.get(father1);
            int size2 = sizeMap.get(father2);
            int smaller = size1 > size2 ? father1 : father2;
            int larger = smaller == father1 ? father2 : father1;

            parentMap.put(smaller, larger);
            sizeMap.remove(smaller);
            sizeMap.put(larger, size1 + size2);
        }

    }

}
