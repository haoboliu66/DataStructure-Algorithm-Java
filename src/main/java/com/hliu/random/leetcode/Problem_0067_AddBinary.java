package com.hliu.random.leetcode;

public class Problem_0067_AddBinary {

    public static void main(String[] args) {
        String a = "1010", b = "1011";
        String res = addBinary(a, b);
        System.out.println(res);
    }

    public static String addBinary(String a, String b) {

        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int index1 = str1.length - 1;
        int index2 = str2.length - 1;
        int carry = 0;
        String res = "";
        for (int i = index1, j = index2; index1 >= 0 && index2 >= 0; ) {
            // ^ == 0 ==> 相等 ==> 结果是0
            int digit = ((str1[i] ^ str2[j]) == 0 ? carry : carry == 0 ? 1 : 0);
            res = digit + res;
            carry = (carry != 0 && ((str1[i] - 48) + (str2[j] - 48) == 1)) ||
                    (str1[i] == '1' && str2[j] == '1') ? 1 : 0;
            /*
            res.insert(0, ((str1[i] ^ str2[j]) == 0 ? "0" : "1"));
             */
            index1--;
            index2--;
        }
        if(index1 < index2){
            while (index1 != 0){

                index1--;
            }
        }else{
            while (index2 != 0){

                index2--;
            }
        }


        return res;
    }

}
