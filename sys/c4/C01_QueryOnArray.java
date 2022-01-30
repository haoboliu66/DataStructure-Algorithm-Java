package sys.c4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C01_QueryOnArray {

    public static class QueryBox2 {
        private HashMap<Integer, ArrayList<Integer>> map;

        public QueryBox2(int[] arr) {
            map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<>());
                }
                map.get(arr[i]).add(i);
            }
        }

        public int query(int L, int R, int value) {
            if (!map.containsKey(value)) {
                return 0;
            }
            ArrayList<Integer> indexArr = map.get(value);
            // 查询 < L 的下标有几个
            int a = countLess(indexArr, L);
            // 查询 < R+1 的下标有几个
            int b = countLess(indexArr, R + 1);
            return b - a;
        }

        // 在有序数组arr中，用二分的方法数出<limit的数有几个
        // 也就是用二分法，找到<limit的数中最右的位置
        private int countLess(ArrayList<Integer> arr, int limit) {
            int L = 0;
            int R = arr.size() - 1;
            int mostRight = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (arr.get(mid) < limit) {
                    mostRight = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return mostRight + 1;
        }

    }

    private static class QueryBox0 {
        Map<Integer, List<Integer>> map = new HashMap<>();

        public QueryBox0(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<>());
                }
                map.get(arr[i]).add(i);
            }
        }

        public int query(int L, int R, int val) {
            if (!map.containsKey(val)) {
                return 0;
            }
            List<Integer> indices = map.get(val);
            //   2,3,16,22,33,56
            //   66, 88
            if (L > indices.get(indices.size() - 1)) {
                return 0;
            }
            int index1 = leftMostMore(indices, L);
            int index2 = rightMostLess(indices, R);
//            if (index1 == -1) return 0;

            return index2 - index1 + 1;
        }

        public int rightMostLess(List<Integer> list, int val) {
            int L = 0, R = list.size() - 1, mid;
            int mostRight = -1;
            while (L <= R) {
                mid = L + (R - L) / 2;
                // L..mid..R上
                if (list.get(mid) <= val) {
                    L = mid + 1;
                    mostRight = mid;
                } else {
                    // [mid] > val
                    R = mid - 1;
                }
            }
            //   2,3,17,22,33,56
            //   17          ,33
            return mostRight;
        }

        public int leftMostMore(List<Integer> list, int val) {
            int L = 0, R = list.size() - 1, mid;
            int mostLeft = -1;
            while (L <= R) {
                mid = L + (R - L) / 2;
                // L..mid..R上
                if (list.get(mid) >= val) {
                    R = mid - 1;
                    mostLeft = mid;
                } else {
                    // [mid] > val
                    L = mid + 1;
                }
            }
            //   2,3,17,22,33,56
            //   17          ,33
            return mostLeft;
        }

    }

    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 300;
        int value = 20;
        int testTimes = 1000;
        int queryTimes = 1000;
        System.out.println("test begin");
        label:
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int N = arr.length;
            QueryBox0 box0 = new QueryBox0(arr);
            QueryBox2 box2 = new QueryBox2(arr);
            for (int j = 0; j < queryTimes; j++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                int L = Math.min(a, b);
                int R = Math.max(a, b);
                int v = (int) (Math.random() * value) + 1;
                if (box0.query(L, R, v) != box2.query(L, R, v)) {
                    System.out.println("Oops!");
                    System.out.println(box0.query(L, R, v));
                    System.out.println(box2.query(L, R, v));
                    break label;
                }
            }
        }
        System.out.println("test end");
    }


}
