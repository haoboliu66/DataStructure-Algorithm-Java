package src.main.java.sys.c17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class C05_DistinctSubsequenceValue {

    // https://leetcode.com/problems/distinct-subsequences-ii/


    // 暴力方法: 生成所有子序列, 用Set存, 最后取size()
    public static Set<String> getSubSequencesNoRepeat(String str) {
        HashSet<String> res = new HashSet<>();
        char[] chars = str.toCharArray();
        String path = "";
        subSequenceNoRepeat(chars, res, 0, path);
        return res;
    }

    public static void subSequenceNoRepeat(char[] str, HashSet<String> ans, int index, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        subSequenceNoRepeat(str, ans, index + 1, path);
        subSequenceNoRepeat(str, ans, index + 1, path + str[index]);
    }

    public static void main(String[] args) {
        String s = "aaaaabababbbabaabab";
        int res1 = getSubSequencesNoRepeat(s).size();
        int res2 = distinctSubseqII(s);
        System.out.println(res1);
        System.out.println(res2);
    }

    public static int distinctSubseqII(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int all = 1;
        for (char c : str) {
            int additionEndWithCur = all;
            all = all + additionEndWithCur - (map.containsKey(c) ? map.get(c) : 0);
            map.put(c, additionEndWithCur);
        }
        return all;
    }

    public static int distinctSubseqIIMod(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int m = 1000000007;
        int all = 1;
        for (char c : str) {
            int additionEndWithCur = all;
//            all = all + additionEndWithCur - (map.containsKey(c) ? map.get(c) : 0);
            all = (all + additionEndWithCur) % m;
            all = (all - (map.containsKey(c) ? map.get(c) : 0) + m) % m;
            map.put(c, additionEndWithCur);
        }
        return all;
    }

}
