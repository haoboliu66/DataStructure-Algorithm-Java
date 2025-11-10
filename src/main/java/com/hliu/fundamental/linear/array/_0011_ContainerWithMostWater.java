package com.hliu.fundamental.linear.array;

public class _0011_ContainerWithMostWater {

  public int maxArea(int[] height) {
    int area = 0;
    int left = 0, right = height.length - 1;

    while (left < right) {
      // 高度只能是left和right中小的 才能兜住水
      int cur = (right - left) * Math.min(height[left], height[right]);
      area = Math.max(area, cur);

      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }
    return area;

  }

}
