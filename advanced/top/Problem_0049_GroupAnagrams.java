package advanced.top;

import java.util.*;

public class Problem_0049_GroupAnagrams {

    /*
    Given an array of strings strs, group the anagrams together.
    You can return the answer in any order.

    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
    typically using all the original letters exactly once.
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();

        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] str = s.toCharArray();
            Arrays.sort(str);
            String key = String.valueOf(str);
            if (!map.containsKey(key)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
            } else {
//                List<String> list = map.get(key);
//                list.add(s);
//                map.put(key, list);
                map.get(key).add(s); // 更简洁的写法
            }
        }

        res.addAll(map.values());

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Math.round(100.7));
    }

}
