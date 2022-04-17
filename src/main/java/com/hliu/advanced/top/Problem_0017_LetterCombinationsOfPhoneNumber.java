package src.main.java.advanced.top;

import java.util.ArrayList;
import java.util.List;

public class Problem_0017_LetterCombinationsOfPhoneNumber {

    static char[][] map = {
            {'a', 'b', 'c'},  // 2
            {'d', 'e', 'f'},  // 3
            {'g', 'h', 'i'},  // 4
            {'j', 'k', 'l'},  // 5
            {'m', 'n', 'o'},  // 6
            {'p', 'q', 'r', 's'}, // 7
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if ("".equals(digits)) return res;
        char[] str = digits.toCharArray();
        char[] path = new char[str.length];

        process(str, 0, path, res);
        return res;
    }

    public static void process(char[] str, int index, char[] path, List<String> res) {
        if (index == str.length) {
            res.add(String.valueOf(path));
            return;
        }
        char cur = str[index];
        char[] candidates = map[cur - '2'];
        for (char c : candidates) {
            path[index] = c;
            process(str, index + 1, path, res);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }

}
