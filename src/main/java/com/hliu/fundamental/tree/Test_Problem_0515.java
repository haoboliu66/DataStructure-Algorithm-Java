package com.hliu.fundamental.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Test_Problem_0515 {

  public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> stack = new Stack<>();
    boolean rockDies = false;
    for (int rock : asteroids) {

      if (!stack.isEmpty() && ((stack.peek() >> 31) & 1) != ((rock >> 31) & 1)) {

        int prev = stack.peek();
        if (Math.abs(prev) > Math.abs(rock)) {

        } else if (Math.abs(prev) == Math.abs(rock)) {
          stack.pop();
          rockDies = true;
        } else {
          // prev dies, need check further before prev
          while (!stack.isEmpty()) {
            int pprev = stack.peek();

            if (Math.abs(pprev) > Math.abs(rock)) {
              rockDies = true;
              break;
            } else if (Math.abs(pprev) == Math.abs(rock)) {
              stack.pop();
              rockDies = true;
              break;
            } else {
              stack.pop();
            }
          }

          if (stack.isEmpty() && rockDies) {
            stack.push(rock);
          }

        }

      }

    }
    int[] res = new int[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--) {
      res[i] = stack.pop();
    }
    return res;
  }

  public int largestAltitude(int[] gain) {

    int[] altitudes = new int[gain.length + 1];

    altitudes[0] = 0;
    for (int i = 1; i < altitudes.length; i++) {
      altitudes[i] = altitudes[i - 1] + gain[i - 1];
    }
    int max = 0;
    for (int altitude : altitudes) {
      max = Math.max(max, altitude);
    }
    return max;
  }

  public long maximumTotalDamage(int[] power) {
    Map<Integer, Set<Integer>> indexMap = new HashMap<>();
    Map<Integer, Integer> numMap = new HashMap<>();
    for (int num : power) {
      numMap.put(num, numMap.getOrDefault(num, 0) + 1);
    }
    Arrays.sort(power);
    int n = power.length;
    for (int i = n - 1; i >= 0; i--) {
      int currPower = power[i];
      int currPowerCount = numMap.get(currPower);


    }

    return 0;
  }


  public boolean canPlaceFlowers(int[] arr, int n) {
    for (int i = 0; i < arr.length; ) {
      if (arr[i] == 1) {   // [x, 1, x], can only forward 2 indices
        i += 2;
        continue;
      }
      // arr[i] == 0
      if (i - 1 >= 0 && arr[i - 1] == 1) {  //  [ 1, 0, x], can only skip current index]
        i++;
      } else if (i + 1 < arr.length && arr[i + 1] == 1) { // [x, 0, 1] current index cannot be used
        i += 3;
      }
      // arr[i-1] == 0 && arr[i+1] == 0
      else {
        if (i >= 2 && arr[i - 2] == 0) {
          n--;
          arr[i - 2] = 1;
          i++;
        } else {
          arr[i] = 1;
          n--;
          i += 2;
        }
        if (n == 0) {
          return true;
        }
      }
    }
    return n <= 0;
  }

  public int numRookCaptures(char[][] board) {
    int m = 0, n = 0;
    int i = 0, j = 0;
    out:
    for (; i < board.length; i++) {
      for (; j < board[i].length; j++) {
        if (board[i][j] == 'R') {
          break out;
        }
      }
      char[] row = board[i];

    }
    return 0;
  }

  public List<Integer> addToArrayForm(int[] num, int k) {
    int carry = 0;
    int i = num.length - 1;
    for (; i >= 0; i--) {
//      int sum =

    }
    return null;
  }


  public String addStrings(String num1, String num2) {
    char[] str1 = num1.toCharArray();
    char[] str2 = num2.toCharArray();
    int i = str1.length - 1, j = str2.length - 1;
    int carry = 0;
    for (; i >= 0 && j >= 0; ) {
      int sum = (str1[i] - '0') + (str2[j] - '0') + carry;
      carry = sum / 10;
      str1[i] = (char) (sum % 10 + '0');
      i--;
      j--;
    }
    while (i >= 0) {
      int sum = str1[i] - '0' + carry;
      carry = sum / 10;
      str1[i] = (char) (sum % 10 + '0');
    }
    while (i >= 0) {
      int sum = str1[i] - '0' + carry;
      carry = sum / 10;
      str1[i] = (char) (sum % 10 + '0');
    }
    return "";
  }


  public String reverseVowels(String s) {
    char[] str = s.toCharArray();
    int left = 0, right = str.length - 1;
    while (left < right) {
      if (!isVowel(str[left])) {
        left++;
        continue;
      }
      if (!isVowel(str[right])) {
        right--;
        continue;
      }
      swap(str, left++, right--);
    }
    return new String(str);
  }

  private void swap(char[] str, int left, int right) {
    char temp = str[left];
    str[left] = str[right];
    str[right] = temp;
  }

  private boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
        || c == 'U';
  }


  public String restoreString(String s, int[] indices) {
    char[] str = s.toCharArray();
    int[] indexMap = new int[str.length];

    for (int i = 0; i < indices.length; i++) {
      int curr = indices[i];


    }

    return "";
  }


}
