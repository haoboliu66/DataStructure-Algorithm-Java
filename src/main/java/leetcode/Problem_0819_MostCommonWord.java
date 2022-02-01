package src.main.java.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Problem_0819_MostCommonWord {

    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
//        String res = mostCommonWord(paragraph, banned);

       String s = "a, a, a, a, b,b,b,c, c";
        String sss = removePunctuation(s);
        System.out.println(sss);
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || "".equals(paragraph)) return paragraph;
        HashSet<String> set = new HashSet<>();
        for (String str : banned) {
            set.add(str);
        }
        System.out.println(set);
        String res = "";
        int max = 1;
        HashMap<String, Integer> map = new HashMap<>();
        String p = removePunctuation(paragraph);
        System.out.println("p: " + p);
        String[] strs = p.split(" ");
        System.out.println("strs: " + Arrays.toString(strs));

        for (String s : strs) {
            if (!set.contains(s)) {

                if (!map.containsKey(s)) {
                    map.put(s, 1);
                } else {
                    int count = map.get(s) + 1;
                    map.put(s, count);
                    if (count > max) {
                        res = s;
                    }
                }
            }
        }

        return res;
    }

    public static String removePunctuation(String paragraph) {
        char[] str = paragraph.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : str) {
            if (c == ' ' || (c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')) {
                sb.append((c <= 'Z' && c >= 'A') ? ((char)(c + 32)) : c);
            }
        }
        return sb.toString();
    }

}
