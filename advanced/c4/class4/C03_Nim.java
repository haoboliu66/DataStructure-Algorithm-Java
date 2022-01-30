package advanced.c4.class4;

public class C03_Nim {

    // arr都是正数

    //当先手面对异或和为0的情况, 他一定赢不了, 因为他不论怎么拿一定会破坏异或和为0的情况,
    //后手操作之后都一定会让他再次面对异或和为0的情况
    public static void printWinner(int[] arr) {
        int xor = 0;
        for (int num : arr) {
            xor ^= num;
        }
        if (xor == 0) {
            System.out.println("后手赢");
        } else {
            System.out.println("先手赢");
        }
    }

}
