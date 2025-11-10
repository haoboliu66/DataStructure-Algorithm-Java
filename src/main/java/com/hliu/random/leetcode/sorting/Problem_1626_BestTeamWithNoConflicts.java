package com.hliu.random.leetcode.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem_1626_BestTeamWithNoConflicts {

  public static void main(String[] args) {
    int[] score = {4,5,6,5};
    int[] age = {2,1,2,1};

    System.out.println(bestTeamScore(score, age));
  }

  public static int bestTeamScore(int[] scores, int[] ages) {
    List<Player> players = new ArrayList<>();
    for (int i = 0; i < scores.length; i++) {
      players.add(new Player(scores[i], ages[i]));
    }
    int total = 0;
    //  age降序, 如果age相同, 挑score大的
    Collections.sort(players, (a, b) -> b.age - a.age != 0 ? b.age - a.age : b.score - a.score);


    int[] dp = new int[players.size()];

    return dp[dp.length - 1];
  }

  public static int process(Player[] players, int total, int index) {
    if(index == players.length) {
      return 0;
    }
    total += process(players, total, index + 1);

    return 0;
  }


  static class Player {

    int score;
    int age;

    public Player(int score, int age) {
      this.score = score;
      this.age = age;
    }

    @Override
    public String toString() {
      return "Player{" +
          "score=" + score +
          ", age=" + age +
          '}';
    }
  }
}