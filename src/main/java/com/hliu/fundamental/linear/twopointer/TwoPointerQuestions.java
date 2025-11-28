package com.hliu.fundamental.linear.twopointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TwoPointerQuestions {

  public String findDifferentBinaryString(String[] nums) {

    byte[] bitMap = new byte[2]; // 2 * 8 = 16 bits

    for (String num : nums) {

      char[] str = num.toCharArray();
      int len = str.length;
      for (int i = len - 1; i >= 0; i++) {

        // [00000000][00000000]
        //  7

        int targetBit = i / 8 + i % 4;


      }

    }

    return "";
  }

  public List<Integer> findMissingElements(int[] nums) {
    List<Integer> res = new ArrayList<>(nums.length);
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
      seen.add(num);
      min = Math.min(min, num);
      max = Math.max(max, num);
    }

    for (int i = min; i <= max; i++) {
      if (!seen.contains(i)) {
        res.add(i);
      }
    }

    return res;
  }

  public int maxProduct0(String[] words) {
    int maxLenProduct = 0;
    for (int i = 0; i < words.length; i++) {
      int len = words[i].length();
      for (int j = i + 1; j < words.length; j++) {
        if (!shareCommonChar(words[i], words[j])) {
          int product = len * words[j].length();
          maxLenProduct = Math.max(maxLenProduct, product);
        }
      }
    }
    return maxLenProduct;
  }

  public int maxProduct1(String[] words) {
    Map<String, Integer> wordToIntMap = new HashMap<>();
    for (String word : words) {
      wordToIntMap.put(word, wordToInt(word));
    }
    int maxLenProduct = 0;
    for (int i = 0; i < words.length; i++) {
      int len = words[i].length();
      for (int j = i + 1; j < words.length; j++) {
        if (!shareCommonBit(wordToIntMap.get(words[i]), wordToIntMap.get(words[j]))) {
          int product = len * words[j].length();
          maxLenProduct = Math.max(maxLenProduct, product);
        }
      }
    }
    return maxLenProduct;
  }

  private int wordToInt(String word) {
    int res = 0;
    for (char ch : word.toCharArray()) {
      res |= 1 << (ch - 'a');
    }
    return res;
  }

  private boolean shareCommonBit(int x, int y) {
    return (x & y) != 0;
  }

  private boolean shareCommonChar(String word1, String word2) {
    boolean[] charSet = new boolean[26];
    for (char c : word1.toCharArray()) {
      charSet[c - 'a'] = true;
    }
    for (char c : word2.toCharArray()) {
      if (charSet[c - 'a']) {
        return true;
      }
    }
    return false;
  }

  // https://leetcode.com/problems/sort-array-by-parity/
  public int[] sortArrayByParity(int[] nums) {
    int odd = nums.length - 1;
    int even = 0;

    for (; even < odd; ) {

      if ((nums[even] & 1) == 0) {
        even++;
        continue;
      }
      if ((nums[odd] & 1) == 1) {
        odd--;
        continue;
      }
      if ((nums[odd] & 1) == 0) {
        swap(nums, odd, even);
        even++;
      }
      if ((nums[even] & 1) == 1) {
        swap(nums, odd, even);
        odd--;
      }
    }
    return nums;
  }

  private void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // https://leetcode.com/problems/sort-array-by-parity-ii/
  public int[] sortArrayByParityII(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return nums;
    }
    int odd = 1;
    int even = 0;
    for (; odd < nums.length && even < nums.length; ) {
      if (odd < nums.length && (nums[odd] & 1) == 0) {
        swap(nums, odd, even);
        even += 2;
      } else {
        odd += 2;
      }
      if (even < nums.length && (nums[even] & 1) == 1) {
        swap(nums, odd, even);
        odd += 2;
      } else {
        even += 2;
      }
    }
    return nums;
  }

  public int[] sortArrayByParityII2(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return nums;
    }
    int odd = 1;
    int even = 0;
    int index = nums.length - 1;
    for (; odd < nums.length && even < nums.length; ) {
      if ((nums[index] & 1) == 0) {
        swap(nums, index, even);
        even += 2;
      } else {
        swap(nums, index, odd);
        odd += 2;
      }
    }
    return nums;
  }

  // https://leetcode.com/problems/find-the-duplicate-number/
  public int findDuplicate(int[] nums) {

    return 0;
  }

  // https://leetcode.com/problems/trapping-rain-water/
  public int trap(int[] height) {

    return 0;
  }

  // https://leetcode.com/problems/boats-to-save-people/
  /*
  贪心: 先排序, 然后让最小的和最大的配对
   */
  public int numRescueBoats(int[] people, int limit) {

    return 0;
  }
  // 扩展要求: 如果2个人的体重sum必须是偶数, 怎么求解
  /*
  因为要求sum是偶数, 所以2个人只能是 偶+偶 或者 奇+奇, 不能是 奇+偶
  因此, 1.先把一个数组分为两个数组, 奇数的单独一个数组, 偶数的单独一个数组
  2. 对两个数组分别按上面的解法计算需要多少个boat，最后相加
   */

  // https://leetcode.com/problems/container-with-most-water/
  public int maxArea(int[] height) {

    return 0;
  }

  // https://leetcode.com/problems/heaters/
  public int findRadius(int[] houses, int[] heaters) {
    // brute force O(m*n)
    int[] minHeaterDistances = new int[houses.length];
    for (int i = 0; i < houses.length; i++) {
      int curHousePosition = houses[i];
      int curHouseMinDistance = Integer.MAX_VALUE;
      /*
       for each house, we need find the closest heater, track it in minHeaterDistances
       i.e.  minHeaterDistances[i] means the min distance from house[i] to its closest heater
       */
      for (int j = 0; j < heaters.length; j++) {
        curHouseMinDistance = Math.min(curHouseMinDistance, Math.abs(curHousePosition - heaters[j]));
      }
      minHeaterDistances[i] = curHouseMinDistance;
    }
    /*
    After the nested loop, we have minHeaterDistances array ready, so that we know the minDistance each house to its
    closest heater.
    We need grab the max among all the distances, because we have to use the max value to cover the farthest house.
     */
    int minRadius = 0;
    for (int distance : minHeaterDistances) {
      minRadius = Math.max(minRadius, distance);
    }
    return minRadius;
  }

  public int findRadius1(int[] houses, int[] heaters) {
//    int[] minHeaterDistances = new int[houses.length];
    int minRadius = 0;
    TreeSet<Integer> heaterSet = new TreeSet<>();
    for (int heater : heaters) {
      heaterSet.add(heater);
    }
    // O(N*logM)
    for (int i = 0; i < houses.length; i++) {
      int curHousePosition = houses[i];
      int curHouseMinDistance = Integer.MAX_VALUE;
      Integer rightHeater = heaterSet.ceiling(curHousePosition);
      Integer leftHeader = heaterSet.floor(curHousePosition);
      int rightDistance = rightHeater != null ? Math.abs(curHousePosition - rightHeater) : Integer.MAX_VALUE;
      int leftDistance = leftHeader != null ? Math.abs(curHousePosition - leftHeader) : Integer.MAX_VALUE;
      curHouseMinDistance = Math.min(rightDistance, leftDistance);
//      minHeaterDistances[i] = curHouseMinDistance;
      minRadius = Math.max(minRadius, curHouseMinDistance);
    }
//    int minRadius = 0;
//    for (int distance : minHeaterDistances) {
//      minRadius = Math.max(minRadius, distance);
//    }
    return minRadius;
  }
}
