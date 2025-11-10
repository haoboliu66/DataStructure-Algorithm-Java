package com.hliu.fundamental.linear.stackqueue;


public class StackWithArray {

    private int[] stack;
    private int limit;
    private final int DEFAULT_LIMIT = 20;
    private int size;

    public StackWithArray() {
        limit = DEFAULT_LIMIT;
        stack = new int[limit];
        size = 0;
    }

//    public StackWithArray(int l) {
//        this.limit = l;
//        stack = new int[limit];
//        size = 0;
//    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(int ele) {
        if (size == limit) {
            throw new RuntimeException("StackOverflowException");
        }
        stack[size] = ele;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("EmptyStackException");
        }
        return stack[--size];
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < size; i++) {
            res += stack[i] + " ";
        }
        res += "\b";
        return res;
    }

    public static void main(String[] args) {
        StackWithArray stack = new StackWithArray();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        System.out.println(stack.size());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.size());
        stack.push(1000);
        System.out.println(stack.size());
        System.out.println(stack);



    }
}
