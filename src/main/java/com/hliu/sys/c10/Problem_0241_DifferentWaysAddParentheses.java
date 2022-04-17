package src.main.java.sys.c10;

import java.util.ArrayList;
import java.util.List;

public class Problem_0241_DifferentWaysAddParentheses {

    public static void main(String[] args) {
        String s =
                "10+5";
        System.out.println(diffWaysToCompute(s));

    }

    public static List<Integer> diffWaysToCompute(String s) {
        char[] str = s.toCharArray();
        if (!validate(str)) {
            int cur = 0;
            for (int i = 0; i < str.length; i++) {
                cur = cur * 10 + (str[i] - '0');
            }
            ArrayList<Integer> res = new ArrayList<>();
            res.add(cur);
            return res;
        }
        return process(str, 0, str.length - 1);
    }

    public static boolean validate(char[] str) {
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '+' || str[i] == '-' || str[i] == '*') {
                count++;
            }
        }
        return count != 0;
    }

    public static List<Integer> process(char[] str, int L, int R) {
        if (L == R) {
            List<Integer> list = new ArrayList<>();
            list.add(str[L] - '0');
            return list;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = L + 1; i < R; i += 2) {
            List<Integer> leftPart = process(str, L, i - 1);
            List<Integer> rightPart = process(str, i + 1, R);

            char op = str[i];
            res.addAll(combination(leftPart, rightPart, op));

        }
        return res;
    }

    private static List<Integer> combination(List<Integer> leftPart, List<Integer> rightPart, char op) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < leftPart.size(); i++) {
            for (int j = 0; j < rightPart.size(); j++) {
                if (op == '+') {
                    res.add(leftPart.get(i) + rightPart.get(j));
                } else if (op == '-') {
                    res.add(leftPart.get(i) - rightPart.get(j));
                } else {
                    res.add(leftPart.get(i) * rightPart.get(j));
                }
            }
        }
        return res;
    }

}
