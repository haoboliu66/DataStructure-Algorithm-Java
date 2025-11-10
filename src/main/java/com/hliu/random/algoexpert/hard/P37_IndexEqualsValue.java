package com.hliu.random.algoexpert.hard;

public class P37_IndexEqualsValue {

  public int indexEqualsValue(int[] arr) {

    int left = 0, right = arr.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if(arr[mid] == mid){
        return mid;
      }else if(arr[mid] > mid){

      }else{

      }

    }

    return -1;
  }

}
