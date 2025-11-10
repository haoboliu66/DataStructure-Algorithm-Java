package com.hliu.random.algoexpert.medium;

public class P52_ReverseWordsInString {


  public static void main(String[] args) {
    String s = "this      string     has a     lot of   whitespace";

    System.out.println(new P52_ReverseWordsInString().reverseWordsInString(s));
  }

  public String reverseWordsInString(String s) {
    s = reverse(s);
    System.out.println(s);
    return reverseAllWords(s);
  }

  private String reverseAllWords(String s) {
    char[] str = s.toCharArray();
    int p1 = 0, p2 = 0;
    while (p2 < str.length) {
      while (p2 + 1 < str.length && str[p2 + 1] != ' ') {
        p2++;
      }
      reverseOneWord(str, p1, p2++);
      while(p2 < str.length && str[p2] == ' '){
        p2++;
      }
      p1 = p2;
    }
    return new String(str);
  }

  private void reverseOneWord(char[] str, int i, int j) {
    while (i <= j) {
      swap(str, i++, j--);
    }
  }

  private String reverse(String s) {
    char[] str = s.toCharArray();
    int i = 0, j = str.length - 1;
    while (i <= j) {
      swap(str, i++, j--);
    }
    return new String(str);
  }

  private void swap(char[] str, int i, int j) {
    char tmp = str[i];
    str[i] = str[j];
    str[j] = tmp;
  }

}
