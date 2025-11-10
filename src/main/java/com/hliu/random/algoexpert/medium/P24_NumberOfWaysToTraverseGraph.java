package com.hliu.random.algoexpert.medium;

/*

since we're only allowed to move down(D) or right(R)
so for example, from (1,1) to (3,4)
we need 2 D and 3 R to get to the final point, that's a permutation problem:
{D,D,D,R,R}, the number of permutation is the result of ways of moving from (1,1) to (3,4)

factorial (R+D) divided by factorial(R) * factorial(D)
(R+D)! /  R! * D!
 */

public class P24_NumberOfWaysToTraverseGraph {

  public static void main(String[] args) {
    System.out.println(numberOfWaysToTraverseGraph(2, 3));
  }

  public static int numberOfWaysToTraverseGraph(int width, int height) {
    return process(height, width, 1, 1);
  }

  public static int process(int row, int col, int r, int c) {
    if (r > row || c > col) {
      return 0;
    }
    if (r == row && c == col) {
      return 1;
    }
    int ways = 0;
    ways += process(row, col, r + 1, c);
    ways += process(row, col, r, c + 1);
    return ways;
  }

  public static int numberOfWaysToTraverseGraph2(int width, int height) {
    return (int)(factorial(width-1+height-1) / (factorial(width-1) * factorial(height-1)));
  }

  public static long factorial(int n){
    long res = 1;
    for(int i = 1; i <= n; i++){
      res *= i;
    }
    return res;
  }

}
