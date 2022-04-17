package fundamental.bitwise;


public class Swap {

    public static int numberOfTrailingZeros(int i) {
        int z = i & ((~i) + 1);

        int count = 0;
        while (z != 0) {
            z = z >> 1;
            count++;
        }
        return count - 1;
    }


    public static int isLessOrEqual(int x, int y) {
        int mask = 0x80000000;
        // x <= y return 1, otherwise return 0
        // x + (-y) <= 0
//        (x + (~y + 1)) & mask
        return 1;
    }


    public static void main(String[] args) {
        int mask = 0x80000000;
        int max = 0x7f_ff_ff_ff;
        // 0111 1111
        System.out.println(mask);
        System.out.println(max);

        System.out.println((0 << 31) - 1); // 1 00000000000
    }

}
