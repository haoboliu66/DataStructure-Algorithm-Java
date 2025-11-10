package com.hliu.fundamental.linear.stackqueue;

public class Kfifo {

  public static void main(String[] args) {

    Kfifo queue = new Kfifo(5);
    queue.enqueue("1");
    queue.enqueue("2");
    queue.enqueue("3");
    queue.enqueue("4");
    queue.enqueue("5");

    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
  }


  String[] queue;
  int inIndex;
  int outIndex;

  public Kfifo(int size) {
    this.queue = new String[size];
  }

  public void enqueue(String s) {
    if(inIndex == queue.length) {
      System.out.println("Queue is full");
      return;
    }
    queue[inIndex++] = s;
  }

  public String dequeue() {
    if(outIndex == inIndex) {
      System.out.println("Queue is empty");
      return null;
    }
    return queue[outIndex++];
  }





}
