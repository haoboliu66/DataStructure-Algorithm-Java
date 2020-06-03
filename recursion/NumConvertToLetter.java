package recursion;

/**
 * @author andy-liu
 * @date 2020/5/24 - 10:51 AM
 */
public class NumConvert {

    public static void convert(String str) {

    }

    //str 0...i-1位置已经处理完, 只看i...往后的位置
    public static int process(char[] str, int i) {
        if (i == str.length) { //base case
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1); // i自己一组, i + 1向下递归
            if (i + 1 < str.length && str[i + 1] >= '0' &&str[i + 1] <= '6') {
                // i 和 i + 1组合,  i + 2去递归
                res += process(str, i + 2);
            }
            return res;
        }
        // str[i] = '3' ~ '9'
        return process(str,i + 1);


    }

}
