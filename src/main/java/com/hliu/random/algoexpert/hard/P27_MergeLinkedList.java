package com.hliu.random.algoexpert.hard;



/*
Write a function that takes in the heads of two
Singly Linked Lists that are in sorted order,
respectively. The function should merge the
lists in place (i.e., it shouldn't create a brand
new list) and return the head of the merged
list; the merged list should be in sorted order.
Each LinkedList node has an integer
value as well as a next node pointing to
the next node in the list or to None / null
if it's the tail of the list.
You can assume that the input linked lists will
always have at least one node; in other words,
the heads will never be None / null .

Input:
headOne = 2 -> 6 -> 7 -> 8
headTwo = 1 -> 3 -> 4 -> 5 -> 9 -> 10

Output:
1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
*/


public class P27_MergeLinkedList {

  private static class LinkedList {

    int value;
    LinkedList next;

    LinkedList(int value) {
      this.value = value;
      this.next = null;
    }
  }

  public static LinkedList mergeLinkedLists(LinkedList head1, LinkedList head2) {
    LinkedList res = head1.value < head2.value ? head1 : head2;
    LinkedList cur = res;
    if (head1 == res) {
      head1 = head1.next;
    }
    if (head2 == res) {
      head2 = head2.next;
    }

    while (head1 != null && head2 != null) {

      if (head1.value < head2.value) {

        cur.next = head1;
        cur = head1;
        head1 = head1.next;

      } else {

        cur.next = head2;
        cur = head2;
        head2 = head2.next;
      }

    }

    while (head1 != null) {
      cur.next = head1;
      cur = head1;
      head1 = head1.next;
    }

    while (head2 != null) {
      cur.next = head2;
      cur = head2;
      head2 = head2.next;
    }

    return res;
  }

}
