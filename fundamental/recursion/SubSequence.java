package fundamental.recursion;

import java.util.*;

public class SubSequence {

    public static Set<String> getSubSequence(String s) {
        HashSet<String> all = new HashSet<>();
        Set<String> addition;
        char[] str = s.toCharArray();
        all.add("");
        for (int i = 0; i < str.length; i++) {
            addition = new HashSet<>();
            Set<String> copy = new HashSet<>(all);
            for(String o: copy){
                addition.add(o + str[i]);
            }
            all.addAll(addition);
        }
        return all;
    }

    public static List<String> get(String s) {
        List<String> res = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();
        char[] str = s.toCharArray();
        process(str, 0, path, res);
        return res;
    }

    public static void process(char[] str, int index, LinkedList<Character> path, List<String> res) {
        if (index == str.length) {
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            res.add(sb.toString());
            return;
        }
        process(str, index + 1, path, res);

        path.add(str[index]);
        process(str, index + 1, path, res);
        path.pollLast();
    }


    public static void main(String[] args) {
        String a = "12131";
        Set<String> res1 = getSubSequence(a);
        Set<String> res2 = getSubSequencesNoRepeat(a);
        System.out.println(res1.equals(res2));
        System.out.println(res2.size());
//        List<String> list = getSubSequences(a);
//        System.out.println(list);
//        String b = "bbb";
//        Set<String> set = getSubSequencesNoRepeat(b);
//        System.out.println(set);
    }

    public static List<String> getSubSequences(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new LinkedList<>();
        String path = "";
        subSequences(chars, result, 0, path);
        return result;
    }

    public static void subSequences(char[] str, List<String> ans, int index, String path) {
        if (index == str.length) { //已处理到最后一个字符
            ans.add(path); //把结果加入List
            return;
        }
        subSequences(str, ans, index + 1, path); // 不拼接当前index字符, 直接进入index + 1
        subSequences(str, ans, index + 1, path + str[index]); // 拼接当前index字符
    }

    /* get all non-duplicate subsequences
     *  Same process with HashSet
     */
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
}
