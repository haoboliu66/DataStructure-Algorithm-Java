package com.hliu.fundamental.linear.array.presum;


 /*
  You are given a 0-indexed array of strings words and a 2D array of integers queries.
  Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.
  Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
  Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.

  Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
  Output: [2,3,0]
   */

public class Problem_2559_CountVowelStringsInRanges {

  public int[] vowelStrings(String[] words, int[][] queries) {
    int[] res = new int[queries.length];
    int[] preSum = new int[words.length];
    preSum[0] = isVowel(words[0]) ? 1 : 0;
    for (int i = 1; i < words.length; i++) {
      String curr = words[i];
      preSum[i] = preSum[i - 1] + (isVowel(curr) ? 1 : 0);
    }
    for (int i = 0; i < queries.length; i++) {
      int[] query = queries[i];
      int start = query[0];
      int end = query[1];
      if (start == 0) {
        res[i] = preSum[end];
      } else {
        res[i] = preSum[end] - preSum[start - 1];
      }
    }

    return res;
  }

  private boolean isVowel(String word) {
    char c = word.charAt(0);
    char c1 = word.charAt(word.length() - 1);
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') &&
        (c1 == 'a' || c1 == 'e' || c1 == 'i' || c1 == 'o' || c1 == 'u');
  }

}
