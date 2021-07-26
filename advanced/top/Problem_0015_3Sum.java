package advanced.top;

import java.util.*;

public class Problem_0015_3Sum {

    // Optimal
    public List<List<Integer>> threeSum(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 3) return ans;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int rest = target - arr[i];
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            int left = i + 1, right = arr.length - 1;
            while (left < right) {
                if (arr[left] + arr[right] < rest) {
                    left++;
                } else if (arr[left] + arr[right] > rest) {
                    right--;
                } else {
                    if (right == arr.length - 1 || arr[right] != arr[right + 1]) {
//                        List<Integer> list = new ArrayList<>();
//                        list.add(arr[i]);
//                        list.add(arr[left]);
//                        list.add(arr[right]);
//                        ans.add(list);
                        ans.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    }
                    left++;
                    right--;
                }
            }
        }
        return ans;
    }

    // Brute Force - TLE
    public List<List<Integer>> threeSum1(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 3) return ans;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int first = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int second = arr[j];
                for (int k = j + 1; k < arr.length; k++) {
                    int third = arr[k];
                    if (first + second + third == target) {
                        List<Integer> list = Arrays.asList(first, second, third);
                        ans.add(list);
                    }
                }
            }
        }
        ans = new ArrayList<>(new HashSet<>(ans));
        return ans;
    }

    // TLE
    public List<List<Integer>> threeSum2(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 3) return ans;
        Arrays.sort(arr);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length - 2; i++) {
            int rest = target - arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(rest - arr[j])) {
                    List<Integer> list = Arrays.asList(arr[i], arr[j], rest - arr[j]);
                    ans.add(list);
                }
                set.add(arr[j]);
            }
            set.clear();
        }
        ans = new ArrayList<>(new HashSet<>(ans));
        return ans;
    }

    // AC
    public List<List<Integer>> threeSum3(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 3) return ans;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int rest = target - arr[i];
            List<LinkedList<Integer>> list = twoSum(arr, i + 1, rest);
            if ((i == 0 || arr[i] != arr[i - 1])) {
                for (LinkedList<Integer> each : list) {
                    each.addFirst(arr[i]);
                    ans.add(each);
                }
            }
        }
        ans = new ArrayList<>(new HashSet<>(ans));
        return ans;
    }

    private List<LinkedList<Integer>> twoSum(int[] arr, int start, int target) {
        Set<Integer> set = new HashSet<>();
        List<LinkedList<Integer>> list = new ArrayList<>();
        for (int i = start; i < arr.length; i++) {
            if (set.contains(target - arr[i])) {
                list.add(new LinkedList<>(Arrays.asList(target - arr[i], arr[i])));
            }
            set.add(arr[i]);
        }
        return list;
    }


    public List<List<Integer>> threeSum4(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 3) return ans;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {

            List<LinkedList<Integer>> list = twoSum4(arr, i + 1, target - arr[i]);

            if ((i == 0 || arr[i] != arr[i - 1])) {
                for (LinkedList<Integer> tuple : list) {
                    tuple.addFirst(arr[i]);
                    ans.add(tuple);
                }
            }
        }
        return ans;
    }

    private List<LinkedList<Integer>> twoSum4(int[] arr, int start, int target) {

        List<LinkedList<Integer>> ans = new LinkedList<>();
        int left = start, right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] > target) {
                right--;
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else {
                if (right == arr.length - 1 || arr[right] != arr[right + 1]) {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(arr[left]);
                    list.add(arr[right]);
                    ans.add(list);
                }
                left++;
                right--;
            }
        }
        return ans;
    }


}
