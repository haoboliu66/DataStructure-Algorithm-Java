package com.hliu.fundamental.linear.array;

public class _0026_RemoveDuplicatesFromSortedArray {

  public int removeDuplicates(int[] arr) {

    int slow = 0, fast = 0;

    for (; fast < arr.length; ) {
      if (arr[fast] == arr[slow]) {
        fast++;
      } else {
        slow++;
        arr[slow] = arr[fast];
      }
    }
    return slow + 1;
  }

}
