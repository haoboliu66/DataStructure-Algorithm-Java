package src.main.java.advanced.top.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_0039_CombinationSum {

    /*
    Input: candidates = [2,3,6,7], target = 7
    Output: [[2,2,3],[7]]

    Input: candidates = [2,3,5], target = 8
    Output: [[2,2,2,2],[2,3,3],[3,5]]
     */

    public static void main(String[] args) {
        int[] cands = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res = combinationSum(cands, target);
        System.out.println(res);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        process(candidates, 0, target, path, res);
        return res;
    }


    public static void process(int[] cand, int index, int rest, LinkedList<Integer> path, List<List<Integer>> res) {
        if (index == cand.length) {
            if (rest == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        // cand [2,3,6,7]
        // cur = 2
        int cur = cand[index];
        // rest = 7
        // 0*2, 1*2,2*2,3*2
        for (int i = 0; i * cur <= rest; i++) {

            for (int j = 1; j <= i; j++) {
                path.addLast(cur);
            }
            process(cand, index + 1, rest - i * cur, path, res);

            for (int j = 1; j <= i; j++) {
                path.pollLast();
            }
        }


    }

}
