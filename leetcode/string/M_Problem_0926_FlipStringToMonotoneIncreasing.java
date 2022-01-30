package leetcode.string;

public class M_Problem_0926_FlipStringToMonotoneIncreasing {

    // My Solution1 : Brute Force
    public static int minFlipsMonoIncrBruteForce(String s) {

        int leftOne = 0, rightZero = 0;
        int min = Integer.MAX_VALUE;
        char[] str = s.toCharArray();

        // All '0' => '1'  vs  All '1' => '0'
        int numOne = 0, numZero = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '1') {
                numOne++;
            } else {
                numZero++;
            }
        }
        min = Math.min(numOne, numZero);
        for (int i = 0; i < str.length; i++) {
            leftOne = rightZero = 0;
            // [i] is considered as at left side
            for (int j = i + 1; j < str.length; j++) {  // number of 1 at right
                rightZero += str[j] == '1' ? 1 : 0;
            }
            for (int k = i; k >= 0; k--) { // number of 0 at left
                leftOne += str[k] == '0' ? 1 : 0;
            }
            min = Math.min(rightZero + leftOne, min);
        }

        return min;
    }

    // My Solution2 : Suffix, Prefix arrays
    public static int minFlipsMonoIncr(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] str = s.toCharArray();
        int n = str.length;
        int numOne = 0, numZero = 0;
        int[] oneAtLeft = new int[n];  // oneAtLeft[i]:    the number of '1' within [0...i]
        int[] zeroAtRight = new int[n]; // zeroAtRight[i]:   the number of '0' within (i..n-1] => zeroAtRight[n-1] is always 0
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '1') {
                numOne++;
            } else {
                numZero++;
            }
        }
        min = Math.min(numOne, numZero);

        for (int i = 0; i < n; i++) {
            oneAtLeft[i] = (i == 0) ? (str[i] == '1' ? 1 : 0) : (oneAtLeft[i - 1] + (str[i] == '1' ? 1 : 0));
        }
        for (int i = n - 2; i >= 0; i--) {
            zeroAtRight[i] = zeroAtRight[i + 1] + (str[i + 1] == '0' ? 1 : 0);
        }

        for (int i = 0; i < n; i++) {
            min = Math.min(min, oneAtLeft[i] + zeroAtRight[i]);
        }

        return min;
    }

    // My Solution3 : Optimal
    public static int minFlipsMonoIncr2(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] str = s.toCharArray();

        int zeroCount = 0, oneCount = 0;

        int min = Integer.MAX_VALUE;
        int zeroPass = 0, onePass = 0;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '0') zeroCount++;
            else oneCount++;
        }
        min = Math.min(zeroCount, oneCount);

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '0') {
                zeroPass++;
            } else {
                onePass++;
            }
            int remainedZeroAtRight = zeroCount - zeroPass;
            min = Math.min(onePass + remainedZeroAtRight, min);
        }

        return min;
    }


    // Solution From Discuss
    /*
    Algorithm:

    1.Skip 0's until we encounter the first 1.
    2.Keep track of number of 1's in onesCount (Prefix).
    3Any 0 that comes after we encounter 1 can be a potential candidate for flip. Keep track of it in flipCount.
    4.If flipCount exceeds oneCount - (Prefix 1's flipped to 0's)
        a. Then we are trying to flip more 0's (suffix) than number of 1's (prefix) we have.
        b. Its better to flip the 1's instead.
     */

    public int minFlipsMonoIncrDiscuss(String s) {
        if(s == null || s.length() <= 0 )  return 0;

        char[] sChars = s.toCharArray();
        int flipCount = 0;
        int onesCount = 0;

        for(int i=0; i<sChars.length; i++){
            if(sChars[i] == '0'){
                if(onesCount == 0) continue;
                else flipCount++;
            }else{
                onesCount++;
            }
            if(flipCount >= onesCount){
                flipCount = onesCount;
            }
        }
        return flipCount;
    }




}
