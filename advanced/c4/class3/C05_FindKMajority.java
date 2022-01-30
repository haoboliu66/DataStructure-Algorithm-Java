package advanced.c4.class3;


import java.util.*;

public class C05_FindKMajority {

    /*
    169. Majority Element
    229. Majority Element II
     */

    // https://leetcode.com/problems/majority-element/
    public static int majorityElement0(int[] arr) {
        int cand = 0, HP = 0;
        for (int i = 0; i < arr.length; i++) {
            if (HP == 0) {
                HP++;
                cand = arr[i];
            } else {
                if (arr[i] != cand) {
                    HP--;
                } else {
                    HP++;
                }
            }
        }
        return cand;
    }

    // https://leetcode.com/problems/majority-element-ii/
    public static List<Integer> majorityElementII(int[] arr) {
        // 两个候选的值不能一样, 否则有可能同时被消掉
        // e.g [2,1,1,3,1,4,5,6]
        List<Integer> ans = new ArrayList<>();
        int c1 = 0, c2 = 0, HP1 = 0, HP2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (HP1 == 0 && arr[i] != c2) {
                c1 = arr[i];
                HP1++;
            } else if (HP2 == 0 && arr[i] != c1) {
                c2 = arr[i];
                HP2++;
            } else {
                if (arr[i] == c1) {
                    HP1++;
                } else if (arr[i] == c2) {
                    HP2++;
                } else {
                    HP1--;
                    HP2--;
                }
            }
        }
        int count1 = 0, count2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c1) {
                count1++;
            }
            if (c1 != arr[i] && arr[i] == c2) {
                count2++;
            }
        }
        if (count1 > arr.length / 3) {
            ans.add(c1);
        }
        if (count2 > arr.length / 3) {
            ans.add(c2);
        }
        return ans;
    }

    public static List<Integer> rightMajorityElementII(int[] arr) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > arr.length / 3) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 200000;
        int maxSize = 200;
        int maxValue = 10;
        System.out.println("Go");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            List<Integer> res1 = majorityElementII(arr);
            List<Integer> res2 = rightMajorityElementII(arr);
            Collections.sort(res1);
            Collections.sort(res2);
            if (!res1.equals(res2)) {
                System.out.println(res1);
                System.out.println(res2);
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }

    // general solution with Map
    public static List<Integer> majorityElementII0(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        // map size => 2
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                if (map.size() == 2) {
                    Iterator<Integer> iterator = map.keySet().iterator();
                    while (iterator.hasNext()) {
                        Integer k = iterator.next();
                        if (map.get(k) == 1) {
                            iterator.remove();
                        } else {
                            map.put(k, map.get(k) - 1);
                        }
                    }
                } else {
                    map.put(arr[i], 1);
                }

            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        Map<Integer, Integer> realTimes = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                if (!realTimes.containsKey(arr[i])) {
                    realTimes.put(arr[i], 1);
                } else {
                    realTimes.put(arr[i], realTimes.get(arr[i]) + 1);
                }
            }
        }

        for (int k : realTimes.keySet()) {
            if (realTimes.get(k) > arr.length / 3) {
                ans.add(k);
            }
        }
        return ans;
    }


    public static int majorityElement(int[] arr) {
        int cand = 0;
        int HP = 0; // HP == 0 代表目前没有候选
        for (int i = 0; i < arr.length; i++) {
            if (HP == 0) {
                // 如果没有候选, 立当前数字为候选, HP变成1
                cand = arr[i];
                HP = 1;
            } else if (cand == arr[i]) {
                // 如果 当前数字 == 候选, HP++
                HP++;
            } else {
                // 如果当前数字 != 候选, HP--, 进入下一轮循环, 相当于一次销掉了2个不同的数
                HP--;
            }
        }
        //如果不确保这个数字一定存在, 那么拿到这个cand之后再单独验一次, 看cand的次数是否真的超过N/2
        HP = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == cand) {
                HP++;
            }
        }
        if (HP > arr.length / 2) {
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

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

}
