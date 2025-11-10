package com.hliu.fundamental.linear.array;

public class _0027_RemoveElement {

  /*
  remove val from arr (in-place)
   */

  public int removeElement(int[] arr, int val) {
    int slow = 0, fast = 0;

    for (; fast < arr.length; ) {
      if (arr[fast] == val) {
        // [fast] will stop at the valid index, and copy the value to [slow]
        fast++;
      } else {
        arr[slow] = arr[fast];
        fast++;
        slow++;
      }
    }
    // slow keeps track of the last valid index
    return slow;
  }

}
