package com.hliu.fundamental.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MergeUsers {

  public static class User {

    public String a;
    public String b;
    public String c;

    public User(String a, String b, String c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }
  }

  private static class Node<V> {

    V value;

    public Node(V v) {
      value = v;
    }
  }

  public static class UnionSet<V> {

    HashMap<V, Node<V>> nodes; // 存进去的元素和他对应的包装元素
    HashMap<Node<V>, Integer> sizeMap; //存的是以V为代表的集合对应的Size
    HashMap<Node<V>, Node<V>> parents; //存的是每个元素和其上面一个元素的映射

    public UnionSet(V[] values) {
      nodes = new HashMap<>();
      sizeMap = new HashMap<>();
      parents = new HashMap<>();
      for (V val : values) {
        Node node = new Node(val);
        nodes.put(val, node);
        parents.put(node, node);
        sizeMap.put(node, 1);
      }
    }

    public Node<V> findFather(Node<V> cur) {
      Stack<Node<V>> stack = new Stack<>();
      while (cur != parents.get(cur)) {
        stack.push(cur);
        cur = parents.get(cur);
      }
      // 长链扁平化(优化)
      while (!stack.isEmpty()) {
        parents.put(stack.pop(), cur);
      }
      return cur;
    }

    public boolean isSameSet(V value1, V value2) {
      if (!nodes.containsKey(value1) || !nodes.containsKey(value2)) {
        return false;
      }
      // find corresponding wrapper node
      Node<V> node1 = nodes.get(value1);
      Node<V> node2 = nodes.get(value2);
      return findFather(node1) == findFather(node2);
    }

    public int getDifferentSetNum() {
      return sizeMap.size();
    }

    public void unionSet(V value1, V value2) {
      if (!nodes.containsKey(value1) || !nodes.containsKey(value2)) {
        return;
      }
      Node<V> node1 = nodes.get(value1);
      Node<V> node2 = nodes.get(value2);
      Node<V> head1 = findFather(node1);
      Node<V> head2 = findFather(node2);
      Node<V> larger = sizeMap.get(head1) >= sizeMap.get(head2) ? head1 : head2;
      Node<V> smaller = larger == head1 ? head2 : head1;
      parents.put(smaller, larger);
      sizeMap.put(larger, sizeMap.get(larger) + sizeMap.get(smaller));
      sizeMap.remove(smaller);
    }
  }

  public static int mergeUsers(User[] users) {
    Map<String, User> mapA = new HashMap<>();
    Map<String, User> mapB = new HashMap<>();
    Map<String, User> mapC = new HashMap<>();
    UnionSet<User> union = new UnionSet<>(users);
    for (User user : users) {
      if (!mapA.containsKey(user.a)) {
        mapA.put(user.a, user);
      } else {
        union.unionSet(user, mapA.get(user.a));
      }
      if (!mapB.containsKey(user.b)) {
        mapB.put(user.b, user);
      } else {
        union.unionSet(user, mapB.get(user.b));
      }
      if (!mapC.containsKey(user.c)) {
        mapC.put(user.c, user);
      } else {
        union.unionSet(user, mapC.get(user.c));
      }
    }
    return union.getDifferentSetNum();
  }

  public static void main(String[] args) {
    User user1 = new User("a", "2", "c");
    User user3 = new User("6", "x", "c");
    User user5 = new User("a", "0", "i");
    User user2 = new User("1", "b", "3");
    User user4 = new User("3", "k", "3");

    User[] users = {user1, user2, user3, user4, user5};
    int count = mergeUsers(users);
    System.out.println(count);
  }
}
