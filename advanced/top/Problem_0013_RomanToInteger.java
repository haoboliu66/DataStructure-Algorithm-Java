package advanced.top;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/8/25 - 10:58 AM
 */
public class Problem_0013_RomanToInteger {

    //  1 <= num <= 3999
    public int romanToInt(String s) {

        int[] num = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {

            switch (s.charAt(i)){

                case 'M':
                    num[i] = 1000;
                    break;
                case 'D':
                    num[i] = 500;
                    break;
                case 'C':
                    num[i] = 100;
                    break;
                case 'L':
                    num[i] = 50;
                    break;
                case 'X':
                    num[i] = 10;
                    break;
                case 'V':
                    num[i] = 5;
                    break;
                case 'I':
                    num[i] = 1;
                    break;
            }
        }


        int res = 0;
        for(int i = 0; i < num.length - 1; i++){
            if(num[i] < num[i + 1]){
                res -= num[i];
            }else{
                res += num[i];
            }
        }
        return res;
    }



}
