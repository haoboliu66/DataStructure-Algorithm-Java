package src.main.java.someOA.codility;

public class FlipCoin {

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 1, 1};
        int[] arr1 = {1, 1, 0, 1, 1};
        System.out.println(solution(arr1));
    }

    public static int solution(int[] arr) {
        int pre = arr[0];
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == pre) {
                count++;
                arr[i] = (arr[i - 1] ^ 1);
            }
        }
        return count;
    }

    public static int process(int[] arr, int i) {
        if (i == arr.length) {
            return 0;
        }
        if (i - 1 >= 0 && i + 1 < arr.length) {
            if ((arr[i] ^ arr[i - 1]) != 0 && (arr[i] ^ arr[i - 1]) != 0) {
                return process(arr, i + 2);
            } else if ((arr[i] ^ arr[i - 1]) != 0) {
//                arr[]
            } else {

            }

        }
        return 1;

    }

}

