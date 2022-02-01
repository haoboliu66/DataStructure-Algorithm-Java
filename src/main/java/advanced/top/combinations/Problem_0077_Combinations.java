package src.main.java.advanced.top.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_0077_Combinations {

    /*
    Given two integers n and k,
    return all possible combinations of k numbers out of 1 ... n.
    You may return the answer in any order.

    Input: n = 4, k = 2
    Output:
    [
      [2,4],
      [3,4],
      [2,3],
      [1,2],
      [1,3],
      [1,4],
    ]
     */

    public static void main(String[] args) {
        List<List<Integer>> res = combine(4, 2);
        System.out.println(res);
    }

    // My Solution
    public static List<List<Integer>>  combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        // size = 2; range = [1...n]
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++){
            arr[i] = i + 1;
        }
        LinkedList<Integer> path = new LinkedList<>();
        process(arr, 0, k, path, ans);
        return ans;
    }

    public static void process(int[] arr, int index, int rest, LinkedList<Integer> path, List<List<Integer>> ans){
        List<Integer> list = new ArrayList<>();
        if(index == arr.length){
            if(rest == 0){
                List<Integer> tmp = new ArrayList<>(path);
                ans.add(tmp);
            }
            return;
        }

        if(rest == 0){
            List<Integer> tmp = new ArrayList<>(path);
            ans.add(tmp);
            return;
        }

        process(arr, index + 1, rest, path, ans);

        path.offerLast(arr[index]);
        process(arr, index + 1, rest - 1, path, ans);
        path.pollLast();

    }

}
