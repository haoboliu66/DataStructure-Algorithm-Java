package advanced.top;

import java.util.*;

public class Problem_0127_WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return 1;
    }

    public static List<String> nexts(String s, Set<String> set) {
        List<String> nexts = new ArrayList<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char tmp = str[i];

            for (char j = 'a'; j <= 'z'; j++) {
                if (str[i] != j) {
                    str[i] = j;
                    if (set.contains(String.valueOf(str))) {
                        nexts.add(String.valueOf(str));
                    }
                }
            }
            str[i] = tmp;
        }

        return nexts;
    }

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        String endWord = "cog";
        List<String> nexts = nexts(endWord, new HashSet<>(wordList));
        System.out.println(nexts);

    }

}
