package sys.c26;

import java.util.*;

public class C03_WordLadder {

     /*
      c3-class5
       */

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        words.add(beginWord);
        Map<String, List<String>> adjMap = getAdjacentMap(words);
        Map<String, Integer> distanceMap = generateDistanceMap(beginWord, adjMap);
        LinkedList<String> path = new LinkedList<>();
        dfs(beginWord, endWord, path, adjMap, distanceMap, ans);
        return ans;
    }

    public static void dfs(String from, String to, LinkedList<String> path, Map<String, List<String>> adjMap, Map<String, Integer> distanceMap, List<List<String>> res) {
        path.addLast(from);
        if (from.equals(to)) {
            res.add(new ArrayList<>(path));
        } else {
            List<String> adjs = adjMap.get(from);
            for (String next : adjs) {
                if (distanceMap.get(next) == distanceMap.get(from) + 1) {
                    dfs(next, to, path, adjMap, distanceMap, res);
                }
            }
        }
        path.pollLast();
    }

    public static Map<String, Integer> generateDistanceMap(String start, Map<String, List<String>> adjMap) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        Map<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 0);
        while (!queue.isEmpty()) {
            String cur = queue.poll();

            List<String> adjs = adjMap.get(cur);

            for (String next : adjs) {
                if (!visited.contains(next)) {
                    distanceMap.put(next, distanceMap.get(cur) + 1);
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        return distanceMap;
    }


    public static Map<String, List<String>> getAdjacentMap(Set<String> words) {
        Map<String, List<String>> adjMap = new HashMap<>();

        for (String s : words) {
            List<String> adjWords = generateNeighbours(s, words);
            adjMap.put(s, adjWords);
        }

        return adjMap;
    }


    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (Objects.equals(beginWord, endWord)) {
            return 0;
        }
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;

        int steps = 1;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();

                List<String> adjs = generateNeighbours(cur, words);  // adjs中的str一定在wordList范围内

                if (adjs.size() == 0) continue;

                for (String s : adjs) {
                    if (s.equals(endWord)) return steps + 1;
                    if (!visited.contains(s)) {
                        visited.add(s);
                        queue.offer(s);
                    }
                }
            }
            steps++;
        }
        return 0;
    }

    public static List<String> generateNeighbours(String word, Set<String> words) {
        List<String> adjs = new ArrayList<>();
        char[] str = word.toCharArray();
        for (int i = 0; i < str.length; i++) {

            for (char s = 'a'; s <= 'z'; s++) {
                char tmp = str[i];
                str[i] = s;
                String swapped = new String(str);
                if (s != tmp && words.contains(swapped)) {
                    adjs.add(swapped);
                }
                str[i] = tmp;
            }
        }
        return adjs;
    }


}
