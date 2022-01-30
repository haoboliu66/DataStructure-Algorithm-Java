package sys.c3;


import java.util.*;

/*
    只由小写字母（a~z）组成的一批字符串
    都放在字符类型的数组String[] arr中
    如果其中某两个字符串所含有的字符种类完全一样
    就将两个字符串算作一类
    比如：baacbba和bac就算作一类
    返回arr中有多少类？
 */
public class C02_CategoryCount {

    public static int getWordCategoryCount0(String[] arr) {
        Set<Set<Character>> check = new HashSet<>();
        for (String s : arr) {
            char[] str = s.toCharArray();
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < str.length; i++) {
                set.add(str[i]);
            }
            check.add(set);
        }
        return check.size();
    }

    public static int getWordCategoryCount(String[] arr) {
        Set<Integer> check = new HashSet<>();
        for (String s : arr) {
            char[] str = s.toCharArray();
            int cur = 0;
            for (int i = 0; i < str.length; i++) {
                cur |= 1 << (str[i] - 'a');
            }
            check.add(cur);
        }
        return check.size();
    }


    public static void main(String[] args) {
        String[] arr = {"zbaacbbaef", "backef", "ccwaefb", "effmabcf", "faabbcccce"};
        System.out.println(getWordCategoryCount(arr));
        System.out.println(getWordCategoryCount0(arr));
//        Set<Character> set1 = new HashSet<>();
//        Set<Character> set2 = new HashSet<>();
//        set1.add('a');
//        set1.add('b');
//        set1.add('c');
//        set2.add('a');
//        set2.add('b');
//        set2.add('c');
//        Map<Set<Character>, Integer> map = new HashMap<>();
//        map.put(set1, 5);
//        System.out.println(map.containsKey(set2));
//        System.out.println(map);

    }


}
