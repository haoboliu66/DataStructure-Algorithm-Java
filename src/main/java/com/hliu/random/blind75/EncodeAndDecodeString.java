package com.hliu.random.blind75;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeString {

  public String encode(List<String> strs) {
    StringBuilder sb = new StringBuilder();
    for (String s : strs) {
      sb.append(s.length())
        .append("#")
        .append(s);
    }
    return sb.toString();
  }


  public List<String> decode(String s) {
    List<String> res = new ArrayList<>();
    char[] str = s.toCharArray();
    for (int i = 0; i < str.length; ) {
      int curIndex = i;
      StringBuilder len = new StringBuilder();
      while (str[curIndex] != '#') {
        len.append(str[curIndex++]);
      }
      curIndex++;
      int length = Integer.parseInt(len.toString());
      StringBuilder string = new StringBuilder();
      for (int j = 0; j < length; j++) {
        string.append(str[curIndex++]);
      }
      res.add(string.toString());
      i = curIndex;
    }

    return res;
  }
}
