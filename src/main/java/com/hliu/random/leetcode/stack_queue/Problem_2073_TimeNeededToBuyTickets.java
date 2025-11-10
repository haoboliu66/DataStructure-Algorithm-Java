package com.hliu.random.leetcode.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_2073_TimeNeededToBuyTickets {

  public int timeRequiredToBuy1(int[] tickets, int k) {
    int time = 0;
    int index = 0;

    while (tickets[k] != 0) {
      if (tickets[index] > 0) {
        tickets[index]--;
        time++;
      }
      index = (index + 1) % tickets.length;  // 数组iteration in a circular manner
    }
    return time;
  }

  public int timeRequiredToBuy(int[] tickets, int k) {

    Queue<Person> q = new LinkedList<>();

    for (int i = 0; i < tickets.length; i++) {

      q.offer(new Person(tickets[i], i));

    }
    int time = 0;
    while (!q.isEmpty()) {
      Person cur = q.poll();
      time++;
      cur.aim--;
      if (cur.aim == 0) {
        if (cur.index == k) {
          break;
        }
      } else {
        q.offer(cur);
      }
    }
    return time;
  }

  static class Person {

    int aim;
    int index;

    public Person(int aim, int index) {
      this.aim = aim;
      this.index = index;
    }

  }

}
