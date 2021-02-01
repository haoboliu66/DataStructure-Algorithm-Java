package advanced.leetcode;

public class Problem_0043_MultiplyStrings {


    public static String multiply(String num1, String num2) {

        return "";
    }


    public static int multiply(int a, int b) {
        int sum = 0;
        int tmp = 0;
        int index = 0;
        while (b != 0) {
            tmp = a * (b & 1);
            tmp <<= index;
            sum += tmp;
            b >>>= 1;
            index++;
        }
        return sum;
    }

    public static void main(String[] args) {
        int times = 1000000;
        double rand1 = Math.random();
        double rand2 = Math.random();
        System.out.println("Started");
        for(int i = 0; i < times; i++){
            int a = (int) (Math.random() * 100 * (rand1 > 0.5? 1: -1));
            int b = (int) (Math.random() * 100 * (rand2 > 0.5? 1: -1));
            int right = a * b;
            int sum = multiply(a, b);
            if(sum != right){
                System.out.println("Oops");
                System.out.println("sum: " + sum);
                System.out.println("right: " + right);
            }
        }
        System.out.println("Done");


    }
}
