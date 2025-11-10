package com.hliu.random.leetcode.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_1700_NumberOfStudentsUnableToEatLunch {

  public static void main(String[] args) {

    int[] students = {1, 1, 1, 0, 0, 1};
    int[] sandwiches = {1, 0, 0, 0, 1, 1};
    System.out.println(countStudents(students, sandwiches));
  }

  /*

  students = [1,1,1] sandwiches = [0,1,1]




   */

  public static int countStudents(int[] students, int[] sandwiches) {
    int circular = 0;
    int square = 0;
    int complete = 0;
    for (int s : sandwiches) {
      if(s == 0) {
        circular++;
      } else {
        square++;
      }
    }
    int index = 0;
    while(complete != students.length) {
      int target = students[index];
      int cur = sandwiches[index];

      if(cur == target) {
        complete++;
        if(target == 0) {
          circular--;
        } else {
          square--;
        }
        students[index] = -1;
        sandwiches[index] = -1;
      }
      if(circular < (students.length - complete)  ) break;
      if(square < (students.length - complete)  ) break;

      index = (index + 1) % students.length;
    }

    return students.length - complete;
  }

  public static int countStudentsQueue(int[] students, int[] sandwiches) {
    Queue<Student> q = new LinkedList<>();
    for (int i = 0; i < students.length; i++) {
      q.offer(new Student(students[i], i));
    }
    int index = 0;
    while (!q.isEmpty()) {
      return 0;
    }
    return 0;
  }

  private static class Student {

    int aim;
    int index;

    public Student(int aim, int index) {
      this.aim = aim;
      this.index = index;
    }
  }

}
