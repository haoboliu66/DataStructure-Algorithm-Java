package com.hliu.fundamental.sorting.heap.greater;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class C01_TopKCustomer {

  private static class Customer {

    int index;
    int count;


  }

  public List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
    Map<Integer, Customer> customers = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      if (!customers.containsKey(arr[i])) {
        Customer customer = new Customer();
        customer.index = arr[i];
        customer.count = 1;
      } else {
        Customer cur = customers.get(arr[i]);
        cur.count += op[i] ? 1 : -1;
      }
    }
    PriorityQueue<Customer> maxHeap = new PriorityQueue<>();


    return null;
  }

}
