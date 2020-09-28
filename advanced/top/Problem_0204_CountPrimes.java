package advanced.top;

public class Problem_0204_CountPrimes {

    /*
    Count the number of prime numbers less than a non-negative number, n.

    Input: n = 10
    Output: 4
    Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

     */

    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        // n >= 2
        int count = 0;
        for (int i = 2; i <= n; i++) {

            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) count += 2;
            }

        }

        return count;
    }
}
