package com.hliu.random.someOA.microsoft;

import java.util.HashMap;
import java.util.Map;

public class CarbonFilters {


  public static void main(String[] args) {

  }

  public static String solution(String s) {

    char[] charArray = s.toCharArray();
    char[] str = new char[charArray.length + 2];
    str[0] = '0';
    str[str.length - 1] = '0';
    //   aabbcc
    //  0aabbcc0
    for (int i = 1; i < str.length - 1; i++) {
      str[i] = charArray[i - 1];
    }

    Map<Integer, int[]> nearestIndex = new HashMap<>();
    for (int i = 0; i < charArray.length; i++) {
      if (charArray[i] == '?') {
        nearestIndex.put(i, new int[]{-1, -1});
      }
    }
    for (int i = 0; i < charArray.length; i++) {
      if (charArray[i] == 'a') {
        nearestIndex.put(i, new int[]{-1, -1});
      }
    }
    for (int i = 0; i < charArray.length; i++) {
      if (charArray[i] == '?') {
        nearestIndex.put(i, new int[]{-1, -1});
      }
    }

    for (int i = 1; i < str.length - 1; i++) {

      if (str[i] == '?') {

      }


    }

    return "";
  }


}
