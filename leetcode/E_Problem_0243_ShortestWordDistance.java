package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E_Problem_0243_ShortestWordDistance {

    // straightforward solution, keeping comparing distance between index
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int n = wordsDict.length, len = Integer.MAX_VALUE, index1 = -1, index2 = -1;
        for (int i = 0; i < n; i++) {
            if (wordsDict[i].equals(word1)) {
                index1 = i;
            }
            if (wordsDict[i].equals(word2)) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                len = Math.min(len, Math.abs(index1 - index2));
            }

        }
        return len;
    }


    /* Brute Force */
    public int shortestDistanceBruteForce(String[] wordsDict, String word1, String word2) {
        if (word1.equals(word2)) return 0;
        HashMap<String, List<Integer>> map = new HashMap<>();
        int n = wordsDict.length, len = Integer.MAX_VALUE, index1 = -1, index2 = -1;
        for (int i = 0; i < n; i++) {
            if (wordsDict[i].equals(word1)) {
                if (!map.containsKey(word1)) {
                    ArrayList<Integer> l1 = new ArrayList<>();
                    l1.add(i);
                    map.put(word1, l1);
                } else {
                    List<Integer> list = map.get(word1);
                    list.add(i);
                }
            }
            if (wordsDict[i].equals(word2)) {
                if (!map.containsKey(word2)) {
                    ArrayList<Integer> l2 = new ArrayList<>();
                    l2.add(i);
                    map.put(word2, l2);
                } else {
                    List<Integer> list = map.get(word2);
                    list.add(i);
                }
            }
        }
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                len = Math.min(len, Math.abs(list1.get(i) - list2.get(j)));
            }
        }

        return len;
    }


}
