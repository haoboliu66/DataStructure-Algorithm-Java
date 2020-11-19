package advanced.c3.class2;

import java.util.Arrays;
import java.util.Comparator;

public class C07_KthMinPair {

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair p1, Pair p2) {
            return p1.x != p2.x ? p1.x - p2.x : p1.y - p2.y;
        }
    }

    // Brute force O(N^2 * log(N^2) )
    public static int[] kthMinPair1(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        Pair[] pairs = new Pair[N * N];
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Pair p = new Pair(arr[i], arr[j]);
                pairs[index++] = p;
            }
        }
        Arrays.sort(pairs, new PairComparator());
        return new int[]{pairs[k - 1].x, pairs[k - 1].y};
    }

    // O(N*logN)
    public static int[] kthMinPair2(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        Arrays.sort(arr);  // O(N*logN)
        int firstNum = arr[(k - 1) / N]; //取到第一位
        int lessNumSize = 0; // 统计比第一位小的数字
        int equalNumSize = 0; // 统计等于第一位的数字
        for (int i = 0; i < N && arr[i] <= firstNum; i++) {
            lessNumSize += arr[i] < firstNum ? 1 : 0;
            equalNumSize += arr[i] == firstNum ? 1 : 0;
        }
        int rest = k - lessNumSize * N; //余下的排名

        return new int[]{firstNum, arr[(rest - 1) / equalNumSize]};
    }

    // O(N)
    public static int[] kthMinPair3(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        //不需要真正的排序, 找到第K小的数 (bfprt, partition)
        int firstNum = getMinKth(arr, (k - 1) / N); //找到第 (k-1)/N 小的数
        int lessNumSize = 0;
        int equalNumSize = 0;
        for (int i = 0; i < N && arr[i] <= firstNum; i++) {
            lessNumSize += arr[i] < firstNum ? 1 : 0;
            equalNumSize += arr[i] == firstNum ? 1 : 0;
        }
        int rest = k - lessNumSize * N; //余下的排名
        return new int[]{firstNum, getMinKth(arr, (rest - 1) / equalNumSize)};
    }

    //如果排序的的话, 找到arr中排在index的数字, partition改迭代
    public static int getMinKth(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (index >= range[0] && index <= range[1]) {
                return arr[index];
            } else if (index < range[0]) {
                R = range[0] - 1;
            } else {
                L = range[0] + 1;
            }
        }
        return arr[L];
    }


    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int index = L;
        while (index < more) {
            if (arr[index] < pivot) {
                swap(arr, index++, ++less);
            } else if (arr[index] > pivot) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // Test
    public static int[] getRandomArray(int max, int len) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // For Test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 随机测试了百万组，保证三种方法都是对的
    public static void main(String[] args) {
        int max = 100;
        int len = 30;
        int testTimes = 100000;
        System.out.println("test bagin, test times : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(max, len);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int N = arr.length * arr.length;
            int k = (int) (Math.random() * N) + 1;
            int[] ans1 = kthMinPair1(arr1, k);
            int[] ans2 = kthMinPair2(arr2, k);
            int[] ans3 = kthMinPair3(arr3, k);
            if (ans1[0] != ans2[0] || ans2[0] != ans3[0] || ans1[1] != ans2[1] || ans2[1] != ans3[1]) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


}
