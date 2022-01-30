package someOA.guidewire;

import java.util.*;

public class GenerateTime {

    public static void main(String[] args) {
        int a = 1, b = 8, c = 3, d = 2;
        int a1 = 1, b1 = 1, c1 = 1, d1 = 2;
        int a2 = 6, b2 = 2, c2 = 4, d2 = 7;
        System.out.println(solution(a1, b1, c1, d1));
    }

    public static int solution(int a, int b, int c, int d) {
        int[] arr = {a, b, c, d};
        List<int[]> list = new ArrayList<>();
        int count = 0;
        process(arr, 0, list);
        for (int[] time : list) {
            if (isValid(time)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isValid(int[] arr) {
        int hour = arr[0] * 10 + arr[1];
        int minute = arr[2] * 10 + arr[3];

        return hour >= 0 && hour <= 24 && minute >= 0 && minute <= 60;
    }

    public static void process(int[] arr, int index, List<int[]> list) {
        if (index == arr.length) {
            int[] copy = Arrays.copyOfRange(arr, 0, arr.length);
            list.add(copy);
            return;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = index; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
                swap(arr, index, i);
                process(arr, index + 1, list);
                swap(arr, index, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
