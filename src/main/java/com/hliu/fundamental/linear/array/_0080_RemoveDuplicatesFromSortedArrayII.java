package com.hliu.fundamental.linear.array;

public class _0080_RemoveDuplicatesFromSortedArrayII {

  public int removeDuplicates(int[] arr) {
    if (arr.length <= 2) {
      return arr.length;
    }
    int fast = 2;
    int slow = 2;
    // 1 1 1 1 2 2 3
    for (; fast < arr.length; ) {
      if (arr[fast] != arr[slow - 2]) {
        arr[slow] = arr[fast];
        slow++;
        fast++;
      } else {
        fast++;
      }
    }
    return slow;
  }

  public int removeDuplicates1(int[] arr) {
    if (arr.length <= 2) {
      return arr.length;
    }
    return 0;
  }


}
