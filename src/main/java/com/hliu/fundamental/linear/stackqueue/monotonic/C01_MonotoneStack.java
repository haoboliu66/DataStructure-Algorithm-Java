package com.hliu.fundamental.linear.stackqueue.monotonic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class C01_MonotoneStack {

  // 解决问题: 求一个数字两侧最近比他小 或 两侧最近比他大

  //    给定一个数组arr[3,2,1,7,0,4,5,6], 求每一个数字左边最近的比它小的数,和右边最近比它小的数
  public static int[][] getNearLessNoDuplicate(int[] arr) {
    if (arr == null || arr.length == 0) {
      return null;
    }
    Stack<Integer> incrStack = new Stack<>(); //要求: 栈底到栈顶值依次增大
    int[][] res = new int[arr.length][2];
    for (int i = 0; i < arr.length; i++) {
      while (!incrStack.isEmpty() && arr[i] < arr[incrStack.peek()]) {
        //弹出数字 收集他的左右两头信息
        Integer curIndex = incrStack.pop();
        res[curIndex][0] = incrStack.isEmpty() ? -1 : incrStack.peek();
        res[curIndex][1] = i;
      }
      incrStack.push(i);
    }
    while (!incrStack.isEmpty()) {
      int curIndex = incrStack.pop();
      res[curIndex][0] = incrStack.isEmpty() ? -1 : incrStack.peek();
      res[curIndex][1] = -1;
    }
    return res;
  }


  // 数组中有重复数字的情况
  public static int[][] getNearLessDuplicate(int[] arr) {
    if (arr == null || arr.length == 0) {
      return null;
    }
    Stack<List<Integer>> incrStack = new Stack<>();
    int[][] res = new int[arr.length][2];
    for (int i = 0; i < arr.length; i++) {
      while (!incrStack.isEmpty() && arr[i] <= arr[incrStack.peek()
                                                            .get(0)]) {
        if (arr[incrStack.peek()
                         .get(0)] == arr[i]) {//如果遇到相等的, 直接加入List
          incrStack.peek()
                   .add(i);  //加完直接进行下一轮
        } else {
          List<Integer> thisIndex = incrStack.pop(); //如果是小于栈顶, 弹出最上面的List,并收集信息
          for (Integer index : thisIndex) { //遍历List中每一个index, 右边是当前i, 左边是压在下面的List中的index
            res[index][1] = i;
            res[index][0] = incrStack.isEmpty() ? -1 : incrStack.peek()
                                                                .get(incrStack.peek()
                                                                              .size() - 1);
          }
        }
      }
      ArrayList<Integer> list = new ArrayList<>();
      list.add(i);
      incrStack.push(list);
    }
    while (!incrStack.isEmpty()) {
      List<Integer> thisIndex = incrStack.pop();
      for (Integer index : thisIndex) { //遍历List中每一个index, 右边是当前i, 左边是压在下面的List中的index
        res[index][1] = -1;
        res[index][0] = incrStack.isEmpty() ? -1 : incrStack.peek()
                                                            .get(incrStack.peek()
                                                                          .size() - 1);
      }
    }
    return res;
  }

  /*  zuo version  */
  public static int[][] getNearLess(int[] arr) {
    int[][] res = new int[arr.length][2];
    // List<Integer> -> 放的是位置，同样值的东西，位置压在一起
    Stack<List<Integer>> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) { // i -> arr[i] 进栈
      // 底 -> 顶， 小 -> 大
      while (!stack.isEmpty() && arr[stack.peek()
                                          .get(0)] > arr[i]) {
        List<Integer> popIs = stack.pop();
        // 取位于下面位置的列表中，最晚加入的那个
        int leftLessIndex = stack.isEmpty() ? -1 : stack.peek()
                                                        .get(stack.peek()
                                                                  .size() - 1);
        for (Integer popi : popIs) {
          res[popi][0] = leftLessIndex;
          res[popi][1] = i;
        }
      }
      // 相等的、比你小的
      if (!stack.isEmpty() && arr[stack.peek()
                                       .get(0)] == arr[i]) {
        stack.peek()
             .add(Integer.valueOf(i));
      } else {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(i);
        stack.push(list);
      }
    }
    while (!stack.isEmpty()) {
      List<Integer> popIs = stack.pop();
      // 取位于下面位置的列表中，最晚加入的那个
      int leftLessIndex = stack.isEmpty() ? -1 : stack.peek()
                                                      .get(stack.peek()
                                                                .size() - 1);
      for (Integer popi : popIs) {
        res[popi][0] = leftLessIndex;
        res[popi][1] = -1;
      }
    }
    return res;
  }


  public static void printArray(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(i + " ");
      System.out.print("[");
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println("\b]");
    }
  }

  public static int[] getRandomArray(int len) {
    if (len < 0) {
      return null;
    }
    int[] arr = new int[len];
    for (int i = 0; i < len; i++) {
      arr[i] = (int) (Math.random() * 100);
    }
    return arr;
  }

  public static boolean isEqual(int[][] arr1, int[][] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr1[i].length; j++) {
        if (arr1[i][j] != arr2[i][j]) {
          return false;
        }
      }
    }
    return true;
  }


  public static void main(String[] args) {
    int[] arr = {1, 5, 2, 5, 1, 6, 2, 5, 3, 8, 6};
    int[][] res = null;
    int[][] myres = null;
    System.out.println("===========");
    for (int i = 0; i < 50000; i++) {
      arr = getRandomArray(100);
      res = getNearLess(arr);
      myres = getNearLessDuplicate(arr);
      if (!isEqual(res, myres)) {
        System.out.println("Oops");
        break;
      }
    }
    System.out.println("done");

  }


}
