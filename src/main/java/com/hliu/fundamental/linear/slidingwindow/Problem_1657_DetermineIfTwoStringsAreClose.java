package com.hliu.fundamental.linear.slidingwindow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Problem_1657_DetermineIfTwoStringsAreClose {

  public static void main(String[] args) throws IOException {

    Stream<Path> walk = Files.walk(Path.of("/Users/hliu02/"));
    walk.forEach(file -> {
      System.out.println(file);
    });

  }

  public boolean closeStrings(String word1, String word2) {
    char[] str1 = word1.toCharArray();
    char[] str2 = word2.toCharArray();
    if (str1.length != str2.length) {
      return false;
    }

    return true;
  }

}
