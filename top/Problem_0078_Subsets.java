package top;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_0078_Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        process(nums, 0, path, res);
        return res;
    }

    public static void process(int[] arr, int index, LinkedList<Integer> path, List<List<Integer>> res) {
        if (index == arr.length) {
            res.add(new ArrayList<>(path));
//            res.add((List<Integer>) path.clone());
        } else {
            process(arr, index + 1, path, res); // 当前位置不统计进结果
            path.addLast(arr[index]);
            process(arr, index + 1, path, res); // 当前位置统计进结果
            path.pollLast();
        }
    }


    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> cur : ans) {
                newSubsets.add(new ArrayList<Integer>(cur) {{
                    add(num);
                }});
            }
            for (List<Integer> curr : newSubsets) {
                ans.add(curr);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
        System.out.println(subsets2(nums));

    }
}
