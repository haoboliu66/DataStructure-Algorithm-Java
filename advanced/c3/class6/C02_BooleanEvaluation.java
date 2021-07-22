package advanced.c3.class6;

public class C02_BooleanExpression {

    public static int getNumbers1(String s, boolean desired) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        if (!isValid(str)) {
            return 0;
        }

        return process(str, 0, str.length - 1, desired);
    }


    public static boolean isValid(char[] exp) {
        if ((exp.length & 1) == 0) { //如果长度不是奇数, 说明表达式不满足条件
            return false;
        }
        for (int i = 0; i < exp.length; i += 2) {
            if (exp[i] != '1' && exp[i] != '0') {
                return false;
            }
        }
        for (int i = 1; i < exp.length; i += 2) {
            if (exp[i] != '&' && exp[i] != '|' && exp[i] != '^') {
                return false;
            }
        }
        return true;
    }

    public static int process(char[] str, int L, int R, boolean desired) {
        if (L == R) {
            if (str[L] == '1') {  //单独剩一个1, 只能表示true
                return desired ? 1 : 0;
            } else { // str[L] == '0'   //单独剩一个0, 只能表示false
                return desired ? 0 : 1;
            }
        }
        int res = 0;
        if (desired) { // 如果最终要的结果是true
            // 假设i位置是最后一个要运算的逻辑运算符, 枚举两侧的可能性
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        // 左true, 右true
                        res += process(str, L, i - 1, true) * process(str, i + 1, R, true);
                        break;
                    case '|':
                        //左true, 右false
                        res += process(str, L, i - 1, true) * process(str, i + 1, R, false);
                        //左true, 右true
                        res += process(str, L, i - 1, true) * process(str, i + 1, R, true);
                        //左false, 右true
                        res += process(str, L, i - 1, false) * process(str, i + 1, R, true);
                        break;
                    case '^':
                        //左右不相同即可
                        res += process(str, L, i - 1, true) * process(str, i + 1, R, false);
                        res += process(str, L, i - 1, false) * process(str, i + 1, R, true);
                        break;
                }
            }
        } else { //期待最终结果为false
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        // 左false, 右true
                        // 左true, 右false
                        // 左false, 右false
                        res += process(str, L, i - 1, false) * process(str, i + 1, R, true);
                        res += process(str, L, i - 1, true) * process(str, i + 1, R, false);
                        res += process(str, L, i - 1, false) * process(str, i + 1, R, false);
                        break;
                    case '|':
                        //false, 右false
                        res += process(str, L, i - 1, false) * process(str, i + 1, R, false);
                        break;
                    case '^':
                        //左右相同
                        res += process(str, L, i - 1, true) * process(str, i + 1, R, true);
                        res += process(str, L, i - 1, false) * process(str, i + 1, R, false);
                        break;
                }
            }
        }
        return res;
    }


    public static int getNumbers2(String s, boolean desired) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] trueMap = new int[N][N];
        int[][] falseMap = new int[N][N];
        //两张表左下半区(表示L>R)不用填, 而且奇数位不用管, 因为奇数位(1..3..5...7)是运算符
        for (int i = 0; i < N; i += 2) {
            trueMap[i][i] = str[i] == 1 ? 1 : 0;
            falseMap[i][i] = str[i] == 0 ? 1 : 0;
        }

        //依赖是 i-1, i+1,所以是左边和下边的格子
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {

                //枚举L...R之间的运算符位置
                for (int i = L + 1; i < R; i += 2) {

                    switch (str[i]) {  //填trueMap
                        case '&':
                            trueMap[L][R] += trueMap[L][i - 1] * trueMap[i + 1][R];
                            break;
                        case '|':
                            trueMap[L][R] += trueMap[L][i - 1] * trueMap[i + 1][R];
                            trueMap[L][R] += trueMap[L][i - 1] * falseMap[i + 1][R];
                            trueMap[L][R] += falseMap[L][i - 1] * trueMap[i + 1][R];
//                            res += process(str, L, i - 1, true) * process(str, i + 1, R, false);
//                            //左true, 右true
//                            res += process(str, L, i - 1, true) * process(str, i + 1, R, true);
//                            //左false, 右true
//                            res += process(str, L, i - 1, false) * process(str, i + 1, R, true);
                            break;
                        case '^': // //左右不相同即可
                            trueMap[L][R] += trueMap[L][i - 1] * falseMap[i + 1][R];
                            trueMap[L][R] += falseMap[L][i - 1] * trueMap[i + 1][R];
                            break;
                    }

                    switch (str[i]) {   //填falseMap
                        case '&':
                            falseMap[L][R] += falseMap[L][i - 1] * trueMap[i + 1][R];
                            falseMap[L][R] += trueMap[L][i - 1] * falseMap[i + 1][R];
                            falseMap[L][R] += falseMap[L][i - 1] * falseMap[i + 1][R];
                            break;
                        case '|':
                            falseMap[L][R] += falseMap[L][i - 1] * falseMap[i + 1][R];
                            break;
                        case '^':
                            falseMap[L][R] = trueMap[L][i - 1] * trueMap[i + 1][R];
                            falseMap[L][R] = trueMap[L][i - 1] * trueMap[i + 1][R];
                            break;
                    }
                }
            }
        }

        return desired ? trueMap[0][N - 1] : falseMap[0][N - 1];
    }


}
