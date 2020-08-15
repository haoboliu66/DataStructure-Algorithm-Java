package advanced.c4.class3;

import java.util.*;

/**
 * @author andy-liu
 * @date 2020/7/23 - 8:32 AM
 */
public class C05_FindKMajority {

    /*
    169. Majority Element
    229. Majority Element II
     */

    public int majorityElement(int[] nums) {

        int cand = 0;
        int times = 0;
        for (int i = 0; i < nums.length; i++) {
            if (times == 0) {
                cand = nums[i];
                times = 1;
            } else if (cand == nums[i]) {
                times++;
            } else {
                times--;
            }
        }
        //如果不确保这个数字一定存在, 那么拿到这个cand之后再单独验一次, 看cand的次数是否真的超过N/2
        times = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cand) {
                times++;
            }
        }
        if (times > nums.length / 2) {
            return cand;
        } else {
            return -1;
        }
    }


    /*
    229. Majority Element II
    */

    public static List<Integer> majorityElementII(int[] nums, int K) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // k == 3 ==> at most 2 candidates

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                if (map.size() == K - 1) {
                    AllCandMinusOne(map);
                } else {
                    map.put(nums[i], 1);
                }
            }
        }
        //拿到候选, 在map里, 再遍历一次统计候选的真正次数
        HashMap<Integer, Integer> realElements = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) { //该数字是候选数字
                if (!realElements.containsKey(nums[i])) {
                    realElements.put(nums[i], 1);
                } else {
                    realElements.put(nums[i], realElements.get(nums[i]) + 1);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (Integer key : realElements.keySet()) {
            if (realElements.get(key) > nums.length / K) {
                res.add(key);
            }
        }
        return res;
    }

    private static void AllCandMinusOne(HashMap<Integer, Integer> map) {
        for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            int key = iterator.next();
            if (map.get(key) == 1) {
                iterator.remove();
            } else {
                map.put(key, map.get(key) - 1);
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {3, 2, 3};
        int[] arr1 = {2, 2, 1, 3};
        int K = 3;
        System.out.println(majorityElementII(arr1, K));
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(1, 6);
//        map.put(2, 3);
//        map.put(3, 6);
//        map.put(4, 3);
//        map.put(5, 6);
//        System.out.println(map);
//        for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
//            int key = iterator.next();
//            if (map.get(key) == 3) {
//                iterator.remove();
//            }
//        }
//        System.out.println(map);
    }

}
