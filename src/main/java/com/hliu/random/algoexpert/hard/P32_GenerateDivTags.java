package com.hliu.random.algoexpert.hard;

import java.util.ArrayList;

public class P32_GenerateDivTags {

  private static final String open = "<div>";
  private static final String close = "</div>";

  public static void main(String[] args) {

    int n = 3;
    System.out.println(generateDivTags(n));
  }

  public static ArrayList<String> generateDivTags(int numberOfTags) {
    ArrayList<String> res = new ArrayList<>();

    return res;
  }

  /*
  <div></div><div></div>

  <div></div>

  <div><div></div></div>

<div><div><div>
   */
  public static void process(int open, int close, StringBuilder sb, ArrayList<String> res) {
    if(open == 0 && close == 0){
      res.add(sb.toString());
      return;
    }



  }


}
