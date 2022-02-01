package src.main.java.someOA.codility;

import java.util.Arrays;
import java.util.HashSet;

public class CutOneTree {

    public static int solution1(int[] A) {
        int result = 0;

        HashSet<Integer> removedTree = new HashSet<>();


        for (int i = 0; i < A.length - 2; i++) {
            if (!check(A, i, i + 1, i + 2)) { //发现不满足
                removedTree.add(i);
                removedTree.add(i + 1);
                removedTree.add(i + 2);
            }

            if (removedTree.size() > 3)
                return -1;
        }
        if (removedTree.size() == 0) return 0;

        for (int index : removedTree) {
            boolean flag = true;

            if (index >= 3) {
                flag = flag && check(A, index - 3, index - 2, index - 1);
            }
            if (index >= 2 && index <= A.length - 2) {
                flag = flag && check(A, index - 2, index - 1, index + 1);
            }
            if (index >= 1 && index <= A.length - 3) {
                flag = flag && check(A, index - 1, index + 1, index + 2);
            }
            if (index <= A.length - 4) {
                flag = flag && check(A, index + 1, index + 2, index + 3);
            }
            if (flag) result++;

        }
        return result > 0 ? result : -1;
    }


    public static void main(String[] args) {
        int times = 10000;
        int[] arr;
        System.out.println("Go");
        for (int i = 0; i < times; i++) {
            arr = generateRandomArray(100, 100);
            int ans1 = solution1(arr);
            int ans2 = right(arr);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr));
                System.out.println("Oops");
                System.out.println("my: " + ans1);
                System.out.println("right: " + ans2);
                break;
            }
        }
        System.out.println("Done");
    }

    public static int right(int[] arr) {
        if (!validateArray(arr)) {
            return -1;
        }
        if (validateArray(arr, 0, arr.length - 1)) {
            return 0;
        }
        int ways = 0;
        for (int i = 0; i < arr.length; i++) {
            int[] cutArray = removeIndex(arr, i);
            if (validateArray(cutArray, 0, cutArray.length - 1)) {
                ways++;
            }
        }
        return ways;
    }

    public static boolean validateArray(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R - 1; i++) {
            if ((arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) || (arr[i] < arr[i - 1] && arr[i] < arr[i + 1])) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static int[] removeIndex(int[] arr, int remove) {
        int[] res = new int[arr.length - 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == remove) continue;
            res[index++] = arr[i];
        }
        return res;
    }


    public static int solution(int[] arr) {
        // write your code in Java SE 8
        if (arr == null || arr.length < 3) return -1;
        if (arr.length == 3) {
            if ((arr[1] > arr[0] && arr[1] > arr[2]) || (arr[1] < arr[0] && arr[1] < arr[2])) {
                return 0;
            } else {
                return 3;
            }
        }

        if (!validateArray(arr)) {
            return -1;
        }
        int ways = 0;
        for (int i = 1; i < arr.length; i++) {
            // 3 4 5 upHill
            if (arr[i] > arr[i - 1] && i + 1 < arr.length && arr[i] < arr[i + 1]) {
                ways++;
                if (i + 2 >= arr.length || arr[i + 2] < arr[i]) {
                    ways++;
                }
                if (i - 2 < 0 || arr[i - 2] > arr[i]) {
                    ways++;
                }
                continue;
            }
            // 5 4 3 downHill
            if (arr[i] < arr[i - 1] && i + 1 < arr.length && arr[i] > arr[i + 1]) {
                ways++;
                if (i + 2 >= arr.length || arr[i + 2] > arr[i]) {
                    ways++;
                }
                if (i - 2 < 0 || arr[i - 2] < arr[i]) {
                    ways++;
                }
            }
        }
        return ways;
    }

    public static boolean check(int[] arr, int pre, int cur, int next) {
        if ((arr[pre] > arr[cur] && arr[cur] < arr[next]) || (arr[pre] < arr[cur] && arr[cur] > arr[next])) {
            return true;
        }
        return false;
    }

    public static boolean validateArray(int[] arr) {
        if (arr.length < 3) return false;
        int count = 0;
        int set = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                count++;
            } else {
                count--;
            }
            if (count >= 3 || count <= -3) {
                return false;
            }
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (check(arr, i - 1, i, i + 1)) {
                set++;
            }
            if (set > 1) return false;
        }
        return true;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            int cur = (int) (Math.random() * (maxValue + 1));
            if (cur == 0) cur++;
            arr[i] = cur;
        }
        return arr;
    }

}
