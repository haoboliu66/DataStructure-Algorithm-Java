package com.hliu.fundamental.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.hliu.fundamental.graph.Graph.Node;

public class DFS {

  public static <V> void depthFirstSearch(Graph.Node<V> node) {
    if (node == null) {
      return;
    }
    Stack<Graph.Node<V>> stack = new Stack<>();
    Set<Node<V>> set = new HashSet<>();
    stack.add(node);
    set.add(node);
    System.out.println(node);
    while (!stack.isEmpty()) {
      Graph.Node<V> cur = stack.pop();
      for (Graph.Node<V> next : cur.neighbours) {
        if (!set.contains(next)) {
          stack.push(cur); // keep cur SBTNode for backtracking
          stack.push(next);
          set.add(next);
          process(next);
          break;
        }
      }
    }
  }

  public static <V> void depthFirstSearch1(Graph.Node<V> node) {
    if (node == null) {
      return;
    }
    Stack<Graph.Node<V>> stack = new Stack<>();
    HashSet<Graph.Node<V>> set = new HashSet<>();
    stack.add(node);
    set.add(node);
    while (!stack.isEmpty()) {
      Graph.Node<V> cur = stack.pop();
      process(cur);
      List<Graph.Node<V>> adjacents = cur.neighbours;
      for (Graph.Node<V> next : adjacents) {
        if (!set.contains(next)) {
          stack.push(next);
          set.add(next);
        }
      }
    }
  }

  private static void process(Graph.Node cur) {
    System.out.println(cur);
  }
}
