package src.main.java.fundamental.bitwise;

public class SingleNumber {

    /**
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     * 一个数组中, 所有数字出现偶数次, 只有一个数字出现了奇数次, 找到这个数字
     */
    public static int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    /*把一个int二进制最右侧的1提取出来 */
    public static int getRightMostOne(int num) {
        return num & (~num + 1);   // num & (-num)
    }

    /* Q:一个数组中, 两个数字出现了奇数次, 其余数字都出现偶数次, 找到这两个数字 */
    public static int[] getTwo(int[] nums) {
        int eor = 0;
        for (int i = 0; i < nums.length; i++) {
            eor ^= nums[i];
        }
        // if these two numbers are a,b, then sum =  a ^ b
        int one = getRightMostOne(eor); // 和nums[0]一样的为一种数, 其余的为另一种数
        int oneSide = 0;
        for (int i = 0; i < nums.length; i++) {
            if((nums[i] & one) != 0){
                oneSide ^= nums[i];
            }
        }
        return new int[]{oneSide, eor ^ oneSide};
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
