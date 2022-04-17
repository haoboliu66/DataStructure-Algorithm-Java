package fundamental.bitwise;

public class SingleNumber {

    /**
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     * 一个数组中, 所有数字出现偶数次, 只有一个数字出现了奇数次, 找到这个数字
     */
    public static int singleNumber(int[] arr) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res ^= arr[i];
        }
        return res;
    }

    /*把一个int二进制最右侧的1提取出来 */
    public static int getRightMostOne(int num) {
        return num & (~num + 1);   // num & (-num)
    }

    /* Q:一个数组中, 两个数字出现了奇数次, 其余数字都出现偶数次, 找到这两个数字 */
    public static int[] getTwo(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // if these two numbers are a, b, then sum =  a ^ b
        int one = getRightMostOne(eor); // 和arr[0]一样的为一种数, 其余的为另一种数
        int oneKind = 0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & one) != 0){
                oneKind ^= arr[i];
            }
        }
        return new int[]{oneKind, eor ^ oneKind};
    }

    /* Q:数出int中二进制1的个数  */
    public static int countOne(int num){
        int count = 0;
        while(num != 0){
            if(getRightMostOne(num) > 0){
                count++;
                num -= getRightMostOne(num);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,3,3,4,5,4,5,6,7};
        int[] two = getTwo(arr);
        System.out.println(two[0] + " and " + two[1]);
//        int a = 131;
//        int count = countOne(a);
//        System.out.println(count);

        int a = 3;
        int b = 5;
        System.out.println(getRightMostOne(a));
        System.out.println(getRightMostOne(b));


    }




}
