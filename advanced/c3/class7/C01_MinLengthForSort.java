package advanced.c3.class7;

/**
 * @author andy-liu
 * @date 2020/7/11 - 9:58 PM
 */
public class C01_MinLengthForSort {

    public static int getMinLength(int[] arr) {

        int leftMax = arr[0];
        int rightMin = arr[arr.length - 1];
        int leftIndex = arr.length - 1;
        int rightIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < leftMax) {
                leftIndex = i;
            }
            leftMax = Math.max(arr[i], leftMax);
        }

        for (int j = arr.length - 1; j >= 0; j--) {
            if (arr[j] > rightMin) {
                rightIndex = j;
            }
            rightMin = Math.min(arr[j], rightMin);
        }
        return leftIndex - rightIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 4, 2, 6, 7, 8};
        int[] arr1 = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        int[] arr2 = {8,7,6,5,4,3,2,1};
        System.out.println(getMinLength(arr2));
    }

}
