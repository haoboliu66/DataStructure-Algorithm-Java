package src.main.java.advanced.top;

public class Problem_0204_CountPrimes {

    /*
    Count the number of prime numbers less than a non-negative number, n.

    Input: n = 10
    Output: 4
    Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

     */

    public static int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        // n > 2
        int count = 1;
        for (int i = 3; i <= n; i++) {  // i [3,4,5...n]
            if (isPrime(i)) count++;
        }
        return count;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
    }
}
