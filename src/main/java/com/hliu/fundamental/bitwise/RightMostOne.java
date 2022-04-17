package fundamental.bitwise;


public class RightMostOne {

    public static void main(String[] args) {
        int a = 14;  // 0 0 0 0 1 1 1 0
        // 8 4 1
        System.out.println(a & ((~a) + 1));  // 0 0 0 0 0 0 1 0
    }
}
