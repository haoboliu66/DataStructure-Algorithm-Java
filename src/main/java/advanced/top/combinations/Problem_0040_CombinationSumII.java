package src.main.java.advanced.top.combinations;

import java.util.*;

public class Problem_0040_CombinationSumII {

    /*
    Each number in candidates may only be used once in the combination.
    Note: The solution set must not contain duplicate combinations.
    Input: candidates = [10,1,2,7,6,1,5], target = 8
    Output:
    [
    [1,1,6],
    [1,2,5],
    [1,7],
    [2,6]
    ]

    Input: candidates = [2,5,2,1,2], target = 5
    Output:
    [
    [1,2,2],
    [5]
    ]
     */

    public static void main(String[] args) {
        int[] cands = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> res = combinationSum2v2(cands, target);
        System.out.println(res);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        Set<List<Integer>> ans = new HashSet<>();
        process(candidates, 0, target, path, ans);
        List<List<Integer>> res = new ArrayList<>(ans);
        return res;
    }

    public static void process(int[] cand, int index, int rest, LinkedList<Integer> path, Set<List<Integer>> ans) {
        if (index == cand.length) {
            if (rest == 0) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        // cands {10, 1, 2, 7, 6, 1, 5}
        // cur = 1
        int cur = cand[index];
        if (cur > rest) {
            process(cand, index + 1, rest, path, ans);
            return;
        }
        path.addLast(cur);

        process(cand, index + 1, rest - cur, path, ans);
        path.pollLast();
        process(cand, index + 1, rest, path, ans);
    }

    /*-----------------------------------------------------------------------------------*/

    public static List<List<Integer>> combinationSum2v2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        process2(candidates, 0, target, path, res);
        return res;
    }

    public static void process2(int[] cand, int index, int rest, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (index == cand.length) {
            if (rest == 0) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        // cands {10, 1, 2, 7, 6, 1, 5}
        // cur = 1
        for (int i = index; i < cand.length; i++) {
            if (i > index && cand[i] == cand[i - 1]) continue;

            int cur = cand[i];
            if (cur > rest) {
                process2(cand, i + 1, rest, path, ans);
                return;
            }
            path.addLast(cur);
            System.out.println("path  == " + path);
            process2(cand, i + 1, rest - cur, path, ans);
            path.pollLast();
//            process2(cand, i + 1, rest, path, ans);
        }

    }


}
