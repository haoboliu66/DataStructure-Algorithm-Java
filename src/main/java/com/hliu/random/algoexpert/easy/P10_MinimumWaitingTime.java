package com.hliu.random.algoexpert.easy;

import java.util.Arrays;


/*

A query's waiting time is defined as the
amount of time that it must wait before its
execution starts. In other words, if a query is
executed second, then its waiting time is the
duration of the first query; if a query is
executed third, then its waiting time is the
sum of the durations of the first two queries.
Write a function that returns the minimum
amount of total waiting time for all of the queries.

For example, if you're given the
queries of durations [1, 4, 5] , then the
total waiting time if the queries were
executed in the order of [5, 1, 4] would
be (0) + (5) + (5 + 1) = 11 . The first
query of duration 5 would be executed immediately, so its waiting time would be 0
, the second query of duration 1 would
have to wait 5 seconds (the duration of the
first query) to be executed, and the last
query would have to wait the duration of the
first two queries before being executed.

Note: you're allowed to mutate the input array

 */
public class P10_MinimumWaitingTime {

  public static int minimumWaitingTime(int[] arr) {

    Arrays.sort(arr);
    int wait = 0;
    int total = 0;
    for (int i = 1; i < arr.length; i++) {
      wait += arr[i - 1];
      total += wait;
    }
    return total;
  }

}
