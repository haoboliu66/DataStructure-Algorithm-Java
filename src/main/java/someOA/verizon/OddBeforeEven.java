package src.main.java.someOA.verizon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OddBeforeEven {

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 500;
        int maxValue = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] right = moveRight(arr);
            move(arr);
            if (!validate(right, arr)) {
                System.out.println("Oops!");
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(right));
                break;
            }
        }
        System.out.println("test finish");
    }

    private static boolean validate(int[] arr1, int[] arr2) {
        int i = 0;
        for (; i < arr1.length; i++) {
            if (arr1[i] % 2 == 0) {
                break;
            }
        }
        for (; i < arr1.length; i++) {
            if (arr1[i] % 2 != 0) {
                return false;
            }
        }
        i = 0;
        for (; i < arr2.length; i++) {
            if (arr2[i] % 2 == 0) {
                break;
            }
        }
        for (; i < arr2.length; i++) {
            if (arr2[i] % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    public static int[] moveRight(int[] arr) {
        int[] res = new int[arr.length];
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for (int n : arr) {
            if (n % 2 == 0) {
                even.add(n);
            } else {
                odd.add(n);
            }
        }
        int index = 0;
        for (int n : odd) {
            res[index++] = n;
        }
        for (int n : even) {
            res[index++] = n;
        }
        return res;
    }

    // put odd before even
    public static void move(int[] arr) {
        int n = arr.length;
        int oddPointer = -1, evenPointer = n;
        for (int i = 0; i < evenPointer; ) {
            if (arr[i] % 2 == 0) { //even
                swap(arr, i, --evenPointer);
            } else {
                swap(arr, i++, ++oddPointer);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
