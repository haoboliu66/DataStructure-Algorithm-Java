package advanced.c3.class2;

public class C04_TrappingRainWater {

    /*
    https://leetcode.com/problems/trapping-rain-water/
     */

    public static int trap(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int left = 1;
        int right = arr.length - 2;
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        int water = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                water += Math.max(leftMax - arr[left], 0);
                leftMax = Math.max(arr[left++], leftMax);
            } else {
                water += Math.max(rightMax - arr[right], 0);
                rightMax = Math.max(rightMax, arr[right--]);
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int x;
        System.out.println("go");
        for (int i = 0; i < 10000; i++) {
            x = (int) (Math.random() * 10);
            if (!Integer.toBinaryString(x).equals(getBinary(x))) {
                System.out.println("x == " + x);
                System.out.println(Integer.toBinaryString(x));
                System.out.println(getBinary(x));
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("done");

    }

    public static String getBinary(int a) {
        int[] bits = new int[32];
        int index = bits.length - 1;
        int i = 0;
        while (i < 32) {
            if ((a & 1) == 0) {
                bits[index--] = 0;
            } else {
                bits[index--] = 1;
            }
            a = a >> 1;
            i++;
        }
        StringBuilder res = new StringBuilder();
        for (int b : bits) {
            res.append(b);
        }
        return res.toString();
    }


}
