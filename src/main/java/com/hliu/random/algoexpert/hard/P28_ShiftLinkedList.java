package com.hliu.random.algoexpert.hard;



/*
Write a function that takes in the head of a
Singly Linked List and an integer k , shifts
the list in place (i.e., doesn't create a brand
new list) by k positions, and returns its new
head.
Shifting a Linked List means moving its nodes
forward or backward and wrapping them around the list where appropriate. For
example, shifting a Linked List forward by
one position would make its tail become the
new head of the linked list.
Whether nodes are moved forward or backward is determined by whether k is positive or negative.
Each LinkedList node has an integer value as well as a next node pointing to
the next node in the list or to None / null
if it's the tail of the list.
You can assume that the input Linked List
will always have at least one node; in other
words, the head will never be None / null .

Input:
head = 0 -> 1 -> 2 -> 3 (pre) -> 4 -> 5(cur)
k = 2

k = -2
5 4 3 2 1 0
- 3 2 1 0 5 4
  - 4 5 0 1 2 3

Output:
4 -> 5 -> 0 -> 1 -> 2 -> 3
*/


public class P28_ShiftLinkedList {

  public static void main(String[] args) {
    LinkedList a = new LinkedList(0);
    LinkedList b = new LinkedList(1);
    LinkedList c = new LinkedList(2);
    LinkedList d = new LinkedList(3);
    LinkedList e = new LinkedList(4);
    LinkedList f = new LinkedList(5);
    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;
    e.next = f;
    System.out.println(shiftLinkedList(a, 6).value);
  }

  public static LinkedList shiftLinkedList(LinkedList head, int k) {
    if (k == 0) {
      return head;
    }
    int len = 0;
    LinkedList cur = head;
    while (cur != null) {
      len++;
      cur = cur.next;
    }
    if (k == len) {
      return reverse(head);
    }
    if (k < 0) {
      LinkedList head1 = reverse(head);
      LinkedList head2 = shiftLinkedList(head1, -k);
      return reverse(head2);
    }
    k %= len;
    int walk = len - k;
    cur = head;
    LinkedList pre = null;
    while (walk != 0) {
      walk--;
      pre = cur;
      cur = cur.next;
    }
    while (cur.next != null) {
      cur = cur.next;
    }
    cur.next = head;
    head = pre.next;
    pre.next = null;

    return head;
  }

  public static LinkedList reverse(LinkedList head) {
    LinkedList pre = null;
    LinkedList next;
    LinkedList cur = head;
    while (cur != null) {
      next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }

  static class LinkedList {

    public int value;
    public LinkedList next;

    public LinkedList(int value) {
      this.value = value;
      next = null;
    }
  }

}
