package src.main.java.advanced.top;

import java.util.*;

public class Problem_0049_GroupAnagrams {

    /*
    Given an array of strings strs, group the anagrams together.
    You can return the answer in any order.

    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
    typically using all the original letters exactly once.
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs.length == 0) {
            return ans;
        }
        Map<String, List<String>> map = new HashMap<>();
        String keyWord;
        for (int i = 0; i < strs.length; i++) {   // O(N*k*logk)
            char[] cur = strs[i].toCharArray();
            Arrays.sort(cur);
            keyWord = new String(cur);
            if (!map.containsKey(keyWord)) {
                map.put(keyWord, new ArrayList<>());
            }
            List<String> list = map.get(keyWord);
            list.add(strs[i]);
        }

        ans.addAll(map.values());
        return ans;
    }

    // wrong solution
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs.length == 0) {
            return ans;
        }
        Map<Integer, List<String>> map = new HashMap<>();
        int key;
        for (int i = 0; i < strs.length; i++) {
            char[] cur = strs[i].toCharArray();
            key = 0;
            for (int j = 0; j < cur.length; j++) {
                key |= (1 << (cur[j] - 'a'));
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }

        ans.addAll(map.values());
        return ans;
    }


}
