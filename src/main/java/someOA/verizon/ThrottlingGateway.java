package src.main.java.someOA.verizon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThrottlingGateway {

    // 3,4, 1, 6, 2, 1, 0, 5, 9
    // https://leetcode.com/problems/partition-equal-subset-sum/
    public static List<Integer> subSetEqual(int[] arr) {


        return null;
    }


    public boolean canPartitionKSubsets(int[] arr, int k) {
        if (k > arr.length) return false;
        int sum = 0;
        for (int num : arr) sum += num;
        if (sum % k != 0) return false;

        boolean[] visited = new boolean[arr.length];

//        Arrays.sort(arr);

        return dfs(arr, 0, 0, visited, sum / k, k);
    }

    public boolean dfs(int[] arr, int sum, int st, boolean[] visited, int target, int round) {
        if (round == 0) return true;

        if (sum == target && dfs(arr, 0, 0, visited, target, round - 1)) return true;

        for (int i = st; i < arr.length; i++) {
            if (!visited[i] && sum + arr[i] <= target) {
                visited[i] = true;

                if (dfs(arr, sum + arr[i], i + 1, visited, target, round)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11);
//        System.out.println(arr.size());
//        System.out.println(droppedRequests0(arr));
        System.out.println("Go");
        for (int i = 0; i < 100000; i++) {
            List<Integer> arr1 = generateRandomList(150, 200);
            int res1 = droppedRequests0(arr1);
            int res2 = droppedRequests1(arr1);
            if (res1 != res2) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");

    }

    public static int droppedRequests0(List<Integer> requestTime) {
        int[] requestTimeArr = requestTime.stream().mapToInt(i -> i).toArray();
        int dropped = 0;
        for (int i = 0; i < requestTimeArr.length; i++) {
            if (i > 2 && requestTimeArr[i] == requestTimeArr[i - 3]) {
                ++dropped;
            } else if (i > 19 && (requestTimeArr[i] - requestTimeArr[i - 20]) < 10) {
                ++dropped;
            } else if (i > 59 && (requestTimeArr[i] - requestTimeArr[i - 60]) < 60) {
                ++dropped;
            }
        }

        return dropped;
    }


    public static int droppedRequests1(List<Integer> requestTime) {
        int dropped = 0;
        for (int i = 0; i < requestTime.size(); i++) {
            if (i > 2 && requestTime.get(i).equals(requestTime.get(i - 3))) {
                ++dropped;
            } else if (i > 19 && (requestTime.get(i) - requestTime.get(i - 20)) < 10) {
                ++dropped;
            } else if (i > 59 && (requestTime.get(i) - requestTime.get(i - 60)) < 60) {
                ++dropped;
            }
        }
        return dropped;
    }


    public static int[] generateRandomArray(int size, int value) {
        int[] ans = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static List<Integer> generateRandomList(int size, int value) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ans.add((int) (Math.random() * value));
        }
        Collections.sort(ans);
        return ans;
    }


}
