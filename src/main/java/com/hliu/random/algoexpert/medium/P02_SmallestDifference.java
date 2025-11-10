package com.hliu.random.algoexpert.medium;

import java.util.Arrays;

public class P02_SmallestDifference {

  public static int[] smallestDifference(int[] one, int[] two) {
    Arrays.sort(one);
    Arrays.sort(two);

    int index1 = 0, index2 = 0, min = Math.abs(one[index1++] - two[index2]);
    while(index1 < one.length && index2 < two.length){
      if(Math.abs(one[index1] - two[index2]) < min){
        min = Math.abs(one[index1] - two[index2]);
        index1++;
      }else{
      }
    }

    return new int[]{index1, index2};

  }

}

