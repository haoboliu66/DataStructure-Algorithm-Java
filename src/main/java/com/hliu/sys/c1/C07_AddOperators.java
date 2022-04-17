package java.sys.c1;

public class C07_AddOperators {

    public static void main(String[] args) {
        int[] arr = {5,3,1,7,2};
        int[] arr2 = {1,1,1,1,1,1};
        int target = 2;
        int res = addOperators(arr2, target);
        System.out.println(res);
    }

    public static int addOperators(int[] arr, int target) {

        return process(arr, 0, target);
    }
    //  10  [5,3,1,7,2]
    //      +5 5

    // arr从i开始, 累计算出rest, 有多少种方法
    public static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            if (rest == 0) {
                return 1;
            }
            return 0;
        }
        // +
        int p1 = process(arr, i + 1, rest - arr[i]);

        // -
        int p2 = process(arr, i + 1, rest + arr[i]);

        return p1 + p2;
    }

}

