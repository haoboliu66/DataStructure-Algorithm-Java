package src.main.java.sys.c10;

public class C05_BooleanEvaluation {

    public static void main(String[] args) {
        String s = "0&0&0&1^1|0";
        int res = 1;
        System.out.println(countEval(s, res));
    }

    public static int countEval(String s, int res) {

        char[] str = s.toCharArray();
        Info info = process(str, 0, str.length - 1);
        return res == 1 ? info.trueWays : info.falseWays;
    }

    public static Info process(char[] str, int left, int right) {
        int trueWays = 0, falseWays = 0;
        if (left == right) {
            trueWays = str[left] == '1' ? 1 : 0;
            falseWays = str[left] == '0' ? 1 : 0;
            return new Info(trueWays, falseWays);
        }
        // [i] => operator    [left...i-1], [i], [i+1...right]
        for (int i = left + 1; i < right; i += 2) {
            Info leftInfo = process(str, left, i - 1);
            Info rightInfo = process(str, i + 1, right);
            int lt = leftInfo.trueWays, lf = leftInfo.falseWays;
            int rt = rightInfo.trueWays, rf = rightInfo.falseWays;
            char operator = str[i];
            switch (operator) {
                case '&':
                    trueWays += lt * rt;
                    falseWays += lt * rf + lf * rt + lf * rf;
                    break;
                case '|':
                    trueWays += lt * rf + lf * rt + lt * rt;
                    falseWays += lf * rf;
                    break;
                case '^':
                    trueWays += lt * rf + lf * rt;
                    falseWays += lt * rt + lf * rf;
                    break;
            }
        }
        return new Info(trueWays, falseWays);
    }

    private static class Info {
        int trueWays;
        int falseWays;

        public Info(int t, int f) {
            this.trueWays = t;
            this.falseWays = f;
        }
    }


}
